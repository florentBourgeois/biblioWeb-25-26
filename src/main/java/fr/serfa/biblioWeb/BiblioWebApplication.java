package fr.serfa.biblioWeb;

import fr.serfa.biblioWeb.dao.AuteurDAO;
import fr.serfa.biblioWeb.manga.dao.LivreDAO;
import fr.serfa.biblioWeb.model.Auteur;
import fr.serfa.biblioWeb.model.Livre;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
public class BiblioWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiblioWebApplication.class, args);
	}


	@Bean
	CommandLineRunner initRepos(AuteurDAO auteurDAO, LivreDAO livreDAO) {
		return args -> {
			//Faker faker = new Faker(Locale.FRENCH); // pour passer faker en francais (mais moins de données dispo)
			Faker faker = new Faker();
			for (int i = 0; i < 10; i++) {
				System.out.println(faker.book().title());

			}

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

			for (int i = 0; i < 5; i++) {
				Auteur a ;
				if(faker.number().numberBetween(0,1) == 0)
					a = new Auteur(faker.book().author(), LocalDate.of(faker.number().numberBetween(1700, 2000),1,1));
				else
					a = new Auteur(faker.book().author(), LocalDate.of(faker.number().numberBetween(1700, 2000),1,1), String.valueOf(faker.number().numberBetween(1990, 2026)));
				auteurDAO.add(a);
			}

			Livre l = new Livre(jkr, 1982, "mon livre", "azeazea");
			livreDAO.save(l);
			Livre harryp = new Livre(jkr, 2000, "harry potter", "3456776543");
			livreDAO.save(harryp);
			livreDAO.save(new Livre(jkr, 2002, "harry potter2", "3456776543"));
			livreDAO.save(new Livre(jkr, 2003, "harry potter3", "3456776543"));
			livreDAO.save(new Livre(jkr, 2007, "harry potter5", "3456776543"));

			livreDAO.save(new Livre(fred, 1800, "ce livre", "345677YG"));

			livreDAO.save(new Livre(huxley, 1932, "Le Meilleur des mondes", "9782266165875"));
			livreDAO.save(new Livre(orwell, 1949, "1984", "9780451524935"));
			livreDAO.save(new Livre(victorhugo, 1862, "Les Misérables", "9782070409185"));
			livreDAO.save(new Livre(victorhugo, 1831, "Le Bossu de Notre-Dame", "9782070409192"));
			livreDAO.save(new Livre(victorhugo, 1856, "Les Travailleurs de la mer", "9782070409208"));






			System.out.println("\n\nFIN initialisation du projet\n---------------");
		};
	}



}
