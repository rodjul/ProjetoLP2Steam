<%-- 
    Document   : formulario
    Created on : Nov 18, 2016, 5:35:30 PM
    Author     : RodrigoPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Remover Análise</title>
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
            <h1>Remover Análises</h1>
            <h3>Todas as suas análises de seu jogos estão aqui.</h3>
            
            <c:if test="${removido!=null}"><span class="text-success">${removido}</span><c:set var="removido" scope="session" value=""></c:set></c:if> <br>
           
            <c:forEach items="${allAnalisesUser}" var="analise">
                <article class="form-group">
                    <c:choose>
                        <c:when test="${analise.aprovacao == 'naorecomendo'}">
                            <b><c:out value="${analise.fkGames.nomeGame}"/></b> -  <b><span class="text-danger">Não Recomendo</span></b>   
                        </c:when>
                        <c:otherwise>
                            <b><c:out value="${analise.fkGames.nomeGame}"/></b> -  <b><span class="text-success">Recomendo</span></b>    
                        </c:otherwise>
                    </c:choose>
                    <span class="close" title="Deletar análise" onclick="deletarAnalise()">x</span> 
                    
                <textarea readonly="true" class="form-control"><c:out value="${analise.analise}"/></textarea> <br>
                <div id="deletar-analise" class="modal"> 
                    <div class="modal-content deletar-conta">
                        <p>Tem certeza que deseja deletar essa análise?</p>
                        <div class="col-lg-13 text-center">
                            <form method="post" action="Controller">
                                <input type="hidden" name="command" value="Games.removerFinal" />
                                <input type="hidden" name="username" value="${user.username}" />
                                <input type="hidden" name="analiseid" value="${analise.idGameAnalises}"/>
                                <input type="submit" value="Sim" class="btn btn-danger" /><input type="button" value="Não" class="btn btn-primary" onclick="getElementById('deletar-analise').style.display='none'" />
                            </form>
                        </div>
                    </div>
                </div>
                
                </article>
            </c:forEach>
            
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
    <script>
        function deletarAnalise(){
            var del = document.getElementById("deletar-analise");
            del.style.display = "block";
            
        }
    </script>
</html>
