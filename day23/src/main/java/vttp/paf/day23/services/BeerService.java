package vttp.paf.day23.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.paf.day23.models.BeerStyle;
import vttp.paf.day23.models.Brewery;
import vttp.paf.day23.repositories.BreweryCache;
import vttp.paf.day23.repositories.BreweryRepository;
import vttp.paf.day23.repositories.StylesRepository;

@Service
public class BeerService {

    @Autowired
    private StylesRepository stylesRepo;

	 @Autowired
	 private BreweryCache breweryCache;

    @Autowired
    private BreweryRepository breweryRepo;

    public List<BeerStyle> getBeerStyles() {
        return stylesRepo.getStyles();
    }

    public List<Brewery> getBreweriesByBeerStyle(Integer beerStyle) {

		  // check if the result is in the cache. If it is, the use the list from Redis
		  Optional<List<Brewery>> opt = breweryCache.getBreweriesByBeerStyle(beerStyle);
		  if (opt.isPresent())
			  return opt.get();

		  // Query the database and cache the result
		  List<Brewery> breweries = breweryRepo.getBreweriesByStyle(beerStyle);
		  breweryCache.saveBreweriesByBeerStyle(beerStyle, breweries);

		  return breweries;
    }
    
}
