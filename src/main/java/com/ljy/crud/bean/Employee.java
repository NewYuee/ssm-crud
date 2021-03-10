package com.ljy.crud.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class Employee {
    private Integer id;


    @Pattern(regexp = "(^[a-zA-Z0-9_=]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",message = "用户名必须是2-5位中文或者6-16位英文数字组合")
    private String empName;

    private String gender;

    //@Email
    @Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",message = "邮箱格式不正确")
    private String email;

    private Integer dId;

    private Department department;

    public Employee(){
        super();
    }

    public Employee(Integer id,String empName,String gender,String email,Integer dId){
        super();
        this.id=id;
        this.empName=empName;
        this.gender=gender;
        this.email=email;
        this.dId=dId;
    }

    public Department getDepartment(){
        return department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String toString(){
        return "id="+id+",empName="+empName+",gender="+gender+",email="+email+",dId="+dId;
    }
}