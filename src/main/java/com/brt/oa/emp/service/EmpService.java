package com.brt.oa.emp.service;

import com.brt.oa.emp.pojo.Emp;

import java.util.List;

public interface EmpService {

    void insertemp(Emp emp);

    List<Emp> findemp(Integer storeid);
}
