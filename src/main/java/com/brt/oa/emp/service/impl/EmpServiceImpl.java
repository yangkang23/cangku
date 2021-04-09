package com.brt.oa.emp.service.impl;

import com.brt.oa.emp.dao.EmpDao;
import com.brt.oa.emp.pojo.Emp;
import com.brt.oa.emp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    public void insertemp(Emp pojo){
         empDao.insertemp(pojo);
    }

    public int insertList(List< Emp> pojos){
        return empDao.insertList(pojos);
    }

    public List<Emp> findemp(Integer storeid){
        return empDao.findemp(storeid);
    }

    public int update(Emp pojo){
        return empDao.update(pojo);
    }

}
