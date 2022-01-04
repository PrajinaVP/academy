<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>List of Courses with AngularJS</title>
		    
		 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		 <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	</head>
	<body ng-app="myApp">
	<jsp:include page="header.jsp"/>
  	<div class="generic-container text-center" ng-controller="CourseController as ctrl">
       <table class="table table-hover table-striped" border=1>
         	<thead>
	            <tr class="table-dark">
	              	<th>Id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Status</th>
					<th>Contact</th>
	            </tr>
            </thead>
            <tbody>
              <tr class="table-dark" ng-repeat="course in ctrl.courses">
		        <td>{{course.id}}</td>  
		        <td>{{course.name}}</td>  
		        <td>{{course.desc}}</td>  
		        <td>{{course.status}}</td> 
		        <td>{{course.contact}}</td>
              </tr>
            </tbody>
        </table>
    </div>
       
    <script src="/static/js/app.js"></script>
    <script src="<c:url value='/static/js/course_service.js' />"></script>
    <script src="<c:url value='/static/js/course_controller.js' />"></script>
  </body>
</html>
