
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

public class delete extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                  
            Integer v=Integer.parseInt(request.getParameter("val"));
                 String femail="";
                 String ue="";
                 String sub="";
                 String date="";
                 String time="";
                 String detail="";
                 String at="";
            connection ob=new connection();
            Connection con=ob.conn();
            PreparedStatement ps1 = con.prepareStatement("select * from inbox where slno =?");
                ps1.setInt(1,v);
                ResultSet rs1 = ps1.executeQuery();
           
                 while(rs1.next())
                  {
             
                   ue=rs1.getString("uemail");
                   femail=rs1.getString("FromEmail");
                   sub=rs1.getString("Subject");
                   date=rs1.getString("recdate");
                   time=rs1.getString("rectime");
                   detail=rs1.getString("content");
                   at=rs1.getString("attachment");
                  }
                  
            PreparedStatement ps2 = con.prepareStatement("insert into trash values(?,?,?,?,?,?,?,?)");
               ps2.setInt(1,v);
               ps2.setString(2,ue);
               ps2.setString(3,femail);
               ps2.setString(4,date);
               ps2.setString(5,time);
               ps2.setString(6,sub);
               ps2.setString(7,detail);
               ps2.setString(8, at);
               ps2.executeUpdate();
            PreparedStatement ps=con.prepareStatement("delete from inbox where slno =?");
            ps.setInt(1,v);
            ps.executeUpdate();
         out.println ("<html><body><script>alert('Sucessfully Deleted');</script></body></html>");
         String msg="The mail has been moved to the Trash and will be permanently deleted in 30 days";
         request.setAttribute("mssg",msg);
                RequestDispatcher rd=request.getRequestDispatcher("inbox");
                rd.include(request, response);
               
            
        }
             catch (Exception ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
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
