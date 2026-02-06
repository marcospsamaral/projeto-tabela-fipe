package com.example.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Modelo(@JsonAlias("code") Integer codigo,
                     @JsonAlias("name") String nome) {
}
