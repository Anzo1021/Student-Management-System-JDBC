import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {
        //About Connection
    /*This is the return type of the method.
    It means this method will return a Connection object from the java.sql package.
     That Connection object represents your live link (bridge) between:
            Your Java program and Your MySQL database.*/
        public static Connection getconnection() {
            Connection con=null;
            try {
                String url = "jdbc:mysql://localhost:3306/student";
                String root = "root";
                String password = "Jaiswal";

           con = DriverManager.getConnection(url, root, password);
                System.out.println("connection established");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return con;
        }
    }

