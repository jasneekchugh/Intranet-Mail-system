
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class upda extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             HttpSession session = request.getSession();
       String name =(String)session.getAttribute("fuser");
            
        String pname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String mob=request.getParameter("phn");
        String coun=request.getParameter("con");
        
            connection ob=new connection();
            Connection con=ob.conn();
            
            PreparedStatement ps = con.prepareStatement("update user1 set fname=?,lname=?,mobile=?,country=? where fname=?");
           ps.setString(1, pname);
           ps.setString(2, lname);
           ps.setString(3, mob);
           ps.setString(4, coun);
           ps.setString(5, name);
           int rs = ps.executeUpdate();
            if(rs==1)
           {
              out.println ("<html><body><script>alert('Profile Updated');</script></body></html>");
               RequestDispatcher rd=request.getRequestDispatcher("up1.jsp");
                rd.include(request, response);
               
             
           }
           else
           {
               out.println ("<html><body><script>alert('Profile not updated');</script></body></html>");
               RequestDispatcher rd=request.getRequestDispatcher("up1.jsp");
                rd.include(request, response);
            
              
           }
     
        } catch (Exception ex) {
            Logger.getLogger(upda.class.getName()).log(Level.SEVERE, null, ex);
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
