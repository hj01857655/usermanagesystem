package com.example.usermanagesystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.usermanagesystem.entity.User;
import com.example.usermanagesystem.view.UserView;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    private static final String secret = "abcdefghijklmnopqrstuvwxyz0123456789823743747";

    public static String createToken(UserView user, int minutes) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create().withIssuer("system") // 发布者
                .withIssuedAt(new Date()) // 生成签名的时间
                .withExpiresAt(DateUtils.addMinutes(new Date(), minutes)) // 生成签名的有效期, 分钟
                .withClaim("userId", user.getId()) // 用户id
                .withClaim("userName", user.getUsername()) // 名称
                .sign(algorithm);
        return token;
    }

    public static String createToken(User user, int minutes) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 发布者
        // 生成签名的时间
        // 生成签名的有效期, 分钟
        // 用户id
        // 名称
        return JWT.create().withIssuer("system") // 发布者
                .withIssuedAt(new Date()) // 生成签名的时间
                .withExpiresAt(DateUtils.addMinutes(new Date(), minutes)) // 生成签名的有效期, 分钟
                .withClaim("userId", user.getId()) // 用户id
                .withClaim("userName", user.getUsername()) // 名称
                .sign(algorithm);
    }

    public static UserView verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("system").build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            if (jwt != null) {
                String userId = jwt.getClaim("userId").asString();
                String userName = jwt.getClaim("userName").asString();
                if (StringUtils.isNotBlank(userId)) {
                    UserView user = new UserView();
                    user.setId(Integer.parseInt(userId));
                    user.setUsername(userName);
                    return user;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
