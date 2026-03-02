package com.example.tabela.fipe.model;

public enum TipoVeiculo {
    TIPO_CARRO(1, "Carro"),
    TIPO_MOTO(2, "Moto"),
    TIPO_CAMINHAO(3, "Caminhão");

    private int codigo;
    private String descricao;

    TipoVeiculo(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
