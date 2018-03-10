
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class author extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession ss=request.getSession();
             connection ob=new connection();
            Connection con=ob.conn();
         
            PreparedStatement ps = con.prepareStatement("select * from user1 where fname not like 'admin'");
            ResultSet rs = ps.executeQuery();
           out.print("<html><body topmargin=0 leftmargin=0> <form name='myform'>");
           out.print("<table  border=0 align=center width=100% cellpadding=5 cellspacing=1>");
            out.println("<TR bgcolor=silver><TH> User Name </TH><TH> Email ID </TH> <TH> gender </TH> <TH> country </TH><TH> unauthorize user </TH> </TR>");
            if(request.getAttribute("mssg")!=null){
            String msg=(String)request.getAttribute("mssg");
            out.print(msg);
            }
            while(rs.next())
            {
                String fname=rs.getString("fname");
                String lname=rs.getString("lname");
                String gender=rs.getString("gender");
                String country=rs.getString("country");
                String em=rs.getString("email");
             
               out.println("<TR>");
                out.println("<TD align=center>"+fname+" "+lname+"</td><td align=center>"+em+"</td><td align=center>"+gender+"</td><td align=center>"+country+"</TD><TD><a href=unauthorize?val="+em+" style=display:block;text-align:center;>Delete </a></TD></TR>");
            }
             out.print("<tr><td colspan=5 ><a href=admin.jsp style=text-decoration:none;display:block;margin-left:1240px;display:block;border:1px solid black; >Back</a></tr>");
            out.print("</form></table></body></html>");
        } catch (Exception ex) {
            Logger.getLogger(author.class.getName()).log(Level.SEVERE, null, ex);
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
