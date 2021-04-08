package com.brt.oa.user.dao;

import com.brt.oa.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
   void insertUser(User user);

   Integer duplicateChecking (String username);

   User findUser (String username);

   void  updatePwd (User user);


}
