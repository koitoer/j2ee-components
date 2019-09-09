package com.koitoer.jparepo.dto;

import com.koitoer.jparepo.model.Department;


public class Express {

	private String name;
	private long salary;
	private Department dept;
	
	
	
	public Express(String name, long salary, Department dept) {
		super();
		this.name = name;
		this.salary = salary;
		this.dept = dept;
	}

	public Express(String name, long salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Express [name=" + name + ", salary=" + salary + ", dept="
				+ dept + "]";
	}


	
	
}
