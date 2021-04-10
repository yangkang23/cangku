package com.brt.oa.emp.dao;

import com.brt.oa.emp.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpDao {

    void insertemp(@Param("pojo") Emp pojo);

    int insertList(@Param("pojos") List< Emp> pojo);

    List<Emp> findemp(@Param("storeid") Integer storeid);

    int update(@Param("pojo") Emp pojo);

}
