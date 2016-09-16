<%-- 
    Document   : minhaconta
    Created on : Sep 3, 2016, 9:18:15 PM
    Author     : RodrigoPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Minha Conta</title>
    </head>
    <body>
        <%@include file="jspf/menulogado.jspf" %>
        <section class="container">
            <h1>Minha Conta</h1>
            <br>
            
            <form method="post" action="Controller" class="form-group">
                <h3>Atualizar nome</h3>
                <input type="hidden" name="command" value="" />
                <p><input type="text" name="" value="${user.userinfo.firstname}" placeholder="Nome" class="form-control"/></p>
                <input type="submit" value="Atualizar nome" class="btn btn-primary" />
            </form>
            
            <form method="post" action="Controller" class="form-group">
                <h3>Atualizar url da steam</h3>
                <input type="hidden" name="command" value="${user.userinfo.urlsteam}" />
                <p><input type="text" name="" value="" placeholder="Steam url" class="form-control"/></p>
                <input type="Submit" value="Atualizar steam" class="btn btn-primary" />
            </form>
            <form method="post" action="Controller" class="form-group">
                <h3>Alterar senha</h3>
                <input type="hidden" name="command" value="" />
                <p><input type="password" name="" value="" placeholder="Senha atual" class="form-control"/></p>
                <p><input type="password" name="" value="" placeholder="Nova senha" class="form-control"/></p>
                <p><input type="password" name="" value="" placeholder="Confirmar senha" class="form-control"/></p>
                <input type="Submit" value="Atualizar senha" class="btn btn-primary" />
            </form>
            
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
