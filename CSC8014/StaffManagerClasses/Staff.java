package uk.ac.ncl.CSC8014.StaffManagerClasses;

/**
 * uk.ac.ncl.CSC8014_AntonioDePasquale.Staff - interface to university staff.
 *
 * @author Rouaa Yassin Kassab
 * Copyright (C) 2023 Newcastle University, UK
 */ 

public interface Staff {

	//DO NOT remove or change the signature of any method. 
	//You can add more methods (e.g. setter methods) if your solution requires that
	
	/**
	 * Returns the staff ID. 
	 * All staff must have an ID
	 * @return the uk.ac.ncl.CSC8014_AntonioDePasquale.StaffID object
	 */
	StaffID getStaffID();
	
	
	/**
	 * Returns the smart card. 
	 * All staff must have a smart card
	 * @return the uk.ac.ncl.CSC8014_AntonioDePasquale.SmartCard object
	 */
	SmartCard getSmartCard();
 
	//
	/**
	 * Returns the uk.ac.ncl.CSC8014_AntonioDePasquale.Staff employment status.
	 * a uk.ac.ncl.CSC8014_AntonioDePasquale.Staff can be either on Permanent or fixed contract
	 * @return a string (Permanent or fixed)
	 */
	String getStaffEmploymentStatus();


	/**
	 * Returns the uk.ac.ncl.CSC8014_AntonioDePasquale.Staff type.
	 * a uk.ac.ncl.CSC8014_AntonioDePasquale.Staff can be either a uk.ac.ncl.CSC8014_AntonioDePasquale.Lecturer or a uk.ac.ncl.CSC8014_AntonioDePasquale.Researcher
	 *
	 * @return a string (uk.ac.ncl.CSC8014_AntonioDePasquale.Lecturer or uk.ac.ncl.CSC8014_AntonioDePasquale.Researcher)
	 */
	String getStaffType();


}
