package vttp.paf.day23.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.paf.day23.models.Brewery;

import static vttp.paf.day23.repositories.Queries.*;

@Repository
public class BreweryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Brewery> getBreweriesByStyle(Integer beerStyle) {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_BREWERIES_BY_STYLE, beerStyle);

        List<Brewery> breweries = new LinkedList<>();
        while (rs.next())
            breweries.add(Brewery.create(rs));

        return breweries;
    }
    
}
