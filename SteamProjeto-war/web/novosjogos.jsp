<%-- 
    Document   : novosjogos
    Created on : Aug 25, 2016, 9:45:22 PM
    Author     : RodrigoPC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="j" uri="/WEB-INF/tlds/jogo.tld" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="jspf/conteudohead.jspf" %>
        <title>Novos jogos</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${user == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:when>
    </c:choose>
        <%@include file="jspf/menulogado.jspf" %>
        <section class="container">
            <h2>Novos jogos</h2>
            <br>
            <!--<p>aqui vai ter um botao que add os novos jogos</p>-->
            <section class="row">
                <div class="col-sm-1">
                    <a href="Controller?command=Usersite.meusJogos"><button type="button" id="meujogos1" class="btn btn-primary">Meus jogos</button></a>
                </div>
                <div class="col-sm-2">
                    <a href="Controller?command=Usersite.pesquisarJogos"><button type="button" class="btn btn-primary">Pesquisar novos jogos</button></a>
                </div>
                <div class="col-sm-1s">
                    <a href="Controller?command=Usersite.mostrarJogos"><button type="button" class="btn btn-primary">Mostrar jogos pesquisados</button></a>
                </div>
            </section>
            <br>
            <!--http://steamcommunity.com/id/numericobr/games?tab=all&xml=1-->
            <!--<p>${cookieuser}</p>-->
            <c:choose> 
                <c:when test="${pagina.equals('meusJogos')}">
                    <j:meusJogos user="${cookieuser}"/>
                </c:when>
                <c:when test="${pagina.equals('pesquisarJogos')}">
                    <j:pesquisarJogos user="${cookieuser}"/>
                </c:when>
                <c:when test="${pagina.equals('mostrarJogos')}">
                    <j:jogosPesquisados user="${cookieuser}"/>
                </c:when>
            </c:choose>  

            
            
        </section>
        <%@include file="jspf/footer.jspf" %>
    </body>
    <script>
    function teste(id_name){
        var articleModal = document.getElementById(id_name);
//        document.getElementById("demo").innerHTML = "span";
//        console.log(span); 
        articleModal.style.display = "block";
        
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
             if (event.target == articleModal) {
                articleModal.style.display = "none";
             }
        };
    }
    function form_analise(idgame){
//        var articleModal = document.getElementById("formulario");
        var articleModal = document.getElementById("formulario"+idgame);
        articleModal.style.display = "block";
        
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
             if (event.target == articleModal) {
                articleModal.style.display = "none";
             }
        };
    }
    
    function ver_resto_descricao(idname){
        var articleModal = document.getElementById("descricao"+idname);
        articleModal.style.display = "block";
        
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
             if (event.target == articleModal) {
                articleModal.style.display = "none";
             }
        };
    }
 
    </script>
    

</html>
