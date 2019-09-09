package com.koitoer.jpa.exam.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue
	@Column(name="DEPT_ID")
	private Long id;

	private String name;
	
	@ManyToOne
	private Employee employee;
	
/*	
 * Case III
 * @ManyToOne
	@JoinTable(name="DEPTO_TABLE", 
		joinColumns=@JoinColumn(name="DEPTO_ID"),
		inverseJoinColumns=@JoinColumn(name="EMP_ID"))
	private Employee employee;*/
	
/*	
 * Case IV
 * 	@ManyToMany(mappedBy="add_depto")
	private Collection<Employee> employee;*/
	
	public Department() {
		super();
	}
	public Department(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
