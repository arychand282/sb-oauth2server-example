package com.bac.example.security.oauth2.config;

import com.bac.example.security.oauth2.token.CustomJwtAccessTokenConverter;
import com.bac.example.security.oauth2.token.store.CustomTokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author chandra on 26/12/2020 3:52
 */

@Slf4j
@Configuration
public class ResourceServerTokenConfiguration {

    private final String publicKey;

    public ResourceServerTokenConfiguration() throws IOException {
        File filePublicKey = ResourceUtils.getFile("classpath:public.pub");
        publicKey = new String(Files.readAllBytes(filePublicKey.toPath()));
    }

    @Bean(name = "resourceJwtAccessTokenConverter")
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        CustomJwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Primary
    @Bean("resourceServerTokenStore")
    public TokenStore tokenStore() {
        return new CustomTokenStore(jwtAccessTokenConverter());
    }

    @Bean("resourceServerTokenStore")
    public TokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {

        log.debug("Initialize Redis Token Store");
        return new RedisTokenStore(redisConnectionFactory);
    }
}
