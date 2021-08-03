package com.example.demo.infrastructure.service;

import com.example.demo.model.Role;

public interface SecurityService {
    Role findRoleById(Long roleId);
    Role findRoleByName(String name);
}
