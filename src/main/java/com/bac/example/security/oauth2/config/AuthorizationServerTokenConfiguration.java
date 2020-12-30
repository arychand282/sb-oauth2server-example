package com.bac.example.security.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author chandra on 26/12/2020 3:43
 */

@Configuration
public class AuthorizationServerTokenConfiguration {
    
//    @Bean("authorizationServerTokenStore")
//    public TokenStore inMemoryTokenStore() {
//        return new InMemoryTokenStore();
//    }

    @Bean("authorizationServerTokenStore")
    public TokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }
    
}
