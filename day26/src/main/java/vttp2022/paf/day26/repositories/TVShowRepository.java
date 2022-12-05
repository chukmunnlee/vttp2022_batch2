package vttp2022.paf.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.paf.day26.models.TVShow;

@Repository
public class TVShowRepository {

    public static final String C_TV_SHOWS = "tvshows";

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     * db.tvshows.distinct('genres') 
     */
    public List<String> getGenres() {
        return mongoTemplate.findDistinct(new Query(), "genres", C_TV_SHOWS, String.class);
    }

    /*
     * db.tvshows.find({
     *      genres: { $in: [ 'some_genre' ] }
     * })
     * .limit(m).skip(n)
     */
	 public List<TVShow> getTVShowByGenre(String genre) {
		 Criteria c = Criteria.where("genres")
			 .in(genre);
		 Query q = Query.query(c);

		 return mongoTemplate.find(q, Document.class, C_TV_SHOWS)
			.stream()
			.map(d -> TVShow.create(d))
			.toList();
	 }

    /*
     * db.tvshows.find({ language: "English" })
     */
    public List<Document> findTVShowByLanguage(String language) {

        // Create a criteria/predicate
        Criteria c = Criteria.where("language").is(language);

        // Query to use the criteria
        Query q = Query.query(c);

        return mongoTemplate.find(q, Document.class, C_TV_SHOWS);
    }

    /*
     * db.tvshows.find({
     *     'rating.average': { $gte: 6  },
     *      year: { $gte: 1990 },
     *      language: "English"
     * })
     */
    public List<Document> findTVShowsByRating(Float f, Integer y) {
        Criteria c = Criteria
            .where("rating.average").gte(f)
            .andOperator(
                //Criteria.where("year").gte(y),
                Criteria.where("language").is("English")
            );

        Query q = Query.query(c);

        return mongoTemplate.find(q, Document.class, "tvshows");
    }
    
}
