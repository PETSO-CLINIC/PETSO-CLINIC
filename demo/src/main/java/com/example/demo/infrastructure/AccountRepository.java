package com.example.demo.infrastructure;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Account getUserByUsername(@Param("username") String username);
}
