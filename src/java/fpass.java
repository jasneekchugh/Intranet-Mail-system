
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


public class fpass extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String mail=request.getParameter("email");
            String code=request.getParameter("code");
            connection ob=new connection();
             Connection con=ob.conn();
             boolean status=false;
                        
            PreparedStatement ps=con.prepareStatement("select * from user1 where email=? and scode=?");
            ps.setString(1,mail);
            ps.setString(2,code);
            ResultSet rs =ps.executeQuery();
            while(rs.next())
            {
                status=true;
            }
            if(status==true)
            {
              HttpSession session = request.getSession();
              session.setAttribute("em",mail);
              RequestDispatcher rd=request.getRequestDispatcher("reset.jsp");
              rd.forward(request, response);  
            
            }
            else
            {
                out.print("Invalid E-mail or Security Code");
                RequestDispatcher rd=request.getRequestDispatcher("fop.jsp");
                rd.include(request, response);
                
                     
            }
        } catch (Exception ex) {
            Logger.getLogger(fpass.class.getName()).log(Level.SEVERE, null, ex);
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
