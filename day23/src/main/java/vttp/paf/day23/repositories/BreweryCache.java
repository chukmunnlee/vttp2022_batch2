package vttp.paf.day23.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp.paf.day23.AppConfig;
import vttp.paf.day23.models.Brewery;

@Repository
public class BreweryCache {

	public static final String DELIMITER = "|";
	// Need to escape | because split() uses regex
	public static final String SPLIT_DELIMITER = "\\|";

	@Value("${brewery.cache.expiration}")
	private Integer expiration;

	@Autowired @Qualifier(AppConfig.BREWERY_CACHE)
	private RedisTemplate<String, String> redisTemplate;

	public void saveBreweriesByBeerStyle(Integer beerStyle, List<Brewery> breweries) {
		ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

		// Convert the list of breweries into a CSV string
		// For more complex data structure, you might want to use JSON 
		// and store the data in a Redis list
		String csv = breweries.stream().map(v -> v.getName())
			.collect(Collectors.joining(DELIMITER));
		
		// Set the expiration time 
		valueOps.set(beerStyle.toString(), csv, expiration, TimeUnit.MINUTES);
	}

	public Optional<List<Brewery>> getBreweriesByBeerStyle(Integer beerStyle) {
		ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
		String csv = valueOps.get(beerStyle.toString());

		// If the data is not cached, then return an empty Optional
		if ((null == csv) || (csv.trim().length() <= 0))
			return Optional.empty();

		// Convert the CSV string to list of breweries
		// Reverse of the save process
		List<Brewery> breweries = Arrays.stream(csv.split(SPLIT_DELIMITER))
			.map(v -> new Brewery(v)).toList();
		return Optional.of(breweries);
	}

	public Boolean hasBreweweriesByBeerStyle(Integer beerStyle) {
		return redisTemplate.hasKey(beerStyle.toString());
	}
}
