package com.furidaweb.server.service.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String extractUsername(String token);

    String generateToken(String username);

    String generateToken(Map<String, Object> extraClaims, String username);

    Long getExpirationTime();

    Boolean isTokenValid(String token, String username);
}
