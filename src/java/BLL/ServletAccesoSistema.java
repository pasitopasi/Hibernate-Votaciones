/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.HibernateUtil;
import DAO.Operaciones;
import POJO.Reloj;
import POJO.Votante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;

/**
 *
 * @author pasito
 */
public class ServletAccesoSistema extends HttpServlet {

    private SessionFactory sessionbuilder;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(true);
            String dni = request.getParameter("dni");
            byte[] contrasena = request.getParameter("contrasena").getBytes();
            Votante user = new Votante(dni, contrasena);
            String salida = new Operaciones().AccesoSistema(sessionbuilder, user);
            if (salida.equals("si")) {
                //funcion que devuelva el array de relojes a votar
                ArrayList<Reloj> r = new Operaciones().ObtenerRelojes(sessionbuilder);
                //Abrimos la session
                session.setAttribute("dni", dni);
                session.setAttribute("ArrayRelojes", r);
                response.sendRedirect("Vistas/MenuVotante.jsp");
            } else {
                session.setAttribute("mensaje", "Error debido a que " + salida);
                response.sendRedirect("index.jsp");
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

    @Override
    public void init() {
        sessionbuilder = HibernateUtil.getSessionFactory();
    }
}