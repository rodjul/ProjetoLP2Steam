/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.taglibs;

import com.br.lp3.controller.command.UsersiteCommand;
import com.br.lp3.json.SteamJSONParser;
import com.br.lp3.model.dao.AnalisesDAO;
import com.br.lp3.model.dao.GamesDAO;
import com.br.lp3.model.dao.UsersiteDAO;
import com.br.lp3.model.entities.Analises;
import com.br.lp3.model.entities.Games;
import com.br.lp3.model.entities.Usersite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspWriter;


/**
 *
 * @author Patrícia
 */
public class MeuJogoTag extends SimpleTagSupport {
    AnalisesDAO analisesDAO = lookupAnalisesDAOBean();
    

    public String user = "";
    public void setUser(String user) {
        this.user = user;
    }
    
    GamesDAO gamesDAO = lookupGamesDAOBean();
    UsersiteDAO usersiteDAO = lookupUsersiteDAOBean();
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        boolean get = false;
        List<Games> games = new ArrayList<>();
        List<Games> games1 = new ArrayList<>();
        
        Usersite temp = usersiteDAO.findByUsername(user);
        String userid = temp.getUserinfo().getUrlsteam().split("/")[2];
        //verifica se já tem temp.getUserinfo().getGamesList().size()6 games no banco para não precisar processar os request
//        games = gamesDAO.findByFkUser(temp.getUserinfo());
        games = gamesDAO.findByFkUserNotSearch(temp.getUserinfo());
        
//        if(temp.getUserinfo().getGamesList().size() == 6){
        if(games.size() == 6){
//            games = temp.getUserinfo().getGamesList();
            get = true;
        }else{
//            games1 = temp.getUserinfo().getGamesList();
            games1 = games;
            for (Games game : games1) {
                gamesDAO.remove(game);
            }
        }
        if(!get){
            List<Games> jogos_obtidos = SteamJSONParser.getOwnedGames(userid);
                for (Games jogos_obtido : jogos_obtidos) {
                    String id = String.valueOf(jogos_obtido.getAppid());
                    Games game = SteamJSONParser.getShortAppDetails(id);
                    if(game != null){
                        games.add(game);
                        gamesDAO.insert(
                                new Games(game.getAppid(),game.getNomeGame(),game.getDescricao(),game.getTags(),game.getUrlSteam(),temp.getUserinfo(), false)
                        );
                    }
                }
        }
//        gamesDAO.findByUser(temp.getUserinfo());
        List<Analises> analises = null;
        out.println("<h3>Meus Jogos</h3>");
        out.println("<div class='container'>");
        out.println("<section id=\"teste\" class=\"row\">");
        
        for (Games game : games) {
            if(game != null){
            out.println("<article class=\"col-lg-4\">\n" +
"                        <div class=\"thumbnail\">\n" +
"                            <img src=\"http://cdn.akamai.steamstatic.com//steam//apps//"+game.getAppid()+"//header.jpg\" alt=\"game\" />\n" +
"                            <div class=\"caption\">\n" +
"                                <h3>"+game.getNomeGame()+"</h3>\n" +
"                                \n");
            
//----------------------------------separando o texto para alinhar com o css-------------------------------------------
            String texto = game.getDescricao();
            int dividirPosicao = 294;
                
            if(texto.length()>dividirPosicao){
                int dividirPosicao2 = 145;
                String parte1 = texto.substring(0,dividirPosicao2);
out.println(
"                               <p>"+parte1+"</p>\n"+
"                               <p><input type\"button\" onclick=\"ver_resto_descricao('"+game.getNomeGame()+"')\" class=\"btn btn-primary\" value=\"Ver resto da descrição\"/></p> \n "+
        "<br>\n"+
"                               <article id=\"descricao"+game.getNomeGame()+"\" class=\"modal\">\n" +
"                                    <article class=\"modal-content\">\n" +
                                        "<span class=\"close\" onclick=\"getElementById('"+game.getNomeGame()+"').style.display='none'\">x</span>\n"+
"                                       <p>"+texto+"</p>\n"+
"                                    </article>\n"+
"                               </article>\n"
);  
                
            }else{
out.println(
"                                <p>"+game.getDescricao()+"</p>\n"
);
            }
//----------------------------------------------- FIM da alinhação ------------------------------------------
            
        out.println(
"                                \n" +
"                                <p><b>Categoria(s):</b></p>\n" +
"                                <b><p class=\"fontstyle\">"+game.getTags()+"</p></b>\n" +
"\n" +
"                                <a href='"+game.getUrlSteam()+"' target=\"_blank\"><input type=\"button\" class=\"btn btn-primary\" value=\"Ir para a Loja Steam\" />\n" +
"                                \n" +
"                                </a> <input type=\"button\" onclick=\"teste('"+game.getNomeGame()+"')\" class=\"btn btn-primary\" value=\"Ver análises\"/>\n" +
//"                                </a> <a href='Controller?command=Games.veranalise&gameid="+game.getIdGames()+"' ><input type=\"button\" class=\"btn btn-primary\" value=\"Ver análises\"/></a>\n" +
"                                <br>\n" +
"                                <input type=\"button\" id=\"botao_analise\" class=\"btn btn-primary\" onclick=\"form_analise("+game.getAppid()+")\" value=\"Adicionar uma análise\"/>\n"+
                    
"                                <a href='Controller?command=Games.removerAnalise&gameid="+game.getAppid()+"'><input type=\"button\" id=\"botao_analise_rm\" class=\"btn btn-primary\"  value=\"Remover sua Análise\" /></a>\n      "+                    
                    
                    
"                                <article id=\'"+game.getNomeGame()+"' class=\"modal\">\n" +
"                                        <article class=\"modal-content\">\n" +
"                                            <span class=\"close\" onclick=\"getElementById('"+game.getNomeGame()+"').style.display='none'\">x</span>\n" );
                    
//---------------------------obtendo as analises e imprimindo ----------------------------
out.println("<div id=\"thumbnail-auto\">");
                    analises = analisesDAO.findAllById(game);
                    for (Analises analise : analises) {
out.println("                                        <div class=\"thumbnail\"><div class=\"caption\">\n"+
"                                                           <h3>"+(analise.getAprovacao().equals("naorecomendo")?"Não Recomendado":"Recomendado")+"</h3>"+
"                                                           <p>"+analise.getAnalise()+"</p>\n"+
"                                                    </div></div>\n");
                    }
out.println("</div>");
//------------------------fim -----------------------------------------              
                    
out.println(
"                                        </article>\n" +
"                                </article>\n" +
                    
"                                <article id=\"formulario"+game.getAppid()+"\" class=\"modal\">\n" +
"                                    <article class=\"modal-content\">\n" +
//"                                        <span class=\"close\" onclick=\"getElementById('"+game.getNomeGame()+"').style.display='none'\">x</span>\n" +
"                                        <span class=\"close\" onclick=\"getElementById('formulario"+game.getAppid()+"').style.display='none'\">x</span>\n" +
"                                        <form method=\"post\" action=\"Controller\" class=\"form-group\">\n" +
//"                                            <input type=\"hidden\" name=\"command\" value=\"Games.addanalise\" />\n" +
"                                            <input type=\"hidden\" name=\"command\" value=\"Games.analise\" />\n" +
"                                            <input type=\"hidden\" name=\"user\" value=\""+user+"\" />\n" +
"                                            <input type=\"hidden\" name=\"gameid\" value=\""+game.getAppid()+"\" />\n" +
"                                            <select class=\"form-control\" name=\"op_avaliacao\">\n"+
"                                                   <option value=\"recomendo\">Recomendo</option>\n <option value=\"naorecomendo\">Não Recomendo</option>\n  "+
"                                            </select>\n"+
"                                            <br><label for=\"comment\">Comentário:</label>\n" +
"                                            <textarea class=\"form-control\" rows=\"5\" id=\"comment\" name=\"comentario\"></textarea>\n" +
"                                            <br><input type=\"submit\" value=\"Enviar análise\" class=\"btn btn-primary\"/> "+
"                                        </form>\n" +
"                                    </article>\n" +
"                                </article>"+
"                                \n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </article>");                
            }
            

            
        }
        out.println("</section>");
        out.println("</div>");
                            
    }
    
    private UsersiteDAO lookupUsersiteDAOBean() {
        try {
            Context c = new InitialContext();
            return (UsersiteDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/UsersiteDAO!com.br.lp3.model.dao.UsersiteDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

    private GamesDAO lookupGamesDAOBean() {
        try {
            Context c = new InitialContext();
            return (GamesDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/GamesDAO!com.br.lp3.model.dao.GamesDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private AnalisesDAO lookupAnalisesDAOBean() {
        try {
            Context c = new InitialContext();
            return (AnalisesDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/AnalisesDAO!com.br.lp3.model.dao.AnalisesDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}