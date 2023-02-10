package vttp2022.csf.day39.server.controllers;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import vttp2022.csf.day39.server.models.Post;
import vttp2022.csf.day39.server.services.PostService;

@Controller
@RequestMapping(path="/post")
public class PostController {

	private Logger logger = Logger.getLogger(PostController.class.getName());

	@Autowired
	private PostService postSvc;

	@PostMapping
	public String postPost(@RequestPart MultipartFile image,
			@RequestPart String email, @RequestPart String title, @RequestPart String text,
			Model model) {

		logger.log(Level.INFO, "File name: %s\n".formatted(image.getOriginalFilename()));

		Post post = new Post();
		post.setEmail(email);
		post.setTitle(title);
		post.setText(text);

		Optional<String> opt = postSvc.createPost(post, image);
		String postId = opt.get();

		logger.log(Level.INFO, "New postId: %s".formatted(postId));

		return "redirect:/post/%s".formatted(postId);
	}

	@GetMapping(path="{postId}")
	public String getPost(@PathVariable String postId, Model model) {
		Optional<Post> opt = postSvc.getPost(postId);

		// If we cannot find post, return to index.html
		if (opt.isEmpty())
			return "redirect:/";

		model.addAttribute("post", opt.get());
		return "post";
	}

	@PostMapping(path="{postId}/{vote}")
	public String postLike(@PathVariable String postId, 
			@PathVariable String vote, Model model) {

		if ("like".equals(vote.trim().toLowerCase()))
			postSvc.like(postId);

		else if ("dislike".equals(vote.trim().toLowerCase()))
			postSvc.dislike(postId);

		Optional<Post> opt = postSvc.getPost(postId);

		model.addAttribute("post", opt.get());
		return "post";
	}
}
