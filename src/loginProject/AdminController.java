package loginProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminController 
{
	public static void main(String[] args) throws Exception 
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","Vishal@9615");
		
		PreparedStatement ps = connection.prepareStatement("select * from Admin where email =? And password=?");
		Scanner sc = new Scanner(System.in);
		System.out.println("<<<...Enter Your Email Sir..>>>");
		String email = sc.next();
		System.out.println("<<<..Please Enter Your Password..>>>");
		String password = sc.next();
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs= ps.executeQuery();
		
		if(rs.next()&&rs.getString(3).equals(email) && rs.getString(4).equals(password))
		{
			
			for(;;)
			{
		    System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=* WELCOME ADMIN *=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
		    System.out.println();
			System.out.println(" 1.Add EMployee      2.View Employee         3.View All EMployees");
			System.out.println(" 4.Block Employee    5.Unblock EMployee      6. Delete Employee ");
			System.out.println(" 7.Update Employee   8.Exit ");
			System.out.println();
			System.out.println("**************************************************************************");
			
				switch(sc.nextInt())
				{
				case 1 :
				{
					System.out.println("EnterId");
					int id = sc.nextInt();
					System.out.println("Enter Name");
					String name = sc.next();
					System.out.println("Enter email");
					String emaill = sc.next();
					System.out.println("Enter password");
					String passwordd = sc.next();
					System.out.println("Enter AccounStatus");
					boolean accountStatus = sc.nextBoolean();
					
					PreparedStatement ps1= connection.prepareStatement("insert into employee(id,name,email,password,accountStatus)values(?,?,?,?,?)");
					ps1.setInt(1, id);
		            ps1.setString(2, name);
		            ps1.setString(3, emaill);
		            ps1.setString(4, passwordd);
		            ps1.setBoolean(5, accountStatus);
		            int ct=ps1.executeUpdate();
					if(ct!=0)
					{
						System.out.println("Data entered successfully");
					}
					else
					{
						System.out.println("Data entry failed");
					}
		            
					break;
				}
				case 2:
				{
					System.out.println("Please Enter Employee ID :=");
					int id = sc.nextInt();
					PreparedStatement ps1 = connection.prepareStatement("select * from employee where id=?");
					ps1.setInt(1, id);
					ResultSet rs1 =ps1.executeQuery();
					if(rs1.next())
					{
						if (rs1.getBoolean("accountStatus")!=false) 
						{
							System.out.println("Emp ID :"+rs1.getInt(1));
							System.out.println("Name :"+rs1.getString("name"));
							System.out.println("Email ID :"+rs1.getString(3));
							System.out.println("Password :"+rs1.getString("password"));
							System.out.println("AccountStatus :"+rs1.getBoolean(5));
							System.out.println();
							System.out.println("*************************");	
						}
						else 
						{
							System.out.println("Employee Is Blocked");
						}
					}
					else 
					{
						System.err.println("<....No data Found");
						System.out.println("Employee is not Present >...");
					}
					break;
				}
				case 3:
				{
					Statement st = connection.createStatement();
					ResultSet rs1 =st.executeQuery("Select * from employee");
					System.err.println("!**!**! All Employee !**!**!**!");
					while(rs1.next())
					{
			        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println("ID: "+rs1.getInt(1));
					System.out.println("Name :"+rs1.getString("name"));
					System.out.println("Email Id :"+rs1.getString(3));
					System.out.println("Password :" +rs1.getString("password"));
					System.out.println("AccountStatus :"+rs1.getBoolean(5));
					System.out.println();
					System.out.println("*****************************");
					}
		
					break;
				}
				case 4:
				{
					System.out.println(" **** Enter employee id to be blocked ?????");
					int id=sc.nextInt();
					PreparedStatement preparedstatement1= connection.prepareStatement("SELECT * FROM Employee where ID=?");
					preparedstatement1.setInt(1, id);
					ResultSet resultset1=preparedstatement1.executeQuery();
					if(resultset1.next())
					{
						if(resultset1.getBoolean(5)!=false)
						{
							PreparedStatement preparedstatement2=connection.prepareStatement("UPDATE EMPLOYEE SET accountStatus = false WHERE ID=?");
							preparedstatement2.setInt(1, id);
							int ct=preparedstatement2.executeUpdate();
							if(ct!=0)
							{
								System.out.println("<*<*<*<*<* Employee blocked successfully >*>*>*>*>*");
							}
							else
							{
								System.out.println("************ Failed to block employee ***********");
							}
						}
						else
						{
							System.out.println("======= Employee is already blocked =======");
						}
					}
					else
					{
						System.out.println(" No employee found-Invalid input ;;;;;;;;");
					}
					break;
				}
			case 5:
				{	
					System.out.println("Enter Employee ID to be UnBlocked");
					int id=sc.nextInt();
					PreparedStatement preparedstatement1= connection.prepareStatement("SELECT * FROM Employee where ID=?");
					preparedstatement1.setInt(1, id);
					ResultSet resultset1=preparedstatement1.executeQuery();
					if(resultset1.next())
					{
						if(resultset1.getBoolean(5)!=true)
						{
							PreparedStatement preparedstatement2=connection.prepareStatement("UPDATE EMPLOYEE SET accountStatus = true WHERE ID=?");
							preparedstatement2.setInt(1, id);
							int ct=preparedstatement2.executeUpdate();
							if(ct!=0)
							{
								System.out.println("<<< Employee Unblocked successfully >>>");
							}
							else
							{
								System.out.println(">>> Failed to Unblocked Employee <<<");
							}
						}
						else
						{
							System.out.println("------ Employee Already Unblocked ------");
						}
					}
					else
					{
						System.out.println("****** No employee found-Invalid input ******");
					}
					break;	
				}
			case 6:
				{
					System.out.println("Enter id of employee to be deleted");
					int id=sc.nextInt();
					PreparedStatement preparedstatement1=connection.prepareStatement("DELETE from EMPLOYEE WHERE ID=?");
					preparedstatement1.setInt(1, id);
					int ct=preparedstatement1.executeUpdate();
					if(ct!=0)
					{
						System.out.println("Employee removed successfully");
					}
					else
					{
						System.out.println("Employee not found-Invalid id");
					}
					break;
				}
				case 7:
				{
					System.out.println("------------------------------------------------------------------------------------------");
					System.out.println();
					System.out.println("1. To update name    2. To update email");
					System.out.println();
					System.out.println("Enter your choice");
					int choice1=sc.nextInt();
					switch(choice1)
					{
					case 1:
					{
						System.out.println("Enter employee id");
						int id=sc.nextInt();
						System.out.println("Entered new name ");
						String name=sc.next();
						PreparedStatement preparedstatement2=connection.prepareStatement("UPDATE EMPLOYEE set NAME=? WHERE ID=?");
						preparedstatement2.setString(1, name);
						preparedstatement2.setInt(2, id);
						int ct1=preparedstatement2.executeUpdate();
						if(ct1!=0)
						{
							System.out.println("Name updated successfully");
						}
						else
						{
							System.out.println("Failed to update name-Enter valid employee id");
						}
						break;
					}
					case 2:
					{
						System.out.println("Enter employee id");
						int id=sc.nextInt();
						System.out.println("Entered new email ");
						String email1=sc.next();
						PreparedStatement preparedstatement2=connection.prepareStatement("UPDATE EMPLOYEE set EMAIL=? WHERE ID=?");
						preparedstatement2.setString(1, email1);
						preparedstatement2.setInt(2, id);
						int ct1=preparedstatement2.executeUpdate();
						if(ct1!=0)
						{
							System.out.println("<*<*<*<*<*<Email updated successfully *>*>*>*>*>");
						}
						else
						{
							System.out.println("<<<<<<<< Failed to update name-Enter valid employee id >>>>>");
						}
						break;
					}
					default:
					{
						System.out.println("Invalid input");
						break;
					}
					}
					
					break;
				}
				case 8:
				{
				
					System.out.println("Are you sure. You want to cloase the application \nY. To exit application \nN. To continue using application");
				    char ch=sc.next().charAt(0);
				    if(ch=='Y'||ch=='y')
					{
						System.out.println("Closing application {{}}");
						System.exit(0);
					}
					else if(ch=='N'||ch=='n')
					{
						break;
					}
					else
					{
						System.out.println("Invalid input ??????????? ");
					}

				 }
				default:
				{
					System.out.println("Enter valid input =======");
					break;
				}
			}
				
		}
			
		}
		else
		{
			System.out.print("===============");
			System.err.print("🙄🙄 .< WRONG CREDENTIAL >.🤨🤨");
			System.out.println("===============");
			System.out.print("=?=?=?  Please Enter Valid Email And Password  =?=?=?=");
		}
		sc.close();	
	}
	

}
