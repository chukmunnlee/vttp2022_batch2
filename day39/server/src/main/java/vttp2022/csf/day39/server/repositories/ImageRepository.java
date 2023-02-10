package vttp2022.csf.day39.server.repositories;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.internal.BucketNameUtils;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import vttp2022.csf.day39.server.models.Post;

@Repository
public class ImageRepository {

	private Logger logger = Logger.getLogger(ImageRepository.class.getName());

	@Value("${spaces.bucket}")
	private String spacesBucket;

	@Value("${spaces.endpoint.url}")
	private String spacesEndpointUrl;

	//
	@Autowired
	private AmazonS3 s3;

	public boolean upload(Post post, MultipartFile file) {

		Map<String, String> userData = new HashMap<>();
		userData.put("name", post.getName());

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());

		// Calculate MD5
		/*
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(file.getBytes());
			byte[] md5hash = md5.digest();
			String hash = HexFormat.of().formatHex(md5hash);
			System.out.printf(">>>> md5 hash: %s\n", hash);
			metadata.setContentMD5(hash);
		} catch (Exception ex) {
			logger.log(Level.WARNING, "Calculate MD5", ex);
		}
		*/

		try {
			PutObjectRequest putReq = new PutObjectRequest(spacesBucket
					, post.getPostId(), file.getInputStream(), metadata);
			putReq.withCannedAcl(CannedAccessControlList.PublicRead);
			s3.putObject(putReq);
		} catch (Exception ex) {
			logger.log(Level.WARNING, "Put S3", ex);
			return false;
		}

		String imageUrl = "https://%s.%s/%s"
				.formatted(spacesBucket, spacesEndpointUrl, post.getPostId());
		post.setImageUrl(imageUrl);

		return true;
	}
}
