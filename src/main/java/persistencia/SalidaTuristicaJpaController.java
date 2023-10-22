/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.SalidaTuristica;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author natil
 */
public class SalidaTuristicaJpaController implements Serializable {

    public SalidaTuristicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public SalidaTuristicaJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalidaTuristica salidaTuristica) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(salidaTuristica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSalidaTuristica(salidaTuristica.getNombre()) != null) {
                throw new PreexistingEntityException("SalidaTuristica " + salidaTuristica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalidaTuristica salidaTuristica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            salidaTuristica = em.merge(salidaTuristica);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = salidaTuristica.getNombre();
                if (findSalidaTuristica(id) == null) {
                    throw new NonexistentEntityException("The salidaTuristica with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<SalidaTuristica> findSalidaTuristicaEntities() {
        return findSalidaTuristicaEntities(true, -1, -1);
    }

    public List<SalidaTuristica> findSalidaTuristicaEntities(int maxResults, int firstResult) {
        return findSalidaTuristicaEntities(false, maxResults, firstResult);
    }

    private List<SalidaTuristica> findSalidaTuristicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalidaTuristica.class));
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

    public SalidaTuristica findSalidaTuristica(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalidaTuristica.class, id);
        } finally {
            em.close();
        }
    }



    
}
