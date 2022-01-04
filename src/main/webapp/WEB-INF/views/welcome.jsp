<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<body>
		<jsp:include page="header.jsp"/>
		<div class="container">
		  <h3> Welcome ${user} </h3>
		  <a href="/academy/course/async">List All Courses (Async)</a>  
		</div>
	</body>
</html>
