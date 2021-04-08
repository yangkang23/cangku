package com.brt.oa.user.service;

import com.brt.oa.user.pojo.User;

public interface UserService {
    void insertUser (User user);

    Integer duplicateChecking (String username);

    void updatePwd(User user);

    User findUserByUsername(String username);

}
