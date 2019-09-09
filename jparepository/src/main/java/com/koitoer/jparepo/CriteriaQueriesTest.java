package com.koitoer.jparepo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.koitoer.jparepo.model.Employee;


public class CriteriaQueriesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaQuery");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		
		Root<Employee> root = cq.from(Employee.class);
		cq.where(cb.like(root.<String>get("name"), "%ani%"));
		cq.orderBy(cb.asc(cb.locate(root.<String>get("name"), "ani")), cb.asc(root.get("name")));

		TypedQuery<Employee> query2 = em.createQuery(cq);
		printList(query2.getResultList());
		
	}

	public static <T> void printList(List<T> list){
		for(T object : list){
			System.out.println(object);
		}
	}
}
