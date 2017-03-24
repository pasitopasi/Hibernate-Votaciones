/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pasito
 */
public class ServletDispatcher extends HttpServlet {

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

//            String peticion = request.getParameter("boton");
            
            String nombre1 = request.getParameter("boton");
            byte[] arrByte = nombre1.getBytes("ISO-8859-1");
            String Peticion = new String(arrByte, "UTF-8");
            
            ServletContext sc = getServletContext();
            RequestDispatcher rd;
            out.print(Peticion);
            switch (Peticion) {
                case "Cierre del Escrutinio":
                    rd = sc.getRequestDispatcher("/ServletMostrarEscrutinio");
                    rd.forward(request, response);
                    break;
                case "Acceder":
                    out.print("Acceder");
                    rd = sc.getRequestDispatcher("/ServletAccesoSistema");
                    rd.forward(request, response);
                    break;
                case "Ver censo":
                    rd = sc.getRequestDispatcher("/ServletVerCenso");
                    rd.forward(request, response);
                    break;
                case "Dar de Alta":
                    rd = sc.getRequestDispatcher("/ServletInsertarUsuario");
                    rd.forward(request, response);
                    break;
                case "Desconectar":
                    rd = sc.getRequestDispatcher("/ServletDesconectarUsuario");
                    rd.forward(request, response);
                    break;
                case "Borrar":
                    rd = sc.getRequestDispatcher("/ServletBorrarUsuario");
                    rd.forward(request, response);
                    break;
                case "Votar":
                    rd = sc.getRequestDispatcher("/ServletVotar");
                    rd.forward(request, response);
                    break;
                case "Cambiar contraseña":
                    rd = sc.getRequestDispatcher("/ServletAccesoCambio");
                    rd.forward(request, response);
                    break;
                case "Cambiar":
                    rd = sc.getRequestDispatcher("/ServletCambioContra");
                    rd.forward(request, response);
                    break;
                case "Volver al menu":
                    response.sendRedirect("index.jsp");
                    break;
            }
            out.print("Algo ocurre");
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
