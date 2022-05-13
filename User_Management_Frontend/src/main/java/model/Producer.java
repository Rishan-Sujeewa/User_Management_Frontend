package model;
import java.sql.*; 
public class Producer {
	private Connection connect()
	{
		Connection con = null;
				
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_management_db", "root", "");
		}
				
		catch (Exception e)
		{
			e.printStackTrace();
		}
				
		return con;
	}
	
	public String insertProducer(String plantId, String projectId, String firstName, String lastName, String email,String userName, String telephoneNo,String password)
	{
		String output = "";
		try 
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
				
			// create a prepared statement
			String query = " insert into producer (`producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password`)"
								+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
			PreparedStatement preparedStmt = con.prepareStatement(query);
				
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, plantId);
			preparedStmt.setString(3, projectId);
			preparedStmt.setString(4, firstName);
			preparedStmt.setString(5, lastName);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, userName);
			preparedStmt.setInt(8, Integer.parseInt(telephoneNo));
			preparedStmt.setString(9, password);
				
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProducer = readProducer(); 
			output = "{\"status\":\"success\", \"data\": \"" + newProducer + "\"}";
				
			output = "Inserted producer details successfully";
		}
			
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the producer.\"}"; 
			System.err.println(e.getMessage());
		}
			
		return output;
	}
	
	//Read producer details
	
	public String readProducer()
	{
		
		String output = "";
			
		try
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			//prepare the html table to be display
			output = "<table border='1'><tr><th>producerId</th>" 
					 +"<th>plantId</th><th>projectId</th>"
					 +"<th>firstName</th>"+"<th>lastName</th>"+"<th>email</th>"+
					 "<th>userName</th>"+"<th>telephoneNo</th>"+"<th>password</th>"
			 + "<th>Update</th><th>Remove</th></tr>";
				
			String query = "select * from producer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
				
			// Prepare the json to be displayed
				
			// iterate through the rows in the result set
			while (rs.next()) {
				
				//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password`	
				String producerId=Integer.toString(rs.getInt("producerId"));
				String plantId=rs.getString("plantId");
				String projectId=rs.getString("projectId");
				String firstName=rs.getString("firstName");
				String lastName=rs.getString("lastName");
				String email=rs.getString("email");
				String userName=rs.getString("userName");
				String telephoneNo=Integer.toString(rs.getInt("telephoneNo"));
				String password=rs.getString("password");
				
				 // Add into the html table
				 output += "<tr><td>" + plantId + "</td>"; 
				 output += "<td>" + projectId + "</td>"; 
				 output += "<td>" + firstName + "</td>"; 
				 output += "<td>" + lastName + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + userName + "</td>"; 
				 output += "<td>" + telephoneNo + "</td>";
				 output += "<td>" + password + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
				+ "class='btnUpdate btn btn-secondary' data-producerid='" + producerId + "'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' "
				+ "class='btnRemove btn btn-danger' data-producerid='" + producerId + "'></td></tr>";
					
			}
			
				
			con.close();
			output += "</table>";
		}
			
		catch (Exception e)
		{
			output = "Error while reading the producer."; 
			 System.err.println(e.getMessage()); 
		}
			
		return output;
	}
	//Update producer details
	public String updateProducer(String producerId, String plantId, String projectId, String firstName, String lastName, String email, String userName,String telephoneNo,String password)
	{
		String output = "";
			
		try
		{
			Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
				
			// create a prepared statement
			String query = "UPDATE producer SET plantId=?,projectId=?,firstName=?,lastName=?,email=?,userName=?,telephoneNo=?,password=? WHERE producerId=?";
				
			PreparedStatement preparedStmt = con.prepareStatement(query);
				
			// binding values
			preparedStmt.setString(1, plantId);
			preparedStmt.setString(2, projectId);
			preparedStmt.setString(3, firstName);
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, userName);
			preparedStmt.setInt(7, Integer.parseInt(telephoneNo));
			preparedStmt.setString(8, password);
			preparedStmt.setInt(9, Integer.parseInt(producerId));
				
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProducer = readProducer();
			output = "{\"status\":\"success\", \"data\": \"" + 
					newProducer + "\"}";
				
			
		}
			
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the producer.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}
	//Delete producer details
	public String deleteProducer(String producerId)
	{
		String output = "error";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			
			// create a prepared statement
			String query = "delete from producer where producerId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(producerId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newProducer  = readProducer(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newProducer + "\"}";
			
		}
		
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the producer.\"}";
			System.err.println(e.getMessage());
		
		}
		
		return output;
	}


}
