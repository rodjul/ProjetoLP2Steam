/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.dao;

import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.GamesAnalise;
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
public class GamesAnaliseDAO implements GenericDAO<GamesAnalise>{
    
    @PersistenceContext(unitName = "SteamProjeto-ejbPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @Override
    public void insert(GamesAnalise e) {
        em.persist(e);
    }

    @Override
    public List<GamesAnalise> findAll() {
        Query query = em.createNamedQuery("GamesAnalise.findAll");
        return (List<GamesAnalise>)query.getResultList();
    }
    
    @Override
    public GamesAnalise findById(long id) {
        return em.find(GamesAnalise.class, id);
    }

//    public List<GameAnalise> findAllByGame(long fkGame){
        
//    }
    
    @Override
    public void modify(GamesAnalise e) {
        em.merge(e);
    }

    @Override
    public void remove(GamesAnalise e) {
        em.remove(em.merge(e));
    }
    
}
