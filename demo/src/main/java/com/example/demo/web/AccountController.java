package com.example.demo.web;

import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.DoctorRepository;
import com.example.demo.infrastructure.RoleRepository;
import com.example.demo.infrastructure.service.SecurityService;
import com.example.demo.model.Account;
import com.example.demo.model.Doctor;
import com.example.demo.model.Role;
import com.example.demo.model.Signup;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    SecurityService securityService;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    AccountRepository accountRepository;

@Autowired
    RoleRepository roleRepository;
@Autowired
    DoctorRepository doctorRepository;
    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
       List<Role> roles= roleRepository.findAll();
       model.addAttribute("roles",roles);
       model.addAttribute("signup",new Signup());

        return "signup";
    }

//    @GetMapping("/login")
//    public String getLogin(){
//        return "login";
//    }
    @PostMapping("/signup")
    public RedirectView attemptSignUp(Signup signup ) {
        Account newAccount = new Account(signup.getUsername(), encoder.encode(signup.getPassword()));
        newAccount.setRole(securityService.findRoleById(signup.getRole()));
        accountRepository.save(newAccount);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newAccount, null, newAccount.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/createProfile");
    }

    @GetMapping("/createProfile")
    public String getCreateProfile( Model model){
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       Account account = null;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            account=accountRepository.getUserByUsername(username);

        } else {
            String username = principal.toString();
        }
        Doctor doctor = new Doctor();
        doctor.setAccount(account);
        model.addAttribute("doctor",doctor );
        return "createProfile";
    }
    @PostMapping ("/createProfile")
    public RedirectView doctorOrPetOwner(Doctor doctor){
        doctorRepository.save(doctor);
        return new RedirectView("/profile");
        }
@GetMapping("/profile")
public String getProfilePage(Principal p, Model m) {
    return "profile";
        }
}
