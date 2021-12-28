'use strict';
 
angular.module('myApp').controller('CourseController', ['$scope', 'CourseService', function($scope, CourseService) {
    var self = this;
    self.course={id:null,name:'',desc:'',status:'', 'contact': ''};
    self.courses=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllCourses();
 
    function fetchAllCourses(){
        CourseService.fetchAllCourses()
            .then(
            function(d) {
                self.courses = d;
            },
            function(errResponse){
                console.error('Error while fetching Courses');
            }
        );
    }
 
    function createCourse(course){
        CourseService.createCourse(course)
            .then(
            fetchAllCourses,
            function(errResponse){
                console.error('Error while creating Course');
            }
        );
    }
 
    function updateCourse(course, id){
        CourseService.updateCourse(course, id)
            .then(
            fetchAllCourses,
            function(errResponse){
                console.error('Error while updating Course');
            }
        );
    }
 
    function deleteCourse(id){
        CourseService.deleteCourse(id)
            .then(
            fetchAllCourses,
            function(errResponse){
                console.error('Error while deleting Course');
            }
        );
    }
 
    function submit() {
        if(self.course.id===null){
            console.log('Saving New Course', self.course);
            createCourse(self.course);
        }else{
            updateCourse(self.course, self.course.id);
            console.log('Course updated with id ', self.course.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.courses.length; i++){
            if(self.courses[i].id === id) {
                self.course = angular.copy(self.courses[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.course.id === id) {//clean form if the course to be deleted is shown there.
            reset();
        }
        deleteCourse(id);
    }
 
 
    function reset(){
        self.course={id:null,name:'',desc:'',status:'', 'contact': ''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);