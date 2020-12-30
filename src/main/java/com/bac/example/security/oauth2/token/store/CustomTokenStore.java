package com.bac.example.security.oauth2.token.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author chandra on 28/12/2020 12:46
 */

@Slf4j
public class CustomTokenStore extends JwtTokenStore {
    
    public CustomTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }
    
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        OAuth2Authentication oAuth2Authentication = super.readAuthentication(token);
        oAuth2Authentication.setDetails(token.getAdditionalInformation());
        return oAuth2Authentication;
    }
    
}
