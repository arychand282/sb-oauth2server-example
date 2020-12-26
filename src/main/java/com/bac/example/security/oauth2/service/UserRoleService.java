package com.bac.example.security.oauth2.service;

import com.bac.example.security.oauth2.domain.UserRole;

import java.util.List;

/**
 * @author chandra on 25/12/2020 19:47
 */

public interface UserRoleService {
    
    List<UserRole> findByUserIdAndRoleDeleteFlag(String userId, boolean roleDeleteFlag);
    
    UserRole save(UserRole userRole);
    
}
