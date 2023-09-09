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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Proveedor;
import logica.Turista;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;
import persistencia.exceptions.CorreoElectronicoExistenteException;

/**
 *
 * @author natil
 */
public class TuristaJpaController implements Serializable {

    public TuristaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    //la vacia
    public TuristaJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Turista turista) throws CorreoElectronicoExistenteException, PreexistingEntityException, Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();

        EmailExistenceChecker checker = new EmailExistenceChecker(em);

        // Verificar si el correo electrónico ya existe en la base de datos para turistas
        if (checker.correoElectronicoExiste(turista.getCorreo(), Turista.class)) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un turista: " + turista.getCorreo());
        }

        // Verificar si el correo electrónico ya existe en la base de datos para proveedores
        if (checker.correoElectronicoExiste(turista.getCorreo(), Proveedor.class)) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un proveedor: " + turista.getCorreo());
        }

        em.persist(turista);
        em.getTransaction().commit();
    } catch (Exception ex) {
        if (findTurista(turista.getNickname()) != null) {
            throw new PreexistingEntityException("Turista " + turista.getNickname() + " already exists.", ex);
        }
        throw ex;
    } finally {
        if (em != null) {
            em.close();
        }
    }
}

public class EmailExistenceChecker {

    private EntityManager em;

    public EmailExistenceChecker(EntityManager em) {
        this.em = em;
    }

    public boolean correoElectronicoExiste(String correo, Class<?> entityClass) {
        TypedQuery<?> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.correo = :correo", entityClass);
        query.setParameter("correo", correo);
        List<?> resultList = query.getResultList();
        return !resultList.isEmpty();
    }
}

    public void edit(Turista turista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            turista = em.merge(turista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = turista.getNickname();
                if (findTurista(id) == null) {
                    throw new NonexistentEntityException("The turista with id " + id + " no longer exists.");
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
            Turista turista;
            try {
                turista = em.getReference(Turista.class, id);
                turista.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turista with id " + id + " no longer exists.", enfe);
            }
            em.remove(turista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turista> findTuristaEntities() {
        return findTuristaEntities(true, -1, -1);
    }

    public List<Turista> findTuristaEntities(int maxResults, int firstResult) {
        return findTuristaEntities(false, maxResults, firstResult);
    }

    private List<Turista> findTuristaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turista.class));
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

    public Turista findTurista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turista.class, id);
        } finally {
            em.close();
        }
    }

    public int getTuristaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turista> rt = cq.from(Turista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
