package com.koitoer.jparepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.koitoer.jparepo.model.Employee;


public class PersistenceOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaQuery");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Employee employee = em.find(Employee.class, 2);
		System.out.println(employee);
		employee.setName("Mauricio");
		//em.remove(employee);
		Employee employeeNew = new Employee();
		employeeNew.setId(2);
		em.persist(employeeNew);
		
		em.getTransaction().commit();

	}

}
