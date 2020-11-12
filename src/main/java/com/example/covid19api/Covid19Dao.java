package com.example.covid19api;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class Covid19Dao {
    @PersistenceContext
    private EntityManager entityManager;


    public void create(Covidcase covidcase){
        entityManager.persist(covidcase);
        return;
    }
    public Covidcase getCaseId(int id){
        return entityManager.find(Covidcase.class,id);
    }

    public Covidcase deleteCase(int id){
        Covidcase covidcase = entityManager.find(Covidcase.class, id);
        entityManager.remove(covidcase);
        return covidcase;
    }

    public int getMaxID() {
        Query q = entityManager.createNativeQuery("Select max(id) from covidcases");
        int maxid = (int) q.getSingleResult();
        return maxid;
    }
}
