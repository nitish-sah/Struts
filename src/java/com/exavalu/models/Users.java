/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.CountryService;
import com.exavalu.services.DistrictService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.StateService;
import com.exavalu.utils.log4jExample;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;


public class Users extends ActionSupport implements ApplicationAware, SessionAware, Serializable {
    
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


    
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String countryCode;
    private String stateCode;
    private String distCode;
    
            public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    
    
    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(this);
        System.out.println(success);
        if (success) {
            System.out.println("returning Success from doLogin method");
            sessionMap.put("LoggedIn", this);
            
            Users user = LoginService.getInstance().getUser(this.emailAddress);
            sessionMap.put("USER", user);
            System.out.println("user complete");
            
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            result = "SUCCESS";
        } else {
            String errorMsg ="Either email orPassword is Wrong";
            sessionMap.put("ErrorMsg", errorMsg);
            Logger log = Logger.getLogger(log4jExample.class.getName());
           log.error(errorMsg+" "+LocalDateTime.now());
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }

    public String doLogout() throws Exception {
        sessionMap.clear();
        return "SUCCESS";
    }

     public String doSignUp() throws Exception{
         String result ="FAILURE";
         
         boolean success=LoginService.getInstance().doSignUp(this);
         
         if (success) {
            System.out.println("returning Success from doSignUp method");
            String successMsg ="Now Login with your Email Id and PassWord";
            sessionMap.put("SuccessMsg", successMsg);
            result = "SUCCESS";
        } else {
            String errorMsg ="Email Id Already Register";
            sessionMap.put("ErrorMsg1", errorMsg);
            System.out.println("returning Failure from doSignUp method");
        }
         
         return result;
     }
     
     
     public String doPreSignUp() throws Exception {
        String result = "SUCCESS";
        ArrayList countryList = CountryService.getInstance().getAllCountry();

        sessionMap.put("CountryList", countryList);
       
        if (this.countryCode != null) {
            ArrayList stateList = StateService.getInstance().getAllState(this.countryCode);
            sessionMap.put("ProvinceList", stateList);
             sessionMap.put("User", this);
             sessionMap.put("CountryCode", this.countryCode);

            result="PROVINCELIST";
        }
        if (this.stateCode != null) {
            ArrayList distList = DistrictService.getInstance().getAllDistrict(this.stateCode);
            sessionMap.put("DistrictList", distList); 
            this.setStateCode(this.stateCode);
                sessionMap.put("User", this);

            result = "DISTRICTLIST";

        }
        
        if (this.firstName != null && this.firstName.length()>0 && this.lastName != null && this.lastName.length()>0 && this.emailAddress != null && this.emailAddress.length()>0 && this.password!= null && this.password.length()>0 && this.stateCode != null && this.countryCode != null && this.distCode != null) {
            boolean success = LoginService.getInstance().doSignUp(this);

            if (success) {
                result = "DONE";
                sessionMap.put("SuccessSignUp", "Successfully Registered");

            }else{
                result="FAIL";
            } 
            System.out.println(sessionMap);
            return result;
        }
        System.out.println(this.countryCode);
        System.out.println(this.stateCode);
        System.out.println(this.distCode);
        System.out.println(this.emailAddress);

        System.out.println(sessionMap);
        return result;

    }

    
    
    

    
    public String apiCall() throws Exception {
        String result = "SUCCESS";
        ArrayList userList=ApiService.getInstance().getAllData();
        boolean success = LoginService.getInstance().doSignUpAll(userList);

        if (success) {
            result = "SUCCESS";
            sessionMap.put("SuccessSignUp", "Successfully Registered");

        } else {
            sessionMap.put("FailSignUp", "Email All Ready Exsists");
        }
        System.out.println(sessionMap);
        return result;

    }
    

}
