package vttp2022.ssf.day16_weather.services;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.day16_weather.models.Weather;
import vttp2022.ssf.day16_weather.repositories.WeatherRepository;

@Service
public class WeatherService {

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";

    @Value("${API_KEY}")
    private String key;

    @Autowired
    private WeatherRepository weatherRepo;

    public List<Weather> getWeather(String city) {

        // Check if we have the weather cached
        Optional<String> opt = weatherRepo.get(city);
        String payload;

        System.out.printf(">>> city: %s\n", city);

        // Check if the box is empty
        if (opt.isEmpty()) {

            System.out.println("Getting weather from OpenWeatherMap");

            try {
                // Create the url with query string
                String url = UriComponentsBuilder.fromUriString(URL)
                    .queryParam("q", URLEncoder.encode(city, "UTF-8"))
                    .queryParam("appid", key)
                    .toUriString();

                // Create the GET request, GET url
                RequestEntity<Void> req = RequestEntity.get(url).build();

                // Make the call to OpenWeatherMap
                RestTemplate template = new RestTemplate();
                ResponseEntity<String> resp;

                // Throws an exception if status code not in between 200 - 399
                resp = template.exchange(req, String.class);

                // Get the payload and do something with it
                payload = resp.getBody();
                System.out.println("payload: " + payload);

                weatherRepo.save(city, payload);
            } catch (Exception ex) {
                System.err.printf("Error: %s\n", ex.getMessage());
                return Collections.emptyList();
            }
        } else {
            // Retrieve the value for the box
            payload = opt.get();
            System.out.printf(">>>> cache: %s\n", payload);
        }

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
