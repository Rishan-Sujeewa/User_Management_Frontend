
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------//consumer_id`,`first_name`,`last_name`,`email`,`username`, `password`,`telephone_n
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateConsumerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidConsumerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ConsumerAPI", 
 type : type, 
 data : $("#formConsumer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onConsumerSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================//consumer_id`,`first_name`,`last_name`,`email`,`username`, `password`,`telephone_n
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidConsumerIDSave").val($(this).data("consumerid")); 
 $("#first_name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#last_name").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#username").val($(this).closest("tr").find('td:eq(3)').text());
 $("#password").val($(this).closest("tr").find('td:eq(4)').text());
 $("#telephone_no").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "ConsumerAPI", 
 type : "DELETE", 
 data : "consumer_id=" + $(this).data("consumerid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onConsumerDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateConsumerForm() 
{ 
// First Name
if ($("#first_name").val().trim() == "") 
 { 
 return "Insert Frist Name."; 
 } 
// Last Name
if ($("#first_name").val().trim() == "") 
 { 
 return "Insert Last Name."; 
 } 
// Email-------------------------------
if ($("#email").val().trim() == "") 
 { 
 return "Insert Email."; 
 } 
 // User Name
 if ($("#username").val().trim() == "") 
 { 
 return "Insert User Name."; 
 } 
 //Pasword
 if ($("#password").val().trim() == "") 
 { 
 return "Insert Password."; 
 }
// is numerical value
var telephone = $("#telephone_no").val().trim(); 
if (!$.isNumeric(telephone)) 
 { 
 return "Insert a numerical value for telephone number."; 
 }  
return true; 
}

function onConsumerSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divConsumerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidConsumerIDSave").val(""); 
 $("#formConsumer")[0].reset(); 
}


function onConsumerDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divConsumerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




