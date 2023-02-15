<%-- 
    Document   : districtlist
    Created on : 13-Feb-2023, 8:07:47 pm
    Author     : ASUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<option value="">Select District</option>
<c:forEach items="${DistrictList}" var="district" >
    <option value=${district.getId()}<c:if test="${district.getId().equalsIgnoreCase(emp.getDistCode())}" > selected </c:if>> ${district.getName()} </option>
</c:forEach>
