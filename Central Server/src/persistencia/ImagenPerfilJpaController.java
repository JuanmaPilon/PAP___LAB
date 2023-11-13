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
import logica.ImagenPerfil;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Pc
 */
public class ImagenPerfilJpaController implements Serializable {

    public ImagenPerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ImagenPerfilJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }

    public void create(ImagenPerfil imagenPerfil) throws PreexistingEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(imagenPerfil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findImagenPerfil(imagenPerfil.getNombre()) != null) {
                throw new PreexistingEntityException("ImagenPerfil " + imagenPerfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ImagenPerfil imagenPerfil) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            imagenPerfil = em.merge(imagenPerfil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = imagenPerfil.getNombre();
                if (findImagenPerfil(id) == null) {
                    throw new NonexistentEntityException("The imagenPerfil with id " + id + " no longer exists.");
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
            ImagenPerfil imagenPerfil;
            try {
                imagenPerfil = em.getReference(ImagenPerfil.class, id);
                imagenPerfil.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagenPerfil with id " + id + " no longer exists.", enfe);
            }
            em.remove(imagenPerfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ImagenPerfil> findImagenPerfilEntities() {
        return findImagenPerfilEntities(true, -1, -1);
    }

    public List<ImagenPerfil> findImagenPerfilEntities(int maxResults, int firstResult) {
        return findImagenPerfilEntities(false, maxResults, firstResult);
    }

    private List<ImagenPerfil> findImagenPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ImagenPerfil.class));
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

    public ImagenPerfil findImagenPerfil(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ImagenPerfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ImagenPerfil> rt = cq.from(ImagenPerfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public ImagenPerfil findImagenPerfilByNickname(String nicknameUsuario) {
    EntityManager em = getEntityManager();
    try {
        TypedQuery<ImagenPerfil> query = em.createQuery(
            "SELECT ip FROM ImagenPerfil ip WHERE ip.nicknameUsuario = :nicknameUsuario", 
            ImagenPerfil.class
        );
        query.setParameter("nicknameUsuario", nicknameUsuario);
        
        List<ImagenPerfil> resultados = query.getResultList();
        
        if (!resultados.isEmpty()) {
            return resultados.get(0); // Devuelve la primera imagen encontrada (puede ser única)
        } else {
            return null; // Si no se encuentra ninguna imagen asociada al usuario
        }
    } finally {
        em.close();
    }
}
    
}
