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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31597947
 */
@Entity
@Table(name = "USERINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinfo.findAll", query = "SELECT u FROM Userinfo u"),
    @NamedQuery(name = "Userinfo.findByIdUserinfo", query = "SELECT u FROM Userinfo u WHERE u.idUserinfo = :idUserinfo"),
    @NamedQuery(name = "Userinfo.findByFirstname", query = "SELECT u FROM Userinfo u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userinfo.findByEmail", query = "SELECT u FROM Userinfo u WHERE u.email = :email"),
    @NamedQuery(name = "Userinfo.findByUrlsteam", query = "SELECT u FROM Userinfo u WHERE u.urlsteam = :urlsteam")})
public class Userinfo implements Serializable {
    @OneToMany(mappedBy = "fkUserinfo")
    private List<Analises> analisesList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USERINFO")
    private Long idUserinfo;
    @Size(max = 50)
    @Column(name = "FIRSTNAME")
    private String firstname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "URLSTEAM")
    private String urlsteam;
    @Lob
    @Column(name = "PICTURE")
    private Serializable picture;
    @JoinColumn(name = "ID_USERINFO", referencedColumnName = "ID_USERSITE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usersite usersite;
    @OneToMany(mappedBy = "fkUserinfo")
    private List<Games> gamesList;

    public Userinfo() {
    }

    public Userinfo(Long idUserinfo) {
        this.idUserinfo = idUserinfo;
    }

    public Long getIdUserinfo() {
        return idUserinfo;
    }

    public void setIdUserinfo(Long idUserinfo) {
        this.idUserinfo = idUserinfo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlsteam() {
        return urlsteam;
    }

    public void setUrlsteam(String urlsteam) {
        this.urlsteam = urlsteam;
    }

    public Serializable getPicture() {
        return picture;
    }

    public void setPicture(Serializable picture) {
        this.picture = picture;
    }

    public Usersite getUsersite() {
        return usersite;
    }

    public void setUsersite(Usersite usersite) {
        this.usersite = usersite;
    }

    @XmlTransient
    public List<Games> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Games> gamesList) {
        this.gamesList = gamesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserinfo != null ? idUserinfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userinfo)) {
            return false;
        }
        Userinfo other = (Userinfo) object;
        if ((this.idUserinfo == null && other.idUserinfo != null) || (this.idUserinfo != null && !this.idUserinfo.equals(other.idUserinfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.Userinfo[ idUserinfo=" + idUserinfo + " ]";
    }

    @XmlTransient
    public List<Analises> getAnalisesList() {
        return analisesList;
    }

    public void setAnalisesList(List<Analises> analisesList) {
        this.analisesList = analisesList;
    }
    
}
