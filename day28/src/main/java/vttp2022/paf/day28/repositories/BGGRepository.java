package vttp2022.paf.day28.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class BGGRepository {

    @Autowired
    private MongoTemplate template;

    /*
        db.games.aggregate([
        { 
            $match: { name: 'Gloomhaven' }
        },
        {
            $lookup: {
            from: 'comments',
            foreignField: 'gid',
            localField: 'gid',
            as: 'comments',
            pipeline: [
                { $sort: { rating: -1 } },
                { $limit: 10 }
            ]
            }
        },
        {
            $project: { name: 1, comments: 1 }
        }
        ]);      
     */
    public void search(String name) {
        // Create stages
        // $match the name
        MatchOperation matchName = Aggregation.match(
            Criteria.where("name").is(name)
        );

        // $lookup
        LookupOperation findComments = Aggregation.lookup("comments", "gid", "gid", "comments");

        // $unwind
        UnwindOperation unwindComments = Aggregation.unwind("comments");

        // $sort
        SortOperation sortByRating = Aggregation.sort(null, null)

        // $project: _id, name
        ProjectionOperation idAndNameOnly = Aggregation.project("_id", "name", "comments");

        // Create the pipeline
        Aggregation pipeline = Aggregation.newAggregation(matchName, findComments, idAndNameOnly);

        // Query the collection
        AggregationResults<Document> results = template.aggregate(pipeline, "games", Document.class);

        for (Document d: results)
            System.out.println(d.toJson());

    }
    
}
