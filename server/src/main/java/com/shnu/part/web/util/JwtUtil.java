package com.shnu.part.web.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    /**
     * 生成token
     * @param subject
     * @return
     */
    public static String buildJWTToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
    }

    /**
     * 解析token返回用户名
     * @param token jwtToken
     * @return
     */
    public static String parseJWTToken(String token){

        return Jwts.parser()
                .setSigningKey("MyJwtSecret")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();
    }
}
