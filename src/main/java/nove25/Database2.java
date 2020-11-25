package nove25;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database2 {

	public static void main(String[] args) throws Throwable{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/primus","root","");
	Statement smt=con.createStatement();
	smt.executeUpdate("update emp set ename='john' where ename='Akhilesh'");
	con.close();

	}

}
