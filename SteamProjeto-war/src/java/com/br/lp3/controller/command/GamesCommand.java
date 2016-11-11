/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 31597947
 */
public class GamesCommand implements Command{
    
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
            case "analise":{
                analiseGame();
                break;
            }
        }
    }

    public void analiseGame(){
        System.out.println("chegou aqui ola");
        
        String user = request.getParameter("user");
        String op_avaliacao = request.getParameter("op_avaliacao");
        String comentario = request.getParameter("comentario");
        
        System.out.println(user + " "+op_avaliacao + " "+comentario);
        responsePage = "novosjogos.jsp";
    }
    
    @Override
    public String getResponsePage() {
        return this.responsePage;
    }
    
}
