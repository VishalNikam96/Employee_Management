package loginProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SaveAdmin
{
	public static void main(String[] args) throws SQLException 
	{
	   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Vishal@9615");
	   PreparedStatement preparedStatement = connection.prepareStatement("insert into Admin(id,name,email,password)values(?,?,?,?)");
	   Scanner scanner = new Scanner(System.in);
	   System.err.println("Enter Id ");
	   int id = scanner.nextInt();
	   System.out.println("Enter Name");
	   String name = scanner.next();
	   System.out.println("Enter Email");
	   String email = scanner.next();
	   System.out.println("Enter password");
	   String password = scanner.next();
	   
	   preparedStatement.setInt(1, id);
	   preparedStatement.setString(2, name);
	   preparedStatement.setString(3, email);
	   preparedStatement.setString(4, password);
	   
	 int a  = preparedStatement.executeUpdate();
	 System.out.println(a);
	   System.out.println("data savved");
	   
		
	}

}
