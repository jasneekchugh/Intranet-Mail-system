

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class register1 extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("1");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        System.out.println("2");
            Calendar calendar = Calendar.getInstance();
	int yy       = calendar.get(Calendar.YEAR);
	int mm      = calendar.get(Calendar.MONTH)+1; // Jan = 0, dec = 11
	int dom = calendar.get(Calendar.DAY_OF_MONTH); 
        String dd=yy+"-"+mm+"-"+dom;
        int hod  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
	int min    = calendar.get(Calendar.MINUTE);
	int sec     = calendar.get(Calendar.SECOND);
        String time=hod+":"+min+":"+sec;
             HttpSession session = request.getSession();
         String fname =(String)session.getAttribute("fuser");
         String lname =(String)session.getAttribute("luser");
         String email =(String)session.getAttribute("em");
         String pass =(String)session.getAttribute("pass");
         String month =(String)session.getAttribute("mon");
         String day =(String)session.getAttribute("da");
         String year =(String)session.getAttribute("ye");
         String gender =(String)session.getAttribute("gen");
         String mobile =(String) session.getAttribute("mob");
         String country =(String)session.getAttribute("country");
         String scode =(String)session.getAttribute("secure");
         String date=year+"/"+month+"/"+day;
         String status="yes";
         System.out.println("3");
         connection ob=new connection();
         Connection con=ob.conn();
          PreparedStatement ps = con.prepareStatement("insert into user1 values(?,?,?,?,?,?,?,?,?,?)");
          System.out.println("4"); 
          ps.setString(1,fname);
           ps.setString(2,lname);
           ps.setString(3,email);
           ps.setString(4,pass);
           ps.setString(5,date);
           ps.setString(6,gender);
           ps.setString(7,mobile);
           ps.setString(8,country);
           ps.setString(9,scode);
           ps.setString(10,status);
           System.out.println("5"); 
           int rs = ps.executeUpdate();
            if(rs==1)
           {    
               System.out.println("6");
               int sno=0;
               PreparedStatement ps2 = con.prepareStatement("Select max(slno) from inbox");
                 ResultSet rs1= ps2.executeQuery();
            while(rs1.next())
            {
                System.out.println("7"); 
                sno=rs1.getInt(1);
                
            }
            sno=sno+1;
               System.out.println("8");
                String frm="admin@ifmail.com";
                String sub="Intranet Mailing System";
                String bod="Hello user, First off, welcome. And thanks for agreeing to help us test Intranet Mailing System. By now you probably know the key ways in which Intranet Mailing System differs from traditional webmail services. Searching instead of filing. A free gigabyte of storage. Messages displayed in context as conversations.So what else is new?" +
"Intranet Mailing System has many other special features that will become apparent as you use your account. To help you get started, we encourage you to visit our Help Center, there you can browse frequently asked questions, read our Getting Started guide, or contact the Gmail User Support Team. You will also find information in the Help Center on such topics as:" +
"Setting up filters for incoming mail Using advanced search options You may also have noticed some text ads or related links to the right of this message. They re placed there in the same way that ads are placed alongside  and, through our AdSense program, on content pages across the web. The matching of ads to content in your Intranet Mailing Systems messages is performed entirely by computers never by people. Because the ads and links are matched to information that is of interest to you, we hope you will find them relevant and useful. " +
"We are working hard during our limited test to improve Intranet Mailing System and make it the best webmail service around. Thanks for taking the plunge with us. We hope you’ll enjoy Madhu corp. approach to email." +
"Thanks, The Intranet Mailing System Team";
                String at="empty";
               PreparedStatement ps1 = con.prepareStatement("insert into inbox values(?,?,?,?,?,?,?,?)");
               ps1.setInt(1,sno);
               ps1.setString(2,email);
               ps1.setString(3,frm);
               ps1.setString(4,dd);
               ps1.setString(5,time);
               ps1.setString(6,sub);
               ps1.setString(7,bod);
               ps1.setString(8,at);
               ps1.executeUpdate();
              session.setAttribute("uemail",email);
              session.setAttribute("fuser", fname);
               
               RequestDispatcher rd=request.getRequestDispatcher("uview.jsp");
                rd.forward(request, response);
              
             
           }
           else
           {
                out.print("Record Not inserted");
               RequestDispatcher rd=request.getRequestDispatcher("ulogin.jsp");
                rd.include(request, response);
            
//             
           }
        } catch (Exception ex) {
            Logger.getLogger(register1.class.getName()).log(Level.SEVERE, null, ex);
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
