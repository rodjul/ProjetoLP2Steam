<%-- 
    Document   : minhaconta
    Created on : Sep 3, 2016, 9:18:15 PM
    Author     : RodrigoPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <input type="hidden" name="command" value="Usersite.alterarNome" />
                <input type="hidden" name="username" value="${user.username}" />
                <p><input type="text" name="nome" value="${user.userinfo.firstname}" placeholder="Nome" class="form-control"/></p>
                <input type="submit" value="Atualizar nome" class="btn btn-primary" /><c:if test="${notificacaoNome != null }">
                    <span>${notificacaoNome}</span>
                    <c:set var="notificacaoNome" value="" scope="session" ></c:set>
                </c:if>
                
            </form>
            
            <form method="post" action="Controller" class="form-group">
                <h3>Atualizar url da steam</h3>
                <input type="hidden" name="command" value="Usersite.alterarSteam" />
                <input type="hidden" name="username" value="${user.username}" />
                <p><input type="text" name="steamurl" value="${user.userinfo.urlsteam}" placeholder="Steam url" class="form-control"/></p>
                <input type="Submit" value="Atualizar steam" class="btn btn-primary" /> <c:if test="${notificacaoSteam != null }">
                    <span>${notificacaoSteam}</span>
                    <c:set var="notificacaoSteam" value="" scope="session" ></c:set>
                </c:if>
            </form>
            <form method="post" action="Controller" class="form-group">
                <h3>Alterar senha</h3>
                <input type="hidden" name="command" value="Usersite.alterarSenha" />
                <input type="hidden" name="username" value="${user.username}" />
                <p><input type="password" name="senhaatual" value="" placeholder="Senha atual" class="form-control"/></p>
                <p><input type="password" name="novasenha" value="" placeholder="Nova senha" class="form-control"/></p>
                <p><input type="password" name="confirmasenha" value="" placeholder="Confirmar senha" class="form-control"/></p>
                <input type="Submit" value="Atualizar senha" class="btn btn-primary" /> <c:if test="${notificacaoSenha != null }">
                    <span>${notificacaoSenha}</span>
                    <c:set var="notificacaoSenha" value="" scope="session" ></c:set>
                </c:if>
            </form>
            
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
</html>
