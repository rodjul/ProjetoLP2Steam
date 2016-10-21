/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author 31597947
 */
public class SteamJSONParser {
    
    private static String api_key = "88CD5D6FFEDAEB520848A84DDEFE2610";
    
    
    public static String openURL(String uri){
        String content = "";
        
        try {
            URL url = new URL(uri);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10",3128));
            //            sem proxy para usar em casa        
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            //usar com proxy no mackenzie
            HttpURLConnection con = (HttpURLConnection)url.openConnection(proxy);
            
            int code = con.getResponseCode();
            if(code == 407) System.out.println("Falha na autenticação do Proxy");
            else if(code==200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
                StringBuilder sb = new StringBuilder();
                String line;
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                br.close();
                con.disconnect();
                
                content = sb.toString();
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(SteamJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SteamJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return content;
    }
    
    public static String getUserID(String url_user) {
        String userid = "";
        try {
            // get URL content
//            String a="http://www.steamcommunity.com/id/numericobr";
            URL url = new URL(url_user);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10",3128));
            URLConnection conn = url.openConnection(proxy);

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine="";
            while ((inputLine = br.readLine()) != null) {
                //		g_rgProfileData = {"url":"http:\/\/steamcommunity.com\/id\/numericobr\/","steamid":"76561198056805863","personaname":"Numerico","summary":"No information given."};
                // \t\tg_rgProfileData={\"url\":\"http:\\/\\/steamcommunity.com\\/id\\/numericobr\\/\",\"steamid\":
               
                if(inputLine.startsWith("		g_rgProfileData")){
                    String [] split = inputLine.split(",");
                    String [] splitId = split[1].split(":");
                    userid = splitId[1];
                }
            }
            br.close();

//            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return userid;
    }

//    public static void getPlayerSumaries(String id){
    public static void getPlayerSumaries(){
        String id = "76561198056805863";
        String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+api_key+"&steamids="+id;
        
        String content = openURL(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        JsonArray players = root.getJsonArray("response");
        JsonObject obj = players.getJsonObject(0);
        
        String steamid = obj.getString("steamid");
        
        
        
        
    }
    
    
    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
