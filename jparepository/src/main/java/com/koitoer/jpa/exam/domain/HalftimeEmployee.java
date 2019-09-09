package com.koitoer.jpa.exam.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


public class HalftimeEmployee extends Employee {

	public double salaryHalf;
}
