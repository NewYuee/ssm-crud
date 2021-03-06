package com.ljy.crud.test;

import com.ljy.crud.bean.Department;
import com.ljy.crud.bean.Employee;
import com.ljy.crud.dao.DepartmentMapper;
import com.ljy.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * 推荐Spring项目就用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    /**
     * 测试DepartmentMapper
     *
     */
    public void testCRUD(){
//        原生单元测试
//        //1.创建SpringIOC容器
//        ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中获取mapper
//        DepartmentMapper bean =ioc.getBean(DepartmentMapper.class);
        System.out.println(departmentMapper);
        //1.插入几个部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        //2.生成员工的数据，插入员工
        //employeeMapper.insertSelective(new Employee(null,"Jenny","F","wef@foxmail.com",2));
        //3.批量插入多个员工；可以执行批量操作的sqlSession
        EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@gmail.com",1));
        }
        System.out.println("批量生成完成！！！！");
    }

}
