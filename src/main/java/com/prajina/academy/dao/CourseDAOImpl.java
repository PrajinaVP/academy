package com.prajina.academy.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.prajina.academy.entity.Course;

@Repository
public class CourseDAOImpl extends AbstractDAO implements CourseDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAll() {
		String hql = "FROM Course c";
		// Uses hibernate query (not javax.persistence.Query)
		return getSession().createQuery(hql).getResultList();
	}

	@Override
	public Course findById(Long id) {
		Course course = null;
		String hql = "FROM Course where id = :id";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		course = (Course) query.getSingleResult();
		
		return course;
	}
	
	@Override
	public Course findByName(String name) {
		Session currentSession = getSession();
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		// Using Expressions
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));
		
		Query query = currentSession.createQuery(criteriaQuery);
		Course course = (Course) query.getSingleResult();
		
		return course;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByDesc(String desc) {
		Session currentSession = getSession();
		CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		// Using Expressions
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("desc"), desc));
		
		Query query = currentSession.createQuery(criteriaQuery);
		
		return query.getResultList();
	}


	@Override
	public Course getCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Course course) {
		getSession().saveOrUpdate(course);
	}

	@Override
	public void delete(Long id) {
		Session currentSession = getSession();
		Course course = currentSession.byId(Course.class).load(id);
		currentSession.delete(course);
	}
}
