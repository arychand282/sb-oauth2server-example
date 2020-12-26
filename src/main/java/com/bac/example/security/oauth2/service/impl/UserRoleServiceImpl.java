package com.bac.example.security.oauth2.service.impl;

import com.bac.example.security.oauth2.domain.UserRole;
import com.bac.example.security.oauth2.repository.UserRoleRepository;
import com.bac.example.security.oauth2.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chandra on 25/12/2020 19:49
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Override
    public List<UserRole> findByUserIdAndRoleDeleteFlag(String userId, boolean roleDeleteFlag) {
        return userRoleRepository.findByUserIdAndRoleDeleteFlag(userId, roleDeleteFlag);
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
