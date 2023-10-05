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
import logica.Departamento;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author carlo
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public DepartamentoJpaController() {
       emf = Persistence.createEntityManagerFactory("Lab01PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) throws PreexistingEntityException, Exception {
        if (departamento.getListaActTur() == null) {
            departamento.setListaActTur(new ArrayList<Actividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Actividad> attachedListaActTur = new ArrayList<Actividad>();
            for (Actividad listaActTurActividadToAttach : departamento.getListaActTur()) {
                listaActTurActividadToAttach = em.getReference(listaActTurActividadToAttach.getClass(), listaActTurActividadToAttach.getNombre());
                attachedListaActTur.add(listaActTurActividadToAttach);
            }
            departamento.setListaActTur(attachedListaActTur);
            em.persist(departamento);
            for (Actividad listaActTurActividad : departamento.getListaActTur()) {
                Departamento oldDepartamentoOfListaActTurActividad = listaActTurActividad.getDepartamento();
                listaActTurActividad.setDepartamento(departamento);
                listaActTurActividad = em.merge(listaActTurActividad);
                if (oldDepartamentoOfListaActTurActividad != null) {
                    oldDepartamentoOfListaActTurActividad.getListaActTur().remove(listaActTurActividad);
                    oldDepartamentoOfListaActTurActividad = em.merge(oldDepartamentoOfListaActTurActividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartamento(departamento.getNombre()) != null) {
                throw new PreexistingEntityException("Departamento " + departamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getNombre());
            ArrayList<Actividad> listaActTurOld = persistentDepartamento.getListaActTur();
            ArrayList<Actividad> listaActTurNew = departamento.getListaActTur();
            ArrayList<Actividad> attachedListaActTurNew = new ArrayList<Actividad>();
            for (Actividad listaActTurNewActividadToAttach : listaActTurNew) {
                listaActTurNewActividadToAttach = em.getReference(listaActTurNewActividadToAttach.getClass(), listaActTurNewActividadToAttach.getNombre());
                attachedListaActTurNew.add(listaActTurNewActividadToAttach);
            }
            listaActTurNew = attachedListaActTurNew;
            departamento.setListaActTur(listaActTurNew);
            departamento = em.merge(departamento);
            for (Actividad listaActTurOldActividad : listaActTurOld) {
                if (!listaActTurNew.contains(listaActTurOldActividad)) {
                    listaActTurOldActividad.setDepartamento(null);
                    listaActTurOldActividad = em.merge(listaActTurOldActividad);
                }
            }
            for (Actividad listaActTurNewActividad : listaActTurNew) {
                if (!listaActTurOld.contains(listaActTurNewActividad)) {
                    Departamento oldDepartamentoOfListaActTurNewActividad = listaActTurNewActividad.getDepartamento();
                    listaActTurNewActividad.setDepartamento(departamento);
                    listaActTurNewActividad = em.merge(listaActTurNewActividad);
                    if (oldDepartamentoOfListaActTurNewActividad != null && !oldDepartamentoOfListaActTurNewActividad.equals(departamento)) {
                        oldDepartamentoOfListaActTurNewActividad.getListaActTur().remove(listaActTurNewActividad);
                        oldDepartamentoOfListaActTurNewActividad = em.merge(oldDepartamentoOfListaActTurNewActividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = departamento.getNombre();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Actividad> listaActTur = departamento.getListaActTur();
            for (Actividad listaActTurActividad : listaActTur) {
                listaActTurActividad.setDepartamento(null);
                listaActTurActividad = em.merge(listaActTurActividad);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    //obtener la lista de departamentos y  convertirla en una lista de objetos que puedan mostrarse en el JComboBox (AltaDeSalidaTuristica)
    public List<String> obtenerNombresDepartamentos() {
    List<Departamento> departamentos = findDepartamentoEntities(true, 0, 0);
    List<String> nombresDepartamentos = new ArrayList<>();

    for (Departamento departamento : departamentos) {
        nombresDepartamentos.add(departamento.getNombre()); // Suponiendo que existe un método "getNombre()" en la clase Departamento
    }

    return nombresDepartamentos;
}
    
}
