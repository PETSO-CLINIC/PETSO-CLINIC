package com.example.demo.web;

import com.example.demo.infrastructure.BlogRepository;
import com.example.demo.infrastructure.UserRepository;
//import com.example.demo.infrastructure.services.BlogService;
import com.example.demo.infrastructure.services.BlogService;
import com.example.demo.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("BlogService")
public class BlogServiceImpl implements BlogService {

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @Override
    public void deleteProfile(Long profileId) {

        blogRepository.deleteById(profileId);

    }


}
