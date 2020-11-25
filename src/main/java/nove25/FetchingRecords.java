package nove25;

import java.sql.*;


public class FetchingRecords {

	public static void main(String[] args)throws Throwable {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/primus","root","");
		Statement smt=con.createStatement();
	ResultSet rs=smt.executeQuery("select *from emp");
	while(rs.next())
	{
		//System.out.println(rs.getString(1)+"  "+rs.getInt(2)+"  "+rs.getInt(3));
		//System.out.println(rs.getString(1));
		System.out.println(rs.getString(1)+"   "+rs.getInt(3));
	}

	}

}
