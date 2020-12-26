package com.bac.example.security.oauth2.repository;

import com.bac.example.security.oauth2.domain.User;
import com.bac.example.security.oauth2.domain.UserStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 16:35
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    
    Optional<User> findByIdAndUserStatus(String id, UserStatus userStatus);
    
    List<User> findAll();
    
}
