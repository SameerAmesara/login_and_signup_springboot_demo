package com.csci5408.Controller;

import com.csci5408.model.UserData;
import com.csci5408.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String homepage() {
        return "index"; // This will render src/main/resources/templates/index.html
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserData());
        return "login"; // This will render src/main/resources/templates/login.html
    }

    @GetMapping("/register_success")
    public String register_success() {
        return "RegistrationSuccessful"; // This will render src/main/resources/templates/RegistrationSuccessful.html
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserData());
        return "signup"; // This will render src/main/resources/templates/signup.html
    }

    @PostMapping("/process_register")
    public String processRegister(UserData user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "RegistrationSuccessful";
    }

    @PostMapping("/process_login")
    public String processLogin(UserData user, Model model) {
        UserData dbUser = userRepo.findByUname(user.getUname());
        if (dbUser != null && passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            // Successful login
            return "Dashboard"; // Redirect to the dashboard page
        } else {
            // Failed login, show an error message
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}