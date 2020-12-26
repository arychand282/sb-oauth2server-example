package com.bac.example.security.oauth2.repository;

import com.bac.example.security.oauth2.domain.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chandra on 25/12/2020 19:39
 */

@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, String> {
    
    List<UserRole> findByUserIdAndRoleDeleteFlag(String userId, boolean roleDeleteFlag);
    
}
