package com.example.swinedatebaseproject.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author 123
 * @Date 2022/11/14
 */
public class JwtUtils {

    /**
     *
     */
    private static final SecretKey JWT_SECRET_KEY = Keys.hmacShaKeyFor("MyFirstBigProjectNameChatRoomWithDD".getBytes());

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM=SignatureAlgorithm.HS256;


    private static final Date START = new Date();
    public static String createJwt(String subject, Map<String, String> claims) {

        START.setTime(System.currentTimeMillis());


        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置 body 部分
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(START)
                .setSubject(subject)

                // header={alg=HS384}
                .signWith(JWT_SECRET_KEY,SIGNATURE_ALGORITHM);

        // 单独添加有效数据部分
        if (Objects.nonNull(claims)) {
            claims.forEach(jwtBuilder::claim);
        }
        return jwtBuilder.compact();
    }

    @SuppressWarnings("all")
    public static Claims parseJWT(String jwt) {
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parse(jwt)
                .getBody();
    }

    @SuppressWarnings("all")
    public static String getJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parse(jwt)
                .toString();
    }


}