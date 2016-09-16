/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.controller.command;

import com.br.lp3.controller.command.Command;
import com.br.lp3.model.dao.UsersiteDAO;
import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.Usersite;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RodrigoPC
 */
public class UsersiteCommand implements Command{
    UsersiteDAO usersiteDAO = lookupUsersiteDAOBean();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;
    
    private String username;
    private String password;
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        switch(action){
            case "register":
                registerUser();
                break;
            case "login":
                loginUser();
            break;
            case "logout":
                request.getSession().invalidate();
                responsePage = "index.jsp";
                break;
        }
        
        
    }
    
    
    private void registerUser(){
        username = request.getParameter("username");
        password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String firstname = request.getParameter("nome");
        String email = request.getParameter("email");
        String urlSteam = request.getParameter("urlsteam");
        Usersite temp1 = usersiteDAO.findByUsername(username);
        Usersite temp2 = usersiteDAO.findByEmail(email);
        if(temp1 != null){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error", "Username already registered");
        }else if(temp2 != null){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error", "Email already registered");
        }else if(!password.equals(password2)){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error", "Passwords don't match");
        }else{
            Usersite user = new Usersite();
            user.setUsername(username);
            user.setPassword(password);

            Userinfo ui = new Userinfo();
            ui.setFirstname(firstname);
            ui.setEmail(email);
            ui.setUrlsteam(urlSteam);

            ui.setUsersite(user);
            user.setUserinfo(ui);

            usersiteDAO.insert(user);
            responsePage = "login.jsp";
        }
    }
    
    private void loginUser(){
        username = request.getParameter("username");
        password = request.getParameter("password");
        Usersite temp3 = usersiteDAO.findByUsername(username);
        if(temp3 == null){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error","User not found!");
        }else if(!password.equals(temp3.getPassword())){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error","Passwords don't match");
        }else{
            request.getSession().setAttribute("user",temp3);
            responsePage = "novosjogos.jsp";
        }
        
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
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
