<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="container">
		  <h3> Welcome ${user} </h3>
		  <a href="/academy/course/async">List All Courses (Async)</a>  
		</div>
	</body>
</html>
