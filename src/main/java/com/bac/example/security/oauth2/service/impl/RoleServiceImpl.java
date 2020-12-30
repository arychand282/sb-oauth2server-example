package com.bac.example.security.oauth2.service.impl;

import com.bac.example.security.oauth2.domain.Role;
import com.bac.example.security.oauth2.repository.RoleRepository;
import com.bac.example.security.oauth2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 19:57
 */

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public List<Role> findAll() {
        return roleRepository.findByDeleteFlag(false);
    }

    @Override
    public Role save(Role role) {
        role.setDeleteFlag(false);
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByCode(String code) {
        return roleRepository.findByCodeAndDeleteFlag(code, false);
    }
}
