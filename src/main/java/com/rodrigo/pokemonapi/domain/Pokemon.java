package com.rodrigo.pokemonapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pokemon {
    @CsvIgnore
    private int id;

    @CsvBindByName(column = "#")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int number;

    @CsvBindByName(column = "Name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @CsvBindByName(column = "Type 1")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type1;

    @CsvBindByName(column = "Type 2")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type2;

    @CsvBindByName(column = "Total")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double total;

    @CsvBindByName(column = "HP")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double HP;

    @CsvBindByName(column = "Attack")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double attack;

    @CsvBindByName(column = "Defense")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double defense;

    @CsvBindByName(column = "Sp. Atk")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double spAtk;

    @CsvBindByName(column = "Sp. Def")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double spDef;

    @CsvBindByName(column = "Speed")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double speed;

    @CsvBindByName(column = "Generation")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double generation;

    @CsvBindByName(column = "Legendary")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isLegendary;
}
