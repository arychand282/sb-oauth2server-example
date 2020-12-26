package com.bac.example.security.oauth2.service;

import com.bac.example.security.oauth2.domain.User;
import com.bac.example.security.oauth2.domain.UserStatus;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 17:26
 */

public interface UserService {
    
    Optional<User> findByIdAndUserStatus(String id, UserStatus userStatus);
    
    User save(User user);
    
    List<User> findAll();
    
}
