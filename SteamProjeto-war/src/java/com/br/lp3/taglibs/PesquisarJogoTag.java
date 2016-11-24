/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.taglibs;

import com.br.lp3.io.LogGames;
import com.br.lp3.json.SteamJSONParser;
import com.br.lp3.model.dao.GamesDAO;
import com.br.lp3.model.dao.UsersiteDAO;
import com.br.lp3.model.entities.Games;
import com.br.lp3.model.entities.Usersite;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Patrícia
 */
public class PesquisarJogoTag extends SimpleTagSupport{
    public String user = "";
    public void setUser(String user) {
        this.user = user;
    }
    
        
    UsersiteDAO usersiteDAO = lookupUsersiteDAOBean();
    GamesDAO gamesDAO = lookupGamesDAOBean();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        Usersite temp = usersiteDAO.findByUsername(user);
        List<Games> games = SteamJSONParser.getNewGames();
//        LogGames log = new LogGames();
        for (Games game : games) { //verificar se não ta no banco de dados
//            log.addLog(game);
            gamesDAO.insert(new Games(game.getAppid(), game.getNomeGame(), game.getDescricao(), game.getTags(), game.getUrlSteam(), temp.getUserinfo(), game.getPesquisa() ));
//            gamesDAO.insert(new Games(game.getAppid(),game.getNomeGame(),game.getDescricao(),game.getTags(),game.getUrlSteam(),temp.getUserinfo() ));
        }
        
        List<Games> test = gamesDAO.findSearch(temp.getUserinfo());
        
        out.println("<h3>Pesquisar jogo</h3><br>");
        
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

    
    
    private GamesDAO lookupGamesDAOBean() {
        try {
            Context c = new InitialContext();
            return (GamesDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/GamesDAO!com.br.lp3.model.dao.GamesDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
    
    public static void main(String[] args) {
        String a = "<p>A tale of the planet's final love story told using the full force of minori's critically acclaimed illustrative style, engaging musical compositions, and";
        String texto1 = "";
        String texto2 = "";
        for (int i=0; i<a.length(); i++){
            if(i>=76) {
                texto2 += a.charAt(i);
            }else{
                if((i+1)==76){
                    System.out.print("... Ver mais\n"); 
                }
            }
      
        }
    }
    
    
}
