<%-- 
    Document   : analises
    Created on : Nov 18, 2016, 9:08:28 PM
    Author     : RodrigoPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Vendo noticias</title>
    </head>
    <body>
        <c:choose>
             <c:when test="${user == null}">
                    <c:redirect url="index.jsp"></c:redirect>
            </c:when>
        <c:when     test="${user !=  null}">
                <%@include file="jspf/menulogado.jspf" %>
            </c:when>
        </c:choose>
        
        <section class="container">
            <c:forEach var="analise" items="${arrayAnalises}">
                <h2>ds/h2>
                <%--<c:out value="${analise.getFkGames().getNomeGame()}"></c:out>--%>
                <h2><c:out value="${analise}"></c:out></h2>
                
            </c:forEach>
            <!--analises.get(0).getFkGames().getNomeGame()-->
            
        </section>
        
        
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
