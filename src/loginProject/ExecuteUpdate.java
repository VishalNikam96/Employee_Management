package loginProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdate
{
	public static void main(String[] args) throws Exception {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Vishal@9615");
	   Statement st = connection.createStatement();
	   int a = st.executeUpdate("insert into Admin(id,name,email,password)values(4,'Amit','Amit@gmail.com','Amit564')");
	   int b = st.executeUpdate("delete from Admin where id=3");
	   int c =st.executeUpdate("update Admin set password ='amit666' where id =1");
	   int d = st.executeUpdate("update admin set name ='Vishal'");
	   
	 
	   System.out.println(a);
	   System.out.println(b);
	   System.out.println(c);
	   System.out.println(d);
	   
	}

}
