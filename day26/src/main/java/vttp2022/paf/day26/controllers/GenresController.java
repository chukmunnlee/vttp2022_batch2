package vttp2022.paf.day26.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.paf.day26.repositories.TVShowRepository;

@RestController
@RequestMapping(path="/api/genres", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenresController {

	@Autowired
	private TVShowRepository tvShowRepo;

	@GetMapping
	public ResponseEntity<String> getGenres() {

		List<String> genres = tvShowRepo.getGenres();

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (String g: genres)
			arrBuilder.add(g);

		return ResponseEntity.ok(arrBuilder.build().toString());
	}

    
}
