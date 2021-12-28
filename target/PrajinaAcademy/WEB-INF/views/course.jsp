<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Prajina Academy</title>  
  </head>
  <body ng-app="myAcademyApp" class="ng-cloak">
    <h1>Course List</h1>  
	<table border="2" width="70%" cellpadding="2">  
	  <tr>
	    <th>Id</th>
		<th>Name</th>
		<th>Designation</th>
		<th>Status</th>
		<th>Contact</th>
		<th>Edit</th>
		<th>Delete</th>
	  </tr>  
   	  <c:forEach var="course" items="${courses}">   
	    <tr>  
	       <td>${course.id}</td>  
	       <td>${course.name}</td>  
	       <td>${course.desc}</td>  
	       <td>${course.status}</td> 
	       <td>${course.contact}</td>
	       <td><a href="edit/${course.id}">Edit</a></td>  
	       <td><a href="delete/${course.id}">Delete</a></td>  
	    </tr>  
	  </c:forEach>   
    </table>  
   <br/>  
   <a href="courseForm">Add New Course</a>  
  </body>
</html>