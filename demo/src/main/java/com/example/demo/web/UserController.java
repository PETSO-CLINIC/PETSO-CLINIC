package com.example.demo.web;

import com.example.demo.infrastructure.UserRepository;
import com.example.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/signup")
    public String getSignUpPage() {

        return "signup";
    }

    @GetMapping("/profile")
    public String getProfilePage(Principal p, Model m) {
       AppUser appUser = userRepository.findByUsername(p.getName());
        m.addAttribute("appUser", appUser);
        return "profile";
    }

    @PostMapping("/signup")
    public RedirectView createUser(@RequestParam String username,@RequestParam String password, @RequestParam String dob, @RequestParam String firstname,@RequestParam String lastname) throws ParseException {
        String hashedpwd = bCryptPasswordEncoder.encode(password);
        Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
      AppUser newAppUser = new AppUser(username,hashedpwd, DOB, firstname, lastname);
        userRepository.save(newAppUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newAppUser, null, newAppUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/login");
    }
//
//    @GetMapping("/users/{id}")
//    public String getSingleAppUserPage(Model m, @PathVariable String id) {
//        long ID = Long.parseLong(id);
//       User user = userRepository.findById(ID);
//        m.addAttribute("user", user);
//        return "user.html";
//    }

}
