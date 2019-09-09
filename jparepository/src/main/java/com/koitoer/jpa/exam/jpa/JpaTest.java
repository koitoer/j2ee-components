package com.koitoer.jpa.exam.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.koitoer.jpa.exam.domain.Employee;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		/*BASIC, EMBEDDABLE
		Employee emp =  new Employee();
		emp.setName("Mauricio");
		Address add = new Address();
		add.setCity("MX");
		add.setStreet("ENS");
		emp.getMyAddress().put("Casa", add);
		System.out.println("Casa".hashCode());
		manager.persist(emp);*/

		Query query = manager.createQuery("select d.employee from Department d");
		/*	    query = manager.createQuery("select e from Department d inner join d.employee e");
			    query = manager.createQuery("select e from Employee e inner join e.department d");*/
		List<Employee> objects = query.getResultList();
		System.out.println(objects);
		System.out.println(objects.size());

		manager.getTransaction().commit();
		System.out.println(".. done");
	}

}
