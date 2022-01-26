<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <body>
   <jsp:include page="header.jsp"/>
    <h1>Course List</h1>  
	<table border="2" width="80%" cellpadding="2">  
	  <tr>
	    <th>Id</th>
		<th>Name</th>
		<th>Description</th>
		<th>Status</th>
		<th>Contact</th>
		<th>Module</th>
		<th>Edit</th>
		<th>Delete</th>
	  </tr>  
   	  <c:forEach var="course" items="${courseList}">   
	    <tr>  
	       <td>${course.id}</td>  
	       <td>${course.name}</td>  
	       <td>${course.description}</td>  
	       <td>${course.status}</td> 
	       <td>${course.contact}</td>
	       <td>${course.modules}</td> 
	       <td><a href="edit/${course.id}">Edit</a></td>  
	       <td><a href="delete/${course.id}">Delete</a></td>  
	    </tr>  
	  </c:forEach>   
    </table>  
   <br/>  
   <a href="/academy/course/courseForm">Add New Course</a>  
  </body>
</html>