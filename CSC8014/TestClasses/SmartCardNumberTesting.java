package uk.ac.ncl.CSC8014.TestClasses;

import uk.ac.ncl.CSC8014.StaffManagerClasses.Name;
import uk.ac.ncl.CSC8014.StaffManagerClasses.SmartCardNumber;

public class SmartCardNumberTesting {

    Name testcase = new Name("test", "dude");
    SmartCardNumber testSmartCard = SmartCardNumber.getInstance(testcase);

    public static void main(String[] args) {
        SmartCardNumberTesting smartCardNumberTest = new SmartCardNumberTesting();
        smartCardNumberTest.createSmartCardNumber();
        smartCardNumberTest.createInitialPrefixTest();
        smartCardNumberTest.testSmartCardNumberGetters();
    }

    private void createSmartCardNumber() {

        //test normal case
        SmartCardNumber smartCard = SmartCardNumber.getInstance(testcase);
        Assertions.assertNotNull(testcase);


        //test exception case (null parameter)
        try {
            SmartCardNumber smartCard2 = SmartCardNumber.getInstance(null);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(NullPointerException.class, e);
        }
    }

    // the initials created by the method should be td when used the with Name object test dude
    private void createInitialPrefixTest() {

        //normal case
        String expectedInitials = "TD";
        String actualInitials = SmartCardNumber.createInitialPrefix(testcase);

        Assertions.assertEquals(expectedInitials, actualInitials);

        //test exception case (null parameter)
        try {
            String expectedInitials2 = "TD";
            String actualInitials2 = SmartCardNumber.createInitialPrefix(null);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(NullPointerException.class, e);
        }
    }

    //testing all getter methods
    private void testSmartCardNumberGetters() {

        SmartCardNumber testSmartCard2 = SmartCardNumber.getInstance(new Name("the", "dude"));

        Integer serialNumber = 13;
        String initials = "TD";
        String fullCardNumber = "TD-13-2023";

        Assertions.assertEquals(serialNumber, testSmartCard2.getSerialNumber());
        Assertions.assertEquals(initials, testSmartCard2.getNameInitials());
        Assertions.assertEquals(fullCardNumber, testSmartCard2.getFullCardNumber());

        //testing if the serial number static variable increases by 1 each time a smartcard is created

        SmartCardNumber testSmartCard3 = SmartCardNumber.getInstance(new Name("very", "testy"));

        serialNumber = 14;
        initials = "VT";
        fullCardNumber = "VT-14-2023";

        Assertions.assertEquals(serialNumber, testSmartCard3.getSerialNumber());
        Assertions.assertEquals(initials, testSmartCard3.getNameInitials());
        Assertions.assertEquals(fullCardNumber, testSmartCard3.getFullCardNumber());
    }
}
