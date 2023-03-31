package wsdemon.server.controllers;

import java.io.StringReader;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Controller
public class ChatController {

	@MessageMapping("/greetings")
	@SendTo("/topic/greetings")
	public String greetings(@Payload String message) {
		System.out.printf(">>>> %s\n", message);
		return process(message).toString();
	}

	private JsonObject process(String message) {
		JsonReader reader = Json.createReader(new StringReader(message));
		JsonObject obj = reader.readObject();
		return Json.createObjectBuilder(obj)
			.add("date", (new Date()).toString())
			.build();
	}

}
