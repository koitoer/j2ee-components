package com.koitoer.jparepo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class QueryTester {

	public static void main(String[] args) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaQuery");
		EntityManager em = emf.createEntityManager();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		for(;;){
			System.out.println("JP QL > ");
			String query = reader.readLine();
			if("quit".equals(query))
				break;
			if(query.length()==0)
				continue;
			
			try{
				List result = em.createQuery(query).getResultList();
				if(result.size() > 0){
					int count = 0;
					for(Object o : result){
						System.out.print(count++ + " ");
						printResult(o);
					}
				}else{
					System.out.println("No records found");
				}
					
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	private static void printResult(Object result) {
		if(result == null){
			System.out.println("NULL");
		}else if(result instanceof Object[]){
			Object [] row = (Object[]) result;
			System.out.println("[");
			for(int i = 0 ; i<row.length ; i++){
				printResult(row[i]);
			}
			System.out.println("]");
		}else if(result instanceof Long || result instanceof Double || result instanceof String){
			System.out.println(result.getClass().getName() + " : " + result);
		}else{
			System.out.println(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
		}
		
	}
}
