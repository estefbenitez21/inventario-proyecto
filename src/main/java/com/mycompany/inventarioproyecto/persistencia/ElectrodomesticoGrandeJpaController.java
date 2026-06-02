
package com.mycompany.inventarioproyecto.persistencia;

import com.mycompany.inventarioproyecto.logica.ElectrodomesticoGrande;
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
public class ElectrodomesticoGrandeJpaController implements Serializable {

    public ElectrodomesticoGrandeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    //constructo
    public ElectrodomesticoGrandeJpaController(){
        emf = Persistence.createEntityManagerFactory("inventarioJPA");
    
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ElectrodomesticoGrande electrodomesticoGrande) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(electrodomesticoGrande);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findElectrodomesticoGrande(electrodomesticoGrande.getCodigo()) != null) {
                throw new PreexistingEntityException("ElectrodomesticoGrande " + electrodomesticoGrande + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ElectrodomesticoGrande electrodomesticoGrande) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            electrodomesticoGrande = em.merge(electrodomesticoGrande);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = electrodomesticoGrande.getCodigo();
                if (findElectrodomesticoGrande(id) == null) {
                    throw new NonexistentEntityException("The electrodomesticoGrande with id " + id + " no longer exists.");
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
            ElectrodomesticoGrande electrodomesticoGrande;
            try {
                electrodomesticoGrande = em.getReference(ElectrodomesticoGrande.class, id);
                electrodomesticoGrande.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The electrodomesticoGrande with id " + id + " no longer exists.", enfe);
            }
            em.remove(electrodomesticoGrande);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ElectrodomesticoGrande> findElectrodomesticoGrandeEntities() {
        return findElectrodomesticoGrandeEntities(true, -1, -1);
    }

    public List<ElectrodomesticoGrande> findElectrodomesticoGrandeEntities(int maxResults, int firstResult) {
        return findElectrodomesticoGrandeEntities(false, maxResults, firstResult);
    }

    private List<ElectrodomesticoGrande> findElectrodomesticoGrandeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ElectrodomesticoGrande.class));
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

    public ElectrodomesticoGrande findElectrodomesticoGrande(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ElectrodomesticoGrande.class, id);
        } finally {
            em.close();
        }
    }

    public int getElectrodomesticoGrandeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ElectrodomesticoGrande> rt = cq.from(ElectrodomesticoGrande.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    ///-------------------MIS FUNCIONES--------------
    ///----------------------------------------------
    ///
    ///
    ///BUSCAR POR MARCA
    //
    public List<ElectrodomesticoGrande> buscarPorMarca(String marca){

        EntityManager em = getEntityManager();

        try{
            
            return em.createQuery(
                "SELECT e FROM ElectrodomesticoGrande e WHERE LOWER(e.marca) = LOWER(:marca)",
                ElectrodomesticoGrande.class)
                .setParameter("marca", marca)
                .getResultList();

        }finally{

            em.close();
        }
    }
}
