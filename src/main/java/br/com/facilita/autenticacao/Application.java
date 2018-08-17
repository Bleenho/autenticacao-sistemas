package br.com.facilita.autenticacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	/**
	 * Main method
	 * <p>
	 * This is the first method called when we startup the application
	 * 
	 * @param args an array of arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
