package ArcWebApp;

import java.sql.*;  
class MysqlConn{  
	public static String get_tuple(){  
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/javaTest","root","root");    
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from user");
		rs.next();
		String str = rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3);  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
		return str;
		}
	catch(Exception e){ 
			System.out.println(e);
		}  
		return "Error in Conn";
	}  
} 