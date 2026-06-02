/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventarioproyecto.persistencia;

import com.mycompany.inventarioproyecto.logica.MovimientoInventario;
import com.mycompany.inventarioproyecto.persistencia.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Estefany
 */
public class MovimientoInventarioJpaController implements Serializable {

    public MovimientoInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
  //constructor
    public MovimientoInventarioJpaController(){
        
        emf = Persistence.createEntityManagerFactory("inventarioJPA");
    
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MovimientoInventario movimientoInventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(movimientoInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MovimientoInventario movimientoInventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            movimientoInventario = em.merge(movimientoInventario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = movimientoInventario.getId();
                if (findMovimientoInventario(id) == null) {
                    throw new NonexistentEntityException("The movimientoInventario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovimientoInventario movimientoInventario;
            try {
                movimientoInventario = em.getReference(MovimientoInventario.class, id);
                movimientoInventario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoInventario with id " + id + " no longer exists.", enfe);
            }
            em.remove(movimientoInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovimientoInventario> findMovimientoInventarioEntities() {
        return findMovimientoInventarioEntities(true, -1, -1);
    }

    public List<MovimientoInventario> findMovimientoInventarioEntities(int maxResults, int firstResult) {
        return findMovimientoInventarioEntities(false, maxResults, firstResult);
    }

    private List<MovimientoInventario> findMovimientoInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MovimientoInventario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MovimientoInventario findMovimientoInventario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MovimientoInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MovimientoInventario> rt = cq.from(MovimientoInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
