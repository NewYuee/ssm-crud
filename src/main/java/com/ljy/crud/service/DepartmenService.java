package com.ljy.crud.service;

import com.ljy.crud.bean.Department;
import com.ljy.crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmenService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        List<Department> list=departmentMapper.selectByExample(null);
        return list;
    }
}
