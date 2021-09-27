package com.rodrigo.pokemonapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse {
    private int totalItems;
    private List<Pokemon> pokemons;
    private int totalPages;
    private int currentPage;
}
