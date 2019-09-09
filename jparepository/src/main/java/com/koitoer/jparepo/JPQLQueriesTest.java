package com.koitoer.jparepo;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.koitoer.jparepo.dto.Express;
import com.koitoer.jparepo.model.Department;
import com.koitoer.jparepo.model.Employee;
import com.koitoer.jparepo.model.Phone;
import com.koitoer.jparepo.model.Project;


public class JPQLQueriesTest {

	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaQuery");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select e from Employee e where e.name like '%ani%' order by locate('ani', e.name) asc, e.name asc";
		TypedQuery<Employee> query2 = em.createQuery(jpql ,Employee.class);
		printList(query2.getResultList());
		
		
		Thread.sleep(50000);
		
		String sqlString = "Select e from Employee e";
		
		//Works but you should add '' to make it work
		sqlString = "Select e from Employee e where e.name = \'Rob\'";
		
		//Can not be done as TypedQuery return Employee
		sqlString = "Select e.id, e.name from Employee e where e.manager.name = \'Sarah\'";
		
		//Object can be used as well
		sqlString = "Select OBJECT(e) from Employee e where e.manager.name = \'Sarah\'";
		
		
		TypedQuery<Employee> query = em.createQuery(sqlString ,Employee.class);
		printList(query.getResultList());
		
		//Work with ManyToOne
		//Use of alias need to be done always.
		sqlString = "select d from Employee e inner join e.department d where e.id = 1";
		
		//This query can be done without query
		sqlString = "select e.department from Employee e where e.id = 1";
		
		//Use object in single value association path is ILLEGAL
		sqlString = "select OBJECT(e.department) from Employee e where e.id = 1";
		
		//Use object is allowed here
		sqlString = "select OBJECT(d) from Employee e inner join e.department d where e.id = 1";

		//Return duplicates as we listing all the departments
		sqlString = "select e.department from Employee e ";
		
		//Avoid duplicates using distinct clause.
		sqlString = "select DISTINCT(e.department) from Employee e ";
		
		//Department to employee
		//OneToMany association
		//Query will return a collection type is ILLEGAL
		//Works but return java.util.Set, and doesnt return the values correctly.
		//sqlString = "select (d.employees) from Department d ";
		
		//Embeddable can be called using path
		//IMPORTANT > embeddable objects returned by the query are not managed.
		//changes to them wont be persistence
		//sqlString = "select e.contactInfo from Employee e ";
		
		TypedQuery<Department> queryDept = em.createQuery(sqlString ,Department.class);
		printList(queryDept.getResultList());
	
	
		//Projects is only return a subset of an entity
		sqlString = "select e.name from Employee e ";
		
		//This works as only return one property in the projection
		TypedQuery<Object> queryArrayUnique = em.createQuery(sqlString ,Object.class);
		printList(queryArrayUnique.getResultList());
		
		//This works as only return one property in the projection
		System.out.println("String based property");
		TypedQuery<String> queryString = em.createQuery(sqlString ,String.class);
		printList(queryString.getResultList());
		
		//If I return more than one, I should use an Object Array instead.
		sqlString = "select e.name, e.salary from Employee e ";
		TypedQuery<Object[]> queryArray = em.createQuery(sqlString ,Object[].class);
		printListObjects(queryArray.getResultList());
		
		
		//Constructor Expression
		sqlString = "select NEW com.koitoer.jparepo.dto.Express(e.name, e.salary, e.department) from Employee e";
		TypedQuery<Express> queryExpression = em.createQuery(sqlString ,Express.class);
		printList(queryExpression.getResultList());
		
		//Inheritance and polymorphism
		//Phones/Employees is OneToMany
		sqlString = "select p from Project p where p.employees is not empty";
		TypedQuery<Project> querInheritance = em.createQuery(sqlString ,Project.class);
		printList(querInheritance.getResultList());
		
		//Use TYPE to work with specific classes.
		//See that DesignProject is not used quotes because is an entityClass
		//Introduce in JPA2.0
		sqlString = "select p from Project p where p.employees is not empty and TYPE(p) = DesignProject";
		querInheritance = em.createQuery(sqlString ,Project.class);
		printList(querInheritance.getResultList());
		
		//JOIN
		//Collection association fields.
		sqlString = "select p from Employee e join e.phones p";
		sqlString = "select p from Employee e join e.phones p where e.id = 1";
		TypedQuery<Phone> queryPhone = em.createQuery(sqlString ,Phone.class);
		printList(queryPhone.getResultList());
		
		//Single value association fields
		//The following queries are equivalents
		sqlString = "select distinct e.department from Project p join p.employees e where p.name = 'Release1' AND e.address.state = 'CA'";
		//Four logical joins was placed there.
		sqlString = "select distinct e.department from Project p join p.employees e join e.address a where p.name = 'Release1' AND a.state = 'CA'";
		queryDept = em.createQuery(sqlString ,Department.class);
		printList(queryDept.getResultList());
		
		System.out.println("JOIN CONDITIONS");
		//Join conditions in where clause
		sqlString = "select DISTINCT d from Department d, Employee e where d = e.department";
		queryDept = em.createQuery(sqlString ,Department.class);
		printList(queryDept.getResultList());
		
		sqlString = "select DISTINCT d,m from Department d, Employee m where d = m.department and m.directs IS NOT EMPTY";
		queryArray = em.createQuery(sqlString ,Object[].class);
		printListObjects(queryArray.getResultList());
		
		//Multiple joins can be done
		//Distinct need to be used to obtain only the different projects.
		//Join can create multiples record for the same
		sqlString = "select distinct(p) from Department d join d.employees e join e.projects p";
		querInheritance = em.createQuery(sqlString ,Project.class);
		printList(querInheritance.getResultList());
		
		//OUTER joins
		//Following query only takes when e.department != NULL
		sqlString = "select  e, d from Employee e join e.department d";
		
		//Consider the employees although the e.department is null
		sqlString = "select  e, d from Employee e LEFT join e.department d";
		
		//Consider the employees although the e.department is null
		//OUTER alone does not exists
		//LEFT INNER does not exists.
		sqlString = "select  e, d from Employee e LEFT OUTER join e.department d";
		queryArray = em.createQuery(sqlString ,Object[].class);
		printListObjects(queryArray.getResultList());
		
		em.clear();
		//fetcH joins.
		//IS JOIN FETCH NOOOOOTTTT FETCH JOIN
		//For the following examples consider adress as lazy 
		sqlString = "SELECT e FROM Employee e ";
		//"Hibernate: select id , address_id ,DEPT_ID , manager_id , name , salary , startDate from Employee"
		
		//As address is LAZY it will return null
		query = em.createQuery(sqlString ,Employee.class);
		printList(query.getResultList());
		
		
		System.out.println("Transalation of fetxh" );
		sqlString = "SELECT e FROM Employee e JOIN FETCH e.address ";
		//EQUIVALEN EXPRESSION SHOULD BE
		//SELECT e, a FROM Employee e join e.address a
		//difference is entities address is not being returned in an array, just be part of Employee
		/*Hibernate: select id , address1_.id , address_id , DEPT_ID , manager_id, name , salary , startDate , 
		address1_.city , address1_.state , address1_.street, address1_.zip 
		from Employee inner join Address address1_ on employee0_.address_id=address1_.id
		*/
		query = em.createQuery(sqlString ,Employee.class);
		printList(query.getResultList());
		
		
		
		//Outer joins fetch joins
		sqlString = "select d from Department d JOIN FETCH d.employees";
		//Return not null departments N times are they are used
		queryDept = em.createQuery(sqlString ,Department.class);
		printList(queryDept.getResultList());
		
		sqlString = "select d from Department d LEFT JOIN FETCH d.employees";
		//Return all departments it does not matter if are used and N times if are used
		queryDept = em.createQuery(sqlString ,Department.class);
		printList(queryDept.getResultList());
		
		sqlString = "select d, e from Department d LEFT JOIN  d.employees e";
		//Return all departments it does not matter if are used and N times if are used
		//Return duplicates..
		queryArray = em.createQuery(sqlString ,Object[].class);
		printListObjects(queryArray.getResultList());
		
		
		sqlString = "select e from Employee e where e.name = :param0";
		query = em.createQuery(sqlString ,Employee.class);
		query.setParameter("param0" , "Joe");
		printList(query.getResultList());
		
		//JPQL accept numeric and named parameter, starts with zero
		sqlString = "select e from Employee e where e.name = ?0";
		query = em.createQuery(sqlString ,Employee.class);
		query.setParameter(0 , "Joe");
		printList(query.getResultList());
		
		
		//EXPRESSION
		sqlString = "select e from Employee e where e.salary >= 4000 and e.salary <=45000";
		sqlString = "select e from Employee e where e.salary BETWEEN 4000 and  45000";

		//lIKE expression %(zero or more) _(one) single wildcard
		sqlString = "select e from Employee e where e.name like 'J%'";
		sqlString = "select e from Employee e where e.name like 'J_nnifer'";
		//ESCAPE '\' said need to be as it is.
		
		//subqueries can be used in where and having clauses
		//scalar 
		//if subquery use same identificator override parent, child can se parent identificators
		sqlString = "select e from Employee e where e.salary = (select max(e.salary) from Employee e)";
		//Correlates query when var from main query is refering in subquery
		//EXIST return true if any result is returned by query
		//Iterate one by one to look if is exist in the subquery, 1 is a practice
		sqlString = "select e from Employee e where EXISTS (select 1 from Phone p where p.employee = e AND p.type = 'Cell' )";
        //Above query is the same of
		sqlString = "select e from Employee e where EXISTS (select 1 from e.phones p where  p.type = 'Cell' )";

		//IN check if a single valued path is a member of a collection
		sqlString = "select e from Employee e where e.address.state IN ('NY', 'CA')";
		sqlString = "select e from Employee e where e.address.state NOT IN (select distinct d from Department d join d.employees de join de.projects p " +
				"where p.name LIKE 'QA%')";

		
		//collection values
		sqlString = "select e from Employee e where e.directs IS NOT EMPTY";
		sqlString = "select e from Employee e where e MEMBER OF e.directs";
		sqlString = "select e from Employee e where e MEMBER e.directs";

		//Managers paid less than ALL their employees
		sqlString = "select e from Employee e where e.directs IS NOT EMPTY AND e.salary < ALL(select d.salary from e.directs d)";
		
		//Managers paid less than at least one of their employees
		sqlString = "select e from Employee e where e.directs IS NOT EMPTY AND e.salary < SOME(select d.salary from e.directs d)";
		sqlString = "select e from Employee e where e.directs IS NOT EMPTY AND e.salary < ANY(select d.salary from e.directs d)";

		sqlString = "select e from Employee e where SIZE(e.directs) = 0 ";
		//Query below said Property index does not exist in collection examples.model.Employee.directs
		//sqlString = "select e from Employee e where INDEX(e.directs) = 0 ";
		
		query = em.createQuery(sqlString ,Employee.class);
		printList(query.getResultList());
		
		
		//CASE
		sqlString = "select p.name, " +
				"case type(p)" +
				"	WHEN DesignProject then 'Development ' " +
				"	WHEN QualityProject then 'QA' " +
				"	else 'Non-Development' " +
				"END " +
				"FROM Project p where p.employees is not empty";
		queryArray = em.createQuery(sqlString ,Object[].class);
		printListObjects(queryArray.getResultList());

		//COALESCE first not null became the result COALESCE(d.name, d.id)
		//NULLIF(d.name, 'QA') if d.name equals to QA then return NULL
		
		
		sqlString = "select e from Employee e order by e.name, salary asc ";
		//ASC is the detault
		sqlString = "select e from Employee e order by salary ";

		query = em.createQuery(sqlString ,Employee.class);
		printList(query.getResultList());
		
		
		//AGREGGATE queries
		sqlString = "select AVG(e.salary) from Employee e ";
		//Return the avg and a name
		sqlString = "select e.name, AVG(e.salary) from Employee e";
		sqlString = "select d.name, AVG(e.salary) from Employee e join e.department d ";
		
		//AVG result is Double
		//COUNT result is Long , ignore null in the count
		//MAX, MIN
		//SUM result long if field is long, and double if is double
		
		sqlString = "select count(e), AVG(e.salary) from Employee e";
		sqlString = "select d.name, AVG(e.salary), count(p) from Department d join d.employees e join e.projects p group by d.name";

		sqlString = "select d.name, AVG(e.salary), count(p) from Department d join d.employees e join e.projects p group by d.name having avg(e.salary) > 50000";
		sqlString = "select d.name, AVG(e.salary), count(p) from Department d join d.employees e join e.projects p group by d.name having d.name = 'QA'";

		queryArray = em.createQuery(sqlString ,Object[].class);
		printListObjects(queryArray.getResultList());

		
	}
	
	private static void printListObjects(List<Object[]> resultList) {
		for(Object[] object : resultList){
			for(Object objectInner : object)
				System.out.print(objectInner + ":");
			System.out.println();
		}
	}

	public static <T> void printList(List<T> list){
		for(T object : list){
			System.out.println(object);
		}
	}

}
