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
import persistencia.exceptions.CorreoElectronicoExistenteException;
import persistencia.exceptions.NicknameExistenteException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author natil
 */
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    //la vacia
    public ProveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("Lab1PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   public void create(Proveedor proveedor) throws CorreoElectronicoExistenteException, NicknameExistenteException, PreexistingEntityException, Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();

        EmailExistenceChecker emailChecker = new EmailExistenceChecker(em);
        NicknameExistenceChecker nicknameChecker = new NicknameExistenceChecker(em);

        // Verificar si el correo electrónico ya existe en la base de datos para proveedores
        if (emailChecker.correoElectronicoExiste(proveedor.getCorreo(), Proveedor.class)) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un proveedor: " + proveedor.getCorreo());
        }

        // Verificar si el correo electrónico ya existe en la base de datos para turistas
        if (emailChecker.correoElectronicoExiste(proveedor.getCorreo(), Turista.class)) {
            throw new CorreoElectronicoExistenteException("Correo electrónico ya en uso por un turista: " + proveedor.getCorreo());
        }

        // Verificar si el nickname ya existe en la base de datos para proveedores
        if (nicknameChecker.nicknameExiste(proveedor.getNickname(), Proveedor.class)) {
            throw new NicknameExistenteException("Nickname ya en uso por un proveedor: " + proveedor.getNickname());
        }

        // Verificar si el nickname ya existe en la base de datos para turistas
        if (nicknameChecker.nicknameExiste(proveedor.getNickname(), Turista.class)) {
            throw new NicknameExistenteException("Nickname ya en uso por un turista: " + proveedor.getNickname());
        }

        em.persist(proveedor);
        em.getTransaction().commit();
    } catch (Exception ex) {
        if (findProveedor(proveedor.getNickname()) != null) {
            throw new PreexistingEntityException("Proveedor " + proveedor.getNickname() + " already exists.", ex);
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

public class NicknameExistenceChecker {

    private EntityManager em;

    public NicknameExistenceChecker(EntityManager em) {
        this.em = em;
    }

    public boolean nicknameExiste(String nickname, Class<?> entityClass) {
        TypedQuery<?> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.nickname = :nickname", entityClass);
        query.setParameter("nickname", nickname);
        List<?> resultList = query.getResultList();
        return !resultList.isEmpty();
    }
}


    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proveedor = em.merge(proveedor);
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

    
}
