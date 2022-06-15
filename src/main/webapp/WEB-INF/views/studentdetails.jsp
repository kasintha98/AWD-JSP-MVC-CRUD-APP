<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Management System</title>
<meta charset="ISO-8859-1">
<style>
.back{
 background-image: url("https://eitrawmaterials.eu/wp-content/uploads/2019/05/Label-brochure-1.jpg");
}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="back">
 
 	<div class="row">
		<div class="container" style="border: 2px solid #f4d03f ; margin-top: 150px; padding:15px; border-radius: 15px; box-shadow: #eaeded  2px 2px 12px; background-color: rgb( 247, 220, 111 , 0.5)" >
			
			<h3 class="text-center" style="font-family: sans-serif; font-size: 40px; margin-top: 30px; margin-bottom:30px; font-weight: bold, padding: 25px; border-radius: 15px; background-color: rgb(  244, 246, 246  , 0.8)">ALL UOK STUDENTS</h3>
			
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/registerForm" class="btn btn-success">Register
					New Student</a>
			
			</div>
			<br>
			<table class="table table-bordered" style="margin-top: 20px;">
				<thead>
					<tr style="background-color: rgb(  244, 246, 246  , 0.8)">
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Index Number</th>
						<th>Faculty</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${listStudent}">
						<tr style="background-color: rgb(  244, 246, 246  , 0.8)">
							<td><c:out value="${student.id}" /></td>
							<td><c:out value="${student.firstName}" /></td>
							<td><c:out value="${student.lastName}" /></td>
							<td><c:out value="${student.indexNumber}" /></td>
							<td><c:out value="${student.faculty}" /></td>
							<td><a href="edit?id=<c:out value='${student.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${student.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>