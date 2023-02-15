

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.exavalu.services.RoleService"%>
<%@page import="com.exavalu.services.DepartmentService"%>
<%@page import="com.exavalu.models.Role"%>
<%@page import="com.exavalu.models.Department"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.exavalu.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
       
    </head>
    <body class="text-center">
        
            <main class="form-signin w-25 m-auto">

                <form action="AddEmployee" method="post">
                    <h1 class="h3 mb-3 fw-normal">Please Add Employee Details</h1>

                <c:set var="msg" value="${ErrorMsg2}"/>
                <c:if test="${msg!=null}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${msg}"/>
                    </div>
                </c:if>


                <div class="form-floating">
                    <input type="text" pattern="[A-Za-z]{1,}" title="Only Alphabets are allowed" class="form-control" id="floatingInput" placeholder="firstName" name="firstName" required="required" >
                    <label for="floatingInput">First Name</label>
                </div>

                <div class="form-floating">
                    <input type="text" pattern="[A-Za-z]{1,}" title="Only Alphabets are allowed" class="form-control" id="floatingInput" placeholder="lastName" name="lastName" required="required">
                    <label for="floatingInput">Last Name</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="phone" name="phone" required="required">
                    <label for="floatingInput">Phone</label>
                </div>

                <div class="form-floating">
                    
                    
                    <select name="gender" class="form-select" id="gender">
                        <option value= >Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                    <label for="floatingInput">Gender</label>
                </div>

                <div class="form-floating">
                    <input type="number" class="form-control" id="floatingInput" placeholder="age" name="age" required="required">
                    <label for="floatingInput">Age</label>
                </div>

                <div class="form-floating">                    

                    <select name="departmentId" class="form-select" id="departmentId">
                        <option value="0" required="required">Select Dept</option>
                        <c:forEach items="${DepartmentService.getAllDepartment()}" var="dept">
                            <option value="${dept.getDepartmentId()}"> <c:out value="${dept.getDepartmentName()}"/>  </option>
                        </c:forEach>
                    </select>
                    <label for="floatingInput">Department Name</label>
                </div>

                <div class="form-floating">                  
                    <select name="roleId" class="form-select" id="roleId">
                        <option value="0" required="required">Select Role</option>
                        <c:forEach items="${RoleService.getAllRole()}" var="role">
                            <option value="${role.getRolesId()}"> <c:out value="${role.getRolesName()}"/>  </option>
                        </c:forEach>
                    </select>
                    <label for="floatingInput">Role Name</label>
                </div>

                <div class="form-floating">
                    <input type="number" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" required="required">
                    <label for="floatingInput">Salary</label>
                </div>

                
                <div class="form-floating">
                    <input type="number" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllaowance" required="required">
                    <label for="floatingInput">Car Allowance</label>
                </div>



                <button class="w-100 btn btn-lg btn-primary" type="submit">ADD DATA</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2023-2024 by nitish </p>
            </form>
        

        
        </main>
    </body>
</html>
