package com.bac.example.security.oauth2.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author chandra on 25/12/2020 19:34
 */

@Data
@Entity
@Table(name = "sec_role_menu")
public class RoleMenu {
    
    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_code", nullable = false)
    private Role role;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_code", nullable = false)
    private Menu menu;
    
}
