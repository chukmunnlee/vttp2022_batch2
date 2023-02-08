package vttp2022.paf.day37.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;

    public String upload(MultipartFile file) throws IOException {

        // User data
        Map<String, String> userData = new HashMap<>();
        userData.put("name", "fred");
        userData.put("uploadTime", (new Date()).toString());
        userData.put("originalFilename", file.getOriginalFilename());

        // Metadata of the file
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userData);

        String key = UUID.randomUUID().toString().substring(0, 8);

        // Create a put request
        PutObjectRequest putReq = new PutObjectRequest(
            "bigbucket", // bucket name
            "myobjects/%s".formatted(key), //key
            file.getInputStream(), //inputstream
            metadata);

        // Allow public access
        putReq.withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putReq);

        return key;

    }
    
}
