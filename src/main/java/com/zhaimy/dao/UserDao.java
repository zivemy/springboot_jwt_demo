package com.zhaimy.dao;

import com.zhaimy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    User login(User user);
}
