
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateProducerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidProducerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ProducerAPI", 
 type : type, 
 data : $("#formProducer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onProducerSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidProducerIDSave").val($(this).data("producerid")); 
 $("#plantId").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#projectId").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#firstName").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#lastName").val($(this).closest("tr").find('td:eq(3)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#userName").val($(this).closest("tr").find('td:eq(3)').text());
 $("#telephoneNo").val($(this).closest("tr").find('td:eq(3)').text());
 $("#password").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "ProducerAPI", 
 type : "DELETE", 
 data : "producerId=" + $(this).data("producerid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onProducerDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password
function validateProducerForm() 
{ 
// Plant ID
if ($("#plantId").val().trim() == "") 
 { 
 return "Insert Plant Id."; 
 } 
// Project ID
if ($("#projectId").val().trim() == "") 
 { 
 return "Insert Project ID."; 
 } 
// firstName-------------------------------
if ($("#firstName").val().trim() == "") 
 { 
 return "Insert First Name."; 
 } 
 //Last name-------------------------------------
 if ($("#lastName").val().trim() == "") 
 { 
 return "Insert Last Name."; 
 } 
 //Email-------------------------------------
 if ($("#email").val().trim() == "") 
 { 
 return "Insert Email."; 
 } 
  //userName-------------------------------------
 if ($("#userName").val().trim() == "") 
 { 
 return "Insert user name."; 
 }
// is numerical value
var telephone = $("#telephoneNo").val().trim(); 
if (!$.isNumeric(telephone)) 
 { 
 return "Insert a numerical value for telephone number."; 
 } 
 //Passowrd-------------------------------------
 if ($("#password").val().trim() == "") 
 { 
 return "Insert password."; 
 } 
return true; 
}

function onProducerSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divProducerGrid").html(resultSet.data); 
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
 $("#hidProducerIDSave").val(""); 
 $("#formProducer")[0].reset(); 
}


function onProducerDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divProducerGrid").html(resultSet.data); 
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




