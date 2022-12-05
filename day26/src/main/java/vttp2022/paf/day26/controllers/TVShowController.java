package vttp2022.paf.day26.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.paf.day26.repositories.TVShowRepository;
import vttp2022.paf.day26.models.TVShow;

@RestController
@RequestMapping(path="/api/tvshow", produces = MediaType.APPLICATION_JSON_VALUE)
public class TVShowController {

	@Autowired
	private TVShowRepository tvshowRepo;

	@GetMapping(path="{genre}")
	public ResponseEntity<String> getTVShow(@PathVariable String genre) {

		List<TVShow> result = tvshowRepo.getTVShowByGenre(genre);

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (TVShow t: result)
			arrBuilder.add(t.toJson());

		return ResponseEntity.ok(arrBuilder.build().toString());
	}
}
