package nove25;
import java.sql.*;
public class Fetching {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection
		("jdbc:mysql://localhost:3306/primus","root","");
		   Statement stmt=con.createStatement();
//ResultSet rs=stmt.executeQuery("Select * from emp where esal=(select max(esal) from emp)");
ResultSet rs=stmt.executeQuery("Select * from emp where esal=(select max(esal) from emp where esal <(select max(esal) from emp))");
		   while(rs.next())
		   {
			  //System.out.println("max sal is:::"+rs.getInt("esal"));
			  System.out.println("second sal is:::"+rs.getInt("esal"));
		   		   }
	}

}
