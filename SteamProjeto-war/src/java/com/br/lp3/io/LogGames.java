/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.io;

import com.br.lp3.model.entities.Games;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodrigoPC
 */
public class LogGames implements Serializable{
    
    private static long serialVersionUID = 1L;
    
    private String fileName = "logGame.txt";
//    private String path = System.getProperty("user.dir")+"/"+this.fileName;
    private String path = "C:\\Users\\RodrigoPC\\Desktop\\github\\github2\\ProjetoLP2Steam\\SteamProjeto-war\\"+this.fileName;
//    private String path = "/"+this.fileName;
    private String a;
//    private List<Games> game = new ArrayList<>();
    
    public LogGames(){}
    
    public void addLog(Games game){
        String gameString = formatStringGame(game);
        writer(gameString);
    }
    
    public void addStringTest(String a){
        writer(a);
    }
    
    public  void writer(String a){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.path,true));
            writer.write(a);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(LogGames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String formatStringGame(Games game){
//        return game.getAppid()+" "+ game.getNomeGame()+" "+ game.getDescricao().length()+" "+ game.getTags()+" "+ game.getUrlSteam()+" "+game.getPesquisa()+"\n";
        return "{"+getDate()+" - Nome:"+game.getNomeGame() + " APPID:"+game.getAppid() + " Descricao.length="+game.getDescricao().length() + " Tags.length:"+game.getTags().length()+"}\n";
    }
    
    public String getDate(){
        String hora = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        hora +=sdf.format(cal.getTime());
        return hora;
    }
    
    private boolean saveLog(String file){
        boolean resultado = false;
        
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            resultado = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogGames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogGames.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
    private void openLog(String file){
        LogGames log = null;
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            log = (LogGames)ois.readObject();
//            this.game = log.getGame();
//            a = log.getA();
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogGames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogGames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LogGames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        LogGames log = new LogGames();
        System.out.println(log.getDate());
    }
}
