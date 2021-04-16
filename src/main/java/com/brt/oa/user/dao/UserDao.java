package com.brt.oa.user.dao;

import com.brt.oa.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserDao {
    void insertUser(@Param("user") User user);

    Integer duplicateChecking(@Param("username") String username);

    List<User> findUser(@Param("realname") String realname, @Param("start") Integer start, @Param("size") Integer size);

    void updatePwd(@Param("user") User user);


    User findUserByUsername(@Param("username") String username);

    User findUserById(@Param("id") Integer id);

    void updateUserById(@Param("user") User user, @Param("id") Integer id);

    void deleteUserById(@Param("id") Integer id, @Param("state") Integer state);

    Integer findTotal(@Param("realname") String realname);
}
