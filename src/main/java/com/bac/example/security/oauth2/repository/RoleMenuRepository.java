package com.bac.example.security.oauth2.repository;

import com.bac.example.security.oauth2.domain.RoleMenu;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chandra on 25/12/2020 19:44
 */

@Repository
public interface RoleMenuRepository extends PagingAndSortingRepository<RoleMenu, String> {
    
    List<RoleMenu> findByRoleCodeAndMenuDeleteFlag(String roleCode, boolean menuDeleteFlag);
    
}
