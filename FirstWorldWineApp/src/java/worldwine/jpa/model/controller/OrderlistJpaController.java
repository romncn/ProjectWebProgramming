/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.jpa.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import worldwine.jpa.model.Accountcustomer;
import worldwine.jpa.model.Orderdetail;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.controller.exceptions.NonexistentEntityException;
import worldwine.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Chutikan
 */
public class OrderlistJpaController implements Serializable {

    public OrderlistJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orderlist orderlist) throws RollbackFailureException, Exception {
        if (orderlist.getOrderdetailList() == null) {
            orderlist.setOrderdetailList(new ArrayList<Orderdetail>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Accountcustomer customerid = orderlist.getCustomerid();
            if (customerid != null) {
                customerid = em.getReference(customerid.getClass(), customerid.getCustomerid());
                orderlist.setCustomerid(customerid);
            }
            List<Orderdetail> attachedOrderdetailList = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListOrderdetailToAttach : orderlist.getOrderdetailList()) {
                orderdetailListOrderdetailToAttach = em.getReference(orderdetailListOrderdetailToAttach.getClass(), orderdetailListOrderdetailToAttach.getOrderdetailid());
                attachedOrderdetailList.add(orderdetailListOrderdetailToAttach);
            }
            orderlist.setOrderdetailList(attachedOrderdetailList);
            em.persist(orderlist);
            if (customerid != null) {
                customerid.getOrderlistList().add(orderlist);
                customerid = em.merge(customerid);
            }
            for (Orderdetail orderdetailListOrderdetail : orderlist.getOrderdetailList()) {
                Orderlist oldOrderidOfOrderdetailListOrderdetail = orderdetailListOrderdetail.getOrderid();
                orderdetailListOrderdetail.setOrderid(orderlist);
                orderdetailListOrderdetail = em.merge(orderdetailListOrderdetail);
                if (oldOrderidOfOrderdetailListOrderdetail != null) {
                    oldOrderidOfOrderdetailListOrderdetail.getOrderdetailList().remove(orderdetailListOrderdetail);
                    oldOrderidOfOrderdetailListOrderdetail = em.merge(oldOrderidOfOrderdetailListOrderdetail);
                }
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

    public void edit(Orderlist orderlist) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderlist persistentOrderlist = em.find(Orderlist.class, orderlist.getOrderid());
            Accountcustomer customeridOld = persistentOrderlist.getCustomerid();
            Accountcustomer customeridNew = orderlist.getCustomerid();
            List<Orderdetail> orderdetailListOld = persistentOrderlist.getOrderdetailList();
            List<Orderdetail> orderdetailListNew = orderlist.getOrderdetailList();
            if (customeridNew != null) {
                customeridNew = em.getReference(customeridNew.getClass(), customeridNew.getCustomerid());
                orderlist.setCustomerid(customeridNew);
            }
            List<Orderdetail> attachedOrderdetailListNew = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListNewOrderdetailToAttach : orderdetailListNew) {
                orderdetailListNewOrderdetailToAttach = em.getReference(orderdetailListNewOrderdetailToAttach.getClass(), orderdetailListNewOrderdetailToAttach.getOrderdetailid());
                attachedOrderdetailListNew.add(orderdetailListNewOrderdetailToAttach);
            }
            orderdetailListNew = attachedOrderdetailListNew;
            orderlist.setOrderdetailList(orderdetailListNew);
            orderlist = em.merge(orderlist);
            if (customeridOld != null && !customeridOld.equals(customeridNew)) {
                customeridOld.getOrderlistList().remove(orderlist);
                customeridOld = em.merge(customeridOld);
            }
            if (customeridNew != null && !customeridNew.equals(customeridOld)) {
                customeridNew.getOrderlistList().add(orderlist);
                customeridNew = em.merge(customeridNew);
            }
            for (Orderdetail orderdetailListOldOrderdetail : orderdetailListOld) {
                if (!orderdetailListNew.contains(orderdetailListOldOrderdetail)) {
                    orderdetailListOldOrderdetail.setOrderid(null);
                    orderdetailListOldOrderdetail = em.merge(orderdetailListOldOrderdetail);
                }
            }
            for (Orderdetail orderdetailListNewOrderdetail : orderdetailListNew) {
                if (!orderdetailListOld.contains(orderdetailListNewOrderdetail)) {
                    Orderlist oldOrderidOfOrderdetailListNewOrderdetail = orderdetailListNewOrderdetail.getOrderid();
                    orderdetailListNewOrderdetail.setOrderid(orderlist);
                    orderdetailListNewOrderdetail = em.merge(orderdetailListNewOrderdetail);
                    if (oldOrderidOfOrderdetailListNewOrderdetail != null && !oldOrderidOfOrderdetailListNewOrderdetail.equals(orderlist)) {
                        oldOrderidOfOrderdetailListNewOrderdetail.getOrderdetailList().remove(orderdetailListNewOrderdetail);
                        oldOrderidOfOrderdetailListNewOrderdetail = em.merge(oldOrderidOfOrderdetailListNewOrderdetail);
                    }
                }
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
                Integer id = orderlist.getOrderid();
                if (findOrderlist(id) == null) {
                    throw new NonexistentEntityException("The orderlist with id " + id + " no longer exists.");
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
            Orderlist orderlist;
            try {
                orderlist = em.getReference(Orderlist.class, id);
                orderlist.getOrderid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderlist with id " + id + " no longer exists.", enfe);
            }
            Accountcustomer customerid = orderlist.getCustomerid();
            if (customerid != null) {
                customerid.getOrderlistList().remove(orderlist);
                customerid = em.merge(customerid);
            }
            List<Orderdetail> orderdetailList = orderlist.getOrderdetailList();
            for (Orderdetail orderdetailListOrderdetail : orderdetailList) {
                orderdetailListOrderdetail.setOrderid(null);
                orderdetailListOrderdetail = em.merge(orderdetailListOrderdetail);
            }
            em.remove(orderlist);
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

    public List<Orderlist> findOrderlistEntities() {
        return findOrderlistEntities(true, -1, -1);
    }

    public List<Orderlist> findOrderlistEntities(int maxResults, int firstResult) {
        return findOrderlistEntities(false, maxResults, firstResult);
    }

    private List<Orderlist> findOrderlistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orderlist.class));
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

    public Orderlist findOrderlist(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orderlist.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderlistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orderlist> rt = cq.from(Orderlist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
