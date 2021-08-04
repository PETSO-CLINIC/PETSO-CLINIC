package com.example.demo.infrastructure.service;

import com.example.demo.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("blogService")
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;

    }


//    @Autowired
//    BlogRepository blogRepository;


    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Album Id:" + id));
    }

    @Override
    public void deleteBlog(Blog blog) {
        blogRepository.delete(blog);

    }

    @Override
    public void updateBlog(Blog blog) {
        blogRepository.save(blog);

    }

    @Override
    public void saveBlog(Blog blog) {
        blogRepository.save(blog);

    }
}
