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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31597947
 */
@Entity
@Table(name = "ANALISES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analises.findAll", query = "SELECT a FROM Analises a"),
    @NamedQuery(name = "Analises.findByIdGameAnalises", query = "SELECT a FROM Analises a WHERE a.idGameAnalises = :idGameAnalises"),
    @NamedQuery(name = "Analises.findByAprovacao", query = "SELECT a FROM Analises a WHERE a.aprovacao = :aprovacao"),
    @NamedQuery(name = "Analises.findByHoras", query = "SELECT a FROM Analises a WHERE a.horas = :horas"),
    @NamedQuery(name = "Analises.findByAnalise", query = "SELECT a FROM Analises a WHERE a.analise = :analise")})
public class Analises implements Serializable {
    @JoinColumn(name = "FK_USERINFO", referencedColumnName = "ID_USERINFO")
    @ManyToOne
    private Userinfo fkUserinfo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GAME_ANALISES")
    private Long idGameAnalises;
    @Size(max = 12)
    @Column(name = "APROVACAO")
    private String aprovacao;
    @Column(name = "HORAS")
    private BigInteger horas;
    @Size(max = 300)
    @Column(name = "ANALISE")
    private String analise;
    @OneToMany(mappedBy = "fkGameAnalises")
    private List<GamesAnalise> gamesAnaliseList;
    @JoinColumn(name = "FK_GAMES", referencedColumnName = "ID_GAMES")
    @ManyToOne
    private Games fkGames;

    public Analises() {
    }

    public Analises(Userinfo fkUserinfo, String aprovacao, String analise, Games fkGames) {
        this.fkUserinfo = fkUserinfo;
        this.aprovacao = aprovacao;
        this.analise = analise;
        this.fkGames = fkGames;
    }
    
    

    public Analises(Long idGameAnalises) {
        this.idGameAnalises = idGameAnalises;
    }

    public Long getIdGameAnalises() {
        return idGameAnalises;
    }

    public void setIdGameAnalises(Long idGameAnalises) {
        this.idGameAnalises = idGameAnalises;
    }

    public String getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(String aprovacao) {
        this.aprovacao = aprovacao;
    }

    public BigInteger getHoras() {
        return horas;
    }

    public void setHoras(BigInteger horas) {
        this.horas = horas;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    @XmlTransient
    public List<GamesAnalise> getGamesAnaliseList() {
        return gamesAnaliseList;
    }

    public void setGamesAnaliseList(List<GamesAnalise> gamesAnaliseList) {
        this.gamesAnaliseList = gamesAnaliseList;
    }

    public Games getFkGames() {
        return fkGames;
    }

    public void setFkGames(Games fkGames) {
        this.fkGames = fkGames;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGameAnalises != null ? idGameAnalises.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analises)) {
            return false;
        }
        Analises other = (Analises) object;
        if ((this.idGameAnalises == null && other.idGameAnalises != null) || (this.idGameAnalises != null && !this.idGameAnalises.equals(other.idGameAnalises))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.Analises[ idGameAnalises=" + idGameAnalises + " ]";
    }

    public Userinfo getFkUserinfo() {
        return fkUserinfo;
    }

    public void setFkUserinfo(Userinfo fkUserinfo) {
        this.fkUserinfo = fkUserinfo;
    }
    
}
