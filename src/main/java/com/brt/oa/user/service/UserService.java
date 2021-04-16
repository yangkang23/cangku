package com.brt.oa.user.service;
import com.brt.oa.user.pojo.User;

import java.util.List;

public interface UserService {
    void insertUser (User user);

    Integer duplicateChecking (String username);

    void updatePwd(User user);

    User findUserByUsername(String username);


    List<User> findUser(String realname,Integer pageIndex,Integer pageSize);

    User findUserById(Integer id);

    void updateUserById(User user, Integer id);

    void deleteUserById(Integer id, Integer state);

    Integer findTotal(String realname);
}
