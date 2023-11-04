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
import logica.Actividad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author natil
 */
public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public PaqueteJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) throws PreexistingEntityException, Exception {
        if (paquete.getListaActividades() == null) {
            paquete.setListaActividades(new ArrayList<Actividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Actividad> attachedListaActividades = new ArrayList<Actividad>();
            for (Actividad listaActividadesActividadToAttach : paquete.getListaActividades()) {
                listaActividadesActividadToAttach = em.getReference(listaActividadesActividadToAttach.getClass(), listaActividadesActividadToAttach.getNombre());
                attachedListaActividades.add(listaActividadesActividadToAttach);
            }
            paquete.setListaActividades(attachedListaActividades);
            em.persist(paquete);
            for (Actividad listaActividadesActividad : paquete.getListaActividades()) {
                listaActividadesActividad.getListaPaquete().add(paquete);
                listaActividadesActividad = em.merge(listaActividadesActividad);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaquete(paquete.getNombre()) != null) {
                throw new PreexistingEntityException("Paquete " + paquete + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getNombre());
            List<Actividad> listaActividadesOld = persistentPaquete.getListaActividades();
            List<Actividad> listaActividadesNew = paquete.getListaActividades();
            ArrayList<Actividad> attachedListaActividadesNew = new ArrayList<Actividad>();
            for (Actividad listaActividadesNewActividadToAttach : listaActividadesNew) {
                listaActividadesNewActividadToAttach = em.getReference(listaActividadesNewActividadToAttach.getClass(), listaActividadesNewActividadToAttach.getNombre());
                attachedListaActividadesNew.add(listaActividadesNewActividadToAttach);
            }
            listaActividadesNew = attachedListaActividadesNew;
            paquete.setListaActividades(listaActividadesNew);
            paquete = em.merge(paquete);
            for (Actividad listaActividadesOldActividad : listaActividadesOld) {
                if (!listaActividadesNew.contains(listaActividadesOldActividad)) {
                    listaActividadesOldActividad.getListaPaquete().remove(paquete);
                    listaActividadesOldActividad = em.merge(listaActividadesOldActividad);
                }
            }
            for (Actividad listaActividadesNewActividad : listaActividadesNew) {
                if (!listaActividadesOld.contains(listaActividadesNewActividad)) {
                    listaActividadesNewActividad.getListaPaquete().add(paquete);
                    listaActividadesNewActividad = em.merge(listaActividadesNewActividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = paquete.getNombre();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
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
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<Actividad> listaActividades = paquete.getListaActividades();
            for (Actividad listaActividadesActividad : listaActividades) {
                listaActividadesActividad.getListaPaquete().remove(paquete);
                listaActividadesActividad = em.merge(listaActividadesActividad);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
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

    public Paquete findPaquete(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
