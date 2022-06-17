<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Student</title>
<style>
.back{
 background-image: url("https://eitrawmaterials.eu/wp-content/uploads/2019/05/Label-brochure-1.jpg");
}

.mmt{
margin-top: 20px;

.w-75{
width: 50%;
}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="back">
 
 	<div class="container" align="center" style="border: none; margin-top: 150px; 
 		 width: 50%; margin-left: auto; margin-right: auto; padding: 20px; 
 		 border-radius: 15px; box-shadow: #eaeded  2px 2px 12px; background-color: rgb( 247, 220, 111 , 0.5)">
				<h3 class="text-center mt-3 mb-3" style="font-family: sans-serif; margin-top: 30px; margin-bottom:30px; font-weight: bold, padding: 25px; border-radius: 15px; background-color: rgb(  244, 246, 246  , 0.8); font-size: 40px;">
					<c:if test="${student != null}">
            		Edit Student
            		</c:if>
					<c:if test="${student == null}">
            		Register New Student
            		</c:if>
				</h3>
				
				<c:if test="${student != null}">
					<form action="<%= request.getContextPath() %>/update" method="post">
				</c:if>
				<c:if test="${student == null}">
					<form action="<%= request.getContextPath() %>/register" method="post">
				</c:if>

				
				<table>
				    <tr class="mmt">
					     <td>First Name</td>
					     <td >
					     	<input type="hidden" value="<c:out value='${student.id}' />" class="form-control" name="id">
							<input type="text" value="<c:out value='${student.firstName}' />" class="form-control" name="firstName" required="required">							
					     </td>
				    </tr>
				      <tr class="mmt">
					     <td>Last Name</td>
					     <td>
							<input type="text" value="<c:out value='${student.lastName}' />" class="form-control" name="lastName" required="required">							
					     </td>
				    </tr>
				      <tr class="mmt">
					     <td>Index Number</td>
					     <td>
							<input type="text" value="<c:out value='${student.indexNumber}' />" class="form-control" name="indexNumber" required="required">					
					     </td>
				    </tr>
				   <tr class="mmt">
					     <td>Faculty</td>
					     <td>
							<input type="text" value="<c:out value='${student.faculty}' />" class="form-control" name="faculty" required="required">					
					     </td>
				    </tr>
				</table>
				
				<button type="submit" class="btn btn-success mmt w-75" >			   
					<c:if test="${student != null}">
            			Update
            		</c:if>
					<c:if test="${student == null}">
            			Add
            		</c:if>	    				   
				</button>
				
				</form>
	</div>
</body>
</html>