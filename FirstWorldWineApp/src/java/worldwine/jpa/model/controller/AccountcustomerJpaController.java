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
import worldwine.jpa.model.Orderlist;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Accountcustomer;
import worldwine.jpa.model.controller.exceptions.NonexistentEntityException;
import worldwine.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Chutikan
 */
public class AccountcustomerJpaController implements Serializable {

    public AccountcustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accountcustomer accountcustomer) throws RollbackFailureException, Exception {
        if (accountcustomer.getOrderlistList() == null) {
            accountcustomer.setOrderlistList(new ArrayList<Orderlist>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Orderlist> attachedOrderlistList = new ArrayList<Orderlist>();
            for (Orderlist orderlistListOrderlistToAttach : accountcustomer.getOrderlistList()) {
                orderlistListOrderlistToAttach = em.getReference(orderlistListOrderlistToAttach.getClass(), orderlistListOrderlistToAttach.getOrderid());
                attachedOrderlistList.add(orderlistListOrderlistToAttach);
            }
            accountcustomer.setOrderlistList(attachedOrderlistList);
            em.persist(accountcustomer);
            for (Orderlist orderlistListOrderlist : accountcustomer.getOrderlistList()) {
                Accountcustomer oldCustomeridOfOrderlistListOrderlist = orderlistListOrderlist.getCustomerid();
                orderlistListOrderlist.setCustomerid(accountcustomer);
                orderlistListOrderlist = em.merge(orderlistListOrderlist);
                if (oldCustomeridOfOrderlistListOrderlist != null) {
                    oldCustomeridOfOrderlistListOrderlist.getOrderlistList().remove(orderlistListOrderlist);
                    oldCustomeridOfOrderlistListOrderlist = em.merge(oldCustomeridOfOrderlistListOrderlist);
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

    public void edit(Accountcustomer accountcustomer) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Accountcustomer persistentAccountcustomer = em.find(Accountcustomer.class, accountcustomer.getCustomerid());
            List<Orderlist> orderlistListOld = persistentAccountcustomer.getOrderlistList();
            List<Orderlist> orderlistListNew = accountcustomer.getOrderlistList();
            List<Orderlist> attachedOrderlistListNew = new ArrayList<Orderlist>();
            for (Orderlist orderlistListNewOrderlistToAttach : orderlistListNew) {
                orderlistListNewOrderlistToAttach = em.getReference(orderlistListNewOrderlistToAttach.getClass(), orderlistListNewOrderlistToAttach.getOrderid());
                attachedOrderlistListNew.add(orderlistListNewOrderlistToAttach);
            }
            orderlistListNew = attachedOrderlistListNew;
            accountcustomer.setOrderlistList(orderlistListNew);
            accountcustomer = em.merge(accountcustomer);
            for (Orderlist orderlistListOldOrderlist : orderlistListOld) {
                if (!orderlistListNew.contains(orderlistListOldOrderlist)) {
                    orderlistListOldOrderlist.setCustomerid(null);
                    orderlistListOldOrderlist = em.merge(orderlistListOldOrderlist);
                }
            }
            for (Orderlist orderlistListNewOrderlist : orderlistListNew) {
                if (!orderlistListOld.contains(orderlistListNewOrderlist)) {
                    Accountcustomer oldCustomeridOfOrderlistListNewOrderlist = orderlistListNewOrderlist.getCustomerid();
                    orderlistListNewOrderlist.setCustomerid(accountcustomer);
                    orderlistListNewOrderlist = em.merge(orderlistListNewOrderlist);
                    if (oldCustomeridOfOrderlistListNewOrderlist != null && !oldCustomeridOfOrderlistListNewOrderlist.equals(accountcustomer)) {
                        oldCustomeridOfOrderlistListNewOrderlist.getOrderlistList().remove(orderlistListNewOrderlist);
                        oldCustomeridOfOrderlistListNewOrderlist = em.merge(oldCustomeridOfOrderlistListNewOrderlist);
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
                Integer id = accountcustomer.getCustomerid();
                if (findAccountcustomer(id) == null) {
                    throw new NonexistentEntityException("The accountcustomer with id " + id + " no longer exists.");
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
            Accountcustomer accountcustomer;
            try {
                accountcustomer = em.getReference(Accountcustomer.class, id);
                accountcustomer.getCustomerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accountcustomer with id " + id + " no longer exists.", enfe);
            }
            List<Orderlist> orderlistList = accountcustomer.getOrderlistList();
            for (Orderlist orderlistListOrderlist : orderlistList) {
                orderlistListOrderlist.setCustomerid(null);
                orderlistListOrderlist = em.merge(orderlistListOrderlist);
            }
            em.remove(accountcustomer);
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

    public List<Accountcustomer> findAccountcustomerEntities() {
        return findAccountcustomerEntities(true, -1, -1);
    }

    public List<Accountcustomer> findAccountcustomerEntities(int maxResults, int firstResult) {
        return findAccountcustomerEntities(false, maxResults, firstResult);
    }

    private List<Accountcustomer> findAccountcustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accountcustomer.class));
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

    public Accountcustomer findAccountcustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accountcustomer.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountcustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accountcustomer> rt = cq.from(Accountcustomer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public Accountcustomer findAccountcustomerUsername(String username) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Accountcustomer.findByUsername");
        query.setParameter("username", username);
        try {
            return (Accountcustomer) query.getSingleResult();
        } catch(NoResultException ex){
                return null;
        }finally {
            em.close();
        }
    }

//    public Accountcustomer findAccountcustomerUsernameCheck(String username) {
//        EntityManager em = getEntityManager();
//        Query query = em.createNamedQuery("Accountcustomer.findByUsername");
//        query.setParameter("username", username);
//        try {
//            return (Accountcustomer) query.getSingleResult();
//        } catch (Exception ex) {
//            return null;
//        } finally {
//            em.close();
//        }
//    } 
//    
}
