package com.ljy.crud.test;

import com.ljy.crud.dao.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MaTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    public void maTest(){
        System.out.println(departmentMapper);
    }
}
