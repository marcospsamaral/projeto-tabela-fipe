package com.example.tabela.fipe.model;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Veiculo {
    private String valor;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;

    public Veiculo(String valor, String marca, String modelo, int ano, String combustivel) {
        this.valor = valor;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel = combustivel;
    }

    @Override
    public String toString() {
        return "Veiculo[" +
                "valor=" + valor +
                ", marca=" + marca +
                ", modelo=" + modelo +
                ", ano=" + ano +
                ", combustivel=" + combustivel +
                ']';
    }
}
