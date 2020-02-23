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
import javax.transaction.UserTransaction;
import worldwine.jpa.model.Product;
import worldwine.jpa.model.controller.ProductJpaController;

/**
 *
 * @author Miki
 */
public class FilterServlet extends HttpServlet {

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
String name = request.getParameter("name");
        String type = request.getParameter("type");
        String vintage = request.getParameter("year");
        String price = request.getParameter("price");

        ProductJpaController PCtrl = new ProductJpaController(utx, emf);

        int yearint;

        try {
            if (name.length() == 0) {
                name = "";
            }
            if (type.length() == 8 || type.equals("all")) {
                type = "";
            }
            if (vintage.equals("year")) {
                if (price.equals("price")) {
                    List<Product> product = PCtrl.findByAllnonYearPrice(name, type);
                    request.setAttribute("Product", product);
                    getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                } else {
                    if (price.equals("1")) {
                        int start = 399;
                        int end = 499;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("2")) {
                        int start = 500;
                        int end = 599;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("3")) {
                        int start = 600;
                        int end = 699;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("4")) {
                        int start = 700;
                        int end = 799;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("5")) {
                        int start = 800;
                        int end = 899;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("6")) {
                        int start = 900;
                        int end = 999;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("7")) {
                        int start = 1000;
                        int end = 1999;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("8")) {
                        int start = 2000;
                        int end = 2999;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    }
                }
            } else {
                if (price.equals("price")) {
                    int year = Integer.parseInt(vintage);
                    List<Product> product = PCtrl.findByAllnonPrice(name, type, year);
                    request.setAttribute("Product", product);
                    getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                } else {
                    if (price.equals("1")) {
                        int start = 399;
                        int end = 499;
                        int year = Integer.parseInt(vintage);
                        List<Product> product = PCtrl.findByProductAll(name, type, year, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("2")) {
                        int start = 500;
                        int end = 599;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("3")) {
                        int start = 600;
                        int end = 699;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("4")) {
                        int start = 700;
                        int end = 799;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("5")) {
                        int start = 800;
                        int end = 899;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("6")) {
                        int start = 900;
                        int end = 999;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("7")) {
                        int start = 1000;
                        int end = 1999;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    } else if (price.equals("8")) {
                        int start = 2000;
                        int end = 2999;
                        List<Product> product = PCtrl.findByAllnonYear(name, type, start, end);
                        request.setAttribute("Product", product);
                        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
                    }
                }
            }

        } catch (NumberFormatException nx) {
            System.out.println(nx);
        }
//        String name = request.getParameter("name");
//        String type = request.getParameter("type");
//        String year = request.getParameter("year");
//        String capacity = request.getParameter("capacity");
//
////        int yearint = Integer.parseInt(year);
////        int priceint = Integer.parseInt(capacity);
//        ProductJpaController PCtrl = new ProductJpaController(utx, emf);
//
//        if (name.length() == 0) {
//            name = "";
//        }
//        if (type.length() == 0) {
//            type = "";
//        }
////        if (year.length() == 0) {
////            yearint = 0;
////        }
////        if (price.length() == 0) {
////            priceint = 0;
////        }
//        System.out.println(name.length());
//        System.out.println(type.length());
//
//        List<Product> product = PCtrl.findByProductsAll(name, type, year, capacity);
//        request.setAttribute("Product", product);
//        getServletContext().getRequestDispatcher("/SearchFilter.jsp").forward(request, response);
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
