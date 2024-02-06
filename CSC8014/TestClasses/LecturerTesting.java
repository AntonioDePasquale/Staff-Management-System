package uk.ac.ncl.CSC8014.TestClasses;

import uk.ac.ncl.CSC8014.StaffManagerClasses.*;
import uk.ac.ncl.CSC8014.StaffManagerClasses.Module;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static uk.ac.ncl.CSC8014.StaffManagerClasses.Lecturer.addModuleList;

public class LecturerTesting {

    public static void main(String[] args) {
        LecturerTesting lecturer = new LecturerTesting();
        lecturer.createLecturer();
        lecturer.getModulesListTest();
        lecturer.addModulesTest();
        lecturer.moduleCreditTest();
    }
    StaffManager newStaffManager = StaffManager.getInstance();
    String firstName = "Jimmy";
    String lastName = "John";
    String lecturerStaffType = "lecturer";
    String researcherStaffType = "researcher";
    String fixedTerm = "fixed-term";
    String permanent = "permanent";

    Date validDOB = validDate();

    private void createLecturer() {


        //test normal case
        Staff testStaff = newStaffManager.employStaff(firstName, lastName, validDOB, lecturerStaffType, fixedTerm);
        Assertions.assertNotNull(testStaff);

        //test exception case (empty string parameter)
        try {
            Staff testStaff3 = newStaffManager.employStaff("", "", validDOB, lecturerStaffType, fixedTerm);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(StringIndexOutOfBoundsException.class, e);
        }

        //test exception case (empty string parameter)
        try {
            Staff testStaff3 = newStaffManager.employStaff(null, null, null, null, null);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(NullPointerException.class, e);
        }


        //test incorrect staffType (researcher string will create a researcher, they should not be equal)
        Staff testStaff3 = newStaffManager.employStaff(firstName, lastName, validDOB, lecturerStaffType, fixedTerm);
        Staff testStaff4 = newStaffManager.employStaff(firstName, lastName, validDOB, researcherStaffType, fixedTerm);
        Assertions.assertNotEquals(testStaff3, testStaff4);

        //test exceptions (lower/upper exception parameters for age requirements of staff)
        //Staff cannot be under 22 or over 67

        Calendar cal = Calendar.getInstance();
        cal.set(2010, 1, 1);
        Date ageUnder22 = cal.getTime();
        cal.set(1900, 1, 1);
        Date ageOver67 = cal.getTime();

        try {
            Staff testStaff5 = newStaffManager.employStaff(firstName, lastName, ageUnder22, lecturerStaffType, fixedTerm);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
        try {
            Staff testStaff6 = newStaffManager.employStaff(firstName, lastName, ageOver67, lecturerStaffType, fixedTerm);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, t);
        }
    }

    //should return a set of modules
    private void getModulesListTest() {
        Set<Module> moduleTestSet = new HashSet<Module>();
        Lecturer testStaff = (Lecturer) newStaffManager.employStaff(firstName, lastName, validDOB, lecturerStaffType, fixedTerm);

        Assertions.assertEquals(moduleTestSet, testStaff.getModuleList());
    }

    //adds a set of modules to a module set in
    private void addModulesTest() {

        Lecturer testStaff = (Lecturer) newStaffManager.employStaff(firstName, lastName, validDOB, lecturerStaffType, fixedTerm);
        Set<Module> moduleTestSet = new HashSet<Module>();
        Module testModule = new Module("moduleCode","moduleName", 2, 20);
        Module testModule2 = new Module("moduleCode2","moduleName2", 2, 10);
        moduleTestSet.add(testModule);
        moduleTestSet.add(testModule2);

        addModuleList(moduleTestSet);

        Assertions.assertEquals(moduleTestSet, testStaff.getModuleList());
    }

    //test to see if true is returned when credits are over 40
    private void moduleCreditTest() {

        Lecturer testStaff2 = (Lecturer) newStaffManager.employStaff(firstName, lastName, validDOB, lecturerStaffType, fixedTerm);

        //after adding modules totaling below 40 credits false should be returned
        Module testModule2 = new Module("moduleCode3","moduleName3", 2, 20);
        Set<Module> moduleTestSet2 = new HashSet<Module>();
        moduleTestSet2.add(testModule2);
        testStaff2.addModuleList(moduleTestSet2);

        Assertions.assertFalse(testStaff2.lecturerModuleCreditCheck());
    }

    //used to create a DateOfBirth between 22 and 67 years of age
    private Date validDate() {
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1990, 1, 1);
        return cal2.getTime();
    }
}
