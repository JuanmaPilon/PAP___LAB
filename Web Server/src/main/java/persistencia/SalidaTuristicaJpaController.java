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
import logica.Inscripcion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.SalidaTuristica;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author carlo
 */
public class SalidaTuristicaJpaController implements Serializable {

    public SalidaTuristicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public SalidaTuristicaJpaController() {
       emf = Persistence.createEntityManagerFactory("Lab01PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalidaTuristica salidaTuristica) throws PreexistingEntityException, Exception {
        if (salidaTuristica.getListaInscripciones() == null) {
            salidaTuristica.setListaInscripciones(new ArrayList<Inscripcion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividad = salidaTuristica.getActividad();
            if (actividad != null) {
                actividad = em.getReference(actividad.getClass(), actividad.getNombre());
                salidaTuristica.setActividad(actividad);
            }
            ArrayList<Inscripcion> attachedListaInscripciones = new ArrayList<Inscripcion>();
            for (Inscripcion listaInscripcionesInscripcionToAttach : salidaTuristica.getListaInscripciones()) {
                listaInscripcionesInscripcionToAttach = em.getReference(listaInscripcionesInscripcionToAttach.getClass(), listaInscripcionesInscripcionToAttach.getId());
                attachedListaInscripciones.add(listaInscripcionesInscripcionToAttach);
            }
            salidaTuristica.setListaInscripciones(attachedListaInscripciones);
            em.persist(salidaTuristica);
            if (actividad != null) {
                actividad.getListaSalidaTuristica().add(salidaTuristica);
                actividad = em.merge(actividad);
            }
            for (Inscripcion listaInscripcionesInscripcion : salidaTuristica.getListaInscripciones()) {
                SalidaTuristica oldSalidaOfListaInscripcionesInscripcion = listaInscripcionesInscripcion.getSalida();
                listaInscripcionesInscripcion.setSalida(salidaTuristica);
                listaInscripcionesInscripcion = em.merge(listaInscripcionesInscripcion);
                if (oldSalidaOfListaInscripcionesInscripcion != null) {
                    oldSalidaOfListaInscripcionesInscripcion.getListaInscripciones().remove(listaInscripcionesInscripcion);
                    oldSalidaOfListaInscripcionesInscripcion = em.merge(oldSalidaOfListaInscripcionesInscripcion);
                }
            }
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
            SalidaTuristica persistentSalidaTuristica = em.find(SalidaTuristica.class, salidaTuristica.getNombre());
            Actividad actividadOld = persistentSalidaTuristica.getActividad();
            Actividad actividadNew = salidaTuristica.getActividad();
            ArrayList<Inscripcion> listaInscripcionesOld = persistentSalidaTuristica.getListaInscripciones();
            ArrayList<Inscripcion> listaInscripcionesNew = salidaTuristica.getListaInscripciones();
            if (actividadNew != null) {
                actividadNew = em.getReference(actividadNew.getClass(), actividadNew.getNombre());
                salidaTuristica.setActividad(actividadNew);
            }
            ArrayList<Inscripcion> attachedListaInscripcionesNew = new ArrayList<Inscripcion>();
            for (Inscripcion listaInscripcionesNewInscripcionToAttach : listaInscripcionesNew) {
                listaInscripcionesNewInscripcionToAttach = em.getReference(listaInscripcionesNewInscripcionToAttach.getClass(), listaInscripcionesNewInscripcionToAttach.getId());
                attachedListaInscripcionesNew.add(listaInscripcionesNewInscripcionToAttach);
            }
            listaInscripcionesNew = attachedListaInscripcionesNew;
            salidaTuristica.setListaInscripciones(listaInscripcionesNew);
            salidaTuristica = em.merge(salidaTuristica);
            if (actividadOld != null && !actividadOld.equals(actividadNew)) {
                actividadOld.getListaSalidaTuristica().remove(salidaTuristica);
                actividadOld = em.merge(actividadOld);
            }
            if (actividadNew != null && !actividadNew.equals(actividadOld)) {
                actividadNew.getListaSalidaTuristica().add(salidaTuristica);
                actividadNew = em.merge(actividadNew);
            }
            for (Inscripcion listaInscripcionesOldInscripcion : listaInscripcionesOld) {
                if (!listaInscripcionesNew.contains(listaInscripcionesOldInscripcion)) {
                    listaInscripcionesOldInscripcion.setSalida(null);
                    listaInscripcionesOldInscripcion = em.merge(listaInscripcionesOldInscripcion);
                }
            }
            for (Inscripcion listaInscripcionesNewInscripcion : listaInscripcionesNew) {
                if (!listaInscripcionesOld.contains(listaInscripcionesNewInscripcion)) {
                    SalidaTuristica oldSalidaOfListaInscripcionesNewInscripcion = listaInscripcionesNewInscripcion.getSalida();
                    listaInscripcionesNewInscripcion.setSalida(salidaTuristica);
                    listaInscripcionesNewInscripcion = em.merge(listaInscripcionesNewInscripcion);
                    if (oldSalidaOfListaInscripcionesNewInscripcion != null && !oldSalidaOfListaInscripcionesNewInscripcion.equals(salidaTuristica)) {
                        oldSalidaOfListaInscripcionesNewInscripcion.getListaInscripciones().remove(listaInscripcionesNewInscripcion);
                        oldSalidaOfListaInscripcionesNewInscripcion = em.merge(oldSalidaOfListaInscripcionesNewInscripcion);
                    }
                }
            }
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

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalidaTuristica salidaTuristica;
            try {
                salidaTuristica = em.getReference(SalidaTuristica.class, id);
                salidaTuristica.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salidaTuristica with id " + id + " no longer exists.", enfe);
            }
            Actividad actividad = salidaTuristica.getActividad();
            if (actividad != null) {
                actividad.getListaSalidaTuristica().remove(salidaTuristica);
                actividad = em.merge(actividad);
            }
            ArrayList<Inscripcion> listaInscripciones = salidaTuristica.getListaInscripciones();
            for (Inscripcion listaInscripcionesInscripcion : listaInscripciones) {
                listaInscripcionesInscripcion.setSalida(null);
                listaInscripcionesInscripcion = em.merge(listaInscripcionesInscripcion);
            }
            em.remove(salidaTuristica);
            em.getTransaction().commit();
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

    public int getSalidaTuristicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalidaTuristica> rt = cq.from(SalidaTuristica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
