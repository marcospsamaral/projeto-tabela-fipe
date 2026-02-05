package com.example.tabela.fipe.service;

import java.util.List;

public interface IConverteDados {
    <T> List<T> obterDados(String json, Class<T> classe);
}
