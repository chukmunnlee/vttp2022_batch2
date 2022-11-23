package vttp.paf.day23.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.paf.day23.models.BeerStyle;
import vttp.paf.day23.models.Brewery;
import vttp.paf.day23.repositories.BreweryRepository;
import vttp.paf.day23.repositories.StylesRepository;

@Service
public class BeerService {

    @Autowired
    private StylesRepository stylesRepo;

    @Autowired
    private BreweryRepository breweryRepo;

    public List<BeerStyle> getBeerStyles() {
        return stylesRepo.getStyles();
    }

    public List<Brewery> getBreweriesByBeerStyle(Integer beerStyle) {
        return breweryRepo.getBreweriesByStyle(beerStyle);
    }
    
}
