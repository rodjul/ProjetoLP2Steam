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
        <c:choose>
            <c:when test="${user == null}">
                <c:redirect url="index.jsp"></c:redirect>
            </c:when>
            <c:when test="${user != null}">
        <%@include file="jspf/menulogado.jspf" %>
                
            </c:when>
        </c:choose>
        
        <section class="container">
            <h1>Minha Conta</h1>
            <!--<br><p>teste ${username.getValue()}</p>-->
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
                <h3>Atualizar email</h3>
                <input type="hidden" name="command" value="Usersite.alterarEmail" />
                <input type="hidden" name="username" value="${user.username}" />
                <p><input type="text" name="email" value="${user.userinfo.email}" placeholder="Nome" class="form-control"/></p>
                <input type="submit" value="Atualizar email" class="btn btn-primary" /><c:if test="${notificacaoEmail != null }">
                    <span>${notificacaoEmail}</span>
                    <c:set var="notificacaoEmail" value="" scope="session" ></c:set>
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
            
            <input type="button" name="botao" class="btn btn-danger" value="Excluir conta" onclick="deletarConta()"/>
            
            <article id="deletar-conta" class="modal">
                <article class="modal-content deletar-conta">
                    <p>Tem certeza que deseja deletar sua conta? Não será possível recuperar futuramente.</p>
                    <div class="col-lg-13 text-center">
                    <!--<a href="Controller?command=Usersite.deletar&username=${user.username}">-->
                        <form method="post" action="Controller">
                            <input type="hidden" name="command" value="Usersite.deletarConta" />
                            <input type="hidden" name="username" value="${user.username}" />
                            <input type="submit" value="Sim" class="btn btn-danger" /><input type="button" value="Não" class="btn btn-primary" onclick="getElementById('deletar-conta').style.display='none'" />
                        </form>
                        
                    </div>
                </article>
            </article>
            
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
    <script>
        function deletarConta(){
            var del = document.getElementById("deletar-conta");
            del.style.display = "block";
            
        }
    </script>
</html>
