 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>Course List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Designation</th>
	<th>Status</th>
	<th>Contact</th>
	<th>Edit</th>
	<th>Delete</th></tr>  
   <c:forEach var="course" items="${courses}">   
   <tr>  
   <td>${course.id}</td>  
   <td>${course.name}</td>  
   <td>${course.description}</td>  
   <td>${course.status}</td> 
   <td>${course.contact}</td>
   <td><a href="edit/${course.id}">Edit</a></td>  
   <td><a href="delete/${course.id}">Delete</a></td>  
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   <a href="empform">Add New Course</a>  