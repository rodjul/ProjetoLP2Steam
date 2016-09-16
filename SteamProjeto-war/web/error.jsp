<%-- 
    Document   : error
    Created on : Sep 7, 2016, 8:45:09 PM
    Author     : RodrigoPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <p>${error}</p>
        <c:set var="error" scope="session" value="" ></c:set>
    </body>
</html>
