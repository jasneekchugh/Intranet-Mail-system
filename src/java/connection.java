
import java.sql.Connection;
import java.sql.*;

import java.sql.DriverManager;


public class connection {
    
    Connection conn() throws Exception
    {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/email","root","root");
            return  con;
            
    }
    
}
