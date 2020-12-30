package com.bac.example.security.oauth2.dto;

import lombok.Data;

/**
 * @author chandra on 26/12/2020 11:02
 */

@Data
public class MenuDto {
    
    private String code;
    
    private String name;
    
    private String description;
    
    private String url;
    
    private String parentMenuCode;
    
}
