/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.dao;

import com.br.lp3.model.entities.Userinfo;
import com.br.lp3.model.entities.Imagem;
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
public class ImagemDAO implements GenericDAO<Imagem>{
    
    @PersistenceContext(unitName = "SteamProjeto-ejbPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    @Override
    public void insert(Imagem e) {
        em.persist(e);
    }

    @Override
    public List<Imagem> findAll() {
        Query query = em.createNamedQuery("Imagem.findAll");
        return (List<Imagem>)query.getResultList();
    }
    
    @Override
    public Imagem findById(long id) {
        return em.find(Imagem.class, id);
    }
    
    

    @Override
    public void modify(Imagem e) {
        em.merge(e);
    }

    @Override
    public void remove(Imagem e) {
        em.merge(e);
        em.remove(e);
    }
    
}
