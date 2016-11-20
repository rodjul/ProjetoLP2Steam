/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.dao;

import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.Analises;
import com.br.lp3.model.entities.Games;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author RodrigoPC
 */
@Stateful
@LocalBean
public class AnalisesDAO implements GenericDAO<Analises>{
    
    @PersistenceContext(unitName = "SteamProjeto-ejbPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @Override
    public void insert(Analises e) {
        em.persist(e);
    }

    @Override
    public List<Analises> findAll() {
        Query query = em.createNamedQuery("Userinfo.findAll");
        return (List<Analises>)query.getResultList();
    }
    
    @Override
    public Analises findById(long id) {
        return em.find(Analises.class, id);
    }
    
    public List<Analises> findAllById(Games fkGame){
        return em.createNamedQuery("Analises.findByFkGames").setParameter("fkGames", fkGame).getResultList();
    }
    public List<Analises> findAllByFkUser(Userinfo user){
        return em.createNamedQuery("Analises.findByFkUser").setParameter("fkUserinfo", user).getResultList();
    }
    
    public List<Analises> findAllByFkGameUser(Games fkGames, Userinfo user){
        return em.createNamedQuery("Analises.findByFkGameFkUser").setParameter("fkGames", fkGames).setParameter("fkUserinfo", user).getResultList();
    }
    
    @Override
    public void modify(Analises e) {
        em.merge(e);
    }

    @Override
    public void remove(Analises e) {
        em.remove(em.merge(e));
    }
    
}
