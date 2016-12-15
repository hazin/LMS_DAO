package com.gcit.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class BookLoan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8738232863578324630L;

	Timestamp dateOut;
	Timestamp dueDate;
	Timestamp dateIn;
	
	
}
