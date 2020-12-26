package com.bac.example.security.oauth2.repository;

import com.bac.example.security.oauth2.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 19:38
 */

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
    
    List<Role> findByDeleteFlag(boolean deleteFlag);
    
    Optional<Role> findByCodeAndDeleteFlag(String code, boolean deleteFlag);
    
}
