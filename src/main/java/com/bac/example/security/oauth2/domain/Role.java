package com.bac.example.security.oauth2.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chandra on 25/12/2020 19:08
 */

@Data
@Entity
@Table(name = "sec_role")
public class Role {
    
    @Id
    @Column(name = "code", length = 40, nullable = false)
    private String code;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "delete_flag")
    private boolean deleteFlag;
    
    public Role() {
        
    }
    
    public Role(String code) {
        setCode(code);
    }
    
}
