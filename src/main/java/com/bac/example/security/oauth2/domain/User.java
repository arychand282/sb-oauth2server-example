package com.bac.example.security.oauth2.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chandra on 25/12/2020 16:31
 */

@Data
@Entity
@Table(name = "sec_user")
public class User {
    
    @Id
    @Column(name = "id", length = 40)
    private String id;
    
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    
    @Column(name = "user_status", length = 40, nullable = false)
    private UserStatus userStatus;
    
}
