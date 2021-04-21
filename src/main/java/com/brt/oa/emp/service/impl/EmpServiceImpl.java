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

    public List<Emp> findemp(Integer storeid,Integer pageIndex, Integer pageSize,String name){

        Integer start = (pageIndex-1)*pageSize;
        Integer size = pageSize;

        return empDao.findemp(storeid,start,size,name);
    }

    @Override
    public void deleteEmpById(Integer id,Integer state) {
        empDao.deleteEmpById(id,state);
    }

    @Override
    public void updateEmpById(Emp emp, Integer id) {
        empDao.updateEmpById(emp, id);
    }

    @Override
    public Integer findTotal(Integer storeid,String name) {
        return empDao.findTotal(storeid,name);
    }

    public int update(Emp pojo){
        return empDao.update(pojo);
    }



}
