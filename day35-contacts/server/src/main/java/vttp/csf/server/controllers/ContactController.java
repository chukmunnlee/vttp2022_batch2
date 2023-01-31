package vttp.csf.server.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.csf.server.models.Contact;

@Controller
@RequestMapping(path="/contact", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ContactController {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    ResponseEntity<String> postContacEntity(@RequestBody MultiValueMap<String, String> form) {

        System.out.println(">>> contact form: " + form);

        Contact newContact = Contact.create(form);
        System.out.println(">>> contact: " + newContact);

        return ResponseEntity.ok(newContact.toJson().toString());
    }
    
}
