package com.example.tabela.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados{
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> List<T> obterDados(String json, Class<T> classe) {
        try {
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, classe);

            return mapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
