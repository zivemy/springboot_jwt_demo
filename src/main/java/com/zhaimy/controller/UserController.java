package com.zhaimy.controller;



import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhaimy.entity.User;
import com.zhaimy.service.UserService;
import com.zhaimy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * @param user
     * @return
     */
    @GetMapping("/user/login")
    public Map<String,Object> login(User user){
        Map<String,Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            //设置payload
            Map<String,String> payload = new HashMap<>();
            payload.put("id",userDB.getId());
            payload.put("name",userDB.getName());
            //生成JWT令牌
            String token = JWTUtils.getToken(payload);

            map.put("state",true);
            map.put("msg","登陆成功");
            map.put("token",token);//响应token
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }


    /**
     * 验证token
     * @param
     * @return
     */
    @PostMapping("/user/test")
    public  Map<String,Object> test(){
        Map<String,Object> map = new HashMap<>();
        map.put("state",true);
        map.put("msg","请求成功");
        return map;
    }
}
