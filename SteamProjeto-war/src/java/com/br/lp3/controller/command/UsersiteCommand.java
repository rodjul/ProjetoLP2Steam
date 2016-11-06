/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.controller.command;

import com.br.lp3.json.SteamJSONParser;
import com.br.lp3.model.dao.UsersiteDAO;
import com.br.lp3.model.entities.Games;
import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.Usersite;
import java.util.ArrayList;
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
                //http://www.javatpoint.com/servlet-login-and-logout-example-using-cookies
                Cookie ck = new Cookie("username","");
                ck.setMaxAge(0);
                response.addCookie(ck);
                
                request.getSession().invalidate();
                responsePage = "index.jsp";
                break;
            case "esqueciSenha":
                esqueciSenha();
                break;
            case "alterarSenha":
                alterarSenha();
                break;
            case "alterarSteam":
                alterarSteam();
                break;
            case "alterarNome":
                alterarNome();
                break;
            case "alterarEmail":
                alterarEmail();
                break;
            case "deletarConta":
                deletarConta();
                break;
            case "meusJogos":
                meusJogos();
                break;
            case "pesquisarJogos":
                pesquisarJogos();
                break;
            case "mostrarJogos":
                mostrarJogos();
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
            responsePage = "singup.jsp";
            request.getSession().setAttribute("erroruser","Username already registered");
//            request.getSession().setAttribute("error", "Username already registered");
        }else if(temp2 != null){
            responsePage = "singup.jsp";
            request.getSession().setAttribute("erroremail","Email já registrado");
//            request.getSession().setAttribute("error", "Email already registered");
        }else if(!password.equals(password2)){
            responsePage = "singup.jsp";
            request.getSession().setAttribute("errorpassword","Passwords don't match");
//            request.getSession().setAttribute("error", "Passwords don't match");
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
            Cookie cookie = new Cookie("username", username);
            response.addCookie(cookie);
//            Cookie ck [] = request.getCookies();
//            String name = ck[0].getValue();
                  
            request.getSession().setAttribute("user",temp3);
            responsePage = "novosjogos.jsp";
        }
        
    }
    
    public void esqueciSenha(){}
    
    public void alterarSenha(){
        String senhaAtual = request.getParameter("senhaatual");
        String senhaNova = request.getParameter("novasenha");
        String senhaConfir = request.getParameter("confirmasenha");
        username = request.getParameter("username");
        
        Usersite temp4 = usersiteDAO.findByUsername(username);
        if(!senhaAtual.equals(temp4.getPassword())){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error","Senha atual não confere com a armazenada");
        }else if(!senhaNova.equals(senhaConfir)){
            responsePage = "error.jsp";
            request.getSession().setAttribute("error","Nova e Confirma senha não são iguais");
        }else{

            temp4.setPassword(senhaNova);
            usersiteDAO.modify(temp4);
            
            responsePage = "minhaconta.jsp";
            request.getSession().setAttribute("notificacaoSenha","Senha alterada com sucesso!");
        }
    }
    
    public void alterarSteam(){
        String newUrlSteam = request.getParameter("steamurl");
        username = request.getParameter("username");
        //alguma condição
        
        Usersite temp = usersiteDAO.findByUsername(username);
        temp.getUserinfo().setUrlsteam(newUrlSteam);
        
        usersiteDAO.modify(temp);
        request.getSession().setAttribute("user", temp);
        
        responsePage = "minhaconta.jsp";
        request.getSession().setAttribute("notificacaoSteam", "URL alterado com sucesso!");
        
    }
    
    public void alterarNome(){
        username = request.getParameter("username");
        String userNome = request.getParameter("nome");
        Usersite temp = usersiteDAO.findByUsername(username);
        temp.getUserinfo().setFirstname(userNome);
        
        usersiteDAO.modify(temp);
        
        request.getSession().setAttribute("user", temp);
        
        responsePage = "minhaconta.jsp";
        request.getSession().setAttribute("notificacaoNome", "Nome alterado com sucesso!");
        
        
    }
    
    public void alterarEmail(){
        responsePage = "minhaconta.jsp";
        username = request.getParameter("username");
        String email = request.getParameter("email");
        Usersite temp = usersiteDAO.findByEmail("email");
        Usersite temp2 = usersiteDAO.findByUsername(username);
        if(temp == null){
            temp2.getUserinfo().setEmail(email);
            usersiteDAO.modify(temp2);
            request.getSession().setAttribute("user",temp2);
            request.getSession().setAttribute("notificacaoEmail","Email atualizado com sucesso");
        }else{
            request.getSession().setAttribute("notificacaoEmail","Email já está sendo utilizado, tente outro.");
        }
    }

    public void deletarConta(){
        username = request.getParameter("username");
        Usersite temp = usersiteDAO.findByUsername(username);
        usersiteDAO.remove(temp);
        request.getSession().invalidate();
        responsePage = "index.jsp";
        
    }
    
    public void meusJogos(){
        Cookie ck [] = request.getCookies();
        String name_user = ck[1].getValue();
//        
//        List<Games> games = new ArrayList<>();
//        Usersite temp = usersiteDAO.findByUsername(name_user);
//        String userid = temp.getUserinfo().getUrlsteam().split("/")[2];
//        List<Games> jogos_obtidos = SteamJSONParser.getOwnedGames(userid);
//        for (Games jogos_obtido : jogos_obtidos) {
//            String id = String.valueOf(jogos_obtido.getAppid());
//            games.add(
//                    SteamJSONParser.getAppDetails(id)
//            );
//            break;
//            
//        }
        request.getSession().setAttribute("cookieuser",name_user);
        request.getSession().setAttribute("pagina", "meusJogos");
        responsePage = "novosjogos.jsp";

    }
    
    public void pesquisarJogos(){
        request.getSession().setAttribute("pagina","pesquisarJogos");
        responsePage = "novosjogos.jsp";
    }
    
    public void mostrarJogos(){
        request.getSession().setAttribute("pagina","mostrarJogos");
        responsePage = "novosjogos.jsp";
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
