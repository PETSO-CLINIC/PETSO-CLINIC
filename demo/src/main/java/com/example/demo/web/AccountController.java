package com.example.demo.web;

import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.AppUserRepository;
import com.example.demo.infrastructure.DoctorRepository;
import com.example.demo.infrastructure.RoleRepository;
import com.example.demo.infrastructure.service.SecurityService;
import com.example.demo.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import static javax.swing.text.html.parser.DTDConstants.ID;

@Controller
public class AccountController {
    @Autowired
    SecurityService securityService;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AppUserRepository appUserRepository;

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

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

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

        AppUser appUser = new AppUser();
        appUser.setAccount(account);
        model.addAttribute("appUser",appUser);
        return "createProfile";
    }



    @PostMapping ("/createProfile")
    public RedirectView doctorOrPetOwner(@RequestParam(required = false) String dob,  @RequestParam(required = false) String img,@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname , @RequestParam(required = false) String major, @RequestParam(required = false) String typeOfPet, @RequestParam(required = false) String dobPetOwner, @RequestParam(required = false) String firstnamePetOwner, @RequestParam(required = false) String lastnamePetOwner, @RequestParam(required = false) String imgPetOwner ){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = null;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            account=accountRepository.getUserByUsername(username);

        } else {
            String username = principal.toString();
        }

        if(major != null){
            Doctor doctor = new Doctor();
            doctor.setAccount(account);
            doctor.setFirstname(firstname);
            doctor.setLastname(lastname);
            doctor.setMajor(major);
            doctor.setDob(dob);
            doctor.setImg(img);
            doctorRepository.save(doctor);
            return new RedirectView("/profile");
        }else{
            AppUser appUser = new AppUser();
            appUser.setAccount(account);
            appUser.setFirstnamePetOwner(firstnamePetOwner);
            appUser.setLastnamePetOwner(lastnamePetOwner);
            appUser.setDobPetOwner(dobPetOwner);
            appUser.setTypeOfPet(typeOfPet);
            appUser.setImgPetOwner(imgPetOwner);
            appUserRepository.save(appUser);
            return new RedirectView("/ownerprofile");
        }

    }

    @GetMapping("/profile")
    public String getProfilePage(Principal p, Model m) {
        Doctor doctor = doctorRepository.findById(accountRepository.getUserByUsername(p.getName()).getId().longValue());
        m.addAttribute("doctor", doctor);
        System.out.println("doctor profile" +doctor);
        return "profile";
    }

    @GetMapping("/ownerprofile")
    public String getProfilePageOwner(Principal p, Model m) {
        AppUser appuser = appUserRepository.findById(accountRepository.getUserByUsername(p.getName()).getId().longValue());
        m.addAttribute("appuser", appuser);
        return "ownerprofile";
    }

    @GetMapping("/logout")
    public String getLogout(){
        return "index";
    }


        @GetMapping("/ourDoctors")
    public String getSingleAppUserPage(Model m) {
       List<Doctor> doctors= doctorRepository.findAll();
        m.addAttribute("doctors", doctors);
        return "ourDoctor";
    }



    //    @GetMapping("/users/{id}")
//    public String getSingleAppUserPage(Model m, @PathVariable String id) {
//        long ID = Long.parseLong(id);
//       User user = userRepository.findById(ID);
//        m.addAttribute("user", user);
//        return "user.html";
//    }
}
