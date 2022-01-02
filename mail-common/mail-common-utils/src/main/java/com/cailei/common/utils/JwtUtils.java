package com.cailei.common.utils;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtils {
    // 颜
    public static final String secret = "onepiece";




    public static String createToken(String subject){

        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return  token;
    }


    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        String subject = body.getSubject();

        return subject;

    }


}
