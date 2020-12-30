package com.bac.example.security.oauth2.service.impl;

import com.bac.example.security.oauth2.domain.RoleMenu;
import com.bac.example.security.oauth2.repository.RoleMenuRepository;
import com.bac.example.security.oauth2.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chandra on 26/12/2020 10:58
 */

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    
    @Autowired
    private RoleMenuRepository roleMenuRepository;
    
    @Override
    public List<RoleMenu> findByRoleCode(String roleCode) {
        return roleMenuRepository.findByRoleCodeAndMenuDeleteFlag(roleCode, false);
    }

    @Override
    public void save(List<RoleMenu> roleMenuList) {
        for (RoleMenu roleMenu : roleMenuList) {
            roleMenuRepository.save(roleMenu);
        }
    }


}
