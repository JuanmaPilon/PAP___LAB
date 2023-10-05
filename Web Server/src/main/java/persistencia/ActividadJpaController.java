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
import logica.Departamento;
import logica.Proveedor;
import logica.SalidaTuristica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Actividad;
import logica.Paquete;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author carlo
 */
public class ActividadJpaController implements Serializable {

    public ActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public ActividadJpaController() {
       emf = Persistence.createEntityManagerFactory("Lab01PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividad actividad) throws PreexistingEntityException, Exception {
        if (actividad.getListaSalidaTuristica() == null) {
            actividad.setListaSalidaTuristica(new ArrayList<SalidaTuristica>());
        }
        if (actividad.getListaPaquete() == null) {
            actividad.setListaPaquete(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento departamento = actividad.getDepartamento();
            if (departamento != null) {
                departamento = em.getReference(departamento.getClass(), departamento.getNombre());
                actividad.setDepartamento(departamento);
            }
            Proveedor proveedor = actividad.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getNickname());
                actividad.setProveedor(proveedor);
            }
            ArrayList<SalidaTuristica> attachedListaSalidaTuristica = new ArrayList<SalidaTuristica>();
            for (SalidaTuristica listaSalidaTuristicaSalidaTuristicaToAttach : actividad.getListaSalidaTuristica()) {
                listaSalidaTuristicaSalidaTuristicaToAttach = em.getReference(listaSalidaTuristicaSalidaTuristicaToAttach.getClass(), listaSalidaTuristicaSalidaTuristicaToAttach.getNombre());
                attachedListaSalidaTuristica.add(listaSalidaTuristicaSalidaTuristicaToAttach);
            }
            actividad.setListaSalidaTuristica(attachedListaSalidaTuristica);
            ArrayList<Paquete> attachedListaPaquete = new ArrayList<Paquete>();
            for (Paquete listaPaquetePaqueteToAttach : actividad.getListaPaquete()) {
                listaPaquetePaqueteToAttach = em.getReference(listaPaquetePaqueteToAttach.getClass(), listaPaquetePaqueteToAttach.getNombre());
                attachedListaPaquete.add(listaPaquetePaqueteToAttach);
            }
            actividad.setListaPaquete(attachedListaPaquete);
            em.persist(actividad);
            if (departamento != null) {
                departamento.getListaActTur().add(actividad);
                departamento = em.merge(departamento);
            }
            if (proveedor != null) {
                proveedor.getListaActividades().add(actividad);
                proveedor = em.merge(proveedor);
            }
            for (SalidaTuristica listaSalidaTuristicaSalidaTuristica : actividad.getListaSalidaTuristica()) {
                Actividad oldActividadOfListaSalidaTuristicaSalidaTuristica = listaSalidaTuristicaSalidaTuristica.getActividad();
                listaSalidaTuristicaSalidaTuristica.setActividad(actividad);
                listaSalidaTuristicaSalidaTuristica = em.merge(listaSalidaTuristicaSalidaTuristica);
                if (oldActividadOfListaSalidaTuristicaSalidaTuristica != null) {
                    oldActividadOfListaSalidaTuristicaSalidaTuristica.getListaSalidaTuristica().remove(listaSalidaTuristicaSalidaTuristica);
                    oldActividadOfListaSalidaTuristicaSalidaTuristica = em.merge(oldActividadOfListaSalidaTuristicaSalidaTuristica);
                }
            }
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
            Departamento departamentoOld = persistentActividad.getDepartamento();
            Departamento departamentoNew = actividad.getDepartamento();
            Proveedor proveedorOld = persistentActividad.getProveedor();
            Proveedor proveedorNew = actividad.getProveedor();
            ArrayList<SalidaTuristica> listaSalidaTuristicaOld = persistentActividad.getListaSalidaTuristica();
            ArrayList<SalidaTuristica> listaSalidaTuristicaNew = actividad.getListaSalidaTuristica();
            ArrayList<Paquete> listaPaqueteOld = persistentActividad.getListaPaquete();
            ArrayList<Paquete> listaPaqueteNew = actividad.getListaPaquete();
            if (departamentoNew != null) {
                departamentoNew = em.getReference(departamentoNew.getClass(), departamentoNew.getNombre());
                actividad.setDepartamento(departamentoNew);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getNickname());
                actividad.setProveedor(proveedorNew);
            }
            ArrayList<SalidaTuristica> attachedListaSalidaTuristicaNew = new ArrayList<SalidaTuristica>();
            for (SalidaTuristica listaSalidaTuristicaNewSalidaTuristicaToAttach : listaSalidaTuristicaNew) {
                listaSalidaTuristicaNewSalidaTuristicaToAttach = em.getReference(listaSalidaTuristicaNewSalidaTuristicaToAttach.getClass(), listaSalidaTuristicaNewSalidaTuristicaToAttach.getNombre());
                attachedListaSalidaTuristicaNew.add(listaSalidaTuristicaNewSalidaTuristicaToAttach);
            }
            listaSalidaTuristicaNew = attachedListaSalidaTuristicaNew;
            actividad.setListaSalidaTuristica(listaSalidaTuristicaNew);
            ArrayList<Paquete> attachedListaPaqueteNew = new ArrayList<Paquete>();
            for (Paquete listaPaqueteNewPaqueteToAttach : listaPaqueteNew) {
                listaPaqueteNewPaqueteToAttach = em.getReference(listaPaqueteNewPaqueteToAttach.getClass(), listaPaqueteNewPaqueteToAttach.getNombre());
                attachedListaPaqueteNew.add(listaPaqueteNewPaqueteToAttach);
            }
            listaPaqueteNew = attachedListaPaqueteNew;
            actividad.setListaPaquete(listaPaqueteNew);
            actividad = em.merge(actividad);
            if (departamentoOld != null && !departamentoOld.equals(departamentoNew)) {
                departamentoOld.getListaActTur().remove(actividad);
                departamentoOld = em.merge(departamentoOld);
            }
            if (departamentoNew != null && !departamentoNew.equals(departamentoOld)) {
                departamentoNew.getListaActTur().add(actividad);
                departamentoNew = em.merge(departamentoNew);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getListaActividades().remove(actividad);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getListaActividades().add(actividad);
                proveedorNew = em.merge(proveedorNew);
            }
            for (SalidaTuristica listaSalidaTuristicaOldSalidaTuristica : listaSalidaTuristicaOld) {
                if (!listaSalidaTuristicaNew.contains(listaSalidaTuristicaOldSalidaTuristica)) {
                    listaSalidaTuristicaOldSalidaTuristica.setActividad(null);
                    listaSalidaTuristicaOldSalidaTuristica = em.merge(listaSalidaTuristicaOldSalidaTuristica);
                }
            }
            for (SalidaTuristica listaSalidaTuristicaNewSalidaTuristica : listaSalidaTuristicaNew) {
                if (!listaSalidaTuristicaOld.contains(listaSalidaTuristicaNewSalidaTuristica)) {
                    Actividad oldActividadOfListaSalidaTuristicaNewSalidaTuristica = listaSalidaTuristicaNewSalidaTuristica.getActividad();
                    listaSalidaTuristicaNewSalidaTuristica.setActividad(actividad);
                    listaSalidaTuristicaNewSalidaTuristica = em.merge(listaSalidaTuristicaNewSalidaTuristica);
                    if (oldActividadOfListaSalidaTuristicaNewSalidaTuristica != null && !oldActividadOfListaSalidaTuristicaNewSalidaTuristica.equals(actividad)) {
                        oldActividadOfListaSalidaTuristicaNewSalidaTuristica.getListaSalidaTuristica().remove(listaSalidaTuristicaNewSalidaTuristica);
                        oldActividadOfListaSalidaTuristicaNewSalidaTuristica = em.merge(oldActividadOfListaSalidaTuristicaNewSalidaTuristica);
                    }
                }
            }
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
            Departamento departamento = actividad.getDepartamento();
            if (departamento != null) {
                departamento.getListaActTur().remove(actividad);
                departamento = em.merge(departamento);
            }
            Proveedor proveedor = actividad.getProveedor();
            if (proveedor != null) {
                proveedor.getListaActividades().remove(actividad);
                proveedor = em.merge(proveedor);
            }
            ArrayList<SalidaTuristica> listaSalidaTuristica = actividad.getListaSalidaTuristica();
            for (SalidaTuristica listaSalidaTuristicaSalidaTuristica : listaSalidaTuristica) {
                listaSalidaTuristicaSalidaTuristica.setActividad(null);
                listaSalidaTuristicaSalidaTuristica = em.merge(listaSalidaTuristicaSalidaTuristica);
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
