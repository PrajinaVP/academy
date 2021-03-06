`<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Prajina Academy - Modules</title>  
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
              <div id="moduleForm" class="formcontainer text-center">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input id="moduleId" type="hidden" ng-model="ctrl.module.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                            <div class="col-md-3">
                            </div>
                              <div class="col-md-4">
                               <label class="col-md-2 control-lable" for="file">Name</label>
                                  <input id="moduleName" type="text" ng-model="ctrl.module.name" name="name" class="academy form-control input-sm" 
                                  	placeholder="Module Name" required />
                                  <div id="nameErrorDiv" class="has-error" ng-show="myForm.$dirty">
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
                                  <input id="description" type="text" ng-model="ctrl.module.description" class="form-control input-sm" 
                                  	placeholder="Module Description"/>
                              </div>
                          </div>
                      </div>
                      
                    <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
	                              <label class="col-md-2 control-lable" for="file">Version</label>
	                              <input id="version" type="text" ng-model="ctrl.module.version" class="form-control input-sm" 
                                  	placeholder="Module Version" required/>
                                  <div id="versionErrorDiv" class="has-error" ng-show="myForm.$dirty">
                                     <span ng-show="myForm.version.$error.required">This is a required field</span>
                                     <span ng-show="myForm.version.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
	                              <label class="col-md-2 control-lable" for="file">Status</label>
	                              <input id="status" type="text" ng-model="ctrl.module.status" class="form-control input-sm" 
                                  	placeholder="Module Status"/>
                              </div>
                          </div>
                      </div>
 					  <div class="row">
                          <div class="form-group col-md-12">
                          	<div class="col-md-3">
                            </div>
                              <div class="col-md-4">
	                              <label class="col-md-2 control-lable" for="file">Contact</label>
	                              <input id="contact" type="text" id="contact" ng-model="ctrl.module.contact" class="form-control input-sm" 
                                  	placeholder="Module Contact"/>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input id="submitBtn" type="submit" value="{{ctrl.module.id ? 'Update' : 'Add'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button id="clearBtn" type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Clear Form</button>  
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div id="resultDiv" class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Modules </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover table-striped" border=1>
                      <thead>
                          <tr class="table-dark">
                              <th>Id</th>
                              <th>Name</th>
                              <th>Description</th>
                              <th>Version</th>
                              <th>Status</th>
                              <th width="10%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr class="table-dark" ng-repeat="module in ctrl.modules track by module.id">
                              <td><span ng-bind="module.id"></span></td>
                              <td><span ng-bind="module.name"></span></td>
                              <td><span ng-bind="module.description"></span></td>
                               <td><span ng-bind="module.version"></span></td>
                              <td><span ng-bind="module.status"></span></td>
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