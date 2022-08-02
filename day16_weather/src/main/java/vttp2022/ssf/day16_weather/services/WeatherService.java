package vttp2022.ssf.day16_weather.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.day16_weather.models.Weather;

@Service
public class WeatherService {

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${API_KEY}")
    private String key;

    public List<Weather> getWeather(String city) {

        // Create the url with query string
        String url = UriComponentsBuilder.fromUriString(URL)
            .queryParam("q", city)
            .queryParam("appid", key)
            .toUriString();

        // Create the GET request, GET url
        RequestEntity<Void> req = RequestEntity.get(url).build();

        // Make the call to OpenWeatherMap
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        // Check status code
        if (resp.getStatusCodeValue() != 200) {
            System.err.println("Error status code is not 200 ");
            return Collections.emptyList();
        }

        // Get the payload and do something with it
        String payload = resp.getBody();
        System.out.println("payload: " + payload);

        // Convert payload to JsonObject
        // Convert the String to a Reader
        Reader strReader = new StringReader(payload);
        // Create a JsonReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);
        // Read the payload as Json object
        JsonObject weatherResult = jsonReader.readObject();
        JsonArray cities = weatherResult.getJsonArray("weather");
        List<Weather> list = new LinkedList<>();
        for (int i = 0; i < cities.size(); i++) {
            // weather[0]
            JsonObject jo = cities.getJsonObject(i);
            list.add(Weather.create(jo));
        }
        return list;
    }
    
}
