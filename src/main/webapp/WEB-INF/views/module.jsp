<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
     <%-- <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
  </head>
  <body ng-app="myApp">
  	<jsp:include page="header.jsp"/>
      <div class="generic-container text-center" ng-controller="ModuleController as ctrl">
          <div class="panel panel-default">
              <div class="d-flex justify-content-center"><span class="lead">Module Form </span></div>
              <div class="formcontainer text-center">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.module.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                            <div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                               <label class="col-md-2 control-lable" for="file">Name</label>
                                  <input type="text" ng-model="ctrl.module.name" name="name" class="academy form-control input-sm" 
                                  	placeholder="Module Name" required />
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.name.$error.required">This is a required field</span>
                                      <span ng-show="myForm.name.$invalid">This field is invalid </span>
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
                                  <input type="text" ng-model="ctrl.module.desc" class="form-control input-sm" 
                                  	placeholder="Module Description"/>
                              </div>
                          </div>
                      </div>
                      
                    <!--  <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
	                              <label class="col-md-2 control-lable" for="file">Status</label>
	                              <input type="text" ng-model="ctrl.module.status" class="form-control input-sm" 
                                  	placeholder="Module Status"/>
                              </div>
                          </div>
                      </div>
                       -->
                      <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                              	 <label class="col-md-2 control-lable" for="file">Course Id</label>
                                 <input type="number" ng-model="ctrl.module.courseId" name="courseId" class="courseId form-control input-sm" 
                                  	placeholder="Course Id" />
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{ctrl.module.id ? 'Update' : 'Add'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Clear Form</button>  
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Modules </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover table-striped" border=1>
                      <thead>
                          <tr class="table-dark">
                              <th>Id</th>
                              <th>Name</th>
                              <th>Description</th>
                              <th>Course Id</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr class="table-dark" ng-repeat="module in ctrl.modules track by module.id">
                              <td><span ng-bind="module.id"></span></td>
                              <td><span ng-bind="module.name"></span></td>
                              <td><span ng-bind="module.desc"></span></td>
                              <td><span ng-bind="module.courseId"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(module.id)" class="btn btn-success custom-width">Edit</button>
                              <button type="button" ng-click="ctrl.remove(module.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/module_service.js' />"></script>
      <script src="<c:url value='/static/js/module_controller.js' />"></script>
  </body>
</html>