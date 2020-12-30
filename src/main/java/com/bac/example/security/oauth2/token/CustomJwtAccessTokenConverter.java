package com.bac.example.security.oauth2.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chandra on 28/12/2020 11:13
 */

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    
    @Override
    protected final String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInformationMap = accessToken.getAdditionalInformation();
        DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        
        Map<String, Object> tokenInformationMap = new HashMap<>();
        tokenInformationMap.put("name", additionalInformationMap.get("name"));
        tokenInformationMap.put("email", additionalInformationMap.get("email"));
        tokenInformationMap.put("roleList", additionalInformationMap.get("roleList"));
//        tokenInformationMap.put("securedPath", additionalInformationMap.get("securedPath"));
//        tokenInformationMap.put("menuCodeList", additionalInformationMap.get("menuCodeList"));
        addInformation(additionalInformationMap, tokenInformationMap);
//        tokenInformationMap.putAll(additionalInformationMap);
        oAuth2AccessToken.setAdditionalInformation(tokenInformationMap);
        
        return super.encode(oAuth2AccessToken, authentication);
    }

    protected void addInformation(Map<String, Object> additionalInformation, Map<String, Object> tokenInformation) {
    }
    
}
