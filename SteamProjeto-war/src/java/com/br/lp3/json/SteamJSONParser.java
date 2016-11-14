/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.json;

import com.br.lp3.io.LogGames;
import com.br.lp3.model.entities.Games;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
    
    public static String openURL(String uri){
        String content = "";
        
        try {
            URL url = new URL(uri);
            //            sem proxy para usar em casa        
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            //usar com proxy no mackenzie
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10",3128));
//            HttpURLConnection con = (HttpURLConnection)url.openConnection(proxy);
            
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
                    String split = inputLine.split(",")[1].split(":")[1];
//                    String [] splitId = split[1].split(":");
                    userid = split;
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
        
        JsonObject response = root.getJsonObject("response");
        JsonArray players = response.getJsonArray("players");
        JsonObject obj = players.getJsonObject(0);
        
        String steamid = obj.getString("steamid");
        String personaname = obj.getString("personaname");
        
        //funcionando
    }
    
    public static List<Games> getOwnedGames(String userid){
        String id = "76561198056805863";
        String url = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+api_key+"&steamid="+userid+"&format=json";
        List<Games> gameList = new ArrayList<>();
        
        String content = openURL(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        JsonObject response = root.getJsonObject("response");
//        int games_count = response.getInt("game_count");
        int games_count = 8;
        JsonArray games = response.getJsonArray("games");
        
        for (int i = 0; i < games_count; i++) {
            JsonObject gamesObj = games.getJsonObject(i);
            int appid = gamesObj.getInt("appid");
            int playtime_forever = gamesObj.getInt("playtime_forever");
            
            gameList.add(new Games(appid));
        }
        
        return gameList;
        
    }
    
//    public static void searchGameByTag(){
//        String uri = "http://www.google.com";
//        ArrayList games = new ArrayList<>();
//        try {
//            URL url = new URL(uri);
//            URLConnection con = url.openConnection();
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            
//            String inputLine;
//            while((inputLine = br.readLine())!= null){
//                if(inputLine.startsWith("<tr class=\"app\" data-appid=\"")){
//                    System.out.println(inputLine);
//                }
//            }
//            
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(SteamJSONParser.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SteamJSONParser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
//    }
    
    
    /**
     * Obtem todos os ids de todos os jogos cadastrados
     */
    public static List<Games> getAppList(){
        String url = "https://api.steampowered.com/ISteamApps/GetAppList/v1";
        String content = openURL(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        List<Games> games = new ArrayList<>();
        
        JsonObject applist = root.getJsonObject("applist");
        JsonObject apps = applist.getJsonObject("apps");
        JsonArray appArray = apps.getJsonArray("app");
        for (int i = 0; i < appArray.size(); i++) {
            JsonObject list_app = appArray.getJsonObject(i);
            int appid = list_app.getInt("appid");
            String name_game = list_app.getString("name");
            games.add(new Games(appid, name_game));
        }
        return games;
    }
    
    public static List<Games> getNewGames(){
        List<Games> games = new ArrayList<>();
        
        List<Games> listOfGames = getAppList();
        
        Random random = new Random();
        int random_number = 0, number_of_game = 3;
        int i=0;
        int length = listOfGames.size()-1;
        do{
            random_number = random.nextInt(length);
            if(random_number != 5 && random_number != 7 && random_number != 8){
            Games get_game = getShortAppDetails( String.valueOf( listOfGames.get(random_number).getAppid() ) );
                
                if(get_game!=null){
                    games.add(get_game);
                    i++;
                }
                
            }
        }while(i<number_of_game);
        
        return games;
    }
    
    public static Games getShortAppDetails(String appid){
        
        // http://jsonviewer.stack.hu/#http://store.steampowered.com/api/appdetails?appids=730    em caso de n funcionar no mack
        
        String url = "http://store.steampowered.com/api/appdetails?appids="+appid+"&l=br";
        String tags = "";
        String content = openURL(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        JsonObject idgame = root.getJsonObject(appid);
        boolean result = idgame.getBoolean("success");
        if(!result){
//            System.out.println(idgame + "= Não foi possível obter os dados do game");
            return null;
        }else{
            JsonObject data = idgame.getJsonObject("data");
            String type = data.getString("type");
            if(!type.equals("movie") && !type.equals("dlc") && !type.equals("advertising") && !type.equals("episode") && !type.equals("hardware")){
                
                String name = data.getString("name");
                String description = data.getString("short_description");
                if(description.equals("")){
                    String detailed_description = data.getString("detailed_description");
                    description = detailed_description;
                }else if(description.equals("")){
                    description = "Não existe descrição no momento.";
                }

                String url_game = "http://store.steampowered.com/app/"+appid+"/";
                long appid_long = Long.parseLong(appid);
                
                LogGames log = new LogGames(); log.addStringTest("ERRO: "+appid+"\n");
                
                JsonArray categories = data.getJsonArray("categories");
                if(!(categories == null)){
                    JsonObject categoriesObj = categories.getJsonObject(0);
                    tags = tags.concat(categoriesObj.getString("description"));
                    for (int i = 1; i < categories.size(); i++) {
                        categoriesObj = categories.getJsonObject(i);
                        tags = tags.concat(", "+categoriesObj.getString("description"));
                    }

                }else{
                    tags = "";
                }
                return new Games(appid_long, name, description, tags, url_game, true);
            }else
                return null;
        }
    }
    
    public static Games getAppDetails(String appid){
//        String url = "http://store.steampowered.com/api/appdetails?appids=730";
        String url = "http://store.steampowered.com/api/appdetails?appids="+appid+"&l=br";
        
//        List categorieList = new ArrayList<>();
        String tags = "";

        String content = openURL(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        JsonObject idgame = root.getJsonObject(appid);
        boolean result = idgame.getBoolean("success");
        if(!result){
            System.out.println("Não foi possível obter os dados do game");
            return null;
        }else{
            JsonObject data = idgame.getJsonObject("data");
            String name = data.getString("name");
            boolean is_free = data.getBoolean("is_free");
            String detailed_description = data.getString("detailed_description");
//            String header_img_url = data.getString("header_img");
            
            int price = 0;
//            if(!is_free){
//                JsonObject price_overview = data.getJsonObject("price_overview");
//                price = price_overview.getInt("initial");
//            }
            
            JsonArray categories = data.getJsonArray("categories");
            for (int i = 0; i < categories.size(); i++) {
                JsonObject categoriesObj = categories.getJsonObject(i);
                String description = categoriesObj.getString("description");
//                categorieList.add(description);
                tags = tags.concat(description+ ", ");
            }
            
            JsonArray genres = data.getJsonArray("genres");
            for (int i = 0; i < genres.size(); i++) {
                JsonObject genresObj = genres.getJsonObject(i);
                String description = genresObj.getString("description");
//                categorieList.add(description);
                if(i+1<genres.size())
                    tags = tags.concat(description+", ");
                else
                    tags = tags.concat(description);
            }
            String url_game = "http://store.steampowered.com/app/"+appid+"/";
            long appid_long = Long.parseLong(appid);
            return new Games(appid_long, name, detailed_description, tags, url_game, price, is_free);
        }
    }
    
    
    public static void getRecentlyPlayedGames(String id){
//        String id = "76561198056805863";
        String url = "https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1/?key="+api_key+"&format=json&steamid="+id;
        
//        ArrayList<Games> gamesArray = new ArrayList<>();
        ArrayList gamesArray = new ArrayList<>();
        
        String content = openURL(url);
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        JsonObject response = root.getJsonObject("response");
        int total_count_games = response.getInt("total_count");
        
        JsonArray games = response.getJsonArray("games");
        for (int i = 0; i < total_count_games; i++) {
            JsonObject gamesObject = games.getJsonObject(i);
            String name = gamesObject.getString("name");
            int appid = gamesObject.getInt("appid");
//            gamesArray.add(new GameTemp(appid,name));
            
        }
        //funcionando
        
    }
    
    

    
}