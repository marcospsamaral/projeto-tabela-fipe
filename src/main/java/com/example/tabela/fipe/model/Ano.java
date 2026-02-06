package com.example.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Ano(@JsonAlias("code") String codigo,
                  @JsonAlias("name") String nome) {
}
