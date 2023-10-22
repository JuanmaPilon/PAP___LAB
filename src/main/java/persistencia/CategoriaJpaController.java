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


    
}
