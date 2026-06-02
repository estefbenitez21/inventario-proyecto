/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventarioproyecto.persistencia;

import com.mycompany.inventarioproyecto.logica.ElectrodomesticoPequeno;
import com.mycompany.inventarioproyecto.persistencia.exceptions.NonexistentEntityException;
import com.mycompany.inventarioproyecto.persistencia.exceptions.PreexistingEntityException;
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
public class ElectrodomesticoPequenoJpaController implements Serializable {

    public ElectrodomesticoPequenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
      //constructo
    public ElectrodomesticoPequenoJpaController(){
        emf = Persistence.createEntityManagerFactory("inventarioJPA");
    
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ElectrodomesticoPequeno electrodomesticoPequeno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(electrodomesticoPequeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findElectrodomesticoPequeno(electrodomesticoPequeno.getCodigo()) != null) {
                throw new PreexistingEntityException("ElectrodomesticoPequeno " + electrodomesticoPequeno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ElectrodomesticoPequeno electrodomesticoPequeno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            electrodomesticoPequeno = em.merge(electrodomesticoPequeno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = electrodomesticoPequeno.getCodigo();
                if (findElectrodomesticoPequeno(id) == null) {
                    throw new NonexistentEntityException("The electrodomesticoPequeno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ElectrodomesticoPequeno electrodomesticoPequeno;
            try {
                electrodomesticoPequeno = em.getReference(ElectrodomesticoPequeno.class, id);
                electrodomesticoPequeno.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The electrodomesticoPequeno with id " + id + " no longer exists.", enfe);
            }
            em.remove(electrodomesticoPequeno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ElectrodomesticoPequeno> findElectrodomesticoPequenoEntities() {
        return findElectrodomesticoPequenoEntities(true, -1, -1);
    }

    public List<ElectrodomesticoPequeno> findElectrodomesticoPequenoEntities(int maxResults, int firstResult) {
        return findElectrodomesticoPequenoEntities(false, maxResults, firstResult);
    }

    private List<ElectrodomesticoPequeno> findElectrodomesticoPequenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ElectrodomesticoPequeno.class));
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

    public ElectrodomesticoPequeno findElectrodomesticoPequeno(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ElectrodomesticoPequeno.class, id);
        } finally {
            em.close();
        }
    }

    public int getElectrodomesticoPequenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ElectrodomesticoPequeno> rt = cq.from(ElectrodomesticoPequeno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    ElectrodomesticoPequeno findElectrodomesticoGrande(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    ///-------------------MIS FUNCIONES--------------
    ///----------------------------------------------
    ///
    ///
    ///BUSCAR POR MARCA
    ///
    public List<ElectrodomesticoPequeno> buscarPorMarca(String marca){

        EntityManager em = getEntityManager();

        try{

            return em.createQuery(
                "SELECT e FROM ElectrodomesticoPequeno e WHERE LOWER(e.marca) = LOWER(:marca)",
                ElectrodomesticoPequeno.class)
                .setParameter("marca", marca)
                .getResultList();

        }finally{

            em.close();
        }
    }
}
