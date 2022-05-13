<%@page import="model.Producer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("plantId") != null) 
{ 
 Producer producerObj = new Producer(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidProducerIDSave") == "") 
 { 
 stsMsg = producerObj.insertProducer(
		 request.getParameter("plantId"), 
		 request.getParameter("projectId"), 
		 request.getParameter("firstName"),
	     request.getParameter("lastName"),
	     request.getParameter("email"),
	     request.getParameter("userName"),
	     request.getParameter("telephoneNo"),
	     request.getParameter("password")); 
 } 
else//Update----------------------
 { 
 stsMsg = producerObj.updateProducer(
 request.getParameter("hidProducerIDSave"), 
 request.getParameter("plantId"), 
 request.getParameter("projectId"), 
 request.getParameter("firstName"),
 request.getParameter("lastName"),
 request.getParameter("email"),
 request.getParameter("userName"),
 request.getParameter("telephoneNo"),
 request.getParameter("password"));
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidProducerIDDelete") != null) 
{ 
    Producer producerObj = new Producer(); 
	String stsMsg = ""; 
	producerObj.deleteProducer(request.getParameter("hidProducerIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Producer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/producer.js"></script>
</head>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Producer Management V10.1</h1>
<form id="formProducer" name="formProducer">
 
  Plant_ID: 
 <input id="plantId" name="plantId" type="text" 
 class="form-control form-control-sm">
 <br> Project_ID: 
 <input id="projectId" name="projectId" type="text" 
 class="form-control form-control-sm">
 <br> First_Name: 
 <input id="firstName" name="firstName" type="text" 
 class="form-control form-control-sm">
 <br> Last_Name: 
 <input id="lastName" name="lastName" type="text" 
 class="form-control form-control-sm">
 <br>Email: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br>User_Name: 
 <input id="userName" name="userName" type="text" 
 class="form-control form-control-sm">
 <br>Telephone_No: 
 <input id="telephoneNo" name="telephoneNo" type="text" 
 class="form-control form-control-sm">
 <br>Password: 
 <input id="password" name="password" type="text" 
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidProducerIDSave" 
 name="hidProducerIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divProducerGrid">
 <%
 Producer producerObj = new Producer(); 
 out.print(producerObj.readProducer()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
