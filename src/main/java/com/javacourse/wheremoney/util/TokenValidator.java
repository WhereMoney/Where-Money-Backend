package com.javacourse.wheremoney.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.javacourse.wheremoney.service.ex.TokenExpireException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenValidator implements HandlerInterceptor {
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.youngToken}")
    private Long youngToken;
    @Value("${token.oldToken}")
    private Long oldToken;
    private final static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    public String getToken(String userName) {
        return JWT.create().withClaim("account", userName).withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
    }

    public Map<String, String> parseToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);
        Claim account = decodedjwt.getClaim("account");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");
        map.put("account", account.asString());
        map.put("timeStamp", timeStamp.asLong().toString());
        return map;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull Object object) {
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        String token = httpServletRequest.getHeader("Authorization");
        if (null == token || "".equals(token.trim())) {
            throw new TokenExpireException("token无效");
        }
        token = token.split(" ")[1];
        Map<String, String> map = parseToken(token);
        String account = map.get("account");
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));
        if (timeOfUse >= youngToken && timeOfUse < oldToken) {
            httpServletResponse.setHeader("Authorization", "Bearer " + getToken(account));
        } else if (timeOfUse >= oldToken) {
            throw new TokenExpireException("token过期");
        }
        setUser(map);
        return true;
    }

    public static void setUser(Map<String, String> userIdentify) {
        threadLocal.set(userIdentify);
    }

    public static Map<String, String> getUser() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }
}
