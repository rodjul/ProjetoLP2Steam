/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.dao;

import java.util.List;

/**
 *
 * @author RodrigoPC
 */
public interface GenericDAO<E> {
    
    public void insert(E e);
    public List<E> findAll();
    public E findById(long id);
    public void modify(E e);
    public void remove(E e);
    
}
