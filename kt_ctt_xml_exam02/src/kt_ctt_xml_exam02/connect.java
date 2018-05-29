package kt_ctt_xml_exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connect {
	
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;

	{
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kt_api", "root", "1234");
			
//			System.out.println("DB 연결이 완료되었습니다. ^^");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결에 실패하였으니 확인해 주세요! :D");
		}
		

	}	
}
