/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Games.findByDescricao", query = "SELECT g FROM Games g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "Games.findByTags", query = "SELECT g FROM Games g WHERE g.tags = :tags"),
    @NamedQuery(name = "Games.findByUrlSteam", query = "SELECT g FROM Games g WHERE g.urlSteam = :urlSteam")})
public class Games implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GAMES")
    private Long idGames;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOME_GAME")
    private String nomeGame;
    @Column(name = "APPID")
    private Long appid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Size(max = 100)
    @Column(name = "TAGS")
    private String tags;
    @Size(max = 50)
    @Column(name = "URL_STEAM")
    private String urlSteam;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "FREE")
    private boolean free;
    @JoinTable(name = "GAMES_ANALISE", joinColumns = {
        @JoinColumn(name = "ID_GAMES", referencedColumnName = "ID_GAMES")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_GAME_ANALISES", referencedColumnName = "ID_GAME_ANALISES")})
    @ManyToMany
    private List<Analises> analisesList;
    @JoinColumn(name = "ID_USERINFO", referencedColumnName = "ID_USERINFO")
    @ManyToOne
    private Userinfo idUserinfo;
    @OneToMany(mappedBy = "idGames")
    private List<Analises> analisesList1;

    public Games() {
    }

    public Games(Long idGames) {
        this.idGames = idGames;
    }
    
    public Games(long appid){
        this.appid = appid;
    }
    
    public Games(Long idGames,String nomeGame){
        this.idGames = idGames;
        this.nomeGame = nomeGame;
    }

    public Games(Long idGames, String nomeGame, String descricao) {
        this.idGames = idGames;
        this.nomeGame = nomeGame;
        this.descricao = descricao;
    }
    
    public Games(Long idGame, String nomeGame, String description, String tags){
        this.idGames = idGame;
        this.nomeGame = nomeGame;
        this.descricao = description;
        this.tags = tags;
    }

    public Games(Long idGames, String nomeGame, String descricao, String tags, String urlSteam, int price, boolean free) {
        this.idGames = idGames;
        this.nomeGame = nomeGame;
        this.descricao = descricao;
        this.tags = tags;
        this.urlSteam = urlSteam;
        this.price = price;
        this.free = free;
    }
    public Games(long appid, String nomeGame, String descricao, String tags, String urlSteam, int price, boolean free) {
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = descricao;
        this.tags = tags;
        this.urlSteam = urlSteam;
        this.price = price;
        this.free = free;
    }
    public Games(long appid, String nomeGame, String descricao, String tags, String urlSteam) {
        this.appid = appid;
        this.nomeGame = nomeGame;
        this.descricao = descricao;
        this.tags = tags;
        this.urlSteam = urlSteam;
    }

    public Games(long appid, String nomeGame){
        this.appid = appid;
        this.nomeGame = nomeGame;
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

    public long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    
    
    @XmlTransient
    public List<Analises> getAnalisesList() {
        return analisesList;
    }

    public void setAnalisesList(List<Analises> analisesList) {
        this.analisesList = analisesList;
    }

    public Userinfo getIdUserinfo() {
        return idUserinfo;
    }

    public void setIdUserinfo(Userinfo idUserinfo) {
        this.idUserinfo = idUserinfo;
    }

    @XmlTransient
    public List<Analises> getAnalisesList1() {
        return analisesList1;
    }

    public void setAnalisesList1(List<Analises> analisesList1) {
        this.analisesList1 = analisesList1;
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
    
}
