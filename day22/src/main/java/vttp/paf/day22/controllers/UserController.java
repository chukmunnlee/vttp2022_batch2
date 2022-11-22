package vttp.paf.day22.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.paf.day22.models.User;
import vttp.paf.day22.services.UserService;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userSvc;

    @PostMapping
    public String postUser(@RequestBody MultiValueMap<String, String> form, Model model) {

        String username = form.getFirst("username");
        String password = form.getFirst("password");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        String dob = form.getFirst("dob");

        System.out.printf("username: %s, password: %s, email: %s, phone: %s, dob: %s\n"
                , username, password, email, phone, dob);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDob(dob);

        try {
            if (!userSvc.createUser(user))
                System.out.println(">>>> error! user not created");
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("errorMsg", ex.getMessage());
            return "error";
        }

        model.addAttribute("username", username);

        return "created";
    }
    
}
