package vttp2022.paf.day29.services;

import java.io.StringReader;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.day29.models.SuperHero;

@Service
public class MarvelService {

    public static final String URL_CHARACTER = "https://gateway.marvel.com:443/v1/public/characters";
    public static final String ATTRIBUTION = "Data provided by Marvel. © 2022 MARVEL";

    // Inject into the service Marvel's private and public key
    @Value("${MARVEL_PUBLIC_KEY}")
    private String publicKey;

    @Value("${MARVEL_PRIVATE_KEY}")
    private String privateKey;

    public List<SuperHero> search(String name) {
        return search(name, 10);
    }

    public List<SuperHero> search(String name, Integer limit) {

        Long ts = System.currentTimeMillis();
        String signature = "%d%s%s".formatted(ts, privateKey, publicKey);
        String hash = "";

        try {
            // Message digest = md5, sha1, sha512
            // Get an instance of MD5
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // Calculate our hash
            // Update our message digest
            md5.update(signature.getBytes());
            // Get the MD5 digest
            byte[] h = md5.digest();
            // Stringify the MD5 digest
            hash = HexFormat.of().formatHex(h);
        } catch (Exception ex) { }

        // https://gateway.marvel.com:443/v1/public/characters?
        //      nameStartsWith=??? limit=10 ts=??? apikey=??? hash=???
        String url = UriComponentsBuilder.fromUriString(URL_CHARACTER)
            .queryParam("nameStartsWith", name)
            .queryParam("limit", limit)
            .queryParam("ts", ts)
            .queryParam("apikey", publicKey)
            .queryParam("hash", hash)
            .toUriString();

        //System.out.printf("url = %s\n", url);

        // Use the url to make a call to Marvel
        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp =  template.exchange(req, String.class);
        String payload = resp.getBody();

        // Parse the String to JsonObject
        JsonReader reader = Json.createReader(new StringReader(payload));
        // { data: { results: [ ] } }
        JsonObject result = reader.readObject();
        JsonArray data = result.getJsonObject("data").getJsonArray("results");

        // Retrive the name, description, image
        List<SuperHero> superHeros = new LinkedList<>();
        for (Integer i = 0; i < data.size(); i++)
            superHeros.add(SuperHero.create(data.getJsonObject(i)));

        return superHeros;
    }
    
}
