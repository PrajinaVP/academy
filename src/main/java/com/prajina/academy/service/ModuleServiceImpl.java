package com.prajina.academy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prajina.academy.model.Module;

@Service("ModuleService")
public class ModuleServiceImpl implements ModuleService {

	// TODO Read from xls file shared by Matt
	private static List modules;
	static {
		modules = getModules();
	}

	@Override
	public Module findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Module> findAllModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module createModule(Module Module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module createModule(List<Module> Module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module updateModule(Module Module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module updateModule(List<Module> Module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteModule(List<Module> ModuleList) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isModuleExist(Module Module) {
		// TODO Auto-generated method stub
		return false;
	}

	private static List<Module> getModules() {
		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module(1, "OOPs", "OOPs Concepts", 100));
		modules.add(new Module(2, "Basic Java Constructs", "Basic Java Constructs", 100));
		modules.add(new Module(3, "String Handling", "String Handling", 100));
		modules.add(new Module(4, "Collection Framework", "Collection Framework", 100));
		modules.add(new Module(5, "Multi Threading", "Multi Threading", 100));
		modules.add(new Module(6, "Exception Handling", "Exception Handling", 100));
		modules.add(new Module(7, "Generics", "Generics", 100));

		modules.add(new Module(1, "JDBC", "Java Database Connectivity", 101));
		modules.add(new Module(2, "Servlet", "Servlet", 101));
		modules.add(new Module(3, "JSP", "Java Server Pages", 101));

		return modules;
	}

}
