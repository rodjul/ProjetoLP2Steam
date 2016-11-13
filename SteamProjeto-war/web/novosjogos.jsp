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
                    <!--<a href="Controller?command=Usersite.meusJogos"><button type="button" class="btn btn-primary">Meus jogos</button></a>-->
                    <form method="post" action="Controller">
                        <input type="hidden" name="command" value="Usersite.meusJogos" />
                        <input type="submit" class="btn btn-primary" value="Meus jogos" />
                    </form>
                </div>
                <div class="col-sm-2">
                    <a href="Controller?command=Usersite.pesquisarJogos"><button type="button" class="btn btn-primary">Pesquisar novos jogos</button></a>
                </div>
                <div class="col-sm-1s">
                    <a href="Controller?command=Usersite.mostrarJogos"><button type="button" class="btn btn-primary">Mostrar jogos pesquisados</button></a>
                </div>
            </section>
            <br>
            <!--<p>Usar essa msm página para listar?</p>-->
            <!--http://steamcommunity.com/id/numericobr/games?tab=all&xml=1-->
            <p>${cookieuser}</p>
            <c:choose> 
                <c:when test="${pagina.equals('meusJogos')}">
                    <h3>Meus jogos</h3>
                    <j:meusJogos user="${cookieuser}"/>
                </c:when>
                <c:when test="${pagina.equals('pesquisarJogos')}">
                    <j:pesquisarJogos user="${cookieuser}"/>
                    <p>${cookieuser}</p>
                </c:when>
                <c:when test="${pagina.equals('mostrarJogos')}">
                    <p>dasasd</p>
                </c:when>
            </c:choose>  
<!--                <section id="teste" class="row">
                    <article class="col-xs-6 col-lg-4">
                        <div class="thumbnail">
                            <img src="img/epicbattlefantasyiii.jpg" alt="game" />
                            <div class="caption">
                                <h3>Epic Battle Fantasy 3</h3>
                                
                                <p>Epic Battle Fantasy 3 is a silly turn-based JRPG, full of useless NPCs, rabid cats, childish humor, unreasonably large weapons, anime boobs, and other nonsense. And it's totally free!</p>
                                
                                <p><b>Categoria(s):</b></p>
                                <b><p class="fontstyle"> RPG, Indie, Aventura, Gratuito para Jogar</p></b>

                                <a href="http://store.steampowered.com/app/521200/" target="_blank"><input type="button" class="btn btn-primary" value="Ir para a Loja Steam" /></a>
                                <input type="button" onfocus="teste('epicbattlefantasy3')" class="btn btn-primary" value="Ver análises"/>
                                <br>
                                <input type="button" class="btn btn-primary" onfocus="form_analise()" value="Adicionar uma análise"/>
                                
                                <article id="epicbattlefantasy3" class="modal">
                                        <article class="modal-content">
                                            <span class="close" onclick="getElementById('epicbattlefantasy3').style.display='none'">x</span>
                                            <div class="thumbnail"><div class="caption">
                                            <p>Eu amei o jogo! O sistema de batalha é muito bom, arte bonita e cenários legais!
                                                10/10.</p>
                                            </div></div>
                                        </article>
                                </article>
                                
                                <article id="formulario" class="modal">
                                    <article class="modal-content">
                                        <span class="close" onclick="getElementById('epicbattlefantasy3').style.display='none'">x</span>
                                        <form method="post" action="" class="form-group">
                                            <input type="hidden" name="command" value="" />
                                            <input type="hidden" name="user" value="${cookieuser}" />
                                            <p><input type="text" placeholder="Insira se recomenda ou não recomenda" name="recomenda" value="" class="form-control"/></p>
                                            <label for="comment">Comentário:</label>
                                            <textarea class="form-control" rows="5" id="comment" name="comentario"></textarea>
                                        </form>
                                    </article>
                                </article>
                                
                            </div>
                        </div>
                    </article>
                    
                    <article class="col-xs-6 col-lg-4">
                        <div class="thumbnail">
                            <img src="img/theturingtest.jpg" alt="game" />
                            <div class="caption">
                                <h3>The Turing Test</h3>
                                <p>The Turing Test is a challenging first-person puzzle game set on Jupiter’s moon, Europa. You are Ava Turing, an engineer for the International Space Agency (ISA) sent to discover the cause behind the disappearance of the ground crew stationed there.</p>
                                
                                <p><b>Categoria(s):</b></p>
                                <b><p class="fontstyle">Aventura, Espacial, Protagonista Feminina, Puzzle</p></b>
                                
                                <a href="http://store.steampowered.com/app/499520/" target="_blank"><input type="button" class="btn btn-primary" value="Ir para a Loja Steam" />
                                </a> <input type="button" onfocus="teste('theturingtest')" class="btn btn-primary analise" value="Ver análises"/>
                                
                                <article id="theturingtest" class="modal">
                                        <article class="modal-content">
                                            <span class="close" onclick="getElementById('theturingtest').style.display='none'">x</span>
                                            <div class="thumbnail"><div class="caption">
                                            <p>Cheguei até o final do chapter 3 e parei para dar uma review rápida sobre esse início do jogo.
                                               Jogo de puzzle mto bom, apesar de ser relativamente fácil até agora.
                                               Notei um problema com a tela de carregamento entre os capítulos, que demora uma eternidade pra carregar no meu CPU da AMD. Já vi outras pessoas reclamando sobre isso, então se você usar um processador da AMD, recomendo esperar um patch do jogo para resolver esse bug.
                                               Gráficos muito bonitos, que conseguem ser apreciados mesmo com a grande maioria das salas tendo um visual muito parecido.
                                               A história do jogo é contada pelo diálogo com a I.A. que te guia durante o jogo, e, apesar de ter opção de legendas, é bom ter um bom conhecimento de inglês para apreciá-la.
                                               O jogo conta com puzzles criativos, história misteriosa e interessante, e com gráficos que não decepcionam, apesar de ter esse bug da tela de carregamento com minha CPU AMD.
                                               Nota 9/10
                                               EDIT: O problema com os processadores da AMD foram resolvidos</p>
                                            </div></div>
                                        </article>
                                </article>
                            </div>
                        </div>
                    </article>
                    
                    <article class="col-xs-6 col-lg-4">
                        <div class="thumbnail">
                            <img src="img/thefinalstation.jpg" alt="game" />
                            <div class="caption">
                                <h3>The Final Station</h3>
                                <p>Viaje de trem por um mundo agonizante. Cuide de seus passageiros, mantenha seu trem funcionando e garanta que seu trem chegue sempre à próxima estação. Trilhe seu caminho por bandos de contaminados em cada estação. Explore estações misteriosas e abandonadas em busca de suprimentos e sobreviventes.</p>
                                
                                <p><b>Categoria(s):</b></p>
                                <b><p class="fontstyle">Indie, Aventura, Ação, Sobrevivência, 2D</p></b>
                                
                                <a href="http://store.steampowered.com/app/435530/" target="_blank"><input type="button" class="btn btn-primary" value="Ir para a Loja Steam" /></a>
                                </a> <input type="button" onfocus="teste('thefinalstation')"  class="btn btn-primary" value="Ver análises"/>
                                
                                <article id="thefinalstation" class="modal">
                                    <article class="modal-content">
                                        <span class="close" onclick="getElementById('thefinalstation').style.display='none'">x</span>
                                        <div class="thumbnail"><div class="caption">
                                        <h3>Recomendado</h3>
                                        <h6>4.5 horas registradas</h6>
                                        <p>
                                            É um joguinho indie muito bom! Você controla um trem que faz paradas em várias estações (abandonadas ou não) num mundo pós-apocalíptico, no trem você ajuda sobreviventes e leva eles para cidades que ainda não caíram. Se você completa uma viagem com sucesso, dando comida e tratando dos ferimentos dos passageiros (com medkits) no final eles te dão dinheiro, munição e até melhoras especiais de habilidades.
                                            Quando você explora as estações abandonadas, encontrará criaturas sombrias e é nesse momento que o jogo brilha mais, pois o aspecto de sobrevivência e cálculo no uso de armas/medkits nas suas aventuras é bem importante. Várias partes da história acontecem no diálogo entre passageiros, conversas salvas em computadores e bilhetes, portanto preste atenção se quiser saber mais. Mas se não quiser saber tanto faz, o jogo é divertido por si só!
                                            Encontrei um bug na primeira vez que joguei que coincidentemente foi corrigido por um patch no mesmo dia, portanto, aparentemente o suporte ao jogo está forte. 
                                            Pros:
                                            - Administração de recursos, como munição, medkits e comida.
                                            - Sobrevivência.
                                            - Bastante exploração nas estações/cidades.
                                            - Clima melancólico/solitário.
                                            Contras:
                                            - Final mal explicado.
                                            - Fator replay não existente.
                                            O jogo tem defeitos, mas é bem divertido. Nota 7. 
                                        </p>
                                        </div></div>
                                        <div class="thumbnail"><div class="caption">
                                        <h3>Recomendado</h3>
                                        <h6>4.5 horas registradas</h6>
                                        <p>
                                            É um joguinho indie muito bom! Você controla um trem que faz paradas em várias estações (abandonadas ou não) num mundo pós-apocalíptico, no trem você ajuda sobreviventes e leva eles para cidades que ainda não caíram. Se você completa uma viagem com sucesso, dando comida e tratando dos ferimentos dos passageiros (com medkits) no final eles te dão dinheiro, munição e até melhoras especiais de habilidades.
                                            Quando você explora as estações abandonadas, encontrará criaturas sombrias e é nesse momento que o jogo brilha mais, pois o aspecto de sobrevivência e cálculo no uso de armas/medkits nas suas aventuras é bem importante. Várias partes da história acontecem no diálogo entre passageiros, conversas salvas em computadores e bilhetes, portanto preste atenção se quiser saber mais. Mas se não quiser saber tanto faz, o jogo é divertido por si só!
                                            Encontrei um bug na primeira vez que joguei que coincidentemente foi corrigido por um patch no mesmo dia, portanto, aparentemente o suporte ao jogo está forte. 
                                            Pros:
                                            - Administração de recursos, como munição, medkits e comida.
                                            - Sobrevivência.
                                            - Bastante exploração nas estações/cidades.
                                            - Clima melancólico/solitário.
                                            Contras:
                                            - Final mal explicado.
                                            - Fator replay não existente.
                                            O jogo tem defeitos, mas é bem divertido. Nota 7. 
                                        </p>
                                        </div></div>
                                        <div class="thumbnail">
                                            <div class="caption">
                                                <h3>Recomendado</h3>
                                                <h6>4.5 horas registradas</h6>
                                                <p>
                                                    É um joguinho indie muito bom! Você controla um trem que faz paradas em várias estações (abandonadas ou não) num mundo pós-apocalíptico, no trem você ajuda sobreviventes e leva eles para cidades que ainda não caíram. Se você completa uma viagem com sucesso, dando comida e tratando dos ferimentos dos passageiros (com medkits) no final eles te dão dinheiro, munição e até melhoras especiais de habilidades.
                                                    Quando você explora as estações abandonadas, encontrará criaturas sombrias e é nesse momento que o jogo brilha mais, pois o aspecto de sobrevivência e cálculo no uso de armas/medkits nas suas aventuras é bem importante. Várias partes da história acontecem no diálogo entre passageiros, conversas salvas em computadores e bilhetes, portanto preste atenção se quiser saber mais. Mas se não quiser saber tanto faz, o jogo é divertido por si só!
                                                    Encontrei um bug na primeira vez que joguei que coincidentemente foi corrigido por um patch no mesmo dia, portanto, aparentemente o suporte ao jogo está forte. 
                                                    Pros:
                                                    - Administração de recursos, como munição, medkits e comida.
                                                    - Sobrevivência.
                                                    - Bastante exploração nas estações/cidades.
                                                    - Clima melancólico/solitário.
                                                    Contras:
                                                    - Final mal explicado.
                                                    - Fator replay não existente.
                                                    O jogo tem defeitos, mas é bem divertido. Nota 7. 
                                                </p>
                                            </div>
                                        </div>
                                        <div class="thumbnail">
                                            <div class="caption">
                                                <h3>Recomendado</h3>
                                                <h6>4.5 horas registradas</h6>
                                                <p>
                                                    É um joguinho indie muito bom! Você controla um trem que faz paradas em várias estações (abandonadas ou não) num mundo pós-apocalíptico, no trem você ajuda sobreviventes e leva eles para cidades que ainda não caíram. Se você completa uma viagem com sucesso, dando comida e tratando dos ferimentos dos passageiros (com medkits) no final eles te dão dinheiro, munição e até melhoras especiais de habilidades.
                                                    Quando você explora as estações abandonadas, encontrará criaturas sombrias e é nesse momento que o jogo brilha mais, pois o aspecto de sobrevivência e cálculo no uso de armas/medkits nas suas aventuras é bem importante. Várias partes da história acontecem no diálogo entre passageiros, conversas salvas em computadores e bilhetes, portanto preste atenção se quiser saber mais. Mas se não quiser saber tanto faz, o jogo é divertido por si só!
                                                    Encontrei um bug na primeira vez que joguei que coincidentemente foi corrigido por um patch no mesmo dia, portanto, aparentemente o suporte ao jogo está forte. 
                                                    Pros:
                                                    - Administração de recursos, como munição, medkits e comida.
                                                    - Sobrevivência.
                                                    - Bastante exploração nas estações/cidades.
                                                    - Clima melancólico/solitário.
                                                    Contras:
                                                    - Final mal explicado.
                                                    - Fator replay não existente.
                                                    O jogo tem defeitos, mas é bem divertido. Nota 7. 
                                                </p>
                                            </div>
                                        </div>
                                    </article>
                                </article>
                            </div>
                            
                        </div>
                    </article>
                    
            
                --></section>
            
            
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

 
    </script>
</html>
