/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;


import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {
    
    public static EmployeeService employeeService = null;
    
    public static EmployeeService getInstance()
    {
        if(employeeService==null)
        {
            return new EmployeeService();
        }
        else
        {
            return employeeService;
        }
    }
    
    public ArrayList getAllEmployees()
    {System.out.println("helllllo");
        ArrayList empList = new ArrayList();
         String sql = "SELECT employees.employeeId,employees.firstName,employees.lastName,"
                 + "employees.gender,employees.age,employees.phone,department.departmentName,"
                 + "roles.rolesName,employees.basicSalary,employees.carAllaowance from employees "
                 + "JOIN department ON employees.departmentId=department.departmentId JOIN roles ON employees.roleId = roles.rolesId";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Employee emp = new Employee();
                
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRolesName(rs.getString("rolesName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));                
                emp.setCarAllaowance(rs.getDouble("carAllaowance"));
                
                empList.add(emp);
            }
            
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("Total rows:"+empList.size());
        return empList;
    }
    
     public Boolean AddEmployeeData(Employee emp) {
        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO employees(firstName,lastName,phone,gender,age,departmentId,roleId,basicSalary,carAllaowance)"
                    + "VALUES(? ,? ,? ,? , ? , ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
                        
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getGender());
            preparedStatement.setInt(5, emp.getAge());
            preparedStatement.setInt(6, emp.getDepartmentId());
            preparedStatement.setInt(7, emp.getRoleId());
            preparedStatement.setDouble(8, emp.getBasicSalary());
            preparedStatement.setDouble(9, emp.getCarAllaowance());

            int row = preparedStatement.executeUpdate();

            System.out.println("SQl=" + preparedStatement);
            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
     
     public ArrayList showSearchEmployeeList(Employee emp1) {
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "SELECT  employeeId,firstName,lastName,phone,gender,age,departmentName,rolesName,basicSalary,carAllaowance FROM employees LEFT JOIN roles On employees.roleId= roles.rolesId LEFT JOIN department On employees.departmentId= department.departmentId where firstName like ? \n"
                    + "and lastName like ? and gender like ? and employees.departmentId like ? and employees.roleId like ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp1.getFirstName() + '%');
            preparedStatement.setString(2, emp1.getLastName() + '%');
            preparedStatement.setString(3, emp1.getGender() + '%');

            if (emp1.getDepartmentId() == 0) {
                preparedStatement.setString(4, "" + '%');
            } else {
                preparedStatement.setString(4, Integer.toString(emp1.getDepartmentId()) + '%');
            }
            if (emp1.getRoleId() == 0) {
                preparedStatement.setString(5, "" + '%');
            } else {
                preparedStatement.setString(5, Integer.toString(emp1.getRoleId()) + '%');
            }

            System.out.println("sql: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRolesName(rs.getString("rolesName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));                
                emp.setCarAllaowance(rs.getDouble("carAllaowance"));

                empList.add(emp);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empList;
    }
     
      public  Employee getEmployee(String employeeId) {
        Employee emp = new Employee();
        try {
            Connection con = JDBCConnectionManager.getConnection();

            String sql = "select * from employees e, department d, roles r " + "where e.departmentId=d.departmentId and e.roleId=r.rolesId and e.employeeId=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRolesName(rs.getString("rolesName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));               ;
                emp.setCarAllaowance(rs.getDouble("carAllaowance"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emp;
    }
    
     public boolean updateEmployee(Employee emp, String employeeId) {
         System.out.println(employeeId);

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employees "
                    + "SET firstName = ? , lastName = ? , phone = ?,"
                    + "gender = ? , age = ? ,departmentId = ?,roleId = ?, basicSalary = ?,carAllaowance = ? "
                    + "WHERE employeeId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getGender());
            preparedStatement.setInt(5, emp.getAge());
            preparedStatement.setInt(6, emp.getDepartmentId());
            preparedStatement.setInt(7, emp.getRoleId());
            preparedStatement.setDouble(8, emp.getBasicSalary());          
            preparedStatement.setDouble(9, emp.getCarAllaowance());

            preparedStatement.setString(10, employeeId);
            
            System.out.println("SQl=" + preparedStatement);
            int row = preparedStatement.executeUpdate();

            
            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
