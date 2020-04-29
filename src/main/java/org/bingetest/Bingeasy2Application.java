package org.bingetest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA permet d'auto générer la base de donnée grace à cette commande
public class Bingeasy2Application {

	public static void main(String[] args) {
		SpringApplication.run(Bingeasy2Application.class, args);
	}

}
