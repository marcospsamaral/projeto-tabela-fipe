package com.example.tabela.fipe;

import com.example.tabela.fipe.model.*;
import com.example.tabela.fipe.service.ConsumoApi;
import com.example.tabela.fipe.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

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

        System.out.println("Digite o nome ou o código do veículo desejado: ");
        String veiculoDigitado = scanner.nextLine();
        String veiculo = veiculoDigitado.trim().toUpperCase();

        String endereco = "";

        if (veiculo.equalsIgnoreCase("1") || veiculo.toUpperCase().contains("mot".toUpperCase())) {
            endereco = URL_BASE+"motorcycles/brands/";
        } else if (veiculo.equalsIgnoreCase("2") || veiculo.toUpperCase().contains("carr".toUpperCase())) {
            endereco = URL_BASE+"cars/brands/";
        } else if (veiculo.equalsIgnoreCase("3") || veiculo.toUpperCase().contains("caminhõ".toUpperCase())) {
            endereco = URL_BASE+"trucks/brands/";
        }

        var json = consumo.obterDados(endereco);

        List<Marca> marcas = conversor.obterDados(json, Marca.class);

        System.out.println("Marcas encontradas do veículo selecionado: ");
        marcas.stream()
                .sorted(Comparator.comparing(Marca::codigo))
                .forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Digite o código ou o nome da marca: ");
        String marcaDigitada = scanner.nextLine();

        Optional<Marca> marcaEncontrada;

        try {
            int intMarca = Integer.parseInt(marcaDigitada);
            marcaEncontrada = marcas.stream()
                    .filter(m -> m.codigo() == intMarca)
                    .findFirst();
        } catch (NumberFormatException e) {
            marcaEncontrada = marcas.stream()
                    .filter(m -> m.nome().equalsIgnoreCase(marcaDigitada))
                    .findFirst();
        } catch (Exception e) {
            System.out.println("Nâo foi possível encontrar a marca digitada.");
            throw new RuntimeException(e);
        }

        if (marcaEncontrada.isEmpty()) {
            throw new RuntimeException("Nenhuma marca encontrada com este código/nome.");
        }

        endereco += marcaEncontrada.get().codigo() + "/models/";

        json = consumo.obterDados(endereco);
        List<Modelo> modelos = conversor.obterDados(json, Modelo.class);

        System.out.println("\nModelos encontrados: ");
        modelos.stream()
                .forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

        System.out.println("--------------------------------------------------------------------");

        boolean umModeloEncontrado = false;
        Optional<Modelo> modeloEncontrado = Optional.empty();

        while (!umModeloEncontrado) {
            System.out.println("Digite o código ou nome do modelo desejado para escolher\nOU\nDigite parte do nome para filtrar:");
            String modeloDigitado = scanner.nextLine();

            try {
                int intModelo = Integer.parseInt(modeloDigitado);
                modeloEncontrado = modelos.stream()
                        .filter(m -> m.codigo() == intModelo)
                        .findFirst();
            } catch (NumberFormatException e) {
                modeloEncontrado = modelos.stream()
                        .filter(m -> m.nome().equalsIgnoreCase(modeloDigitado))
                        .findFirst();
            } catch (Exception e) {
                System.out.println("Nâo foi possível encontrar o modelo digitado.");
                throw new RuntimeException(e);
            }

            if (modeloEncontrado.isPresent()) umModeloEncontrado = true;
            else {
                List<Modelo> modelosEncontrados = modelos.stream()
                        .filter(m -> m.nome().toUpperCase().contains(modeloDigitado.toUpperCase()))
                        .collect(Collectors.toList());

                if(modelosEncontrados.isEmpty()) {
                    throw new RuntimeException("Nenhum modelo encontrado com este código/nome.");
                }

                System.out.println("Modelos com parte do nome informado: ");
                modelosEncontrados.stream()
                        .forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));
            }
        }

        endereco += modeloEncontrado.get().codigo() + "/years/";

        json = consumo.obterDados(endereco);

        List<Ano> anos = conversor.obterDados(json, Ano.class);
        List<FipeInformations> fipeInformations = new ArrayList<>();

        for (Ano ano : anos) {
            json = consumo.obterDados(endereco + ano.codigo());
            fipeInformations.add(conversor.obterDadosObjeto(json, FipeInformations.class));
        }

        List<Veiculo> veiculos = fipeInformations.stream()
                .map(f -> new Veiculo(f.preco(), f.marca(), f.modelo(), f.mesReferencia(), f.combustivel()))
                .collect(Collectors.toList());

        veiculos.forEach(System.out::println);
    }
}
