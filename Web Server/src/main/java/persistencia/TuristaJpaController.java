/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Inscripcion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Turista;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author carlo
 */
public class TuristaJpaController implements Serializable {

    public TuristaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public TuristaJpaController() {
       emf = Persistence.createEntityManagerFactory("Lab01PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turista turista) throws PreexistingEntityException, Exception {
        if (turista.getListaInscripcion() == null) {
            turista.setListaInscripcion(new ArrayList<Inscripcion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Inscripcion> attachedListaInscripcion = new ArrayList<Inscripcion>();
            for (Inscripcion listaInscripcionInscripcionToAttach : turista.getListaInscripcion()) {
                listaInscripcionInscripcionToAttach = em.getReference(listaInscripcionInscripcionToAttach.getClass(), listaInscripcionInscripcionToAttach.getId());
                attachedListaInscripcion.add(listaInscripcionInscripcionToAttach);
            }
            turista.setListaInscripcion(attachedListaInscripcion);
            em.persist(turista);
            for (Inscripcion listaInscripcionInscripcion : turista.getListaInscripcion()) {
                Turista oldTuristaOfListaInscripcionInscripcion = listaInscripcionInscripcion.getTurista();
                listaInscripcionInscripcion.setTurista(turista);
                listaInscripcionInscripcion = em.merge(listaInscripcionInscripcion);
                if (oldTuristaOfListaInscripcionInscripcion != null) {
                    oldTuristaOfListaInscripcionInscripcion.getListaInscripcion().remove(listaInscripcionInscripcion);
                    oldTuristaOfListaInscripcionInscripcion = em.merge(oldTuristaOfListaInscripcionInscripcion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTurista(turista.getNickname()) != null) {
                throw new PreexistingEntityException("Turista " + turista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turista turista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turista persistentTurista = em.find(Turista.class, turista.getNickname());
            ArrayList<Inscripcion> listaInscripcionOld = persistentTurista.getListaInscripcion();
            ArrayList<Inscripcion> listaInscripcionNew = turista.getListaInscripcion();
            ArrayList<Inscripcion> attachedListaInscripcionNew = new ArrayList<Inscripcion>();
            for (Inscripcion listaInscripcionNewInscripcionToAttach : listaInscripcionNew) {
                listaInscripcionNewInscripcionToAttach = em.getReference(listaInscripcionNewInscripcionToAttach.getClass(), listaInscripcionNewInscripcionToAttach.getId());
                attachedListaInscripcionNew.add(listaInscripcionNewInscripcionToAttach);
            }
            listaInscripcionNew = attachedListaInscripcionNew;
            turista.setListaInscripcion(listaInscripcionNew);
            turista = em.merge(turista);
            for (Inscripcion listaInscripcionOldInscripcion : listaInscripcionOld) {
                if (!listaInscripcionNew.contains(listaInscripcionOldInscripcion)) {
                    listaInscripcionOldInscripcion.setTurista(null);
                    listaInscripcionOldInscripcion = em.merge(listaInscripcionOldInscripcion);
                }
            }
            for (Inscripcion listaInscripcionNewInscripcion : listaInscripcionNew) {
                if (!listaInscripcionOld.contains(listaInscripcionNewInscripcion)) {
                    Turista oldTuristaOfListaInscripcionNewInscripcion = listaInscripcionNewInscripcion.getTurista();
                    listaInscripcionNewInscripcion.setTurista(turista);
                    listaInscripcionNewInscripcion = em.merge(listaInscripcionNewInscripcion);
                    if (oldTuristaOfListaInscripcionNewInscripcion != null && !oldTuristaOfListaInscripcionNewInscripcion.equals(turista)) {
                        oldTuristaOfListaInscripcionNewInscripcion.getListaInscripcion().remove(listaInscripcionNewInscripcion);
                        oldTuristaOfListaInscripcionNewInscripcion = em.merge(oldTuristaOfListaInscripcionNewInscripcion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = turista.getNickname();
                if (findTurista(id) == null) {
                    throw new NonexistentEntityException("The turista with id " + id + " no longer exists.");
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
            Turista turista;
            try {
                turista = em.getReference(Turista.class, id);
                turista.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turista with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Inscripcion> listaInscripcion = turista.getListaInscripcion();
            for (Inscripcion listaInscripcionInscripcion : listaInscripcion) {
                listaInscripcionInscripcion.setTurista(null);
                listaInscripcionInscripcion = em.merge(listaInscripcionInscripcion);
            }
            em.remove(turista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turista> findTuristaEntities() {
        return findTuristaEntities(true, -1, -1);
    }

    public List<Turista> findTuristaEntities(int maxResults, int firstResult) {
        return findTuristaEntities(false, maxResults, firstResult);
    }

    private List<Turista> findTuristaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turista.class));
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

    public Turista findTurista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turista.class, id);
        } finally {
            em.close();
        }
    }

    public int getTuristaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turista> rt = cq.from(Turista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
