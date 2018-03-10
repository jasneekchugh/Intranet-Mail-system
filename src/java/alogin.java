

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;


public class alogin extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name=request.getParameter("aname");
            String pass=request.getParameter("apass");
            connection ob=new connection();
             Connection con=ob.conn();
             boolean status=false;
            //used to fetch data from the database.
            PreparedStatement ps=con.prepareStatement("select * from admin1 where aname=? and apass=?");
            ps.setString(1,name);
            ps.setString(2,pass);
            ResultSet rs =ps.executeQuery();
            while(rs.next())
            {
                status=true;
            }
            if(status==true)
            {
              RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
              rd.forward(request, response);  
            
            }
            else
            {
                String registererror="Invalid Email Id or Password";
                request.setAttribute("registererror",registererror);
                RequestDispatcher rd=request.getRequestDispatcher("alogin.jsp");
                rd.include(request, response);
                
                     
            }
        } catch (Exception ex) {
            Logger.getLogger(alogin.class.getName()).log(Level.SEVERE, null, ex);
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
