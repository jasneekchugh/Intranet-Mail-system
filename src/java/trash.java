
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


public class trash extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
              HttpSession session = request.getSession();
           String uem=(String)session.getAttribute("uemail");
           
            connection ob=new connection();
            Connection con=ob.conn();
            
            boolean status=false;
            PreparedStatement ps = con.prepareStatement("select * from trash where uemail=? Order by drec Desc,trec Desc");
            ps.setString(1,uem);
           
            ResultSet rs = ps.executeQuery();
            
           if(rs.next())
           {
               status=true;
               out.print("<html><body topmargin=0 leftmargin=0> <form name='myform'>");
               out.print("<table  border=0 align=center width=100% cellpadding=5 cellspacing=1>");
               out.println("<TR bgcolor=silver><TH> From E-Mail </TH><TH> Subject </TH> <TH> Date </TH> <TH> Time </TH><TH> Delete Mail </TH> </TR>");
               if(request.getAttribute("mssg")!=null){
               String msg=(String)request.getAttribute("mssg");
               out.print(msg);
               }
               PreparedStatement ps1 = con.prepareStatement("select * from trash where uemail=? Order by drec Desc,trec Desc");
            ps1.setString(1,uem);
            
            ResultSet rs1 = ps1.executeQuery();
                while(rs1.next())
                {
               
                    String femail=rs1.getString("fromemail");
                    String sub=rs1.getString("Subject");
                    String rdate=rs1.getString("drec");
                    String rtime=rs1.getString("trec");
                    String detail=rs1.getString("content");
                    int sno=rs.getInt(1);
                    out.println("<TR>");
                    out.println("<TD><a href=tshow.jsp?id="+sno+" style=text-decoration:none;display:block;text-align:center;>"+femail+"</a></TD><TD><a href=tshow.jsp?id="+sno+" style=text-decoration:none;display:block;text-align:center;>"+sub+"</A></TD><TD><a href=tshow.jsp?id="+sno+" style=text-decoration:none;display:block;text-align:center;>"+rdate+"</A></TD><TD><a href=tshow.jsp?id="+sno+" style=text-decoration:none;display:block;text-align:center;>"+rtime+"</A></TD></A><TD><a href=tdelete?val="+sno+" style=display:block;text-align:center;>Delete </a></TD></TR>");
                }
            
                    out.print("</form></table></body></html>");
           }
            if(status==false)
            {
                out.println ("<html><body><script>alert('Trash Empty');</script></body></html>");
                RequestDispatcher rd=request.getRequestDispatcher("empty.jsp");
                rd.include(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(trash.class.getName()).log(Level.SEVERE, null, ex);
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
