package com.brt.oa.user.service.impl;

import com.brt.oa.user.dao.UserDao;
import com.brt.oa.user.pojo.User;
import com.brt.oa.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public   UserDao ud;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public void insertUser(User user) {
        ud.insertUser(user);
    }

    @Override
    public Integer duplicateChecking(String username) {
        return ud.duplicateChecking(username);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public void updatePwd(User user) {
        ud.updatePwd(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return ud.findUser(username);
    }
}
