(function() {
	
	'use strict';
	angular.module('myApp')
	.controller('CourseController', CourseController);
	
	CourseController.$inject = ['CourseService', '$log'];
	
	function CourseController(CourseService, $log) {
		var vm = this;
		vm.course = {id: null, name: '', desc: '',status: '', contact: '' };
		vm.courses = [];
		vm.fetchAllCourses = fetchAllCourses;
		
		fetchAllCourses();

		function fetchAllCourses(){
	        CourseService.fetchAllCourses()
	            .then(
	            function(data) {
	                vm.courses = data;
					$log.debug('Courses  :: ' + JSON.stringify(vm.courses));
					return vm.courses;
	            },
	            function(errResponse){
	                $log.error('Error while fetching Courses :: \n' + errResponse);
	            }
	        );
    	}


 		vm.createCourse = function(course){
        CourseService.createCourse(course)
            .then(
            fetchAllCourses,
            function(errResponse){
                $log.error('Error while creating Course');
            }
        );
    }
 
    vm.updateCourse = function(course, id){
        CourseService.updateCourse(course, id)
            .then(
            fetchAllCourses,
            function(errResponse){
                $log.error('Error while updating Course');
            }
        );
    }
 
    vm.deleteCourse = function(id){
        CourseService.deleteCourse(id)
            .then(
            fetchAllCourses,
            function(errResponse){
                $log.error('Error while deleting Course');
            }
        );
    }
 
    vm.submit = function() {
        if(vm.course.id===null){
            $log.debug('Saving New Course', vm.course);
            createCourse(vm.course);
        }else{
            updateCourse(vm.course, vm.course.id);
            $log.debug('Course updated with id ', vm.course.id);
        }
        reset();
    }
 
    vm.edit = function(id){
        $log.debug('id to be edited', id);
        for(var i = 0; i < vm.courses.length; i++){
            if(vm.courses[i].id === id) {
                vm.course = angular.copy(vm.courses[i]);
                break;
            }
        }
    }
 
    vm.remove = function(id){
        $log.debug('id to be deleted', id);
        if(vm.course.id === id) {
            reset();
        }
        deleteCourse(id);
    }
 
 
    function reset(){
        vm.course={id:null,name:'',desc:'',status:'', 'contact': ''};
        $scope.myForm.$setPristine(); //reset Form
    }
  }

})();
