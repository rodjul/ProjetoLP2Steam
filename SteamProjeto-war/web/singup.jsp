<%-- 
    Document   : register
    Created on : Aug 25, 2016, 4:35:20 PM
    Author     : RodrigoPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Sing Up!</title>
    </head>
    <body>
        <%@include file="jspf/menu.jspf" %>
        <section class="container">
            
            <h2>Cadastro</h2> <br>
            
        <form method="post" action="Controller" class="form-singup" >
            <input type="hidden" name="command" value="Usersite.register" />
            <h2>Se cadastre para ter acesso ao site:</h2>
            <label for="name">Nome: </label><input type="text" name="nome" class="form-control" placeholder="Insira o nome" />
            <label for="email">Email: </label><input type="text" name="email" class="form-control" placeholder="Insira o email" />
            <label for="urlsteam">URL da Steam: </label><input type="text" name="urlsteam" class="form-control" placeholder="Insira sua url da steam" />
            <label for="login">Login: </label><input type="text" name="username" class="form-control" placeholder="Insira seu login" />
            <label for="password">Password: </label><input type="password" name="password" class="form-control" placeholder="Insira sua senha" />
            <label for="password">Password2: </label><input type="password" name="password2" class="form-control" placeholder="Insira sua senha novamente" /><br>
            <input type="submit" value="Cadastrar" class="btn btn-primary btn-block"/>
            
            
        </form>
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
