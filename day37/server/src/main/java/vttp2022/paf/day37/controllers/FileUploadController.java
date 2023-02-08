package vttp2022.paf.day37.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import vttp2022.paf.day37.services.S3Service;

@Controller
@RequestMapping
public class FileUploadController {

    @Autowired
    private S3Service s3Svc;

    @PostMapping(path="/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postUpload(
        @RequestPart MultipartFile myfile,
        @RequestPart String name, Model model) {

        String key = "";

        try {
            key = s3Svc.upload(myfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        model.addAttribute("name", name);
        model.addAttribute("file", myfile);
        model.addAttribute("key", "myobjects/%s".formatted(key));

        return "upload";
    }
    
}
