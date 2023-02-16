/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.RoleService;
import com.exavalu.utils.log4jExample;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private int age;
    private int departmentId;
    private int roleId;
    private double basicSalary;
    private double carAllaowance;
    private String rolesName;
    private String departmentName;

   
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String doAddEmployeeData() throws Exception {
        String result = "FAILURE";
       
        boolean success = EmployeeService.getInstance().AddEmployeeData(this);

        if (success) {
            System.out.println("returning Success from doAddEmployeeData method");
            String Msg ="Data Added Successfully!!!";
            sessionMap.put("SuccessMsg2", Msg);
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            result = "SUCCESS";
        } else {
            String errorMsg ="Somehting Went is Wrong Try Again!!!";
            sessionMap.put("ErrorMsg2", errorMsg);
//            Logger log = Logger.getLogger(log4jExample.class.getName());
//           log.error(errorMsg);
            System.out.println("returning Failure from doAddEmployeeData method");
        }

        return result;
    }
    
    public String doShowSearchEmployeeList() throws Exception {
        String result = "SUCCESS";
        ArrayList showEmpList = EmployeeService.getInstance().showSearchEmployeeList(this);
        sessionMap.put("ShowEmpList", showEmpList);
        return result;
    }
    
    public String doEditEmployee() throws Exception {
        Employee emp = EmployeeService.getInstance().getEmployee(Integer.toString(this.employeeId));
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();

        sessionMap.put("Emp", emp);
        sessionMap.put("DeptList", deptList);
        sessionMap.put("RoleList", roleList);
        
        return "SUCCESS";
    }
    public String doUpdateEmployee() throws Exception {
        String result = "FAILURE";
        System.out.println("inside do employee");
        boolean success = EmployeeService.getInstance().updateEmployee(this,Integer.toString(this.employeeId));
        
        if (success) {
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            result = "SUCCESS";
            System.out.print("done");
        }else{
            System.out.print("done");
        } 
        return result;
    }
  
    
    
    

    private int status;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    

    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
 public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getCarAllaowance() {
        return carAllaowance;
    }

    public void setCarAllaowance(double carAllaowance) {
        this.carAllaowance = carAllaowance;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
    
    private String work;
    
   public String getNavBarWork()throws Exception{
          String result="FAILURE";
        switch (this.work) {
            case "show":
                result="SHOW";
                break;
            case "search":
                result="SEARCH";
                break;
            case "add":
                result="ADD";
                break;
            case "home":
                result="HOME";
                break;
            default:
                break;
        }
          
          return result;
      }
    
}
