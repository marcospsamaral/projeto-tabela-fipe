package com.example.tabela.fipe;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.example.tabela.fipe.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		Principal principal = new Principal();
		principal.menu();
	}
}
