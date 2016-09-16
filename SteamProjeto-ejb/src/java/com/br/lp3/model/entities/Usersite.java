/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RodrigoPC
 */
@Entity
@Table(name = "USERSITE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usersite.findAll", query = "SELECT u FROM Usersite u"),
    @NamedQuery(name = "Usersite.findByIdUsersite", query = "SELECT u FROM Usersite u WHERE u.idUsersite = :idUsersite"),
    @NamedQuery(name = "Usersite.findByUsername", query = "SELECT u FROM Usersite u WHERE u.username = :username"),
    @NamedQuery(name = "Usersite.findByPassword", query = "SELECT u FROM Usersite u WHERE u.password = :password")})
public class Usersite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USERSITE")
    private Long idUsersite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PASSWORD")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usersite")
    private Userinfo userinfo;

    public Usersite() {
    }

    public Usersite(Long idUsersite) {
        this.idUsersite = idUsersite;
    }

    public Usersite(Long idUsersite, String username, String password) {
        this.idUsersite = idUsersite;
        this.username = username;
        this.password = password;
    }

    public Long getIdUsersite() {
        return idUsersite;
    }

    public void setIdUsersite(Long idUsersite) {
        this.idUsersite = idUsersite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsersite != null ? idUsersite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usersite)) {
            return false;
        }
        Usersite other = (Usersite) object;
        if ((this.idUsersite == null && other.idUsersite != null) || (this.idUsersite != null && !this.idUsersite.equals(other.idUsersite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.Usersite[ idUsersite=" + idUsersite + " ]";
    }
    
}
