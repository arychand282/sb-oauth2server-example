package com.bac.example.security.oauth2.authentication;

import com.bac.example.security.oauth2.domain.User;
import com.bac.example.security.oauth2.domain.UserRole;
import com.bac.example.security.oauth2.domain.UserStatus;
import com.bac.example.security.oauth2.service.UserRoleService;
import com.bac.example.security.oauth2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author chandra on 24/12/2020 22:17
 */

@Slf4j
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    @Qualifier("authorizationServerTokenStore")
    private TokenStore tokenStore;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        Map<String, String> userDetails = (Map<String, String>) authentication.getDetails();
        User user = userService.findByIdAndUserStatus(username, UserStatus.ACTIVE)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database."));
        
        String password = (String) authentication.getCredentials();

        Collection<OAuth2AccessToken> oAuth2AccessTokens = tokenStore.findTokensByClientIdAndUserName(
                userDetails.get("client_id"), username);
        log.info("oAuth2AccessTokens: {}", oAuth2AccessTokens);
        oAuth2AccessTokens.forEach(oAuth2AccessToken -> {
            log.info("oAuth2AccessToken: {}", oAuth2AccessToken);
            log.info("==============================");
        });
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Username and password did not match");
        }

        Map<String, Object> details = new HashMap<>();
        details.put("name", user.getName());
        details.put("email", user.getEmail());
        
        List<UserRole> userRoleList = userRoleService.findByUserIdAndRoleDeleteFlag(username, false);
        
        List<String> roleList = new ArrayList<>();
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        userRoleList.forEach(userRole -> {
            roleList.add(userRole.getRole().getCode());
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getCode()));
        });
        
        details.put("roleList", roleList);
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), null, grantedAuthorities);
        authenticationToken.setDetails(details);
        
        return authenticationToken;
        
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
