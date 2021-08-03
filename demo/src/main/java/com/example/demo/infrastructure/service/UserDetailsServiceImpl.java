package com.example.demo.infrastructure.service;

import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.getUserByUsername(username);
        if (account == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }
        return account;
    }
}
