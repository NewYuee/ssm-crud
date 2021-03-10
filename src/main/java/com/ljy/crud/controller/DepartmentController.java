package com.ljy.crud.controller;

import com.ljy.crud.bean.Department;
import com.ljy.crud.bean.Msg;
import com.ljy.crud.service.DepartmenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理和部门有关的请求
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmenService departmenService;
    /**
     * 返回所有的部门信息
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list=departmenService.getDepts();

        return Msg.success().add("depts",list);
    }
}
