package com.prajina.academy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.prajina.academy.model.Module;

@Service("ModuleService")
public class ModuleServiceImpl implements ModuleService {

	private static final AtomicLong counter = new AtomicLong();

	// TODO Read from xls file
	private static List<Module> modules;

	static {
		modules = getModules();
	}

	@Override
	public List<Module> findAll() {
		return modules;
	}

	@Override
	public Module findById(Long id) {
		for (Module module : modules) {
			if (module.getId() == id) {
				return module;
			}
		}
		return null;
	}

	@Override
	public Module findByName(String name) {
		for (Module module : modules) {
			if (module.getName().equalsIgnoreCase(name)) {
				return module;
			}
		}

		return null;
	}

	@Override
	public List<Module> findByDesc(String desc) {
		List moduleContainingDesc = new ArrayList();

		for (Module module : modules) {
			if (module.getDesc().contains(desc)) {
				moduleContainingDesc.add(module);
			}
		}

		return moduleContainingDesc;
	}

	@Override
	public Module create(Module module) {
		if (module == null) {
			throw new RuntimeException("No module provided!");
		}
		Long id = counter.incrementAndGet();
		module.setId(id);
		modules.add(module);

		return modules.get(modules.indexOf(module));
	}

	@Override
	public List<Module> create(List<Module> moduleList) {
		if (moduleList == null) {
			throw new RuntimeException("No module provided!");
		}

		for (Module module : moduleList) {
			module.setId(counter.incrementAndGet());
			modules.add(module);
		}

		return modules;
	}

	@Override
	public Module update(Module module) {
		if (module == null || module.getId() == null) {
			throw new RuntimeException("No module provided for update or module id does not exist");
		}
		int index = modules.indexOf(module);
		modules.set(index, module);

		return modules.get(index);
	}

	@Override
	public void updateModule(List<Module> moduleList) {
		for (Module module : modules) {
			int index = modules.indexOf(module);
			modules.set(index, module);
		}
	}

	@Override
	public void deleteModule(List<Module> moduleList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {

		for (Iterator<Module> iterator = modules.iterator(); iterator.hasNext();) {
			Module module = iterator.next();
			if (module.getId() == id) {
				iterator.remove();
			}
		}
	}

	@Override
	public void deleteAll() {
		modules.clear();
	}

	@Override
	public boolean isModuleExist(Module module) {

		return findById(module.getId()) != null;
	}

	private static List<Module> getModules() {
		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module(counter.incrementAndGet(), "Modifier", "Java Modifiers", 1L));
		modules.add(new Module(counter.incrementAndGet(), "Streams", "Java Stream", 1L));
		modules.add(new Module(counter.incrementAndGet(), "Angular", "Angular JS", 2L));

		return modules;
	}
}
