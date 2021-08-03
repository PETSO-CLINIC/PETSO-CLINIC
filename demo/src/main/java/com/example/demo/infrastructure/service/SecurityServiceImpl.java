package com.example.demo.infrastructure.service;
import com.example.demo.infrastructure.RoleRepository;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(IllegalStateException::new);
    }

}