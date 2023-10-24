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
import logica.Compra;
import logica.Turista;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author natil
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
       //la vacia
    public CompraJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turista turista = compra.getTurista();
            if (turista != null) {
                turista = em.getReference(turista.getClass(), turista.getNickname());
                compra.setTurista(turista);
            }
            em.persist(compra);
            if (turista != null) {
                turista.getListaCompras().add(compra);
                turista = em.merge(turista);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getId());
            Turista turistaOld = persistentCompra.getTurista();
            Turista turistaNew = compra.getTurista();
            if (turistaNew != null) {
                turistaNew = em.getReference(turistaNew.getClass(), turistaNew.getNickname());
                compra.setTurista(turistaNew);
            }
            compra = em.merge(compra);
            if (turistaOld != null && !turistaOld.equals(turistaNew)) {
                turistaOld.getListaCompras().remove(compra);
                turistaOld = em.merge(turistaOld);
            }
            if (turistaNew != null && !turistaNew.equals(turistaOld)) {
                turistaNew.getListaCompras().add(compra);
                turistaNew = em.merge(turistaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = compra.getId();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            Turista turista = compra.getTurista();
            if (turista != null) {
                turista.getListaCompras().remove(compra);
                turista = em.merge(turista);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
