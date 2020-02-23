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
import worldwine.jpa.model.Accountcustomer;
import worldwine.jpa.model.Orderdetail;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.controller.AccountcustomerJpaController;
import worldwine.jpa.model.controller.OrderlistJpaController;

/**
 *
 * @author Chutikan
 */
public class HistoryServlet extends HttpServlet {

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
        Accountcustomer account = (Accountcustomer) session.getAttribute("account");


        if (session.getAttribute("account") == null) {
            session.setAttribute("checklogin", "/History");
            getServletContext().getRequestDispatcher("/LoginPage.jsp").forward(request, response);
        } else {
             Accountcustomer acc = accCtrl.findAccountcustomer(account.getCustomerid());
            List<Orderlist> history = acc.getOrderlistList();           
 
            request.setAttribute("history", history);
            getServletContext().getRequestDispatcher("/History.jsp").forward(request, response);

        }
        
       //  OrderlistJpaController orderCtrl = new OrderlistJpaController(utx, emf); 
//            session.getAttribute("orderdetail");
//            session.getAttribute("orderlist");  
        /* HttpSession session = request.getSession();
//        Accountcustomer account = (Accountcustomer) session.getAttribute("account");
        Orderlist order = (Orderlist) session.getAttribute("orderlist");
        AccountcustomerJpaController accCtrl = new AccountcustomerJpaController(utx, emf);
        int accHis = Integer.parseInt(request.getParameter("accHistory"));
        accCtrl.findAccountcustomer(accHis);
        Accountcustomer accountHis = order.getCustomerid();
               if(accHis == accountHis.getCustomerid() ){
            session.getAttribute("orderdetail");
        } */
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
