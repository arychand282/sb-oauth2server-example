package com.bac.example.security.oauth2.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author chandra on 25/12/2020 19:23
 */

@Data
@Entity
@Table(name = "sec_menu")
public class Menu {
    
    @Id
    @Column(name = "code", length = 40, nullable = false)
    private String code;
    
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "url", length = 500, nullable = false)
    private String url;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_menu_code")
    private Menu parentMenu;
    
    @Column(name = "delete_flag")
    private boolean deleteFlag;
    
}
