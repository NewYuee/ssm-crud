package com.ljy.crud.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljy.crud.bean.Employee;
import com.ljy.crud.bean.Msg;
import com.ljy.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 导入jackson包
     * @param pn
     * @param model
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //但是不是一个分页查询
        //引入PageHelper插件
        //在查询之前只需要调用
        PageHelper.startPage(pn,5);
        //startPage后面紧跟分页查询
        List<Employee> emps= employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要pageInfo交给页面就行
        //封装详细的分页信息，包括有我们查询出来的数据
        PageInfo page=new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);
        return Msg.success().add("pageInfo",page);
    }

    /**
     * 员工保存
     * 1.支持JSR303校验
     * 2.导入Hibernate-Validator
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            Map<String,Object> map=new HashMap<>();
            //校验失败返回失败，在模态框显示校验失败的错误信息
            List<FieldError> errors= result.getFieldErrors();
            for(FieldError fieldError:errors){
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFileds",map);
        }else {
        employeeService.saveEmp(employee);
        return Msg.success();
        }
    }

    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("empName") String empName){
        //先判断用户名是否合法
        String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg","用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        //数据库重复校验
        boolean b= employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名不可用");
        }
    }

    /**
     * 查询员工数据（分页查询）
     * @return
     */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //但是不是一个分页查询
        //引入PageHelper插件
        //在查询之前只需要调用
        PageHelper.startPage(pn,5);
        //startPage后面紧跟分页查询
        List<Employee> emps= employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要pageInfo交给页面就行
        //封装详细的分页信息，包括有我们查询出来的数据

        PageInfo page=new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);

        return "list";
    }

    /**
     * 如果直接发送Ajax=PUT形式的请求
     * 问题：请求体中有数据，但是employee对象封装不上
     * 封装的数据，全是null就拼装成update tbl_emp  where id = 5
     *
     * 原因：
     *      tomcat：
     *              1.将请求体中的数据封装一个map
     *              2.request.getParameter("empName")就会从这个map中取值
     *              3.SpringMVC封装POJO对象的时候
     *                      会把POJO中每个属性的值，request.getParameter("email");
     * ajax发送PUT请求引发的血案：
     *      put请求，请求中的数据：request.getParameter("empName")拿不到
     *      Tomcat一看是put不会封装请求体中的数据为map，只有POST形式的请求才封装请求体
     *
     * 解决方案：
     *我们要能直接发送PUT之类的请求还需要封装请求体中的数据
     * 1.配置上HttpPutFormContentFilter（spring5.1以上已过时，建议用FormContentFilter）
     * 2.它的作用：将请求体中的数据解析包装成一个map。
     * 3.request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
     * 员工更新的方法
     *
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    public Msg saveEmp(Employee employee, HttpServletRequest request){
        employeeService.updateEmp(employee);
        return  Msg.success();
    }


    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @RequestMapping(value ="/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee= employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }


    /**
     * 单个、批量二合一
     * 单个删除：1
     * 批量删除：1-2-3...
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("ids") String ids) {
        if (ids.contains("-")) {
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }

}
