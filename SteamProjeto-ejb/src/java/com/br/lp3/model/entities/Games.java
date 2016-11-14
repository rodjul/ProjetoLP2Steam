/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31597947
 */
@Entity
@Table(name = "GAMES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Games.findAll", query = "SELECT g FROM Games g"),
    @NamedQuery(name = "Games.findByIdGames", query = "SELECT g FROM Games g WHERE g.idGames = :idGames"),
    @NamedQuery(name = "Games.findByNomeGame", query = "SELECT g FROM Games g WHERE g.nomeGame = :nomeGame"),
    @NamedQuery(name = "Games.findByAppid", query = "SELECT g FROM Games g WHERE g.appid = :appid"),
    @NamedQuery(name = "Games.findByDescricao", query = "SELECT g FROM Games g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "Games.findByTags", query = "SELECT g FROM Games g WHERE g.tags = :tags"),
    @NamedQuery(name = "Games.findByUrlSteam", query = "SELECT g FROM Games g WHERE g.urlSteam = :urlSteam"),
    @NamedQuery(name = "Games.findByPrice", query = "SELECT g FROM Games g WHERE g.price = :price"),
    @NamedQuery(name = "Games.findByPesquisa", query = "SELECT g FROM Games g WHERE g.pesquisa = :pesquisa"),
    @NamedQuery(name = "Games.findByPesquisaAndUserInfo", query = "SELECT g FROM Games g WHERE g.pesquisa = :pesquisa AND g.fkUserinfo = :fkUserinfo"),
    @NamedQuery(name = "Games.findByUserInfo", query = "SELECT g FROM Games g WHERE g.fkUserinfo = :fkUserinfo"),
    @NamedQuery(name = "Games.findByFree", query = "SELECT g FROM Games g WHERE g.free = :free")})
public class Games implements Serializable {
    @Column(name = "APPID")
    private long appid;
    @Column(name = "PRICE")
    private Integer price;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GAMES")
    private Long idGames;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOME_GAME")
    private String nomeGame;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 20000)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Size(max = 500)
    @Column(name = "TAGS")
    private String tags;
    @Size(max = 50)
    @Column(name = "URL_STEAM")
    private String urlSteam;
    @Column(name = "PESQUISA")
    private Boolean pesquisa;
    @Column(name = "FREE")
    private Boolean free;
    @JoinColumn(name = "FK_USERINFO", referencedColumnName = "ID_USERINFO")
    @ManyToOne
    private Userinfo fkUserinfo;
    @OneToMany(mappedBy = "fkGames")
    private List<GamesAnalise> gamesAnaliseList;
    @OneToMany(mappedBy = "fkGames")
    private List<Analises> analisesList;

    public Games() {
    }

    public Games(Long idGames) {
        this.idGames = idGames;
    }
    
    public Games(long appid){
        this.appid = appid;
    }
    
    public Games(long appid, String name){
        this.appid = appid;
        this.nomeGame = name;
    }
    
    public Games(long appid, String nomeGame, String description, String tags, String url_game){
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = description;
        this.tags = tags;
        this.urlSteam = url_game;
    }
    public Games(long appid, String nomeGame, String description, String tags, String url_game, Boolean pesquisa){
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = description;
        this.tags = tags;
        this.urlSteam = url_game;
        this.pesquisa = pesquisa;
    }
    public Games(long appid, String nomeGame, String description, String tags, String url_game, Userinfo fkuser){
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = description;
        this.tags = tags;
        this.urlSteam = url_game;
        this.fkUserinfo = fkuser;
    }
    public Games(long appid, String nomeGame, String description, String tags, String url_game, Userinfo fkuser, Boolean pesquisa){
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = description;
        this.tags = tags;
        this.urlSteam = url_game;
        this.fkUserinfo = fkuser;
        this.pesquisa = pesquisa;
    }
    public Games(Games e,Userinfo fkuser, Boolean pesquisa){
        this.appid = e.getAppid();
        this.nomeGame = e.getNomeGame();
        this.descricao = e.getDescricao();
        this.tags = e.getTags();
        this.urlSteam = e.getUrlSteam();
        this.fkUserinfo = fkuser;
    }

    public Games(long appid, String nomeGame, String descricao, String tags, String urlSteam, int price, Boolean free){
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = descricao;
        this.tags = tags;
        this.urlSteam = urlSteam;
        this.price = price;
        this.free = free;
    }
    
    

    public Games(Long idGames, String nomeGame, String descricao) {
        this.idGames = idGames;
        this.nomeGame = nomeGame;
        this.descricao = descricao;
    }

    public Long getIdGames() {
        return idGames;
    }

    public void setIdGames(Long idGames) {
        this.idGames = idGames;
    }

    public String getNomeGame() {
        return nomeGame;
    }

    public void setNomeGame(String nomeGame) {
        this.nomeGame = nomeGame;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUrlSteam() {
        return urlSteam;
    }

    public void setUrlSteam(String urlSteam) {
        this.urlSteam = urlSteam;
    }


    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Userinfo getFkUserinfo() {
        return fkUserinfo;
    }

    public void setFkUserinfo(Userinfo fkUserinfo) {
        this.fkUserinfo = fkUserinfo;
    }

    public Boolean getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    

    @XmlTransient
    public List<GamesAnalise> getGamesAnaliseList() {
        return gamesAnaliseList;
    }

    public void setGamesAnaliseList(List<GamesAnalise> gamesAnaliseList) {
        this.gamesAnaliseList = gamesAnaliseList;
    }

    @XmlTransient
    public List<Analises> getAnalisesList() {
        return analisesList;
    }

    public void setAnalisesList(List<Analises> analisesList) {
        this.analisesList = analisesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGames != null ? idGames.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Games)) {
            return false;
        }
        Games other = (Games) object;
        if ((this.idGames == null && other.idGames != null) || (this.idGames != null && !this.idGames.equals(other.idGames))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.Games[ idGames=" + idGames + " ]";
    }

    public long getAppid() {
        return appid;
    }

    public void setAppid(long appid) {
        this.appid = appid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
