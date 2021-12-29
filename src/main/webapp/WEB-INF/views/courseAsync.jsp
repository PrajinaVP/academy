<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Show Courses with angularjs</title>
    
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
</head>
<body ng-app="myApp">
    <div class="generic-container" ng-controller="CourseController as ctrl">
        <table border="1">
            <tr>
              	<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Status</th>
				<th>Contact</th>
            </tr>
            <tbody>
              <tr ng-repeat="course in ctrl.courses">
		        <td>${course.id}</td>  
		        <td>${course.name}</td>  
		        <td>${course.desc}</td>  
		        <td>${course.status}</td> 
		        <td>${course.contact}</td>
              </tr>
            </tbody>
        </table>
    </div>
       
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/service/course_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/course_controller.js' />"></script>
  </body>
</html>
