(function() {
	'use strict';
	angular.module('myApp')
		.factory('CourseService', CourseService);
		
	CourseService.$inject = ['$http', '$log'];
	
	function CourseService($http, $log) {
		var REST_SERVICE_URI = 'http://localhost:8080/rest/courses'; 
		
		var service = {
	        fetchAllCourses: fetchAllCourses,
	        createCourse: createCourse,
	        updateCourse: updateCourse,
	        deleteCourse: deleteCourse
    	};

		return service;
		
		function fetchAllCourses() {
	 
	      return $http.get(REST_SERVICE_URI)
	            .then(
	            function (response) {
				$log.debug("service data :: "+ JSON.stringify(response));
	    
					return response.data;
	
	            },
	            function(errResponse){
	                $log.error('Error while fetching Courses');
					//optional error handling in UI
	            }
	        );

	       
	    }
	 
	 	function createCourse(user) {
	        var deferred = $q.defer();
	        $http.post(REST_SERVICE_URI, user)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating Course');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	 
	    function updateCourse(user, id) {
	        var deferred = $q.defer();
	        $http.put(REST_SERVICE_URI+id, user)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating Course');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function deleteCourse(id) {
	        var deferred = $q.defer();
	        $http.delete(REST_SERVICE_URI+id)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while deleting Course');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
    }		
})();
