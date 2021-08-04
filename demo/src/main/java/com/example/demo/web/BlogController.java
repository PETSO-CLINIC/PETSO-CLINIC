package com.example.demo.web;

import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.AppUserRepository;
import com.example.demo.infrastructure.service.BlogRepository;
import com.example.demo.infrastructure.service.BlogService;
import com.example.demo.model.AppUser;
import com.example.demo.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BlogService blogService;


    @GetMapping("/addblog")
    public String getAddBlog() {
        return "blogForm";
    }

    @GetMapping("/blog")
    public String getBlogPage(Principal p, Model m) {

        AppUser appUser = appUserRepository.findById(accountRepository.getUserByUsername(p.getName()).getId().longValue());
        List<Blog> blogList = blogRepository.findAll();
        m.addAttribute("blog", blogList);

        return "blog";
    }

    @PostMapping("/blog")
    public RedirectView postMapping(@RequestParam String petName, @RequestParam String imgUrl, @RequestParam String description) throws IOException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser appUser = appUserRepository.findById(accountRepository.getUserByUsername(userDetails.getUsername()).getId().longValue());
        LocalDateTime localDateTime = LocalDateTime.now();

        LocalTime localTime = localDateTime.toLocalTime();
        Blog blog = new Blog(petName, imgUrl, description, localTime, appUser);
        blogRepository.save(blog);

        return new RedirectView("/");
    }

    @GetMapping("/delete/{id}")
    String deleteBlog(@PathVariable("id") long id, Model model) {
        Blog blog = blogService.getBlog(id);
        blogService.deleteBlog(blog);
        return "redirect:/blog";
    }

    @GetMapping("/edit/{id}")
    String showUpdateForm(@PathVariable("id") long id, Model model) {
        Blog blog = blogService.getBlog(id);

        model.addAttribute("blog", blog);
        return "update_blog";
    }

    @PostMapping("/update/{id}")
    String updateAlbum(@PathVariable("id") long id, @Valid Blog blog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            blog.setId(id);
            return "update_blog";
        }

        blogService.saveBlog(blog);
        return "redirect:/blog";
    }
}