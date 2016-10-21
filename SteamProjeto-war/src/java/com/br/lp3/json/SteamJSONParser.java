/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 31597947
 */
public class SteamJSONParser {
    
    private String api_key = "88CD5D6FFEDAEB520848A84DDEFE2610";
    
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
    
    public static void testeURLContent() {

        URL url;

        try {
            // get URL content

            String a="http://www.steamcommunity.com/id/numericobr";
            url = new URL(a);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10",3128));
            URLConnection conn = url.openConnection(proxy);

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                //		g_rgProfileData = {"url":"http:\/\/steamcommunity.com\/id\/numericobr\/","steamid":"76561198056805863","personaname":"Numerico","summary":"No information given."};
                // \t\tg_rgProfileData={\"url\":\"http:\\/\\/steamcommunity.com\\/id\\/numericobr\\/\",\"steamid\":
                //deu certo, agora é so pegar o id
                if(inputLine.startsWith("		g_rgProfileData"))
                    System.out.println(inputLine);
            }
            br.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
}
