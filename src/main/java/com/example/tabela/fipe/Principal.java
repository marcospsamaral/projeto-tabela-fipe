package com.example.tabela.fipe;

import com.example.tabela.fipe.model.Marca;
import com.example.tabela.fipe.service.ConsumoApi;
import com.example.tabela.fipe.service.ConverteDados;

import java.util.*;

public class Principal {
    ConsumoApi consumo = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();
    Scanner scanner = new Scanner(System.in);
    final String URL_BASE = "https://fipe.parallelum.com.br/api/v2/";

    public void menu() {
        System.out.println("""
                Qual veículo deseja buscar?
                1 - Motos
                2 - Carros
                3 - Caminhões
                """);

        System.out.println("Digite o nome ou o código da marca do veículo: ");
        String marcaDigitada = scanner.nextLine();
        String marca = marcaDigitada.trim().toUpperCase();

        String endereco = "";

        if (marca.equalsIgnoreCase("1") || marca.toUpperCase().contains("mot".toUpperCase())) {
            endereco = URL_BASE+"motorcycles/brands/";
        } else if (marca.equalsIgnoreCase("2") || marca.toUpperCase().contains("carr".toUpperCase())) {
            endereco = URL_BASE+"cars/brands/";
        } else if (marca.equalsIgnoreCase("3") || marca.toUpperCase().contains("caminhõ".toUpperCase())) {
            endereco = URL_BASE+"trucks/brands/";
        }

        var json = consumo.obterDados(endereco);

        List<Marca> marcas = conversor.obterDados(json, Marca.class);

        System.out.println("Selecione a marca do veículo: ");
        marcas.stream()
                .sorted(Comparator.comparing(Marca::codigo))
                .forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Digite o código ou o nome do modelo: ");
        String modelo = scanner.nextLine();

        Optional<Marca> modeloEncontrado;

        try {
            int intModelo = Integer.parseInt(modelo);
            modeloEncontrado = marcas.stream()
                    .filter(m -> m.codigo() == intModelo)
                    .findFirst();
        } catch (NumberFormatException e) {
            modeloEncontrado = marcas.stream()
                    .filter(m -> m.nome().equalsIgnoreCase(modelo))
                    .findFirst();
        } catch (Exception e) {
            System.out.println("Nâo foi possível encontrar o modelo digitado.");
            throw new RuntimeException(e);
        }

        if (modeloEncontrado.isEmpty()) {
            throw new RuntimeException("Nenhum modelo encontrado com este código/nome.");
        }

        endereco += modeloEncontrado.get().codigo() + "/models";

        json = consumo.obterDados(endereco);


    }
}
