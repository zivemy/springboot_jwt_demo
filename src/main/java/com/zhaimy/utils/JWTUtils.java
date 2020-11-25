package com.zhaimy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class JWTUtils {

    private static final String Signature="!s*df$%^dfsdg#";

    /**
     * 生成token header.payload.signature
     * @return
     */

    public static String getToken(Map<String,String> map){
        Calendar instance = new GregorianCalendar();
        instance.add(Calendar.DATE,7);//默认7天过期
        //创建JWTbuilder
        JWTCreator.Builder builder = JWT.create();
        //设置payload
        map.forEach(builder::withClaim);
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(Signature));//指定令牌的过期时间

        return token;
    }

    /**
     * 验证token合法性
     * @param token
     */
    public static void verifyToken(String token) {
        //创建验证对象并验证token
        JWT.require(Algorithm.HMAC256(Signature)).build().verify(token);
    }

    /**
     * 获取token里面信息的方法
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(Signature)).build().verify(token);
        return decodedJWT;
    }
}
