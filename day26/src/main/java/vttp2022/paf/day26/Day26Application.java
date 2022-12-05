package vttp2022.paf.day26;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2022.paf.day26.repositories.TVShowRepository;

@SpringBootApplication
public class Day26Application implements CommandLineRunner {

	@Autowired
	private TVShowRepository tvShowRepo;

	@Override
	public void run(String... args) {

		/*
		List<String> genres = tvShowRepo.getGenres();
		for (String g: genres)
			System.out.printf(">>> :%s\n", g);

		//List<Document> results = tvShowRepo.findTVShowByLanguage("English");
		/*
		List<Document> results = tvShowRepo.findTVShowsByRating(6.5f, 1990);
		for (Document d: results) {
			Document ratingDoc = d.get("rating", Document.class);
			System.out.printf("Name: %s\nSummary: %s\nRating: %s\n"
				, d.getString("name"), d.getString("summary"), ratingDoc.get("average"));
			//System.out.println(d.toJson());
		}

		System.exit(0);

		*/

	}

	public static void main(String[] args) {
		SpringApplication.run(Day26Application.class, args);
	}
}
