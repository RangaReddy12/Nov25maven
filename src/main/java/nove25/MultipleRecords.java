package nove25;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class MultipleRecords {

	public static void main(String[] args)throws Throwable {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/primus","root","");
		Statement smt=con.createStatement();	
		FileInputStream fi= new FileInputStream("e://database.xlsx");
		Workbook wb= WorkbookFactory.create(fi);
		Sheet ws=wb.getSheetAt(0);
		int rc=ws.getLastRowNum();
		for(int i=1;i<=rc;i++)
		{
		String ename=ws.getRow(i).getCell(0).getStringCellValue();
		double eno=ws.getRow(i).getCell(1).getNumericCellValue();
		double esal=ws.getRow(i).getCell(2).getNumericCellValue();
		smt.executeUpdate("insert into emp values('"+ename+"',"+eno+","+esal+")");
		}

	}

}
