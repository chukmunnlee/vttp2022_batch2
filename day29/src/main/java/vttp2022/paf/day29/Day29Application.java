package vttp2022.paf.day29;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2022.paf.day29.models.SuperHero;
import vttp2022.paf.day29.services.MarvelService;

@SpringBootApplication
public class Day29Application implements CommandLineRunner {

	@Autowired
	private MarvelService marvelSvc;

	@Override
	public void run(String... args) {

		List<SuperHero> result =  marvelSvc.search("doctor", 5);
		System.out.println(result);

		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(Day29Application.class, args);
	}

}
