

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("1");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String mail=request.getParameter("uid");
            String pass=request.getParameter("upass");
            connection ob=new connection();
            Connection con=ob.conn();
            
            
            boolean status=false;
            PreparedStatement ps = con.prepareStatement("select * from user1 where email = ? and password  = ?");
            ps.setString(1,mail);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
           
           while(rs.next())
           {
               status = true;
           }
           
        
        if(status==true)
        {
            String st="Yes";
            PreparedStatement ps1 = con.prepareStatement("update user1 set online=? where email=?");
            ps1.setString(1, st);
            ps1.setString(2, mail);
            ps1.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("select fname from user1 where email=?");
            ps2.setString(1,mail);
            ResultSet rs1= ps2.executeQuery();
            while(rs1.next())
            {
                String name=rs1.getString("fname");
                HttpSession session = request.getSession();
                session.setAttribute("fuser",name);
            }
            HttpSession session = request.getSession();
                session.setAttribute("uemail",mail);
            RequestDispatcher rd=request.getRequestDispatcher("uview.jsp");
            rd.forward(request, response);
        }
        else
        {
                String registererror="Invalid Email Id or Password";
                request.setAttribute("registererror",registererror);
                RequestDispatcher rd=request.getRequestDispatcher("ulogin.jsp");
                rd.include(request,response);
                
        }
        } catch (Exception ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
