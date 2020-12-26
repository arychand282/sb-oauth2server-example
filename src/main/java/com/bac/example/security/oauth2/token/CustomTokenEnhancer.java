package com.bac.example.security.oauth2.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @author chandra on 26/12/2020 1:57
 */

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        oAuth2AccessToken.setAdditionalInformation((Map<String, Object>) authentication.getUserAuthentication().getDetails());
        return oAuth2AccessToken;
    }

}
