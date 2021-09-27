package com.rodrigo.pokemonapi.controller;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.rodrigo.pokemonapi.domain.PaginationResponse;
import com.rodrigo.pokemonapi.domain.Pokemon;
import com.rodrigo.pokemonapi.domain.PokemonDao;
import com.rodrigo.pokemonapi.exception.InvalidRequestException;
import com.rodrigo.pokemonapi.exception.PokemonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PokemonController {

    @GetMapping(path = "/pokemons")
    public ResponseEntity<Object> getAllPokemons(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int pageSize
    ) {
        try {
            PaginationResponse response = PokemonDao.get(pageSize, page);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = "/pokemons/{id}")
    public ResponseEntity<Object> getPokemon(@PathVariable int id) {
        try {
            Pokemon pokemon = PokemonDao.getFromId(id);
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping(path = "/pokemons/{id}")
    public ResponseEntity<Object> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable int id) {
        try {
            PokemonDao.update(pokemon, id);
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        } catch (CsvRequiredFieldEmptyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields: " + e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type mismatch for fields: " + e.getMessage());
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(path = "/pokemons")
    public ResponseEntity<Object> createPokemon(@RequestBody Pokemon pokemon) {
        try {
            PokemonDao.create(pokemon);
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        } catch (CsvRequiredFieldEmptyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields: " + e.getMessage());
        } catch (CsvDataTypeMismatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type mismatch for fields: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/pokemons/{id}")
    public ResponseEntity<Object> deletePokemon(@PathVariable int id) {
        try {
            PokemonDao.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
