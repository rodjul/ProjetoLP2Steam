<%-- 
    Document   : about
    Created on : Aug 25, 2016, 4:35:01 PM
    Author     : RodrigoPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Sobre</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${user == null}">
            <%@include file="jspf/menu.jspf" %>
        </c:when>
        <c:when test="${user !=  null}">
            <%@include file="jspf/menulogado.jspf" %>
        </c:when>
    </c:choose>
        <section class="container">
            
            <h2>About</h2>
            <br>
            <h3>O objetivo do site é buscar novos jogos baseado na categoria do jogo mais recente que jogou. O novo jogo, pode ser tanto antigo como recém-lançado e 
                a busca é baseado na plataforma Steam</h3>
            
        
        
        
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
