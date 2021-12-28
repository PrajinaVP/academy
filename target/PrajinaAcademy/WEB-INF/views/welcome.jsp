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
</div>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script type="text/javascript">
            var app = angular.module('simpleAngularJsApp',[]);
            app.controller('AccountController', function($scope, $http){
                $http.get('http://localhost:8080/simpleWeb/entry/v1/forecasting/accounts.json')
                     .then(
                        function (response) {
                            console.log("starting to fetch accounts...");
                            $scope.accounts = response.data;
                        },
                        function(errResponse){
                            console.error('Error while retrieving accounts');
                        })
            });
    </script>
</body>
</html>
