package com.example.tabela.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FipeInformations(@JsonAlias("codeFipe") String codigo,
                               @JsonAlias("vehicleType") int tipoVeiculo,
                               @JsonAlias("price") String preco,
                               @JsonAlias("brand") String marca,
                               @JsonAlias("model") String modelo,
                               @JsonAlias("modelYear") int anoModelo,
                               @JsonAlias("referenceMonth") String mesReferencia,
                               @JsonAlias("fuel") String combustivel) {
}
