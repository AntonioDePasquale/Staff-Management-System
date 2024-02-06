package uk.ac.ncl.CSC8014.StaffManagerClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Calendar;
import java.util.Date;


public class StaffManager {

	//you can add attributes and other methods if needed. 
	//you can throw an exception if needed

	/**
	 * creation of collection objects to store Staff, Students and Modules.
	 * staffMap is used to store employed Staff objects.
	 * studentNameSet is used to store student Name objects.
	 * moduleSet is used to store module Objects.
	 */
	private Map<StaffID, Staff> staffMap;
	private Set<Name> studentNameSet;
	private Set<Module> moduleSet;

	/**
	 * Main method left empty as per assignment program is not meant to be run as it is incomplete.
	 */
	public static void main(String[] args) {}

	/**
	 * static variable instance of singleton class
	 */
	private static StaffManager single_staffManager_instance = null;

	/**
	 *private constructor restricted to the class
	 */
	private StaffManager() {
		staffMap =  new HashMap<>();
		studentNameSet = new HashSet<>();
		moduleSet = new HashSet<>();
	}

	/**
	 * static method to create an instance of the singleton class.
	 * singleton would be instantiated in a main class with StaffManager newStaffManager = StaffManager.getInstance();.
	 * as the program is incomplete per coursework specification no main class has been created.
	 */
	public static StaffManager getInstance() {
		if (single_staffManager_instance == null)single_staffManager_instance = new StaffManager();
		return single_staffManager_instance;
	}
	/**
	 * Method to read in the csv file of modules from the path given as a parameter.
	 * @param path file path to read modules as csv from.
	 * reads ech line of the csv one by one separating it into 4 variables by splitting at the comma.
	 * each variable used as parameters in the Module constructor.
	 * Module is added to the set of Modules moduleSet.
	 * @return The set of Modules moduleSet.
	 */
	public Set<Module> readInModules(String path) {
		//add your code here. Do NOT change the method signature

		try {
			Scanner scan = new Scanner(new File(path));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] moduleAttributes = line.split(",");

				String moduleCode = moduleAttributes[0].trim();
				String moduleName = moduleAttributes[1].trim();
				Integer semester = Integer.parseInt(moduleAttributes[2].trim());
				Integer credits = Integer.parseInt(moduleAttributes[3].trim());

				Module tempModule = new Module(moduleCode, moduleName, semester, credits);
				moduleSet.add(tempModule);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return moduleSet;
	}

	/**
	 * Method to read in the csv file of names from the path given as a parameter.
	 * @param path file path to read names as csv from.
	 * reads each line of the csv one by one separating it into 2 variables by splitting at the space.
	 * first and last name variable used as parameters in the Name constructor.
	 * Name is added to the set of Names studentNameSet.
	 * @return The set of student Names studentNameSet.
	 */
	public Set<Name> readInStudents(String path) {
		//add your code here. Do NOT change the method signature
		try {
			Scanner scan = new Scanner(new File(path));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] nameAttributes = line.split(" ");

				String firstName = nameAttributes[0].trim();
				String secondName = nameAttributes[1].trim();

				Name studentName = new Name(firstName, secondName);
				studentNameSet.add(studentName);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return studentNameSet;
	}

	/**
	 * Method to get the number of staff in the Staff map staffMap of the parameter type.
	 * stafftype has to be either a lecturer or a researcher or exception is thrown.
	 * int count is initialised to 0 and increases by 1 each time a staffmember is iterated over in the staffMap.
	 * @return the count is returned.
	 */
	public int noOfStaff(String type) {
		//add your code here. Do NOT change the method signature
		int count = 0;
		String staffType = null;

		if (type.equalsIgnoreCase("lecturer")) {
			staffType = "lecturer";
		} else if (type.equalsIgnoreCase("researcher")) {
			staffType = "researcher";
		} else {
			throw new IllegalArgumentException("\"Enter either \"lecturer\" or \"researcher\"");
		}

		for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
			if (entry.getValue().getStaffType().equalsIgnoreCase(staffType)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Method to get the number of staff in the Staff map staffMap of the parameter type.
	 * Iterates over staffMap map, checks each key for the parameter StaffID.
	 * If staffID is found, checks the staff type of that staff member object.
	 * returns true if is of correct type and adds set of modules to lecturers and set of student Names to researchers.
	 * returns false if staffID is not found or staff is not of correct type.
	 * @return true or false
	 */
	public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
		//add your code here. Do NOT change the method signature
		for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
			if (entry.getKey().toString().equalsIgnoreCase(id.toString())) {

				String type = entry.getValue().getStaffType();

				if (type.equalsIgnoreCase("lecturer")) {
					Lecturer.addModuleList(modules);
					return true;
				} else if (type.equalsIgnoreCase("researcher")) {
					Researcher.addStudentNames(students);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method to employ staff which creates a chain of new Obj instances using the given parameters.
	 * Name object is created using the Name constructor from first name and lastName.
	 * SmartCard is created using the Name object, dob and employments status as parameters using getInstance.
	 * The SmartCardNumber object is created within the SmartCard getInstance method so that it is tied to the creation of SmartCards.
	 * Using these Object instances the Abstract GetInstance method can be called which creates either a Lecturer or Researcher instance.
	 * The age range is checked and the name and dob is checked against existing Staff instances using the compareTo() outlined in SmartCard.
	 * this ensures that the same person cannot be employed twice.
	 * @return the newly created Staff member is returned.
	 */
	public Staff employStaff (String firstName, String lastName, Date dob, String staffType, String employmentStatus) {
		//add your code here. Do NOT change the method signature
		Name staffName = new Name(firstName, lastName);
		SmartCard staffSmartCard = SmartCard.getInstance(staffName, dob, employmentStatus);
		Staff staffMember = AbstractStaff.getInstance(employmentStatus, staffType, staffSmartCard);

		if (calculateAge(dob) >= 22 && calculateAge(dob) <= 67) {
			for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
				if (entry.getValue().getSmartCard().compareTo(staffSmartCard) != 0) {
					StaffID id = staffMember.getStaffID();
					staffMap.put(id, staffMember);
				} else {
					throw new IllegalArgumentException("A staff member with this name of " + staffType + "already exists");
				}
			}
		} else {
			throw new IllegalArgumentException("A staff member must be at least 22 and at most 67");
		}
		return staffMember;
	}

	/**
	 * method to get a collection view of all staff members in the staff map.
	 * @return a collection view of all staff members.
	 */
	public Collection<Staff> getAllStaff () {
		//add your code here. Do NOT change the method signature
		return staffMap.values();
	}

	/**
	 * Method to terminate the Staff member with the parameter StaffID.
	 * Iterates over all staff members checking if their StaffId matches the parameter.
	 * If they match the Staff member is removed from the staff map.
	 */
	public void terminateStaff (StaffID id){
		//add your code here. Do NOT change the method signature
		for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
			if (entry.getKey().toString().equalsIgnoreCase(id.toString())) {
				staffMap.remove(id);
			}
		}
	}

	/**
	 * Method to turn a Date object into a calendar.
	 * Made to avoid repetition of code elsewhere in the project.
	 * @return Calendar newCal which is the entered date parameter as a Calendar Object.
	 */
	public static Calendar dateToCalendar (Date date){
		Calendar newCal = Calendar.getInstance();
		newCal.setTime(date);
		return newCal;
	}

	/**
	 * Method to calculate the age of the Staff member.
	 * Uses the difference between current date and parameter date of birth to find the age.
	 * @returns age in years as an integer.
	 */
	public Integer calculateAge(Date dob) {
		int birthYear = dateToCalendar(dob).get(Calendar.YEAR);
		int birthMonth = dateToCalendar(dob).get(Calendar.MONTH);
		int birthDay = dateToCalendar(dob).get(Calendar.DAY_OF_YEAR);
		Calendar currentDate = Calendar.getInstance();

		currentDate.add(Calendar.YEAR, -birthYear);
		currentDate.add(Calendar.MONTH, -birthMonth);
		currentDate.add(Calendar.DAY_OF_YEAR, -birthDay);

		return currentDate.get(Calendar.YEAR);
	}
}

