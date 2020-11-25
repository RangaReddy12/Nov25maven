package nove25;

import java.sql.*;


public class database1 {

	public static void main(String[] args) throws Throwable{
	//load jdbc
		Class.forName("com.mysql.jdbc.Driver");
	//connect to datase
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/primus","root","");	
Statement stm=con.createStatement();
stm.executeUpdate("insert into emp values('Akhilesh',201,50000)");
con.close();
	}

}
