 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import worldwine.jpa.model.controller.AccountcustomerJpaController;

/**
 *
 * @author ROM
 */
public class LoginServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String path = request.getParameter("path");
        
        if (session.getAttribute("account") != null) {
            getServletContext().getRequestDispatcher("/MyAccount.jsp").forward(request, response);
        } else {
            if (username == null) {
                session.setAttribute("errorIn", "Username of Password isn't correct");
                getServletContext().getRequestDispatcher(path).forward(request, response);
            } else {       
                AccountcustomerJpaController accCtrl = new AccountcustomerJpaController(utx, emf);
                Accountcustomer account = (Accountcustomer) accCtrl.findAccountcustomerUsername(username);
                
                if (account == null) {
                    session.setAttribute("errorIn", "Username of Password isn't correct");
                    getServletContext().getRequestDispatcher(path).forward(request, response);
                } else {
                    if (account.getPassword().equals(password)) {
                        session.setAttribute("account", account);
                        session.setAttribute("errorIn", "");
                        getServletContext().getRequestDispatcher(path).forward(request, response);
                    } else {
                        session.setAttribute("errorIn", "\nUsername of Password isn't correct");
                        getServletContext().getRequestDispatcher(path).forward(request, response);
                    }
                }
            }
        }
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
