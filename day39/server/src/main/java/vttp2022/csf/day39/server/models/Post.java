package vttp2022.csf.day39.server.models;

import java.util.Date;

import org.bson.Document;

public class Post {

	private String postId;
	private Date postDate;
	private int userId;
	private String email;
	private String name;
	private String title;
	private String text;
	private String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png";
	private int like = 0;
	private int dislike = 0;

	public void setPostId(String postId) { this.postId = postId; }
	public String getPostId() {  return this.postId; }

	public void setPostDate(Date postDate) { this.postDate = postDate; }
	public Date getPostDate() {  return this.postDate; }
	public void now() { this.setPostDate(new Date()); }

	public void setUserId(int userId) { this.userId = userId; }
	public int getUserId() {  return this.userId; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() {  return this.email; }

	public void setName(String name) { this.name = name; }
	public String getName() {  return this.name; }

	public void setTitle(String title) { this.title = title; }
	public String getTitle() {  return this.title; }

	public void setText(String text) { this.text = text; }
	public String getText() {  return this.text; }

	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
	public String getImageUrl() {  return this.imageUrl; }

	public void setLike(int like) { this.like = like; }
	public int getLike() {  return this.like; }

	public void setDislike(int dislike) { this.dislike = dislike; }
	public int getDislike() {  return this.dislike; }

	public Document toDocument() {
		Document document = new Document();
		document.put("postId", postId);
		document.put("postDate", postDate);
		document.put("userId", userId);
		document.put("email", email);
		document.put("name", name);
		document.put("title", title);
		document.put("text", text);
		document.put("imageUrl", imageUrl);
		return document;
	}

	public static Post create(Document doc) {
		Post post = new Post();
		post.setPostId(doc.getString("postId"));
		post.setPostDate(doc.getDate("postDate"));
		post.setUserId(doc.getInteger("userId"));
		post.setName(doc.getString("name"));
		post.setEmail(doc.getString("email"));
		post.setTitle(doc.getString("title"));
		post.setText(doc.getString("text"));
		post.setImageUrl(doc.getString("imageUrl"));
		return post;
	}

}
