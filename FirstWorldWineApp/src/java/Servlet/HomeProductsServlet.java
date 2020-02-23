/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
import worldwine.jpa.model.Product;
import worldwine.jpa.model.controller.ProductJpaController;

/**
 *
 * @author Miki
 */
public class HomeProductsServlet extends HttpServlet {

    @PersistenceUnit(unitName = "FirstWorldWineAppPU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;

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
        String type = request.getParameter("type");
//        ProductJpaController PCtrl = new ProductJpaController(utx, emf);
        ProductJpaController PCtrl = new ProductJpaController(utx, emf);

        if (type == null) {
            List<Product> product = PCtrl.findProductEntities();
            session.setAttribute("Product", product);
            getServletContext().getRequestDispatcher("/HomeProducts.jsp").forward(request, response);
        }
        
        //else {
//            if (type.equals("ALL")) {
//                List<Product> product = PCtrl.findProductEntities();
//                request.setAttribute("Product", product);
//                getServletContext().getRequestDispatcher("/AllProducts").forward(request, response);
//            }
//            List<Product> product = PCtrl.findByProductsType(type);
//            request.setAttribute("Product", product);
//            if (type.equals("RED")) {
//                getServletContext().getRequestDispatcher("/RedProducts").forward(request, response);
//            } else if (type.equals("ROSE")) {
//                getServletContext().getRequestDispatcher("/RoseProducts").forward(request, response);
//            } else if (type.equals("WHITE")) {
//                getServletContext().getRequestDispatcher("/WhiteProducts").forward(request, response);
//            } else if (type.equals("BLUE")) {
//                getServletContext().getRequestDispatcher("/SparklingProducts").forward(request, response);
//            }
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
