package model;
import java.sql.*; 

public class Consumer {
	//A common method to connect to the DB
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
			
			//Insert consumer dtails to DB table
			public String insertConsumer(String first_name, String last_name, String email, String username, String password, String telephone_no )
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
					String query = " insert into consumer (`consumer_id`,`first_name`,`last_name`,`email`,`username`, `password`,`telephone_no`)"
										+ " values (?, ?, ?, ?, ?, ?, ?)";
						
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, first_name);
					preparedStmt.setString(3, last_name);
					preparedStmt.setString(4, email);
					preparedStmt.setString(5, username);
					preparedStmt.setString(6, password);
					preparedStmt.setInt(7, Integer.parseInt(telephone_no));
						
					// execute the statement
					preparedStmt.execute();
					con.close();
					String newConsumer = readConsumer(); 
					output = "{\"status\":\"success\", \"data\": \"" + newConsumer + "\"}"; 
						
					
				}
					
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the consumer.\"}"; 
					System.err.println(e.getMessage());
				}
					
				return output;
			}
			
			//Read consumer details
			public String readConsumer()
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
					 output = "<table border='1'><tr><th>consumer_id</th>" +"<th>first_name</th><th>last_name</th>"
							 +"<th>email</th>"+"<th>username</th>"+"<th>password</th>"+
							 "<th>telephone_no</th>"
					 + "<th>Update</th><th>Remove</th></tr>";	
						
					String query = "select * from consumer";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
						
					
					
						
					// iterate through the rows in the result set
					while (rs.next()) {
						
							
						String consumer_id=Integer.toString(rs.getInt("consumer_id"));
						String first_name=rs.getString("first_name");
						String last_name=rs.getString("last_name");
						String email=rs.getString("email");
						String username=rs.getString("username");
						String password=rs.getString("password");
						String telephone_no=Integer.toString(rs.getInt("telephone_no"));
						
						 // Add into the html table
						 output += "<tr><td>" + first_name + "</td>"; 
						 output += "<td>" + last_name + "</td>"; 
						 output += "<td>" + email + "</td>"; 
						 output += "<td>" + username + "</td>";
						 output += "<td>" + password + "</td>";
						 output += "<td>" + telephone_no + "</td>"; 
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-consumerid='" + consumer_id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-consumerid='" + consumer_id + "'></td></tr>"; 
		
							
					}
				
						
					con.close();
					output += "</table>"; 
				}
					
				catch (Exception e)
				{
					output = "Error while reading the consumers."; 
					 System.err.println(e.getMessage()); 
				}
					
				return output;
			}
			
			//Update consumer details
			public String updateConsumer(String consumer_id, String first_name, String last_name, String email, String username, String password, String telephone_no) 
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
					String query = "UPDATE consumer SET first_name=?,last_name=?,email=?,username=?,password=?, telephone_no=? WHERE consumer_id=?";
						
					PreparedStatement preparedStmt = con.prepareStatement(query);
						
					// binding values
					preparedStmt.setString(1, first_name);
					preparedStmt.setString(2, last_name);
					preparedStmt.setString(3, email);
					preparedStmt.setString(4, username);
					preparedStmt.setString(5, password);
					preparedStmt.setInt(6, Integer.parseInt(telephone_no));
					preparedStmt.setInt(7, Integer.parseInt(consumer_id));
						
					// execute the statement
					preparedStmt.execute();
					con.close();
					String newConsumer = readConsumer();
					output = "{\"status\":\"success\", \"data\": \"" + 
							newConsumer + "\"}"; 
						
					
				}
					
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while updating the consumer.\"}";
					System.err.println(e.getMessage());
				}
					
				return output;
			}
			//Delete consumer details
			public String deleteConsumer(String consumer_id)
			{
				String output = "";
				
				try
				{
					Connection con = connect();
					
					if (con == null)
					{
						return "Error while connecting to the database for deleting."; 
					}
					
					// create a prepared statement
					String query = "delete from consumer where consumer_id=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(consumer_id));
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newConsumer  = readConsumer(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
							 newConsumer + "\"}"; 
				}
				
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the consumer.\"}";
					System.err.println(e.getMessage());
				}
				
				return output;
			}

}
