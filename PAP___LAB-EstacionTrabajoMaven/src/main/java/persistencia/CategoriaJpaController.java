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
import logica.Categoria;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author natil
 */
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //la vacia
    public CategoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }

    public void create(Categoria categoria) throws PreexistingEntityException, Exception {
        if (categoria.getListaActividad() == null) {
            categoria.setListaActividad(new ArrayList<Actividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Actividad> attachedListaActividad = new ArrayList<Actividad>();
            for (Actividad listaActividadActividadToAttach : categoria.getListaActividad()) {
                listaActividadActividadToAttach = em.getReference(listaActividadActividadToAttach.getClass(), listaActividadActividadToAttach.getNombre());
                attachedListaActividad.add(listaActividadActividadToAttach);
            }
            categoria.setListaActividad(attachedListaActividad);
            em.persist(categoria);
            for (Actividad listaActividadActividad : categoria.getListaActividad()) {
                listaActividadActividad.getListaCategoria().add(categoria);
                listaActividadActividad = em.merge(listaActividadActividad);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoria(categoria.getNombre()) != null) {
                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getNombre());
            List<Actividad> listaActividadOld = persistentCategoria.getListaActividad();
            List<Actividad> listaActividadNew = categoria.getListaActividad();
            ArrayList<Actividad> attachedListaActividadNew = new ArrayList<Actividad>();
            for (Actividad listaActividadNewActividadToAttach : listaActividadNew) {
                listaActividadNewActividadToAttach = em.getReference(listaActividadNewActividadToAttach.getClass(), listaActividadNewActividadToAttach.getNombre());
                attachedListaActividadNew.add(listaActividadNewActividadToAttach);
            }
            listaActividadNew = attachedListaActividadNew;
            categoria.setListaActividad(listaActividadNew);
            categoria = em.merge(categoria);
            for (Actividad listaActividadOldActividad : listaActividadOld) {
                if (!listaActividadNew.contains(listaActividadOldActividad)) {
                    listaActividadOldActividad.getListaCategoria().remove(categoria);
                    listaActividadOldActividad = em.merge(listaActividadOldActividad);
                }
            }
            for (Actividad listaActividadNewActividad : listaActividadNew) {
                if (!listaActividadOld.contains(listaActividadNewActividad)) {
                    listaActividadNewActividad.getListaCategoria().add(categoria);
                    listaActividadNewActividad = em.merge(listaActividadNewActividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = categoria.getNombre();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<Actividad> listaActividad = categoria.getListaActividad();
            for (Actividad listaActividadActividad : listaActividad) {
                listaActividadActividad.getListaCategoria().remove(categoria);
                listaActividadActividad = em.merge(listaActividadActividad);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
