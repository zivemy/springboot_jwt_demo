package com.zhaimy.service.impl;

import com.zhaimy.dao.UserDao;
import com.zhaimy.entity.User;
import com.zhaimy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) {
        User login = userDao.login(user);
        if (login!=null){
            return login;
        }else {
           throw new RuntimeException("登陆失败");
        }

    }
}
