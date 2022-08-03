package com.company.avtokg.util;

import com.company.avtokg.dto.ProfileJwtDTO;
import com.company.avtokg.exception.AppBadRequestException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private final static String secretKey = "kalitso'z";

    public static String encode(String id) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(id);
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
//        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000 * 24)));
        jwtBuilder.setIssuer("mazgi production");

        return jwtBuilder.compact();
    }

    public static ProfileJwtDTO decode(String jwt) {
        try {
            JwtParser jwtParser = Jwts.parser();

            jwtParser.setSigningKey(secretKey);
            Jws jws = jwtParser.parseClaimsJws(jwt);

            Claims claims = (Claims) jws.getBody();

            String id = claims.getSubject();

            return new ProfileJwtDTO(id);

        } catch (JwtException e) {
            throw new AppBadRequestException("JWT invalid!");
        }
    }

}
