package com.harjoitustyo.movierater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.harjoitustyo.movierater.model.Genre;
import com.harjoitustyo.movierater.model.GenreRepository;
import com.harjoitustyo.movierater.model.Movie;
import com.harjoitustyo.movierater.model.MovieRepository;
import com.harjoitustyo.movierater.model.MovieUser;
import com.harjoitustyo.movierater.model.Rating;
import com.harjoitustyo.movierater.model.RatingRepository;
import com.harjoitustyo.movierater.model.UserRepository;

@SpringBootApplication
public class MovieraterApplication {
	private static final Logger log = LoggerFactory.getLogger(MovieraterApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(MovieraterApplication.class, args);
	}

	@Bean
	public CommandLineRunner movieDemo(MovieRepository mRepository, GenreRepository gRepository, RatingRepository rRepository, UserRepository uRepository) {
		return (args) -> {
			log.info("save some demo genres");

			Genre genre0 = new Genre("Action");
			Genre genre1 = new Genre("Adventure");
			Genre genre2 = new Genre("Comedy");
			Genre genre3 = new Genre("Drama");
			Genre genre4 = new Genre("Fantasy");
			Genre genre5 = new Genre("Horror");
			Genre genre6 = new Genre("Mystery");
			Genre genre7 = new Genre("Romance");
			Genre genre8 = new Genre("Science Fiction");
			Genre genre9 = new Genre("Thriller");
			
			gRepository.save(genre0);
			gRepository.save(genre1);
			gRepository.save(genre2);
			gRepository.save(genre3);
			gRepository.save(genre4);
			gRepository.save(genre5);
			gRepository.save(genre6);
			gRepository.save(genre7);
			gRepository.save(genre8);
			gRepository.save(genre9);

			log.info("Save the ratings");

			Rating stars1 = new Rating("*");
			Rating stars2 = new Rating("**");
			Rating stars3 = new Rating("***");
			Rating stars4 = new Rating("****");
			Rating stars5 = new Rating("*****");

			rRepository.save(stars1);
			rRepository.save(stars2);
			rRepository.save(stars3);
			rRepository.save(stars4);
			rRepository.save(stars5);

			log.info("Save some demo movies");

			mRepository.save(new Movie("Terminator 2", "James Cameron", 1991, genre0, stars5));
			mRepository.save(new Movie("Ted", "Seth McFarlane", 2012, genre2, stars4));

			log.info("create ADMIN and USER users");

			MovieUser user0 = new MovieUser("admin",
					"$2a$10$ARzJyF.BwH.QvRL53MLLtuS5GEbwfihgXxRMT.K46NBgAKwbl0Mi2",
					"ADMIN");

			MovieUser user1 = new MovieUser("user",
					"$2a$10$CllNFI1aaX25UnMaEKP5Yeq.dNzW15FAJW6uYokVRGlXuM7WCljaK",
					"USER");

			uRepository.save(user0);
			uRepository.save(user1);

		};

	}
}
