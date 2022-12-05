package vttp2022.paf.day26.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TVShow {

	private String name;
	private String summary;
	private String image;
	private Double rating;

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public String getSummary() { return this.summary; }
	public void setSummary(String summary) { this.summary = summary; }

	public String getImage() { return this.image; }
	public void setImage(String image) { this.image = image; }

	public Double getRating() { return this.rating; }
	public void setRating(Double rating) { this.rating = rating; }

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("name", name)
			.add("summary", defaultValue(summary, "no summary"))
			.add("image", defaultValue(image, "https://user-images.githubusercontent.com/2351721/31314483-7611c488-ac0e-11e7-97d1-3cfc1c79610e.png"))
			.add("rating", defaultValue(rating, 0d))
			.build();
	}

	public static TVShow create(Document doc) {
		final TVShow tvShow = new TVShow();
		tvShow.setName(doc.getString("name"));
		tvShow.setSummary(doc.getString("summary"));

		Document d = doc.get("rating", Document.class);
		Object r = d.get("average");
		if (r instanceof Integer)
			tvShow.setRating(((Integer)r).doubleValue());
		else
			tvShow.setRating((Double)r);

		d = doc.get("image", Document.class);
		tvShow.setImage(d.getString("original"));

		return tvShow;
	}

	private <T> T defaultValue(T value, T defValue) {
		if (null != value)
			return value;
		return  defValue;
	}

}
