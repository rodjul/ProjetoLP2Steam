/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.dao;

import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.Usersite;
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
public class UsersiteDAO implements GenericDAO<Usersite>{
    
    @PersistenceContext(unitName = "SteamProjeto-ejbPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @Override
    public void insert(Usersite e) {
        em.persist(e);
    }

    @Override
    public List<Usersite> findAll() {
        Query query = em.createNamedQuery("Userinfo.findAll");
        return (List<Usersite>)query.getResultList();
    }
    
    @Override
    public Usersite findById(long id) {
        return em.find(Usersite.class, id);
    }
    
    public Usersite findByUsername(String username){
        Query query = em.createNamedQuery("Usersite.findByUsername").setParameter("username", username);
        Object object = null;
        try{
            object = query.getSingleResult();
            return (Usersite)object;
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public Usersite findByEmail (String email){
        Query query = em.createNamedQuery("Userinfo.findByEmail").setParameter("email", email);
        Object object = null;
        try{
            object = query.getSingleResult();
            return ((Userinfo)object).getUsersite();
        }catch(NoResultException ex){
            return null;
        }
    }
    
    

    @Override
    public void modify(Usersite e) {
        em.merge(e);
    }

    @Override
    public void remove(Usersite e) {
        em.merge(e);
        em.remove(e);
    }
    
}
