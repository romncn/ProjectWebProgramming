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
import worldwine.jpa.model.Orderdetail;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.controller.OrderdetailJpaController;
import worldwine.jpa.model.controller.OrderlistJpaController;

/**
 *
 * @author Chutikan
 */
public class BillDetailServlet extends HttpServlet {

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
        String billOr = request.getParameter("billorder");

        OrderlistJpaController orlistCtrl = new OrderlistJpaController(utx, emf);
        OrderdetailJpaController ordetailCtrl = new OrderdetailJpaController(utx, emf);

        int order = Integer.parseInt(billOr);

        Orderlist orderlist = orlistCtrl.findOrderlist(order);

        //-----------------------------------------------------------//
        List<Orderdetail> odL = ordetailCtrl.findOrderId(orderlist);

        session.setAttribute("bill", odL);
        getServletContext().getRequestDispatcher("/BillDetail.jsp").forward(request, response);
    }

//        Orderlist orderList = (Orderlist) session.getAttribute("history");
//        Orderlist od = (Orderlist) orlistCtrl.findOrderId(Integer.parseInt(billOr));
//        if (od != null) {
//            Orderlist odL = (Orderlist) orlistCtrl.findOrderId(Integer.parseInt(billOr));
//            List<Orderdetail> bill = odL.getOrderdetailList();
//            request.setAttribute("bill", bill);
//            getServletContext().getRequestDispatcher("/BillDetail.jsp").forward(request, response);
//        }
//        HttpSession session = request.getSession();
//
//        OrderlistJpaController orlistCtrl = new OrderlistJpaController(utx, emf);
//        Orderlist orderList = (Orderlist) session.getAttribute("history");
//        Orderlist odL = orlistCtrl.findOrderlist(orderList.getOrderid());
//        List<Orderdetail> bill = odL.getOrderdetailList();
//        request.setAttribute("bill", bill);
//        getServletContext().getRequestDispatcher("/BillDetail.jsp").forward(request, response);
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
