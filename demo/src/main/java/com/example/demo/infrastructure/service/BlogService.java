package com.example.demo.infrastructure.service;

import com.example.demo.model.Blog;

import java.util.List;

public interface BlogService {

     List<Blog> getAllBlogs();
     Blog getBlog(Long id);
     void deleteBlog(Blog blog);

     void updateBlog(Blog blog);
     void saveBlog(Blog blog);
}
