/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Users;
import com.exavalu.utils.JDBCConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.log4j.Logger;


public class LoginService {
    

    public static LoginService loginService = null;
    public static Logger log = Logger.getLogger(LoginService.class.getName());


    private LoginService() {
    }

    public static LoginService getInstance() {
        if (loginService == null) {
            return new LoginService();
        } else {
            return loginService;
        }
    }

    public boolean doLogin(Users user) {
        boolean success = false;

        String sql = "Select * from user where emailAddress=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmailAddress());
            ps.setString(2, user.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            
             log.error("Not Found");
             System.out.println(ex.getErrorCode());

            ex.printStackTrace();
        }

        return success;
    }

    public boolean doSignUp(Users user) {

        boolean result = false;
        Connection con = JDBCConnectionManager.getConnection();
        String sql ="INSERT INTO user(emailAddress,password,firstName,lastName,countrycode,statecode,distcode)" + "VALUES(? ,? ,? ,?,?,?,?)";


        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());

            preparedStatement.setString(5, user.getCountryCode() );
            preparedStatement.setString(6, user.getStateCode() );
            preparedStatement.setString(7, user.getDistCode() );

            System.out.println("LoginService :: " + preparedStatement);

            int rs = preparedStatement.executeUpdate();

            if (rs == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            int e = ex.getErrorCode();
            log.error(LocalDateTime.now()+"Sql Error :"+e+" Duplicate Email Address");
            System.out.println(LocalDateTime.now()+"error code:"+e+"Duplicate Email Address" );
            ex.printStackTrace();
        }
        return result;
    }

    public Users getUser(String emailAddress) {
        Connection con = JDBCConnectionManager.getConnection();
        Users user = new Users();
        try {

            String sql = "Select * from user where emailAddress=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emailAddress);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }


    public boolean doSignUpAll(ArrayList userList) {
        boolean result = true;
        for(int i=0;i<userList.size();i++)
        {
            if(!doSignUp((Users)userList.get(i)))
            {
                result=false;
            }
        }
        return result;
    }
}
