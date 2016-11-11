/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.taglibs;

import com.br.lp3.controller.command.UsersiteCommand;
import com.br.lp3.json.SteamJSONParser;
import com.br.lp3.model.dao.GamesDAO;
import com.br.lp3.model.dao.UsersiteDAO;
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
        //verifica se já tem 6 games no banco para não precisar processar os request
        if(temp.getUserinfo().getGamesList().size() == 6){
            games = temp.getUserinfo().getGamesList();
            get = true;
        }else{
            games1 = temp.getUserinfo().getGamesList();
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
                                new Games(game.getAppid(),game.getNomeGame(),game.getDescricao(),game.getTags(),game.getUrlSteam(),temp.getUserinfo())
                        );
                    }
                }
        }
//        gamesDAO.findByUser(temp.getUserinfo());
        
        
        out.println("<div class='container'>");
        out.println("<section id=\"teste\" class=\"row\">");
        
        for (Games game : games) {
            if(game != null){
            out.println("<article class=\"col-lg-4\">\n" +
"                        <div class=\"thumbnail\">\n" +
"                            <img src=\"http://cdn.akamai.steamstatic.com//steam//apps//"+game.getAppid()+"//header.jpg\" alt=\"game\" />\n" +
"                            <div class=\"caption\">\n" +
"                                <h3>"+game.getNomeGame()+"</h3>\n" +
"                                \n" +
"                                <p>"+game.getDescricao()+"</p>\n" +
"                                \n" +
"                                <p><b>Categoria(s):</b></p>\n" +
"                                <b><p class=\"fontstyle\">"+game.getTags()+"</p></b>\n" +
"\n" +
"                                <a href='"+game.getUrlSteam()+"' target=\"_blank\"><input type=\"button\" class=\"btn btn-primary\" value=\"Ir para a Loja Steam\" />\n" +
"                                \n" +
"                                </a> <input type=\"button\" onfocus=\"teste('"+game.getNomeGame()+"')\" class=\"btn btn-primary\" value=\"Ver análises\"/>\n" +
"                                \n" +
"                                <article id=\"epicbattlefantasy3\" class=\"modal\">\n" +
"                                        <article class=\"modal-content\">\n" +
"                                            <span class=\"close\" onclick=\"getElementById('"+game.getNomeGame()+"').style.display='none'\">x</span>\n" +
"                                            <div class=\"thumbnail\"><div class=\"caption\">\n" +
"                                            </div></div>\n" +
"                                        </article>\n" +
"                                </article>\n" +
"                                \n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </article>");                
            }
            

            
        }
        out.println("</section>");
        out.println("</div>");
                            
    }
//                                <a href="http://store.steampowered.com/app/521200/" target="_blank"><input type="button" class="btn btn-primary" value="Ir para a Loja Steam" />
//                                </a> <input type="button" onfocus="teste('epicbattlefantasy3')" class="btn btn-primary" value="Ver análises"/>
//                                <article id="epicbattlefantasy3" class="modal">
//                                        <article class="modal-content">
//                                            <span class="close" onclick="getElementById('epicbattlefantasy3').style.display='none'">x</span>
//                                            <div class="thumbnail"><div class="caption">
//                                            <p>Eu amei o jogo! O sistema de batalha é muito bom, arte bonita e cenários legais!
//                                                10/10.</p>
//                                            </div></div>
//                                        </article>
//                                </article>
//                                
//                            </div>
//                        </div>
//                    </article>
    
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

    
    
}