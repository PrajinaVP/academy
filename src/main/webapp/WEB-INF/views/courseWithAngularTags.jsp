<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Prajina Academy</title>  
    <style>
      .academy.name.ng-valid {
          background-color: green;
      }
      .academy.name.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .academy.ng-dirty.ng-invalid-minlength {
          background-color: orange;
      }

      .email.ng-dirty.ng-invalid-email {
          background-color: orange;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myAcademyApp" class="ng-cloak">
      <div class="generic-container text-center" ng-controller="CourseController as ctrl">
          <div class="panel panel-default">
              <div class="d-flex justify-content-center"><span class="lead">Course Registration Form </span></div>
              <div class="formcontainer text-center">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.course.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                            <div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                               <label class="col-md-2 control-lable" for="file">Name</label>
                                  <input type="text" ng-model="ctrl.course.name" name="name" class="academy form-control input-sm" 
                                  	placeholder="Course Name" required />
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                               
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                                  <input type="text" ng-model="ctrl.course.description" class="form-control input-sm" 
                                  	placeholder="Course Description"/>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                             	 <label class="col-md-2 control-lable" for="file">Modules</label>
                                  <input type="text" ng-model="ctrl.course.module" class="form-control input-sm" 
                                  	placeholder="Enter Choices for Poll"/>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                                  <input type="text" ng-model="ctrl.poll.choice2" class="form-control input-sm" 
                                  	placeholder="Enter Choices for Poll"/>
                              </div>
                              	  <label class="col-md-2 control-lable" for="file">Choice 2</label>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                                  <input type="email" ng-model="ctrl.course.email" name="email" class="email form-control input-sm" 
                                  	placeholder="Contact Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                              		  <label class="col-md-2 control-lable" for="file">Email</label>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{ctrl.course.id ? 'Update' : 'Add'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Clear Form</button>  
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Courses </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover table-striped">
                      <thead>
                          <tr class="table-dark">
                              <th>#</th>
                              <th>Name</th>
                              <th>Description</th>
                              <th>Status</th>
                              <th>Modules</th>
                              <th>Contact</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr class="table-dark" ng-repeat="course in ctrl.courses">
                              <td><span ng-bind="course.id"></span></td>
                              <td><span ng-bind="course.name"></span></td>
                              <td><span ng-bind="course.desc"></span></td>
                              <td><span ng-bind="course.status"></span></td>
                              <td><a ng-href="/module">Modules</a>	</td>
                              <td><span ng-bind="course.contact"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(course.id)" class="btn btn-success custom-width">Edit</button>
                              <button type="button" ng-click="ctrl.remove(course.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
  </body>
</html>