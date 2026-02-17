package fr.serfa.biblioWeb;

import fr.serfa.biblioWeb.dao.AuteurDAO;
import fr.serfa.biblioWeb.model.Auteur;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BiblioWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioWebApplication.class, args);
	}


	@Bean
	CommandLineRunner initRepos(AuteurDAO auteurDAO) {
		return args -> {
			Faker faker = new Faker();

			System.out.println("\n\nInitialisation du projet\n---------------");

			System.out.println("\n\tInitialisation des auteurs\n---------------");
			Auteur jkr = new Auteur("J.K.Rowling", LocalDate.of(1963,1,1));
			auteurDAO.add(jkr);
			Auteur fred = new Auteur("fred", LocalDate.of(1756,1,1));
			auteurDAO.add(fred);
			Auteur huxley = new Auteur("Aldous Huxley", LocalDate.of(1894,7,26), "1963-11-22");
			auteurDAO.add(huxley);
			Auteur orwell = new Auteur("George Orwell", LocalDate.of(1903,6,25), "1950-01-21");
			auteurDAO.add(orwell);
			Auteur victorhugo = new Auteur("Victor Hugo", LocalDate.of(1802,2,26), "1885-05-22");
			auteurDAO.add(victorhugo);
			Auteur sansLivre = new Auteur("Auteur Sans Livre", LocalDate.of(1900,1,1));

			for (int i = 0; i < 50; i++) {
				Auteur a ;
				if(faker.number().numberBetween(0,1) == 0)
					a = new Auteur(faker.book().author(), LocalDate.of(faker.number().numberBetween(1700, 2000),1,1));
				else
					a = new Auteur(faker.book().author(), LocalDate.of(faker.number().numberBetween(1700, 2000),1,1), String.valueOf(faker.number().numberBetween(1990, 2026)));
				auteurDAO.add(a);
			}




			System.out.println("\n\nFIN initialisation du projet\n---------------");
		};
	}



}
