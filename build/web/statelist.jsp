<%-- 
    Document   : statelist
    Created on : 13-Feb-2023, 8:03:50 pm
    Author     : ASUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<option value="">Select State</option>

<c:forEach items="${ProvinceList}" var="province" >
    <option value=${province.getStateid()}<c:if test="${province.getStateid().equalsIgnoreCase(emp.getStateCode())}" > selected </c:if>> ${province.getName()} </option>
</c:forEach>