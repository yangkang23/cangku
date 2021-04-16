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

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public   UserDao ud;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

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
        return ud.findUserByUsername(username);
    }


    @Override
    public List<User> findUser(String realname,Integer pageIndex,Integer pageSize) {
        Integer start = (pageIndex-1)*pageSize;
        Integer size = pageSize;
        return ud.findUser(realname,start,size);
    }

    @Override
    public User findUserById(Integer id) {
        return ud.findUserById(id);
    }

    @Override
    public void updateUserById(User user, Integer id) {
        ud.updateUserById(user,id);
    }

    @Override
    public void deleteUserById(Integer id, Integer state) {
        ud.deleteUserById(id, state);
    }

    @Override
    public Integer findTotal(String realname) {
        return ud.findTotal(realname);
    }


}
