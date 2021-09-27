package com.rodrigo.pokemonapi.domain;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.rodrigo.pokemonapi.exception.InvalidRequestException;
import com.rodrigo.pokemonapi.exception.PokemonNotFoundException;
import org.apache.commons.collections4.ListUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PokemonDao {
    private static final String POKEMON_CSV_FILE = "src/main/resources/pokemon.csv";
    private static final int ID_START = 1;

    private static void writeToFile(List<Pokemon> pokemons) throws Exception {
        Writer writer = Files.newBufferedWriter(Paths.get(POKEMON_CSV_FILE));
        StatefulBeanToCsv<Pokemon> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();
        beanToCsv.write(pokemons);
        writer.close();
    }

    private static List<Pokemon> readFromFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(POKEMON_CSV_FILE));
        List<Pokemon> pokemons = new CsvToBeanBuilder(reader)
                .withType(Pokemon.class)
                .build()
                .parse();

        pokemons.forEach(pokemon -> pokemon.setId(pokemons.indexOf(pokemon) + ID_START));

        reader.close();
        return pokemons;
    }

    public static PaginationResponse get(int pageSize, int currentPage) throws IOException {
        List<Pokemon> pokemons = retrieveAll();
        List<List<Pokemon>> paginated = ListUtils.partition(pokemons, pageSize);
        if (currentPage > paginated.size() || currentPage < 1 || pageSize > pokemons.size())
            throw new InvalidRequestException("Pagination properties provided are invalid");

        return new PaginationResponse(pokemons.size(), paginated.get(currentPage - ID_START), paginated.size(), currentPage);
    }

    public static List<Pokemon> retrieveAll() throws IOException {
        return readFromFile();
    }

    public static Pokemon getFromId(int id) throws IOException {
        List<Pokemon> pokemons = readFromFile();
        if (pokemons.stream().anyMatch(pokemon -> pokemon.getId() == id)) {
            return pokemons.get(id - ID_START);
        } else {
            throw new PokemonNotFoundException(id);
        }
    }

    public static void update(Pokemon updatedPokemon, int id) throws Exception {
        if (id == updatedPokemon.getId()) {
            List<Pokemon> pokemons = readFromFile();
            if (pokemons.stream().anyMatch(pokemon -> pokemon.getId() == updatedPokemon.getId())){
                pokemons.set(updatedPokemon.getId() - ID_START, updatedPokemon);
                writeToFile(pokemons);
            } else {
                throw new PokemonNotFoundException(updatedPokemon.getId());
            }
        } else throw new InvalidRequestException("Id mismatch");
    }

    public static void create(Pokemon newPokemon) throws Exception {
        List<Pokemon> pokemons = readFromFile();
        newPokemon.setId(pokemons.size() + ID_START);
        pokemons.add(newPokemon);
        writeToFile(pokemons);
    }

    public static void delete(int id) throws Exception {
        List<Pokemon> pokemons = readFromFile();
        if (pokemons.stream().anyMatch(pokemon -> pokemon.getId() == id)){
            pokemons.remove(id - ID_START);
            writeToFile(pokemons);
        } else {
            throw new PokemonNotFoundException(id);
        }
    }
}