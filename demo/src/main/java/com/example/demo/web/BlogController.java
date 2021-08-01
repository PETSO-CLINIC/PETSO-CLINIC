package com.example.demo.web;


import com.example.demo.infrastructure.BlogRepository;
import com.example.demo.infrastructure.UserRepository;
import com.example.demo.infrastructure.services.BlogService;
import com.example.demo.model.AppUser;
import com.example.demo.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogService blogService;


    @GetMapping("/addblog")
    public String getAddBlog(){
        return "blogForm";
    }

    @GetMapping("/blog")
    public String getBlogPage(Model m) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser user = userRepository.findByUsername(userDetails.getUsername());
        List<Blog>blogList = blogRepository.findAll();
        m.addAttribute("blog", blogList);

        return "blog";
    }

    @PostMapping("/blog")
    public RedirectView postMapping(@RequestParam String petName,@RequestParam  String imgUrl,@RequestParam  String description )throws IOException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser user = userRepository.findByUsername(userDetails.getUsername());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Blog blog = new Blog( petName,imgUrl,description ,timestamp, user );
//        m.addAttribute("principal", p.getName());
        blogRepository.save(blog);

        return new RedirectView("/");
    }

    @GetMapping("/delete")
    public String showdeleteForm() {
        return "delete";
    }

    @PostMapping("/delete")
    public RedirectView deleteProfile(@RequestParam Long profileId) {
        blogService.deleteProfile(profileId);

        return new RedirectView("/delete");
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/403";
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/blog/{id}")
//    public void deleteBlog(@PathVariable Long id){
//        blogService.deleteBlog(id);
//    }
//    @DeleteMapping("/blog/{id}")
//    public String deleteBlog(@PathVariable("id") Long id){
//        blogRepository.deleteById(id);
//        return "redirect:/blog";
//    }

}
