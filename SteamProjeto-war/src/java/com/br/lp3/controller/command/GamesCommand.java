/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.controller.command;

import com.br.lp3.model.dao.AnalisesDAO;
import com.br.lp3.model.dao.GamesAnaliseDAO;
import com.br.lp3.model.dao.GamesDAO;
import com.br.lp3.model.dao.UsersiteDAO;
import com.br.lp3.model.entities.Analises;
import com.br.lp3.model.entities.Games;
import com.br.lp3.model.entities.GamesAnalise;
import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.Usersite;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 31597947
 */
public class GamesCommand implements Command{
    GamesAnaliseDAO gamesAnaliseDAO1 = lookupGamesAnaliseDAOBean1();
    GamesDAO gamesDAO = lookupGamesDAOBean();
    UsersiteDAO usersiteDAO = lookupUsersiteDAOBean();
    GamesAnaliseDAO gamesAnaliseDAO = lookupGamesAnaliseDAOBean();
    AnalisesDAO analisesDAO = lookupAnalisesDAOBean();
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        switch(action){
            case "analise":
                analiseGame();
                break;
            case "removerAnalise":
                removerAnaliseMostrar();
                break;
            case "removerFinal":
                removerAnalise();
                break;
        }
    }

    public void analiseGame(){
//        System.out.println("chegou aqui ola");
        
        String user = request.getParameter("user");
        String op_avaliacao = request.getParameter("op_avaliacao");
        String comentario = request.getParameter("comentario");
        long gameid = Long.parseLong(request.getParameter("gameid"));
        
        Usersite username = usersiteDAO.findByUsername(user);
//        Games game = gamesDAO.findById(gameid);
        Games game = gamesDAO.findByAppid(gameid);
        Analises analise = new Analises(username.getUserinfo(), op_avaliacao, comentario, game);
        
        analisesDAO.insert(analise);
        gamesAnaliseDAO.insert(new GamesAnalise(analise, game));

        responsePage = "novosjogos.jsp";
    }

    
    public void removerAnaliseMostrar(){
        Cookie ck [] = request.getCookies();
        String name_user = ck[0].getValue();
        
        long gameid = Long.parseLong(request.getParameter("gameid"));
        Games temp = gamesDAO.findById(gameid);
        
        Usersite user = usersiteDAO.findByUsername(name_user);
        
        List<Analises> allAnalises = analisesDAO.findAllByFkGameUser(temp,user.getUserinfo());
        
        request.getSession().setAttribute("allAnalisesUser",allAnalises);
        responsePage = "removerAnalise.jsp";
    }
    
    public void removerAnalise(){
        
        String username = request.getParameter("username");
        Usersite user = usersiteDAO.findByUsername(username);
        long id = Long.parseLong(request.getParameter("analiseid"));
        Analises temp = analisesDAO.findById(id);
        
        analisesDAO.remove(temp);
        
        List<Analises> allAnalises = analisesDAO.findAllByFkUser(user.getUserinfo());
        request.getSession().setAttribute("allAnalisesUser",allAnalises);
        
        
        request.getSession().setAttribute("removido","Removido com sucesso");
        responsePage = "removerAnalise.jsp";
    }
    
    @Override
    public String getResponsePage() {
        return this.responsePage;
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

    private GamesAnaliseDAO lookupGamesAnaliseDAOBean() {
        try {
            Context c = new InitialContext();
            return (GamesAnaliseDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/GamesAnaliseDAO!com.br.lp3.model.dao.GamesAnaliseDAO");
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

    private GamesDAO lookupGamesDAOBean() {
        try {
            Context c = new InitialContext();
            return (GamesDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/GamesDAO!com.br.lp3.model.dao.GamesDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GamesAnaliseDAO lookupGamesAnaliseDAOBean1() {
        try {
            Context c = new InitialContext();
            return (GamesAnaliseDAO) c.lookup("java:global/SteamProjeto/SteamProjeto-ejb/GamesAnaliseDAO!com.br.lp3.model.dao.GamesAnaliseDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
