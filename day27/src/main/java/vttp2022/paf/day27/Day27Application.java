package vttp2022.paf.day27;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.result.DeleteResult;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@SpringBootApplication
public class Day27Application implements CommandLineRunner {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void run(String... args) {

		//insertNewDocument();
		insertFromJsonObject();
		deleteJson();

		System.exit(0);
	}

	public void deleteJson() {
		// Create a criteria
		Criteria c = Criteria.where("title").regex(".*json.*", "i");
		//Criteria c = Criteria.where("title").is("Concering Json-P");
		Query q = Query.query(c);

		// Delete 
		DeleteResult delResult = mongoTemplate.remove(q, "posts");

		System.out.printf(">>> delete count: %d\n", delResult.getDeletedCount());
		//System.out.printf(">>> delete unack: %d\n", DeleteResult.unacknowledged().getDeletedCount());
		//System.out.printf(">>> delete ack: %d\n", DeleteResult.acknowledged(2).getDeletedCount());
		//System.out.printf(">>> delete wasAck: %d\n", delResult.wasAcknowledged());

	}

	public void insertFromJsonObject() {
		// Json object in JsonObject or JsonArray
		JsonObject json = Json.createObjectBuilder()
			.add("title", "Concering Json-P")
			.add("date", (new Date().toString()))
			.add("summary", "Convert JSON-P to String")
			.build();

		Document blog = Document.parse(json.toString());
		//blog.put("date", new Date(json.getString("date")));

		Document inserted = mongoTemplate.insert(blog, "posts");
		System.out.printf("inserted: %s\n", inserted);
	}

	public void insertNewDocument() {
		// Create a document to be inserted
		Document blog = new Document();
		blog.put("title", "Third blog");
		blog.put("date", new Date());
		blog.put("summary", "The meaning of life");

		// A single comment
		Document comment = new Document();
		comment.put("user", "fred");
		comment.put("comment", "I love your blog");

		// Create a list to hold the commments
		List<Document> comments = new LinkedList<>();
		comments.add(comment);

		blog.put("comments", comments);
		blog.put("tags", List.of("one", "two", "three"));

		Document inserted = mongoTemplate.insert(blog, "posts");

		System.out.println(inserted.toJson());
	}

	public static void main(String[] args) {
		SpringApplication.run(Day27Application.class, args);
	}

}
