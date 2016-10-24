/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.taglibs;

import com.br.lp3.controller.command.UsersiteCommand;
import com.br.lp3.json.SteamJSONParser;
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
    
    
    UsersiteDAO usersiteDAO = lookupUsersiteDAOBean();
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
//        Cookie ck [] = request.getCookies();
//        String name_user = ck[1].getValue();
        
        List<Games> games = new ArrayList<>();
        Usersite temp = usersiteDAO.findByUsername(user);
        String userid = temp.getUserinfo().getUrlsteam().split("/")[2];
        List<Games> jogos_obtidos = SteamJSONParser.getOwnedGames(userid);
        for (Games jogos_obtido : jogos_obtidos) {
            String id = String.valueOf(jogos_obtido.getAppid());
            games.add(
                    SteamJSONParser.getAppDetails(id)
            );
            
        }
        out.println("<section id=\"teste\" class=\"row\">");
        
        for (Games game : games) {
            if(game.getNomeGame() != null){
                out.println("<article class=\"col-xs-6 col-lg-4\">\n" +
                "                        <div class=\"thumbnail\">");
                out.println("                 <div class=\"thumbnail\">");
                out.println("<img src=\"img/epicbattlefantasyiii.jpg\" alt=\"game\" />");
                out.println("<h3>"+game.getNomeGame()+"</h3>");
                out.println("<p>"+game.getDescricao()+"<p>");
                out.println("<p><b>Categoria(s):<b></p>");
                out.println("<b><p class='fontstyle'>"+game.getTags()+"</p></b>");
                out.println("<a href='"+game.getUrlSteam()+"'</a>");
                out.println("</article>");
                
            }
        }
        out.println("</section>");
                            

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

    
    
}