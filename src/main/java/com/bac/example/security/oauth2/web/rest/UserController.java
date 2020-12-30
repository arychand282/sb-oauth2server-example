package com.bac.example.security.oauth2.web.rest;

import com.bac.example.security.oauth2.domain.Role;
import com.bac.example.security.oauth2.domain.User;
import com.bac.example.security.oauth2.domain.UserRole;
import com.bac.example.security.oauth2.dto.RoleDto;
import com.bac.example.security.oauth2.dto.UserDto;
import com.bac.example.security.oauth2.service.RoleService;
import com.bac.example.security.oauth2.service.UserRoleService;
import com.bac.example.security.oauth2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chandra on 25/12/2020 17:29
 */

@RequestMapping(value = "/api/v1/user")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto save(@RequestBody UserDto userDto) {
        User user = userService.save(toUser(userDto));
        userDto.getRoleDtoList().forEach(roleDto -> {
            UserRole userRole = userRole(user, roleDto);
            userRoleService.save(userRole);
        });
        return userDto;
    }
    
    @GetMapping(value = "/list")
    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        userService.findAll().forEach(user -> {
            List<UserRole> userRoleList = userRoleService.findByUserIdAndRoleDeleteFlag(user.getId(), false);
            userDtoList.add(toUserDto(user, userRoleList));
        });
        return userDtoList;
    }
    
    private User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        return user;
    }
    
    private UserRole userRole(User user, RoleDto roleDto) {
        UserRole userRole = new UserRole();
        Role role = roleService.findByCode(roleDto.getCode())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        userRole.setRole(role);
        userRole.setUser(user);
        return userRole;
    }
    
    private UserDto toUserDto(User user, List<UserRole> userRoleList) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUserStatus(user.getUserStatus().name());
        
        List<RoleDto> roleDtoList = new ArrayList<>();
        userRoleList.forEach(userRole -> roleDtoList.add(toRoleDto(userRole.getRole())));
        
        userDto.setRoleDtoList(roleDtoList);
        return userDto;
    }
    
    private RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
    
}
