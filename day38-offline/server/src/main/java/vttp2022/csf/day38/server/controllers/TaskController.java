package vttp2022.csf.day38.server.controllers;

import java.io.StringReader;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.csf.day38.server.models.Task;
import vttp2022.csf.day38.server.services.TodoService;

@Controller
@RequestMapping(path="/api")
public class TaskController {

	@Autowired
	private TodoService todoSvc;

	@PostMapping(path="/tasks")
	@ResponseBody
	public ResponseEntity<String> postTask(@RequestBody String payload) {

		System.out.printf(">>> payload: %s\n", payload);

		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonArray jsonArr = reader.readArray();

		List<Task> tasks = jsonArr.stream()
				.map(v -> v.asJsonObject())
				.map(v -> Task.create(v))
				// method reference
				//.map(Task::create)
				.toList();

		todoSvc.addTasks(tasks);

		JsonObject response = Json.createObjectBuilder()
			.add("insertCount", tasks.size())
			.build();
		return ResponseEntity.ok(response.toString());
	}

}
