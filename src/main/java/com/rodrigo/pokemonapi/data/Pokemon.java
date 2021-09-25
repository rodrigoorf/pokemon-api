package com.rodrigo.pokemonapi.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pokemon {
    @CsvBindByName(column = "#")
    private int id;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Type 1")
    private String type1;

    @CsvBindByName(column = "Type 2")
    private String type2;

    @CsvBindByName(column = "Total")
    private double total;

    @CsvBindByName(column = "HP")
    private double HP;

    @CsvBindByName(column = "Attack")
    private double attack;

    @CsvBindByName(column = "Defense")
    private double defense;

    @CsvBindByName(column = "Sp. Atk")
    private double spAtk;

    @CsvBindByName(column = "Sp. Def")
    private double spDef;

    @CsvBindByName(column = "Speed")
    private double speed;

    @CsvBindByName(column = "Generation")
    private double generation;

    @CsvBindByName(column = "Legendary")
    private boolean isLegendary;
}
