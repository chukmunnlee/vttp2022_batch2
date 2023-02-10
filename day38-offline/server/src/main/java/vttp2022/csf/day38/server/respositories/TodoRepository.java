package vttp2022.csf.day38.server.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.stereotype.Repository;

import com.mongodb.bulk.BulkWriteResult;

import vttp2022.csf.day38.server.models.Task;

@Repository
public class TodoRepository {

	@Autowired
	private MongoTemplate template;

	public void add(List<Task> tasks) {
		List<Document> bsonDocs = tasks.stream()
			.map(v -> v.asBsonDocument())
			.toList();
			
		BulkOperations ops = template.bulkOps(BulkMode.ORDERED, "tasks")
			.insert(bsonDocs);

		BulkWriteResult writeResult = ops.execute();
		System.out.printf(">> write Result: %s\n", writeResult);
	}


}
