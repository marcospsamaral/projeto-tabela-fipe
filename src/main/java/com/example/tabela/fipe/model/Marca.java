package com.example.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Marca(@JsonAlias("code") int codigo,
                    @JsonAlias("name") String nome) {
}
