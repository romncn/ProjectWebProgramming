/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.jpa.model.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Orderdetail;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.Product;
import worldwine.jpa.model.controller.exceptions.NonexistentEntityException;
import worldwine.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Chutikan
 */
public class OrderdetailJpaController implements Serializable {

    public OrderdetailJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orderdetail orderdetail) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderlist orderid = orderdetail.getOrderid();
            if (orderid != null) {
                orderid = em.getReference(orderid.getClass(), orderid.getOrderid());
                orderdetail.setOrderid(orderid);
            }
            Product productcode = orderdetail.getProductcode();
            if (productcode != null) {
                productcode = em.getReference(productcode.getClass(), productcode.getProductcode());
                orderdetail.setProductcode(productcode);
            }
            em.persist(orderdetail);
            if (orderid != null) {
                orderid.getOrderdetailList().add(orderdetail);
                orderid = em.merge(orderid);
            }
            if (productcode != null) {
                productcode.getOrderdetailList().add(orderdetail);
                productcode = em.merge(productcode);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orderdetail orderdetail) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderdetail persistentOrderdetail = em.find(Orderdetail.class, orderdetail.getOrderdetailid());
            Orderlist orderidOld = persistentOrderdetail.getOrderid();
            Orderlist orderidNew = orderdetail.getOrderid();
            Product productcodeOld = persistentOrderdetail.getProductcode();
            Product productcodeNew = orderdetail.getProductcode();
            if (orderidNew != null) {
                orderidNew = em.getReference(orderidNew.getClass(), orderidNew.getOrderid());
                orderdetail.setOrderid(orderidNew);
            }
            if (productcodeNew != null) {
                productcodeNew = em.getReference(productcodeNew.getClass(), productcodeNew.getProductcode());
                orderdetail.setProductcode(productcodeNew);
            }
            orderdetail = em.merge(orderdetail);
            if (orderidOld != null && !orderidOld.equals(orderidNew)) {
                orderidOld.getOrderdetailList().remove(orderdetail);
                orderidOld = em.merge(orderidOld);
            }
            if (orderidNew != null && !orderidNew.equals(orderidOld)) {
                orderidNew.getOrderdetailList().add(orderdetail);
                orderidNew = em.merge(orderidNew);
            }
            if (productcodeOld != null && !productcodeOld.equals(productcodeNew)) {
                productcodeOld.getOrderdetailList().remove(orderdetail);
                productcodeOld = em.merge(productcodeOld);
            }
            if (productcodeNew != null && !productcodeNew.equals(productcodeOld)) {
                productcodeNew.getOrderdetailList().add(orderdetail);
                productcodeNew = em.merge(productcodeNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderdetail.getOrderdetailid();
                if (findOrderdetail(id) == null) {
                    throw new NonexistentEntityException("The orderdetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderdetail orderdetail;
            try {
                orderdetail = em.getReference(Orderdetail.class, id);
                orderdetail.getOrderdetailid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderdetail with id " + id + " no longer exists.", enfe);
            }
            Orderlist orderid = orderdetail.getOrderid();
            if (orderid != null) {
                orderid.getOrderdetailList().remove(orderdetail);
                orderid = em.merge(orderid);
            }
            Product productcode = orderdetail.getProductcode();
            if (productcode != null) {
                productcode.getOrderdetailList().remove(orderdetail);
                productcode = em.merge(productcode);
            }
            em.remove(orderdetail);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orderdetail> findOrderdetailEntities() {
        return findOrderdetailEntities(true, -1, -1);
    }

    public List<Orderdetail> findOrderdetailEntities(int maxResults, int firstResult) {
        return findOrderdetailEntities(false, maxResults, firstResult);
    }

    private List<Orderdetail> findOrderdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orderdetail.class));
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

    public Orderdetail findOrderdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orderdetail.class, id);
        } finally {
            em.close();
        }
    }

    public List<Orderdetail> findOrderId(Orderlist orderid) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Orderdetail.findByOrderId");
            query.setParameter("orderid", orderid);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getOrderdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orderdetail> rt = cq.from(Orderdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
