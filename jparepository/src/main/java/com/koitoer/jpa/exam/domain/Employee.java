package com.koitoer.jpa.exam.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OF")
public class Employee {
	@Id
	@GeneratedValue
	private Long id;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department="
				+ department + "]";
	}

	private String name;
	
	@OneToMany(mappedBy="employee")
	private Collection<Department> department;
	
	/*@ElementCollection
	@CollectionTable(name="MAP_TABLE")
	@MapKeyColumn(name="MAP_KEY")
	@Column(name="MAP_VALUE")
	private Map<String, String> myMap;*/

	/*@ElementCollection
	@CollectionTable(name="MAP_TABLE", joinColumns = @JoinColumn(name = "EMPLOYEE_ADDRESS"))
	@AttributeOverrides({
		@AttributeOverride(name="value.city", column=@Column(name="CIUDAD")),
		@AttributeOverride(name="value.street", column=@Column(name="CALLE")),
	})
	private Map<String, Address> myAddress = new HashMap<String, Address>();*/
	
	
	/*
	 * Case III
	 * @OneToMany(mappedBy="employee")
	@MapKeyColumn(name="MY_STRING")
	private Map<String, Department> myDepartment;
*/
/*	
	@ElementCollection
	@CollectionTable(name="ADDRR_STTT", joinColumns=@JoinColumn(name="EMP_ID"))
	@AttributeOverrides(value = { 
			@AttributeOverride(column = @Column(name="CIU"), name = "key.city"),
			@AttributeOverride(column = @Column(name="CAL"), name = "key.street") 
	})
	@Column(name="USED_STRING")
	private Map<Address, String> myAddressString;*/
	
	/*@ElementCollection
	@CollectionTable(name="ADDRR_STTT", joinColumns=@JoinColumn(name="EMP_ID"))
	@AttributeOverrides(value = { 
			@AttributeOverride(column = @Column(name="CIU"), name = "key.city"),
			@AttributeOverride(column = @Column(name="CAL"), name = "key.street") ,
			@AttributeOverride(column = @Column(name="NUMERO"), name = "value.number"),
			@AttributeOverride(column = @Column(name="TIPE"), name = "value.type") 
	})
	private Map<Address, Phone> addr_phon;*/

/*
 * Case IV
 * 	@ManyToMany
	@JoinTable(name="AD_DEPTOS", 
		joinColumns=@JoinColumn(name="EMPP_ID", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="DEPTOO_ID", referencedColumnName="DEPT_ID"))
	@AttributeOverrides(value = { 
			@AttributeOverride(column = @Column(name="CIU"), name = "key.city"),
			@AttributeOverride(column = @Column(name="CAL"), name = "key.street")
	})
	private Map<Address, Department> add_depto;
	*/
	
/*	@ElementCollection
	@CollectionTable(name="ADDRR_STTT", joinColumns=@JoinColumn(name="EMP_ID"))
	@MapKeyJoinColumn(name="DEPT_ID")
	@AttributeOverrides(value = { 
			@AttributeOverride(column = @Column(name="CIU"), name = "value.city"),
			@AttributeOverride(column = @Column(name="CAL"), name = "value.street")
	})
	private Map<Department, Address> addresDepto;*/
	
/*	Case 8
 * @ManyToMany
	@JoinTable(name="ROOM_DEPTOS", 
		joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="ROOM_ID", referencedColumnName="idRoom"))
	@MapKeyJoinColumn(name="DEPTO_KEY")
	private Map<Department, Room> dep_room;*/
	
	/*@OneToMany
	@JoinColumn(name="DEPT_FK")
	private Set<Department> department;*/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
