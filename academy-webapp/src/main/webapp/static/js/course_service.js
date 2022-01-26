(function() {
	'use strict';
	angular.module('myApp')
		.factory('CourseService', CourseService);
		
	CourseService.$inject = ['$http', '$q', '$log'];
	
	function CourseService($http, $q, $log) {
		var REST_SERVICE_URI = 'http://localhost:8080/course/'; 
		
		var service = {
	        fetchAllCourses: fetchAllCourses,
	        createCourse: createCourse,
	        updateCourse: updateCourse,
	        deleteCourse: deleteCourse
    	};

		return service;
		
		function fetchAllCourses() {
	 
	      return $http.get(`${REST_SERVICE_URI}`)
	            .then( function (response) {
					$log.debug(`service data :: ${JSON.stringify(response)}`);
	   
					return response.data;
	            },
	            function(errResponse){
	                $log.error(`Error while fetching Courses ${errResponse}`);
					//optional error handling in UI
	            }
	        );

	       
	    }
	 
	 	function createCourse(course) {
		
	        return $http.post(REST_SERVICE_URI, course)
	            .then( function (response) {
					$log.debug(`create course response :: ${JSON.stringify(response)}`);
					return response.data;
	            },
	            function(errResponse){
	                 $log.error(`Error while creating Courses ${JSON.stringify(errResponse)}`);
					//optional error handling in UI
	            }
	        );
	
	    }
	 
	 	function updateCourse(id, course) {
			return $http.put(REST_SERVICE_URI+id, course)
	            .then( function (response) {
					$log.debug(`update course response :: ${JSON.stringify(response)}`);
	                return response.data;
	            },
	            function(errResponse){
	                 $log.error(`Error while updating Courses ${JSON.stringify(errResponse)}`);
					//optional error handling in UI
	            }
	        );
	
	    }
	 
	    function deleteCourse(id) {
	        
	        return $http.delete(REST_SERVICE_URI+id)
	            .then( function (response) {
	               $log.debug(`delete course response :: ${JSON.stringify(response)}`);
	   
					return response.data;
	            },
	            function(errResponse){
	                 $log.error(`Error while deleting Courses ${errResponse}`);
					//optional error handling in UI
	            }
	        );
	        
	    }
    }		
})();
