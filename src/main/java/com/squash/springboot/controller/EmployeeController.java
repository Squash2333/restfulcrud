package com.squash.springboot.controller;

import com.squash.springboot.dao.DepartmentDao;
import com.squash.springboot.dao.EmployeeDao;
import com.squash.springboot.entities.Department;
import com.squash.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工列表，返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees=employeeDao.getAll();

        //放在请求域中共享
        model.addAttribute("emps",employees);

        //thymeleaf默认就会拼串
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        Collection<Department> departments=departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    //springMVc自动将请求参数和入参对象进行一一绑定；要求请求参数的名字和JavaBean入参的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息"+employee);
        //保存员工
        employeeDao.save(employee);
        //添加完成，来到员工列表页面
        //redirect：表示重定向到一个地址    /代表当前项目路径
        //forward：表示转发到一个地址
        return "redirect:/emps";
    }
}
