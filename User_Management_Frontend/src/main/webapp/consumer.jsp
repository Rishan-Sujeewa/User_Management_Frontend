<%@page import="model.Consumer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("first_name") != null) 
{ 
 Consumer consumerObj = new Consumer(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidConsumerIDSave") == "") 
 { 
 			stsMsg = consumerObj.insertConsumer( 
			request.getParameter("first_name"), 
			request.getParameter("last_name"), 
			request.getParameter("email"),
	        request.getParameter("username"),
	        request.getParameter("password"),
	        request.getParameter("telephone_no")); 
 } 
else//Update----------------------
 { 
 stsMsg = consumerObj.updateConsumer(
 request.getParameter("hidConsumerIDSave"), 
 request.getParameter("first_name"), 
 request.getParameter("last_name"), 
 request.getParameter("email"),
 request.getParameter("username"),
 request.getParameter("password"),
 request.getParameter("telephone_no")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidConsumerDelete") != null) 
{ 
 Consumer consumerObj = new Consumer(); 
 String stsMsg = 
 consumerObj.deleteConsumer(request.getParameter("hidConsumerDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumer Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/consumer.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Consumer Management V10.1</h1>
<form id="formConsumer" name="formConsumer">
 First_Name: 
 <input id="first_name" name="first_name" type="text" 
 class="form-control form-control-sm">
 <br> Last_Name: 
 <input id="last_name" name="last_name" type="text" 
 class="form-control form-control-sm">
 <br> Email: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br> User_Name: 
 <input id="username" name="username" type="text" 
 class="form-control form-control-sm">
 <br>Password: 
 <input id="password" name="password" type="text" 
 class="form-control form-control-sm">
 <br>Telephone_No: 
 <input id="telephone_no" name="telephone_no" type="text" 
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidConsumerIDSave" 
 name="hidConsumerIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divConsumerGrid">
 <%
 	Consumer consumerObj = new Consumer(); 
 	out.print(consumerObj.readConsumer()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>