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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.imagenActividad;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Pc
 */
public class imagenActividadJpaController implements Serializable {

    public imagenActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public imagenActividadJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(imagenActividad imagenActividad) throws PreexistingEntityException{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(imagenActividad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findimagenActividad(imagenActividad.getNombre()) != null) {
                throw new PreexistingEntityException("imagenActividad " + imagenActividad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(imagenActividad imagenActividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            imagenActividad = em.merge(imagenActividad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = imagenActividad.getNombre();
                if (findimagenActividad(id) == null) {
                    throw new NonexistentEntityException("The imagenActividad with id " + id + " no longer exists.");
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
            imagenActividad imagenActividad;
            try {
                imagenActividad = em.getReference(imagenActividad.class, id);
                imagenActividad.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagenActividad with id " + id + " no longer exists.", enfe);
            }
            em.remove(imagenActividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<imagenActividad> findimagenActividadEntities() {
        return findimagenActividadEntities(true, -1, -1);
    }

    public List<imagenActividad> findimagenActividadEntities(int maxResults, int firstResult) {
        return findimagenActividadEntities(false, maxResults, firstResult);
    }

    private List<imagenActividad> findimagenActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(imagenActividad.class));
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

    public imagenActividad findimagenActividad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(imagenActividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getimagenActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<imagenActividad> rt = cq.from(imagenActividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public imagenActividad findImagenPerfilByNombreActividad(String nombreActividad) {
    EntityManager em = getEntityManager();
    try {
        TypedQuery<imagenActividad> query = em.createQuery(
            "SELECT ip FROM imagenActividad ip WHERE ip.nombreActividad = :nombreActividad", 
            imagenActividad.class
        );
        query.setParameter("nombreActividad", nombreActividad);
        
        List<imagenActividad> resultados = query.getResultList();
        
        if (!resultados.isEmpty()) {
            return resultados.get(0); // Devuelve la primera imagen encontrada (puede ser única)
        } else {
            return null; // Si no se encuentra ninguna imagen asociada a la actividad
        }
    } finally {
        em.close();
    }
}
    
}
