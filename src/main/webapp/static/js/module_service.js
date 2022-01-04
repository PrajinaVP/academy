(function() {
	'use strict';
	angular.module('myApp')
		.factory('ModuleService', ModuleService);
		
	ModuleService.$inject = ['$http', '$q', '$log'];
	
	function ModuleService($http, $q, $log) {
		var REST_SERVICE_URI = 'http://localhost:8080/module/'; 
		
		var service = {
	        fetchAllModules: fetchAllModules,
	        createModule: createModule,
	        updateModule: updateModule,
	        deleteModule: deleteModule
    	};

		return service;
		
		function fetchAllModules() {
	 
	      return $http.get(`${REST_SERVICE_URI}all`)
	            .then( function (response) {
					$log.debug(`service data :: ${JSON.stringify(response)}`);
	   
					return response.data;
	            },
	            function(errResponse){
	                $log.error(`Error while fetching Modules ${errResponse}`);
					//optional error handling in UI
	            }
	        );

	       
	    }
	 
	 	function createModule(module) {
		
	        return $http.post(REST_SERVICE_URI, module)
	            .then( function (response) {
					$log.debug(`create module response :: ${JSON.stringify(response)}`);
	                return response.data;
	            },
	            function(errResponse){
	                 $log.error(`Error while creating Modules ${errResponse}`);
					//optional error handling in UI
	            }
	        );
	
	    }
	 
	 	function updateModule(module, id) {
		
	         return $http.put(REST_SERVICE_URI+id, module)
	            .then( function (response) {
					$log.debug(`update module response :: ${JSON.stringify(response)}`);
	                return response.data;
	            },
	            function(errResponse){
	                 $log.error(`Error while creating Modules ${errResponse}`);
					//optional error handling in UI
	            }
	        );
	
	    }
	 
	    /*function updateModule(module, id) {
			var deferred = $q.defer();
	        $http.put(REST_SERVICE_URI+id, module)
	            .then( function (response) {
	                $log.debug(`update module response :: ${JSON.stringify(response)}`);
				
					deferred.resolve(response.data);
	            },
	            function(errResponse){
	                 $log.error(`Error while updating Modules ${JSON.stringify(errResponse)}`);
					 deferred.reject(errResponse);
					//optional error handling in UI
	            }
	        );

			return deferred.promise;	        
	    }*/
	 
	    function deleteModule(id) {
	        
	        return $http.delete(REST_SERVICE_URI+id)
	            .then( function (response) {
	               $log.debug(`delete module response :: ${JSON.stringify(response)}`);
	   
					return response.data;
	            },
	            function(errResponse){
	                 $log.error(`Error while deleting Modules ${errResponse}`);
					//optional error handling in UI
	            }
	        );
	        
	    }
    }		
})();
