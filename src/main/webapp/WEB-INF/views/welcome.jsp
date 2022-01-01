<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>

<body>
  
 <jsp:include page="header.jsp"/>
  
<div class="container">
  <h3> Welcome ${user} </h3>
  <a href="/v1/courses">List All Courses (Async)</a>  
</div>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script> 
</body>
</html>
