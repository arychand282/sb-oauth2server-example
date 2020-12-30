package com.bac.example.security.oauth2.service;

import com.bac.example.security.oauth2.domain.Menu;

import java.util.Optional;

/**
 * @author chandra on 26/12/2020 11:05
 */

public interface MenuService {
    
    Optional<Menu> findByCode(String code);
    
    Menu save(Menu menu);
    
}
