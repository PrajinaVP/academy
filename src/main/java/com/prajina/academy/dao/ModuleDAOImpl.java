package com.prajina.academy.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.prajina.academy.entity.Module;

@Repository
public class ModuleDAOImpl extends AbstractDAO implements ModuleDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findAll() {
		Session currentSession = getSession();
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Module> criteriaQuery = criteriaBuilder.createQuery(Module.class);
		Root<Module> root = criteriaQuery.from(Module.class);
		criteriaQuery.select(root);
		
		Query query = currentSession.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

	@Override
	public Module findById(Long id) {
		Module module = getSession().get(Module.class, id);
		
		return module;
	}
	
	@Override
	public Module findByName(String name) {
		Session currentSession = getSession();
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Module> criteriaQuery = criteriaBuilder.createQuery(Module.class);
		Root<Module> root = criteriaQuery.from(Module.class);
		// Using Expressions
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
		
		Query query = currentSession.createQuery(criteriaQuery);
		Module module = (Module) query.getSingleResult();
		
		return module;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findByDesc(String desc) {
		Session currentSession = getSession();
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Module> criteriaQuery = criteriaBuilder.createQuery(Module.class);
		Root<Module> root = criteriaQuery.from(Module.class);
		// Using Expressions
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("desc"), desc));
		
		Query query = currentSession.createQuery(criteriaQuery);
		
		return query.getResultList();
	}


	@Override
	public Module getModule(Module module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Module module) {
		getSession().saveOrUpdate(module);
	}

	@Override
	public void delete(Long id) {
		Session currentSession = getSession();
		Module module = currentSession.byId(Module.class).load(id);
		currentSession.delete(module);
	}
}
