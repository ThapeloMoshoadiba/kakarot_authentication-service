package com.capsule.corp.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;

@Configuration
public class ClientConfig {
        @Bean
        public RegisteredClientRepository registeredClientRepository() {

                RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("KAKAROT_LOCAL")
                        .clientSecret("{noop}Earth-name-Goku")
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .scope("read")
                        .build();

                return new InMemoryRegisteredClientRepository(client);
        }
}
