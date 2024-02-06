package uk.ac.ncl.CSC8014.TestClasses;

import uk.ac.ncl.CSC8014.StaffManagerClasses.StaffID;

public class StaffIDTesting {

    public static void main(String[] args) {
        StaffIDTesting staffIDTest = new StaffIDTesting();
        staffIDTest.createStaffID();
        staffIDTest.toStringTest();
    }

    //StaffID's are completely randomised when getInstance() is called, so it is difficult test
    private static void createStaffID() {

        //test normal case
        StaffID testStaffID = StaffID.getInstance();
        Assertions.assertNotNull(testStaffID);
    }

    private static void toStringTest() {
        StaffID testStaffID2 = StaffID.getInstance();
        Assertions.assertNotNull(testStaffID2);
    }
}
