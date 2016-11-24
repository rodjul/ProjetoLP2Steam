<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Index</title>
        
    </head>
    <body>
        <c:choose>
            <c:when test="${user != null}">
                <%--<%@include file="jspf/menulogado.jspf" %>--%>
                <c:redirect url="novosjogos.jsp" ></c:redirect>
            </c:when>
            <c:when test="${user == null}" >
        <nav id="navbarcolor" class="navbar navbar-default">
            <div class="container">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Index</a></li>
                <li><a href="about.jsp">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="singup.jsp">Cadastrar</a></li>
            </ul>
            </div>
        </nav>
                
        
        
        <section class="container">
            <h1>ARRUMAR ADD ANALISE</h1>
            <h1>Bem vindo!</h1> 
            <p>Para ter acesso as funcionalidades, precisa se logar.</p>
            <br>
            <form action="Controller" method="post" class="form-singup">
                <!--<h2>Login</h2> <br>-->
                <input type="hidden" name="command" value="Usersite.login" />
                <p><input type="text" name="username" class="form-control" placeholder="Insira o login" /></p>
                <p><input type="password" name="password" class="form-control" placeholder="Insira a senha" />
                    <!--<a href="Controller?command=User.esqueciSenha">Esqueci minha senha</a></p>--><br>
                <input type="submit" value="Logar" class="btn btn-block btn-primary"/>
            </form>
            
            </c:when>
        </c:choose>
            
            
        </section>
        <%@include file="jspf/footer.jspf" %>
        <a href="Controller" >Cont</a>
        <a href="Controller?command=Games.veranalise" >veranalise</a> 
        <a href="Controller?command=Games.analise&gameid=12120&user=rodrigo">12120</a>
    </body>
    
    
</html>
