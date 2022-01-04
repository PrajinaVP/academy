(function() {
	
	'use strict';
	angular.module('myApp')
	.controller('ModuleController', ModuleController);
	
	ModuleController.$inject = ['ModuleService', '$log'];
	
	function ModuleController(ModuleService, $log) {
		var vm = this;
		vm.module = {id: null, name: '', desc: '',status: '', courseId: null };
		vm.modules = [];
		vm.fetchAllModules = fetchAllModules;
		vm.createModule = createModule;
		vm.updateModule = updateModule;
		vm.deleteModule = deleteModule;
		vm.edit = edit;
		vm.submit = submit;
		vm.reset = reset;
		
		fetchAllModules();

		function fetchAllModules(){
	        ModuleService.fetchAllModules()
	            .then(
	            function(data) {
	                vm.modules = data;
					$log.debug('Modules  :: ' + JSON.stringify(vm.modules));
					return vm.modules;
	            },
	            function(errResponse){
	                $log.error('Error while fetching Modules :: \n' + errResponse);
	            }
	        );
    	}

 	  function createModule(module){
		$log.debug('Create Modules  :: ', module);
        ModuleService.createModule(module)
            .then(
            fetchAllModules,
            function(errResponse){
                $log.error('Error while creating Modules :: \n' + errResponse);
            }
        );
      }
 
    function updateModule(module, id){
        ModuleService.updateModule(module, id)
            .then(
            fetchAllModules(),
            function(errResponse){
                $log.error('Error while updating Module', JSON.stringify(errResponse));
            }
        );
    }
 
    function deleteModule(id){
		$log.debug('Deleting Modules  id ::', id);
        ModuleService.deleteModule(id)
            .then(
            fetchAllModules,
            function(errResponse){
                $log.error('Error while deleting Module');
            }
        );
    }
 
    function submit() {
        if(vm.module.id===null){
            $log.debug('Saving New Module', vm.module);
            createModule(vm.module);
        }else{
            updateModule(vm.module, vm.module.id);
            $log.debug('Module updated with id ', vm.module.id);
        }
        reset();
    }
 
    function edit(id){
        $log.debug('id to be edited', id);
        for(var i = 0; i < vm.modules.length; i++){
            if(vm.modules[i].id === id) {
                vm.module = angular.copy(vm.modules[i]);
                break;
            }
        }
    }
 
    function remove(id){
        $log.debug('id to be deleted', id);
        if(vm.module.id === id) {
            reset();
        }
        deleteModule(id);
    }
 
 
    function reset(){
        vm.module={id:null,name:'',desc:'',status:'', 'courseId': null};
    }
  }

})();
