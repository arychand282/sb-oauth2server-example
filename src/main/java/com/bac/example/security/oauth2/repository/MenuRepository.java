package com.bac.example.security.oauth2.repository;

import com.bac.example.security.oauth2.domain.Menu;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author chandra on 25/12/2020 19:43
 */

@Repository
public interface MenuRepository extends PagingAndSortingRepository<Menu, String> {
    
    List<Menu> findByDeleteFlag(boolean deleteFlag);
    
    Optional<Menu> findByCodeAndDeleteFlag(String code, boolean deleteFlag);
    
}
