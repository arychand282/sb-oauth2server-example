package com.bac.example.security.oauth2.web.rest;

import com.bac.example.security.oauth2.domain.Role;
import com.bac.example.security.oauth2.dto.RoleDto;
import com.bac.example.security.oauth2.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chandra on 25/12/2020 19:55
 */

@RequestMapping(value = "/api/v1/role")
@RestController
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping(value = "/list")
    public List<RoleDto> findAll() {
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleService.findAll().forEach(role -> roleDtoList.add(toRoleDto(role)));
        return roleDtoList;
    }
    
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RoleDto save(@RequestBody RoleDto roleDto) {
        return toRoleDto(roleService.save(toRole(roleDto)));
    }
    
    private Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setCode(roleDto.getCode());
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return role;
    }
    
    private RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
    
}
