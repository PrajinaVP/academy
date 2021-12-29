<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
  <title>Prajina Academy</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
  
<nav id="navbar" class="navbar class="navbar navbar-light" style="background-color: #e3f2fd;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Home</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="nav-item"><a class="nav-link" href="/PrajinaAcademy/courses">Courses</a></li>
      <li class="nav-item"><a class="nav-link" href="#login">Modules</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Assignments</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <h3> Welcome ${firstname} </h3>
  <a href="/PrajinaAcademy/v1/courses">List All Courses (Async)</a>  
</div>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script> 
</body>
</html>
