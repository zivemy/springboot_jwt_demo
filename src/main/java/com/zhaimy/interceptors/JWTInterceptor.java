package com.zhaimy.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaimy.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的令牌
        String token = request.getHeader("token");
        Map<String,Object> map = new HashMap<>();
        try {
            JWTUtils.verifyToken(token);
            return true;

        }catch (SignatureVerificationException e){
            map.put("msg","无效签名");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","令牌过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法不匹配");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效");
        }
        //请求失败
        map.put("state",false);

        //将map转为json响应到前端
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

        return false;
    }
}
