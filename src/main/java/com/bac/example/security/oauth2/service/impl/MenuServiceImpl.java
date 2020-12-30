package com.bac.example.security.oauth2.service.impl;

import com.bac.example.security.oauth2.domain.Menu;
import com.bac.example.security.oauth2.repository.MenuRepository;
import com.bac.example.security.oauth2.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author chandra on 26/12/2020 11:06
 */

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Override
    public Optional<Menu> findByCode(String code) {
        return menuRepository.findByCodeAndDeleteFlag(code, false);
    }

    @Override
    public Menu save(Menu menu) {
        menu.setDeleteFlag(false);
        return menuRepository.save(menu);
    }
}
