package com.rodrigo.pokemonapi.exception;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(int id) {
        super("Could not find Pokémon with id " + id);
    }
}
