package com.bac.example.security.oauth2.service.impl;

import com.bac.example.security.oauth2.domain.User;
import com.bac.example.security.oauth2.domain.UserStatus;
import com.bac.example.security.oauth2.repository.UserRepository;
import com.bac.example.security.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 17:27
 */

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Optional<User> findByIdAndUserStatus(String id, UserStatus userStatus) {
        return userRepository.findByIdAndUserStatus(id, userStatus);
    }

    @Override
    public User save(User user) {
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
