/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.dao;

import com.br.lp3.model.entities.Userinfo;
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
public class GamesDAO implements GenericDAO<Games>{
    
    @PersistenceContext(unitName = "SteamProjeto-ejbPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @Override
    public void insert(Games e) {
        em.persist(e);
    }

    @Override
    public List<Games> findAll() {
        Query query = em.createNamedQuery("Userinfo.findAll");
        return (List<Games>)query.getResultList();
    }
    
    public List<Games> findByUser(Userinfo ui){
        return em.createNamedQuery("Games.findByUser", Games.class).setParameter("fkUserinfo", ui).getResultList();
    }
    
    @Override
    public Games findById(long id) {
        return em.find(Games.class, id);
    }

    @Override
    public void modify(Games e) {
        em.merge(e);
    }

    @Override
    public void remove(Games e) {
        em.merge(e);
        em.remove(e);
    }
    
}
