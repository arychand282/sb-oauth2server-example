package com.bac.example.security.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author chandra on 26/12/2020 3:52
 */

@Configuration
public class ResourceServerTokenConfiguration {

    private final String publicKey;

    public ResourceServerTokenConfiguration() throws IOException {
        File filePublicKey = ResourceUtils.getFile("classpath:public.pub");
        publicKey = new String(Files.readAllBytes(filePublicKey.toPath()));
    }

    @Bean(name = "resourceJwtAccessTokenConverter")
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Primary
    @Bean("resourceServerTokenStore")
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
