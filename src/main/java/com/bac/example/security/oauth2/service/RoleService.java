package com.bac.example.security.oauth2.service;

import com.bac.example.security.oauth2.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 19:57
 */

public interface RoleService {
    
    List<Role> findAll();
    
    Role save(Role role);
    
    Optional<Role> findByCodeAndDeleteFlag(String code, boolean deleteFlag);
    
}
