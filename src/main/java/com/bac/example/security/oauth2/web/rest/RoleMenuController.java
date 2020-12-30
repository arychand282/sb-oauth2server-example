package com.bac.example.security.oauth2.web.rest;

import com.bac.example.security.oauth2.domain.Menu;
import com.bac.example.security.oauth2.domain.Role;
import com.bac.example.security.oauth2.domain.RoleMenu;
import com.bac.example.security.oauth2.dto.MenuDto;
import com.bac.example.security.oauth2.dto.RoleDto;
import com.bac.example.security.oauth2.dto.RoleMenuDto;
import com.bac.example.security.oauth2.service.MenuService;
import com.bac.example.security.oauth2.service.RoleMenuService;
import com.bac.example.security.oauth2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chandra on 26/12/2020 11:01
 */

@RequestMapping(value = "/api/v1/role_menu")
@RestController
public class RoleMenuController {
    
    @Autowired
    private RoleMenuService roleMenuService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private MenuService menuService;
    
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RoleMenuDto save(@RequestBody RoleMenuDto roleMenuDto) {
        roleMenuService.save(toRoleMenuList(roleMenuDto));
        return roleMenuDto;
    }
    
    @GetMapping(value = "/{roleCode}")
    public RoleMenuDto findByRoleCode(@PathVariable String roleCode) {
        List<RoleMenu> roleMenuList = roleMenuService.findByRoleCode(roleCode);
        return toRoleMenuDto(roleMenuList.get(0).getRole(), roleMenuList);
    }
    
    private RoleMenuDto toRoleMenuDto(Role role, List<RoleMenu> roleMenuList) {
        RoleMenuDto roleMenuDto = new RoleMenuDto();
        roleMenuDto.setRoleDto(toRoleDto(role));
        
        List<MenuDto> menuDtoList = new ArrayList<>();
        roleMenuList.forEach(roleMenu -> menuDtoList.add(toMenuDto(roleMenu.getMenu())));
        roleMenuDto.setMenuDtoList(menuDtoList);
        return roleMenuDto;
    }
    
    private RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setCode(role.getCode());
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }
    
    private MenuDto toMenuDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setCode(menu.getCode());
        menuDto.setName(menu.getName());
        menuDto.setDescription(menu.getDescription());
        menuDto.setUrl(menu.getUrl());
        menuDto.setParentMenuCode(menu.getParentMenu() != null ? menu.getParentMenu().getCode() : null);
        return menuDto;
    }
    
    private List<RoleMenu> toRoleMenuList(RoleMenuDto roleMenuDto) {
        List<RoleMenu> roleMenuList = new ArrayList<>();
        Role role = roleService.findByCode(roleMenuDto.getRoleDto().getCode())
                .orElseThrow(() -> new RuntimeException("Role Not Found: " + roleMenuDto.getRoleDto().getCode()));
        roleMenuDto.getMenuDtoList().forEach(menuDto -> {
            Menu menu = menuService.findByCode(menuDto.getCode())
                    .orElseThrow(() -> new RuntimeException("Menu Not Found: " + menuDto.getCode()));

            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(role);
            roleMenuList.add(roleMenu);
        });
        return roleMenuList;
    }
    
}
