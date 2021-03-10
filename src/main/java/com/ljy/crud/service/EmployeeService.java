package com.ljy.crud.service;

import com.ljy.crud.bean.Employee;
import com.ljy.crud.bean.EmployeeExample;
import com.ljy.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检验员工名是否可用
     * @param empName
     */
    public boolean checkUser(String empName) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria= example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count= employeeMapper.countByExample(example);
        return count == 0;

    }

    /**
     * 按照员工id查询员工
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        Employee employee=employeeMapper.selectByPrimaryKey(id);
        return employee;


    }

    //员工更新
    public void updateEmp(Employee employee) {
            employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 员工删除
     *
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria=example.createCriteria();
        //帮我们生成这样的sql语句delete from xxx where id in(1,2,3) 完成批量删除
        criteria.andIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
