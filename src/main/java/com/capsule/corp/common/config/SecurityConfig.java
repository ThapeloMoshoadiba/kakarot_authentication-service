package com.capsule.corp.common.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.UUID;

@Configuration
public class SecurityConfig {
        @Bean
        public SecurityFilterChain authServerSecurity(final HttpSecurity http) throws Exception {
                return http
                        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                        .formLogin(Customizer.withDefaults())
                        .build();
        }

        @Bean
        public JWKSource<SecurityContext> jwkSource() {
                RSAKey rsaKey = generateRsa();
                JWKSet jwkSet = new JWKSet(rsaKey);
                return (selector, context) -> selector.select(jwkSet);
        }

        private RSAKey generateRsa() {
                try {
                        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
                        generator.initialize(2048);
                        KeyPair kp = generator.generateKeyPair();

                        return new RSAKey.Builder((java.security.interfaces.RSAPublicKey) kp.getPublic())
                                .privateKey((java.security.interfaces.RSAPrivateKey) kp.getPrivate())
                                .keyID(UUID.randomUUID().toString())
                                .build();
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }
}
