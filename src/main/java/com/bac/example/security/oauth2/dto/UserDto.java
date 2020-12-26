package com.bac.example.security.oauth2.dto;

import com.bac.example.security.oauth2.domain.UserStatus;
import lombok.Data;

import java.util.List;

/**
 * @author chandra on 25/12/2020 19:53
 */

@Data
public class UserDto {
    
    private String username;
    
    private String password;
    
    private String name;
    
    private String email;
    
    private String userStatus;
    
    private List<RoleDto> roleDtoList;
    
}
