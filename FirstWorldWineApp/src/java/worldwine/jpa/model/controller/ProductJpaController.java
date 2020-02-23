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
import worldwine.jpa.model.Orderdetail;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Product;
import worldwine.jpa.model.controller.exceptions.NonexistentEntityException;
import worldwine.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Chutikan
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) throws RollbackFailureException, Exception {
        if (product.getOrderdetailList() == null) {
            product.setOrderdetailList(new ArrayList<Orderdetail>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Orderdetail> attachedOrderdetailList = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListOrderdetailToAttach : product.getOrderdetailList()) {
                orderdetailListOrderdetailToAttach = em.getReference(orderdetailListOrderdetailToAttach.getClass(), orderdetailListOrderdetailToAttach.getOrderdetailid());
                attachedOrderdetailList.add(orderdetailListOrderdetailToAttach);
            }
            product.setOrderdetailList(attachedOrderdetailList);
            em.persist(product);
            for (Orderdetail orderdetailListOrderdetail : product.getOrderdetailList()) {
                Product oldProductcodeOfOrderdetailListOrderdetail = orderdetailListOrderdetail.getProductcode();
                orderdetailListOrderdetail.setProductcode(product);
                orderdetailListOrderdetail = em.merge(orderdetailListOrderdetail);
                if (oldProductcodeOfOrderdetailListOrderdetail != null) {
                    oldProductcodeOfOrderdetailListOrderdetail.getOrderdetailList().remove(orderdetailListOrderdetail);
                    oldProductcodeOfOrderdetailListOrderdetail = em.merge(oldProductcodeOfOrderdetailListOrderdetail);
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

    public void edit(Product product) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Product persistentProduct = em.find(Product.class, product.getProductcode());
            List<Orderdetail> orderdetailListOld = persistentProduct.getOrderdetailList();
            List<Orderdetail> orderdetailListNew = product.getOrderdetailList();
            List<Orderdetail> attachedOrderdetailListNew = new ArrayList<Orderdetail>();
            for (Orderdetail orderdetailListNewOrderdetailToAttach : orderdetailListNew) {
                orderdetailListNewOrderdetailToAttach = em.getReference(orderdetailListNewOrderdetailToAttach.getClass(), orderdetailListNewOrderdetailToAttach.getOrderdetailid());
                attachedOrderdetailListNew.add(orderdetailListNewOrderdetailToAttach);
            }
            orderdetailListNew = attachedOrderdetailListNew;
            product.setOrderdetailList(orderdetailListNew);
            product = em.merge(product);
            for (Orderdetail orderdetailListOldOrderdetail : orderdetailListOld) {
                if (!orderdetailListNew.contains(orderdetailListOldOrderdetail)) {
                    orderdetailListOldOrderdetail.setProductcode(null);
                    orderdetailListOldOrderdetail = em.merge(orderdetailListOldOrderdetail);
                }
            }
            for (Orderdetail orderdetailListNewOrderdetail : orderdetailListNew) {
                if (!orderdetailListOld.contains(orderdetailListNewOrderdetail)) {
                    Product oldProductcodeOfOrderdetailListNewOrderdetail = orderdetailListNewOrderdetail.getProductcode();
                    orderdetailListNewOrderdetail.setProductcode(product);
                    orderdetailListNewOrderdetail = em.merge(orderdetailListNewOrderdetail);
                    if (oldProductcodeOfOrderdetailListNewOrderdetail != null && !oldProductcodeOfOrderdetailListNewOrderdetail.equals(product)) {
                        oldProductcodeOfOrderdetailListNewOrderdetail.getOrderdetailList().remove(orderdetailListNewOrderdetail);
                        oldProductcodeOfOrderdetailListNewOrderdetail = em.merge(oldProductcodeOfOrderdetailListNewOrderdetail);
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
                Integer id = product.getProductcode();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getProductcode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<Orderdetail> orderdetailList = product.getOrderdetailList();
            for (Orderdetail orderdetailListOrderdetail : orderdetailList) {
                orderdetailListOrderdetail.setProductcode(null);
                orderdetailListOrderdetail = em.merge(orderdetailListOrderdetail);
            }
            em.remove(product);
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

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Product> findByProductsType(String type) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByType");
            query.setParameter("type", type);
            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Product> findByProductsName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByProductname");
            query.setParameter("productname", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByProductsVintage(String vintage) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByVintage");
            query.setParameter("vintage", vintage);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByProductsPrice(String capacity) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByCapacity");
            query.setParameter("capacity", capacity);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

//    public List<Product> findByProductsAll(String name, String type, String vintage, String capacity) {
//        EntityManager em = getEntityManager();
//        try {
//            Query query = em.createNamedQuery("Product.findByAll");
//            query.setParameter("productname", "%" + name + "%");
//            query.setParameter("type", "%" + type + "%");
////            query.setParameter("vintage", Integer.parseInt(vintage));
////            query.setParameter("capacity", Integer.parseInt(capacity));
//            return query.getResultList();
//        } finally {
//            em.close();
//        }
//    }

    public Product findByProductsAll(int ListOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Product> findByAllnonYear(String name, String type, int start, int end) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByAllnonYear");
            query.setParameter("productname", "%" + name + "%");
            query.setParameter("type", "%" + type + "%");
            query.setParameter("start", start);
            query.setParameter("end", end);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByProductAll(String name, String type, int year, int start, int end) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByProductAll");
            query.setParameter("productname", "%" + name + "%");
            query.setParameter("type", "%" + type + "%");
            query.setParameter("vintage", year);
            query.setParameter("start", start);
            query.setParameter("end", end);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByAllnonYearPrice(String name, String type) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByAllnonYearPrice");
            query.setParameter("productname", "%" + name + "%");
            query.setParameter("type", "%" + type + "%");

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findByAllnonPrice(String name, String type, int year) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findByAllnonPrice");
            query.setParameter("productname", "%" + name + "%");
            query.setParameter("type", "%" + type + "%");
            query.setParameter("vintage", year);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
