package com.koitoer.jpa.exam.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Phone {

	private String number;
	private String type;
}
