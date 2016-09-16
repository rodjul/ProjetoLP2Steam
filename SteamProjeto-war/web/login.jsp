<%-- 
    Document   : singin
    Created on : Aug 25, 2016, 4:36:15 PM
    Author     : RodrigoPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Sing In!</title>
    </head>
    <body>
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
            <h2>Login</h2> <br> <br>
            <form action="Controller" method="post" class="form-singup">
                <input type="hidden" name="command" value="Usersite.login" />
                <p><input type="text" name="username" class="form-control" placeholder="Insira o login" /></p>
                <p><label for="password">Password: </label><input type="password" name="password" class="form-control" placeholder="Insira a senha" />
                    <a href="">Esqueci minha senha</a></p>
                <input type="submit" value="Logar" class="btn btn-block btn-primary"/>
            </form>
        
        
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
