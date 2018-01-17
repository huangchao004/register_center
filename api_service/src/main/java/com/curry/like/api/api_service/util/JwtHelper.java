package com.curry.like.api.api_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * jwt生成token和解析token工具类
 */
public class JwtHelper {


    static final long EXPIRATION_TIME = 3600_000; // 1 hour
    static final String SECRET = "SecretKey";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    private Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    public static String generateToken(Map<String, Object> map) {
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public static Integer validateToken(String token) {
        if (token != null) {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            Integer aid = (Integer) (body.get("aid"));
            String  role = (String) (body.get("role"));
            System.out.print("validateToken aid "+aid +" role "+role);
            if (aid == null || aid == 0)
                throw new TokenValidationException("Wrong token without AccountId");
            else
                return aid;
        } else {
            throw new TokenValidationException("Missing token");
        }
    }

    static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }
}
