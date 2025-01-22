package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {

    private Key key;

    private static JwtProvider instance;

    private JwtProvider() {
        final String SECRET = "792305754af767422a7506aef25c3e42b4d2a9a1524849d0772c2ef6d6544f30ad4c512d0d8c71e928593f2ee9c3585e3028b05a587c435036f9dad5f32cdb9f90f474068a4e28f3dd271e731094607f79153cc5808934573c3e428e4975a50986d3ead4b7647c07bba615d2780700e26e86b475894de02a3cd2737cd3e953f941bf47693299d7bccb568d3e4e02c156ad3b84ad9362202b555367dbec0c592f1dd001d6e4d3a6d14edd523c62a8394981feb5905d9bd76639bce6f72a28bd6d5ada7dec625160301190656d3e41e17e296c40f3af1dd146f0fdebbba96543425572a650a754eda0d99ea511f8acb8f8f41828051549723fcdc95c8d3ce2dd57";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpiryDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60 * 24 * 365));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("user_id", user.getUser_id())
                .setExpiration(getExpiryDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}
