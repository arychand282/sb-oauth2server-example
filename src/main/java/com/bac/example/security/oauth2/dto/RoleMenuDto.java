package com.bac.example.security.oauth2.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chandra on 26/12/2020 11:03
 */

@Data
public class RoleMenuDto {
    
    private RoleDto roleDto;
    
    private List<MenuDto> menuDtoList;
    
}
