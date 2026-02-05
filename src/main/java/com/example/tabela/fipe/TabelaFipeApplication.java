package com.example.tabela.fipe;

import com.example.tabela.fipe.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		Principal principal = new Principal();
		principal.menu();
	}
}
