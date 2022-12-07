package vttp2022.paf.day28;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import vttp2022.paf.day28.models.Game;
import vttp2022.paf.day28.repositories.BGGRepository;

@SpringBootApplication
public class Day28Application implements CommandLineRunner {

	@Autowired
	private BGGRepository bggRepo;

	@Override
	public void run(String... args) {

		/* 
		Optional<Game> opt =  bggRepo.search("Twilight Imperium");
		if (opt.isPresent())
			System.out.println(opt.get());
		else
			System.out.println("No result");

		System.exit(0);
		*/
	}

	public static void main(String[] args) {
		SpringApplication.run(Day28Application.class, args);
	}
}