package com.bac.example.security.oauth2.service;

import com.bac.example.security.oauth2.domain.RoleMenu;

import java.util.List;

/**
 * @author chandra on 26/12/2020 10:58
 */

public interface RoleMenuService {
    
    List<RoleMenu> findByRoleCode(String roleCode);
    
    void save(List<RoleMenu> roleMenuList);
    
}
