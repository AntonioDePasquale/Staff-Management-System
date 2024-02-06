package uk.ac.ncl.CSC8014.TestClasses;

import uk.ac.ncl.CSC8014.StaffManagerClasses.*;

import java.util.Calendar;
import java.util.Date;

public class SmartCardTesting {

    private final Name testName = new Name("test", "dude");
    private final Date dob = validDate();
    private final String employmentStatus = "permanent";

    public static void main(String[] args) {
        SmartCardTesting test = new SmartCardTesting();
        test.createSmartCard();
        test.testStaffIDGetters();
    }

    private void createSmartCard() {

        //test normal case
        SmartCard testSmartCard = SmartCard.getInstance(testName, dob, employmentStatus);
        Assertions.assertNotNull(testSmartCard);

        //test exception case (null parameters)
        try {
            final SmartCard testSmartCard2 = SmartCard.getInstance(null, dob, employmentStatus);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(NullPointerException.class, e);
        }

        //test exception case (empty string parameter)
        try {
            final SmartCard testSmartCard3 = SmartCard.getInstance(new Name("", ""), dob, employmentStatus);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(StringIndexOutOfBoundsException.class, e);
        }
    }

    private void testStaffIDGetters() {

        Calendar expiryCal = Calendar.getInstance();
            expiryCal.add(Calendar.YEAR, 10);
            Date expiryDate = expiryCal.getTime();

        String staffName = "test dude";
        String smartCardNumberTest = "TD-12-2023";
        Date issuedDate = new Date();

        SmartCard testSmartCard = SmartCard.getInstance(testName, dob, employmentStatus);
        Assertions.assertEquals(staffName, testSmartCard.getStaffName());
        Assertions.assertEquals(smartCardNumberTest, testSmartCard.getSmartCardNumber());
        Assertions.assertEquals(issuedDate, testSmartCard.getDateIssued());
        Assertions.assertEquals(expiryDate, testSmartCard.getExpiryDate());
        }

        //test to see if 10 years is added to the issue date when employment status is permanent
        // 2 years are added to the issue date when employment status is fixed-term
    private void setExpiryDateTest() {

        Calendar expiryCal = Calendar.getInstance();
        expiryCal.add(Calendar.YEAR, 10);
        Date expiryDatePermanent = expiryCal.getTime();

        Calendar expiryCal2 = Calendar.getInstance();
        expiryCal2.add(Calendar.YEAR, 2);
        Date expiryDateFixed = expiryCal2.getTime();

        //setExpiryDateMethod is called in the SmartCard constructor
        SmartCard testSmartCard = SmartCard.getInstance(testName, dob, "fixed-term");
        SmartCard testSmartCard2 = SmartCard.getInstance(testName, dob, "permanent");

        Assertions.assertEquals(expiryDateFixed, testSmartCard.getExpiryDate());
        Assertions.assertEquals(expiryDatePermanent, testSmartCard2.getExpiryDate());
    }

    //not to be tested, only used to get a valid date
    private Date validDate() {
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1990, 1, 1);
        return cal2.getTime();
    }

}
