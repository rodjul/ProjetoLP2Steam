/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 31597947
 */
@Entity
@Table(name = "GAMES_ANALISE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GamesAnalise.findAll", query = "SELECT g FROM GamesAnalise g"),
    @NamedQuery(name = "GamesAnalise.findByIdGamesAnalise", query = "SELECT g FROM GamesAnalise g WHERE g.idGamesAnalise = :idGamesAnalise")})
public class GamesAnalise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GAMES_ANALISE")
    private Long idGamesAnalise;
    @JoinColumn(name = "FK_GAME_ANALISES", referencedColumnName = "ID_GAME_ANALISES")
    @ManyToOne
    private Analises fkGameAnalises;
    @JoinColumn(name = "FK_GAMES", referencedColumnName = "ID_GAMES")
    @ManyToOne
    private Games fkGames;

    public GamesAnalise() {
    }

    public GamesAnalise(Long idGamesAnalise) {
        this.idGamesAnalise = idGamesAnalise;
    }

    public Long getIdGamesAnalise() {
        return idGamesAnalise;
    }

    public void setIdGamesAnalise(Long idGamesAnalise) {
        this.idGamesAnalise = idGamesAnalise;
    }

    public Analises getFkGameAnalises() {
        return fkGameAnalises;
    }

    public void setFkGameAnalises(Analises fkGameAnalises) {
        this.fkGameAnalises = fkGameAnalises;
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
        hash += (idGamesAnalise != null ? idGamesAnalise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GamesAnalise)) {
            return false;
        }
        GamesAnalise other = (GamesAnalise) object;
        if ((this.idGamesAnalise == null && other.idGamesAnalise != null) || (this.idGamesAnalise != null && !this.idGamesAnalise.equals(other.idGamesAnalise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.GamesAnalise[ idGamesAnalise=" + idGamesAnalise + " ]";
    }
    
}
