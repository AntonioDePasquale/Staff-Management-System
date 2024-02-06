package uk.ac.ncl.CSC8014.TestClasses;

import uk.ac.ncl.CSC8014.StaffManagerClasses.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static uk.ac.ncl.CSC8014.StaffManagerClasses.Researcher.addStudentNames;
import static uk.ac.ncl.CSC8014.StaffManagerClasses.Researcher.supervisingTenStudentsCheck;

public class ResearcherTesting {

    public static void main(String[] args) {
        ResearcherTesting researcher = new ResearcherTesting();
        researcher.createResearcher();
        researcher.getStudentNameSetTest();
        researcher.addStudentNamesTest();
        researcher.supervisingTenStudentsCheckTest();
    }

    StaffManager newStaffManager = StaffManager.getInstance();
    String firstName = "Jimmy";
    String lastName = "John";
    String lecturerStaffType = "lecturer";
    String researcherStaffType = "researcher";
    String fixedTerm = "fixed-term";
    String permanent = "permanent";

    Date validDOB = validDate();

    private void createResearcher() {

        //test normal case
        Staff testStaff = newStaffManager.employStaff(firstName, lastName, validDOB, researcherStaffType, fixedTerm);
        Assertions.assertNotNull(testStaff);

        //test exception case (empty string parameter)
        try {
            Staff testStaff3 = newStaffManager.employStaff("", "", validDOB, researcherStaffType, fixedTerm);
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
            Staff testStaff5 = newStaffManager.employStaff(firstName, lastName, ageUnder22, researcherStaffType, fixedTerm);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
        try {
            Staff testStaff6 = newStaffManager.employStaff(firstName, lastName, ageOver67, researcherStaffType, fixedTerm);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, t);
        }
    }

    private void getStudentNameSetTest() {
        Set<Name> nameTestSet = new HashSet<Name>();
        Researcher testStaff = (Researcher) newStaffManager.employStaff(firstName, lastName, validDOB, researcherStaffType, fixedTerm);

        Assertions.assertEquals(nameTestSet, testStaff.getStudentNameSet());
    }

    //test to see if the student Name sets are equal
    private void addStudentNamesTest() {
        Researcher testStaff = (Researcher) newStaffManager.employStaff(firstName, lastName, validDOB, researcherStaffType, fixedTerm);
        Set<Name> studentNameTestSet = new HashSet<Name>();
        Name testStudent3 = new Name(firstName, lastName);
        Name testStudent4 = new Name("jim", "john");
        studentNameTestSet.add(testStudent3);
        studentNameTestSet.add(testStudent4);

        addStudentNames(studentNameTestSet);

        Assertions.assertEquals(studentNameTestSet, testStaff.getStudentNameSet());
    }

    //less than 10 students will return false
    private void supervisingTenStudentsCheckTest() {
        Researcher testStaff2 = (Researcher) newStaffManager.employStaff(firstName, lastName, validDOB, researcherStaffType, fixedTerm);

        Name testStudent = new Name(firstName, lastName);
        Set<Name> nameTestSet = new HashSet<Name>();
        nameTestSet.add(testStudent);
        Researcher.addStudentNames(nameTestSet);

        Assertions.assertFalse(supervisingTenStudentsCheck());

        //after adding a set of >10 students the function returns true
        for (int i = 0; i <11; i++ ) {
            String temp = "a" + i;
            Name tempStudent = new Name(temp, temp);
            nameTestSet.add(tempStudent);
        }
        Researcher.addStudentNames(nameTestSet);

        Assertions.assertTrue(supervisingTenStudentsCheck());
    }

    private Date validDate() {
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1990, 1, 1);
        return cal2.getTime();
    }
}
