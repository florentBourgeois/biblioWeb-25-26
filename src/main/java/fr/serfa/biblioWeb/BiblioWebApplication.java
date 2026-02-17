package fr.serfa.biblioWeb;

import fr.serfa.biblioWeb.dao.AuteurDAO;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BiblioWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioWebApplication.class, args);
	}


	@Bean
	CommandLineRunner initRepos(AuteurDAO auteurDAO) {
		return args -> {
			System.out.println("\n\nInitialisation du projet\n---------------");
			Faker faker = new Faker();
			for (int i = 0; i < 100; i++) {
				System.out.println(faker.clashOfClans().troop());


			}

			System.out.println("\n\nFIN initialisation du projet\n---------------");
		};
	}



}
