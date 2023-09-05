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
import logica.Paquete;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Actividad;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author natil
 */
public class ActividadJpaController implements Serializable {

    public ActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ActividadJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividad actividad) throws PreexistingEntityException, Exception {
        if (actividad.getListaPaquete() == null) {
            actividad.setListaPaquete(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Paquete> attachedListaPaquete = new ArrayList<Paquete>();
            for (Paquete listaPaquetePaqueteToAttach : actividad.getListaPaquete()) {
                listaPaquetePaqueteToAttach = em.getReference(listaPaquetePaqueteToAttach.getClass(), listaPaquetePaqueteToAttach.getNombre());
                attachedListaPaquete.add(listaPaquetePaqueteToAttach);
            }
            actividad.setListaPaquete(attachedListaPaquete);
            em.persist(actividad);
            for (Paquete listaPaquetePaquete : actividad.getListaPaquete()) {
                listaPaquetePaquete.getListaActividades().add(actividad);
                listaPaquetePaquete = em.merge(listaPaquetePaquete);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActividad(actividad.getNombre()) != null) {
                throw new PreexistingEntityException("Actividad " + actividad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividad actividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad persistentActividad = em.find(Actividad.class, actividad.getNombre());
            ArrayList<Paquete> listaPaqueteOld = persistentActividad.getListaPaquete();
            ArrayList<Paquete> listaPaqueteNew = actividad.getListaPaquete();
            ArrayList<Paquete> attachedListaPaqueteNew = new ArrayList<Paquete>();
            for (Paquete listaPaqueteNewPaqueteToAttach : listaPaqueteNew) {
                listaPaqueteNewPaqueteToAttach = em.getReference(listaPaqueteNewPaqueteToAttach.getClass(), listaPaqueteNewPaqueteToAttach.getNombre());
                attachedListaPaqueteNew.add(listaPaqueteNewPaqueteToAttach);
            }
            listaPaqueteNew = attachedListaPaqueteNew;
            actividad.setListaPaquete(listaPaqueteNew);
            actividad = em.merge(actividad);
            for (Paquete listaPaqueteOldPaquete : listaPaqueteOld) {
                if (!listaPaqueteNew.contains(listaPaqueteOldPaquete)) {
                    listaPaqueteOldPaquete.getListaActividades().remove(actividad);
                    listaPaqueteOldPaquete = em.merge(listaPaqueteOldPaquete);
                }
            }
            for (Paquete listaPaqueteNewPaquete : listaPaqueteNew) {
                if (!listaPaqueteOld.contains(listaPaqueteNewPaquete)) {
                    listaPaqueteNewPaquete.getListaActividades().add(actividad);
                    listaPaqueteNewPaquete = em.merge(listaPaqueteNewPaquete);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = actividad.getNombre();
                if (findActividad(id) == null) {
                    throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.");
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
            Actividad actividad;
            try {
                actividad = em.getReference(Actividad.class, id);
                actividad.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Paquete> listaPaquete = actividad.getListaPaquete();
            for (Paquete listaPaquetePaquete : listaPaquete) {
                listaPaquetePaquete.getListaActividades().remove(actividad);
                listaPaquetePaquete = em.merge(listaPaquetePaquete);
            }
            em.remove(actividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividad> findActividadEntities() {
        return findActividadEntities(true, -1, -1);
    }

    public List<Actividad> findActividadEntities(int maxResults, int firstResult) {
        return findActividadEntities(false, maxResults, firstResult);
    }

    private List<Actividad> findActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividad.class));
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

    public Actividad findActividad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividad> rt = cq.from(Actividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
