/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Accountcustomer;
import worldwine.jpa.model.Orderdetail;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.Product;
import worldwine.jpa.model.controller.AccountcustomerJpaController;
import worldwine.jpa.model.controller.OrderdetailJpaController;
import worldwine.jpa.model.controller.OrderlistJpaController;
import worldwine.jpa.model.controller.ProductJpaController;
import worldwine.model.Cart;
import worldwine.model.LineItem;

/**
 *
 * @author Chutikan
 */
public class ListOrderListServlet extends HttpServlet {

    @Resource
    UserTransaction utx;
    @PersistenceUnit(unitName = "FirstWorldWineAppPU")
    EntityManagerFactory emf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountcustomerJpaController accCtrl = new AccountcustomerJpaController(utx, emf);
        Accountcustomer acc = (Accountcustomer) session.getAttribute("account");
        OrderdetailJpaController order = new OrderdetailJpaController(utx, emf);
        ProductJpaController proCtrl = new ProductJpaController(utx, emf);
        Cart cart = (Cart) session.getAttribute("cart");
//        session.getAttribute("cart");
        List<Orderdetail> detail = new ArrayList<>();
        if(cart.getLineItems() != null){
            for (LineItem item : cart.getLineItems()) {
                Orderdetail orderdetail = new Orderdetail();
orderdetail.setUnitsordered(item.getQuantity());
orderdetail.setPriceeach(item.getTotalPrice());
orderdetail.setProductcode(item.getProduct());
                try {
                    order.create(orderdetail);
                    session.setAttribute("orderdetail", orderdetail);
                } catch (Exception ex) {
                    Logger.getLogger(ListOrderListServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                detail.add(orderdetail);
            }
        }
//        int ListOrder = Integer.parseInt(request.getParameter("listorder"));
//        List<Orderdetail> detail = (List<Orderdetail>) session.getAttribute("itemdetail");
//        Orderdetail eachdetail = detail.get(ListOrder);
//        Product product = proCtrl.findByProductsAll(ListOrder);
        OrderlistJpaController orderCtrl = new OrderlistJpaController(utx, emf);
////        Orderlist orderList = new Orderlist(acc,detail);
        Orderlist orderlist = new Orderlist(new Date(), acc, detail);
        session.setAttribute("orderlist",orderlist);
        
        try {
           orderCtrl.create(orderlist);
       } catch (Exception ex) {
            Logger.getLogger(ListOrderListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/PaymentPageSucces").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
