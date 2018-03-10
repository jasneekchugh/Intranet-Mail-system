
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

 @WebServlet("/send")
 @MultipartConfig(maxFileSize = 16177215)


public class send extends HttpServlet {
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("attach");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        
        try (PrintWriter out = response.getWriter()) {
         
            HttpSession session = request.getSession();
            String mail=(String)session.getAttribute("uemail");
            String to=request.getParameter("recem");
            String sub=request.getParameter("sub");
            String message=request.getParameter("message");
            Calendar calendar = Calendar.getInstance();
            int yy       = calendar.get(Calendar.YEAR);
            int mm      = calendar.get(Calendar.MONTH)+1; // Jan = 0, dec = 11
            int dom = calendar.get(Calendar.DAY_OF_MONTH); 
            String dd=yy+"-"+mm+"-"+dom;
            int hod  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
            int min    = calendar.get(Calendar.MINUTE);
            int sec     = calendar.get(Calendar.SECOND);
            String time=hod+":"+min+":"+sec;
            boolean status=false;
            int sno=0;
           connection ob=new connection();
            Connection con=ob.conn();
            PreparedStatement ps = con.prepareStatement("select * from user1 where email = ? ");
            ps.setString(1,to);
            ResultSet rs = ps.executeQuery();
             while(rs.next())
           {
               status = true;
           }
           if(status==true)
           {   
            
            PreparedStatement ps2 = con.prepareStatement("Select max(slno) from inbox");
            ResultSet rs1= ps2.executeQuery();
            while(rs1.next())
            {
                 sno=rs1.getInt(1);
                
            }
            sno=sno+1;
            String at=request.getParameter("attach");
            PreparedStatement ps1 = con.prepareStatement("insert into inbox values(?,?,?,?,?,?,?,?)");
               ps1.setInt(1,sno);
               ps1.setString(2,to);
               ps1.setString(3,mail);
               ps1.setString(4,dd);
               ps1.setString(5,time);
               ps1.setString(6,sub);
               ps1.setString(7,message);
               if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps1.setBlob(8, inputStream);
            }
               
               ps1.executeUpdate();
               
               PreparedStatement ps3 = con.prepareStatement("insert into sent values(?,?,?,?,?,?,?,?)");
               ps3.setInt(1,sno);
               ps3.setString(2,to);
               ps3.setString(3,mail);
               ps3.setString(4,dd);
               ps3.setString(5,time);
               ps3.setString(6,sub);
               ps3.setString(7,message);
               if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps3.setBlob(8, inputStream);
            }
               ps3.executeUpdate();
               out.println ("<html><body><script>alert('Message Sent!');</script></body></html>");
               RequestDispatcher rd=request.getRequestDispatcher("sendmail.jsp");
               rd.include(request, response);
           }
           else
           {
               out.println ("<html><body><script>alert('Email Id does not exist!');</script></body></html>");
                RequestDispatcher rd=request.getRequestDispatcher("sendmail.jsp");
                rd.include(request, response);
           }
 
        } catch (Exception ex) {
            Logger.getLogger(send.class.getName()).log(Level.SEVERE, null, ex);
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
