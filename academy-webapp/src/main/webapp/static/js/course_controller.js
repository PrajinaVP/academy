(function() {
	
	'use strict';
	angular.module('myApp')
	.controller('CourseController', CourseController);
	
	CourseController.$inject = ['CourseService', '$log'];
	
	function CourseController(CourseService, $log) {
		var vm = this;
		vm.course = {id: null, name: '', description: '',status: '', contact: '', version: '', modules: null};
		vm.courses = [];
		vm.fetchAllCourses = fetchAllCourses;
		vm.createCourse = createCourse;
		vm.updateCourse = updateCourse;
		vm.deleteCourse = deleteCourse;
		vm.edit = edit;
		vm.submit = submit;
		vm.remove = remove;
		vm.reset = reset;
		vm.modules = [];
		vm.selectedModules = [];
		
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
	                $log.error('Error while fetching Courses :: \n' + JSON.stringify(errResponse));
	            }
	        );
    	}

 		function createCourse(course){
		$log.debug('Create Course  :: ', course);
        CourseService.createCourse(course)
            .then(
			function(data) {
				fetchAllCourses();
			},
            function(errResponse){
                $log.error('Error while creating Courses :: \n' + JSON.stringify(errResponse));
            }
        );
      }
 
    function updateCourse(id, course){
		$log.debug('Updating Course  id ::', id);
		CourseService.updateCourse(id, course)
            .then(
			function(data) {
				fetchAllCourses();
			},
            function(errResponse){
                $log.error('Error while updating Module', JSON.stringify(errResponse));
            }
        );
    }
 
    function deleteCourse(id){
		$log.debug('Deleting Course  id ::', id);
        CourseService.deleteCourse(id)
            .then(
            function(data) {
				fetchAllCourses();
			},
            function(errResponse){
                $log.error('Error while deleting Course');
            }
        );
    }
 
    function submit() {
        if(vm.course.id===null){
            $log.debug('Saving New Course', vm.course);
            createCourse(vm.course);
        }else{
            updateCourse(vm.course.id, vm.course);
            $log.debug('Course updated with id ', vm.course.id);
        }
        reset();
    }
 
    function edit(id){
        $log.debug('id to be edited', id);
        for(var i = 0; i < vm.courses.length; i++){
            if(vm.courses[i].id === id) {
                vm.course = angular.copy(vm.courses[i]);
                break;
            }
        }
    }
 
    function remove(id){
	    $log.debug('id to be deleted', id);
        if(vm.course.id === id) {
            reset();
        }
        deleteCourse(id);
    }
 
 
    function reset(){
        vm.course={id: null, name: '', description: '',status: '', contact: '', version: '', modules: null};
    }
  }
})();