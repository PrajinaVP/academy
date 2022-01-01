(function() {
	
	'use strict';
	angular.module('myApp')
	.controller('CourseController', CourseController);
	
	CourseController.$inject = ['CourseService'];
	
	function CourseController(CourseService) {
		var vm = this;
		//vm.course = {id: null, name: '', desc: '',status: '', contact: '' };
		vm.courses = [];
		vm.fetchAllCourses = fetchAllCourses;
		
		fetchAllCourses();
	/*	vm.init = function() {
			vm.fetchAllCourses();
			vm.createCourse();
		}
		*/
		function fetchAllCourses(){
	        CourseService.fetchAllCourses()
	            .then(
	            function(data) {
	                vm.courses = data;
console.log("courses stringified:: \n " + JSON.stringify(data));
console.log("vm.courses stringified:: \n " + JSON.stringify(vm.courses));
					return vm.courses;
	            },
	            function(errResponse){
	                console.error('Error while fetching Courses :: \n' + errResponse);
	            }
	        );
    	}


 		vm.createCourse = function(course){
        CourseService.createCourse(course)
            .then(
            fetchAllCourses,
            function(errResponse){
                console.error('Error while creating Course');
            }
        );
    }
 
    vm.updateCourse = function(course, id){
        CourseService.updateCourse(course, id)
            .then(
            fetchAllCourses,
            function(errResponse){
                console.error('Error while updating Course');
            }
        );
    }
 
    vm.deleteCourse = function(id){
        CourseService.deleteCourse(id)
            .then(
            fetchAllCourses,
            function(errResponse){
                console.error('Error while deleting Course');
            }
        );
    }
 
    vm.submit = function() {
        if(vm.course.id===null){
            console.log('Saving New Course', vm.course);
            createCourse(vm.course);
        }else{
            updateCourse(vm.course, vm.course.id);
            console.log('Course updated with id ', vm.course.id);
        }
        reset();
    }
 
    vm.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < vm.courses.length; i++){
            if(vm.courses[i].id === id) {
                vm.course = angular.copy(vm.courses[i]);
                break;
            }
        }
    }
 
    vm.remove = function(id){
        console.log('id to be deleted', id);
        if(vm.course.id === id) {//clean form if the course to be deleted is shown there.
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
