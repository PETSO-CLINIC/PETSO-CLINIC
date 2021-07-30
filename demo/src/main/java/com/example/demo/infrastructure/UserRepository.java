package com.example.demo.infrastructure;

import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <AppUser,Long> {
    AppUser findByUsername(String username);
    AppUser findById(long id);

}
