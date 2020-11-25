package com.zhaimy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.GregorianCalendar;


class SpringbootJwtDemoApplicationTests {

    //获取令牌
    @Test
    void contextLoads() {
        Calendar instance = new GregorianCalendar();
        instance.add(Calendar.MINUTE,30);
        String sign = JWT.create()
                .withClaim("userId", 123) //payload
                .withClaim("userName", "zhaimy")
                .withClaim("age", 24)
                .withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256("!qwererfgf"));//签名
        System.out.println(sign);

    }

    //验证令牌
    @Test
    public void test(){
        //创建验证对象
        JWTVerifier build = JWT.require(Algorithm.HMAC256("!qwererfgf")).build();
        //验证token
        DecodedJWT verify = build.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InpoYWlteSIsImV4cCI6MTYwNjI5NTE3NywidXNlcklkIjoxMjMsImFnZSI6MjR9.C4Sj3idUJ6jDTlMRo5z7lmbVfv_U8edCuem4wl0zjUE");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("userName").asString());
        System.out.println(verify.getClaim("age").asInt());

    }


}
