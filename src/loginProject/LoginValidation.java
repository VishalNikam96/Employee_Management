package loginProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginValidation 
{
	public static void main(String[] args) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Vishal@9615");
	
		PreparedStatement ps = connection.prepareStatement("select * from Admin where email =? And password=?");
		System.out.println("Enter EMail");
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		 
		String email = sc.next();
		System.out.println("Enter Pasord");
		String password = sc.next();
		
		ps.setString(1, email);
		
		ps.setString(2, password);
		
		java.sql.ResultSet rs= ps.executeQuery();
		
		if(rs.next())
		{
			System.err.println("Welcome");
		}
		else {
			System.err.println("Weong Credintial");
		}
		
	}

}
