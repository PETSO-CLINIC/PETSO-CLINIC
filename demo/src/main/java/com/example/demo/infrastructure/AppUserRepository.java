package com.example.demo.infrastructure;

import com.example.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AppUserRepository extends JpaRepository<AppUser,Long> {

        AppUser findById(long id);
}
