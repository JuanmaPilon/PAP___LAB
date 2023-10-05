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
import logica.Proveedor;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author carlo
 */
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ProveedorJpaController() {
       emf = Persistence.createEntityManagerFactory("Lab01PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getListaActividades() == null) {
            proveedor.setListaActividades(new ArrayList<Actividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Actividad> attachedListaActividades = new ArrayList<Actividad>();
            for (Actividad listaActividadesActividadToAttach : proveedor.getListaActividades()) {
                listaActividadesActividadToAttach = em.getReference(listaActividadesActividadToAttach.getClass(), listaActividadesActividadToAttach.getNombre());
                attachedListaActividades.add(listaActividadesActividadToAttach);
            }
            proveedor.setListaActividades(attachedListaActividades);
            em.persist(proveedor);
            for (Actividad listaActividadesActividad : proveedor.getListaActividades()) {
                Proveedor oldProveedorOfListaActividadesActividad = listaActividadesActividad.getProveedor();
                listaActividadesActividad.setProveedor(proveedor);
                listaActividadesActividad = em.merge(listaActividadesActividad);
                if (oldProveedorOfListaActividadesActividad != null) {
                    oldProveedorOfListaActividadesActividad.getListaActividades().remove(listaActividadesActividad);
                    oldProveedorOfListaActividadesActividad = em.merge(oldProveedorOfListaActividadesActividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedor(proveedor.getNickname()) != null) {
                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getNickname());
            ArrayList<Actividad> listaActividadesOld = persistentProveedor.getListaActividades();
            ArrayList<Actividad> listaActividadesNew = proveedor.getListaActividades();
            ArrayList<Actividad> attachedListaActividadesNew = new ArrayList<Actividad>();
            for (Actividad listaActividadesNewActividadToAttach : listaActividadesNew) {
                listaActividadesNewActividadToAttach = em.getReference(listaActividadesNewActividadToAttach.getClass(), listaActividadesNewActividadToAttach.getNombre());
                attachedListaActividadesNew.add(listaActividadesNewActividadToAttach);
            }
            listaActividadesNew = attachedListaActividadesNew;
            proveedor.setListaActividades(listaActividadesNew);
            proveedor = em.merge(proveedor);
            for (Actividad listaActividadesOldActividad : listaActividadesOld) {
                if (!listaActividadesNew.contains(listaActividadesOldActividad)) {
                    listaActividadesOldActividad.setProveedor(null);
                    listaActividadesOldActividad = em.merge(listaActividadesOldActividad);
                }
            }
            for (Actividad listaActividadesNewActividad : listaActividadesNew) {
                if (!listaActividadesOld.contains(listaActividadesNewActividad)) {
                    Proveedor oldProveedorOfListaActividadesNewActividad = listaActividadesNewActividad.getProveedor();
                    listaActividadesNewActividad.setProveedor(proveedor);
                    listaActividadesNewActividad = em.merge(listaActividadesNewActividad);
                    if (oldProveedorOfListaActividadesNewActividad != null && !oldProveedorOfListaActividadesNewActividad.equals(proveedor)) {
                        oldProveedorOfListaActividadesNewActividad.getListaActividades().remove(listaActividadesNewActividad);
                        oldProveedorOfListaActividadesNewActividad = em.merge(oldProveedorOfListaActividadesNewActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = proveedor.getNickname();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Actividad> listaActividades = proveedor.getListaActividades();
            for (Actividad listaActividadesActividad : listaActividades) {
                listaActividadesActividad.setProveedor(null);
                listaActividadesActividad = em.merge(listaActividadesActividad);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
