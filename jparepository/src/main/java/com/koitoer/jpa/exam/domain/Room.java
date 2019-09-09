package com.koitoer.jpa.exam.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

	@Id
	private int idRoom;
	private int noRooms;
	private String salesMan;
	
}
