/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.Product;
import worldwine.jpa.model.controller.AccountcustomerJpaController;
import worldwine.jpa.model.controller.ProductJpaController;
import worldwine.model.Cart;
import worldwine.model.LineItem;

/**
 *
 * @author Chutikan
 */
public class AddItemToCartServlet extends HttpServlet {
@Resource
UserTransaction utx;
@PersistenceUnit (unitName = "FirstWorldWineAppPU")
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
        int AddItem = Integer.parseInt(request.getParameter("add"));
        String path = request.getParameter("pathproduct");
//        Orderlist order = new Orderlist
        Cart cart = (Cart) session.getAttribute("cart");
        ProductJpaController productCtrl = new ProductJpaController(utx, emf);
        Product p = productCtrl.findProduct(AddItem);
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
//       Integer productCode = request.getParameter("productCode");
//                System.out.println(productCode);    
          
//        for (Product product : p) {
//            System.out.println(product.getProductcode());
//        }

        cart.add(p);
//        session.setAttribute("totalprice",cart.getTotalPrice());
//        session.setAttribute("totalcart",cart.getTotalPrice2());
//        session.setAttribute("cart", cart);
//
//        request.setAttribute("line", line);
//        response.sendRedirect("Cart");
        getServletContext().getRequestDispatcher(path).forward(request, response);
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
