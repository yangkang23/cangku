package com.brt.oa.emp.dao;

import com.brt.oa.emp.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpDao {

    void insertemp(@Param("pojo") Emp pojo);

    int insertList(@Param("pojos") List<Emp> pojo);

    List<Emp> findemp(@Param("storeid") Integer storeid, @Param("start") Integer start, @Param("size") Integer size, @Param("name") String name);

    int update(@Param("pojo") Emp pojo);

    void deleteEmpById(@Param("id") Integer id, @Param("state") Integer state);

    void updateEmpById(@Param("emp") Emp emp, @Param("id") Integer id);

    Integer findTotal(@Param("storeid") Integer storeid, @Param("name") String name);
}
