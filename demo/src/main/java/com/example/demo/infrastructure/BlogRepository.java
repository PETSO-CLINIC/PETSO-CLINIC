package com.example.demo.infrastructure;


import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository <Blog,Long> {
//    void deleteBlog(Long id);
}
