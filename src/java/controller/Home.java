/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BookDAO;
import dal.CategoryDAO;
import dal.CompanyDAO;
import dal.CoverTypeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Book;

/**
 *
 * @author anh21
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("search");
        ArrayList<String> categorys = new ArrayList<>(),companys = new ArrayList<>(),covers = new ArrayList<>();
        if(request.getParameterValues("category")!=null)categorys.addAll(Arrays.asList(request.getParameterValues("category")));
        if(request.getParameterValues("company")!=null)companys.addAll(Arrays.asList(request.getParameterValues("company")));
        if(request.getParameterValues("coverType")!=null)covers.addAll(Arrays.asList(request.getParameterValues("coverType")));
        ArrayList<Book> books;
        if(request.getParameter("sort")==null)books = new BookDAO().getListBookBy(search, companys, categorys, covers, "BookID");
        else if(request.getParameter("sort").equals("1")) books = new BookDAO().getListBookBy(search, companys, categorys, covers, "Price");
        else books = new BookDAO().getListBookBy(search, companys, categorys, covers, "Price desc");
        HttpSession session=request.getSession();
        session.setAttribute("category", new CategoryDAO().getAllCategory());
        session.setAttribute("company", new CompanyDAO().getAllCompany());
        session.setAttribute("coverType", new CoverTypeDAO().getAllCoverType());
        session.setAttribute("books", books);
        request.getRequestDispatcher("home.jsp").forward(request, response);
        
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
