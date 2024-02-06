package uk.ac.ncl.CSC8014.TestClasses;
import uk.ac.ncl.CSC8014.StaffManagerClasses.Name;

public class NameTesting {

    public static void main(String[] args) {
        NameTesting nameTesting = new NameTesting();
        nameTesting.createName();
        nameTesting.testNameGetters();
    }

    //test normal case
    private void createName() {
        //test normal case
        Name testName = new Name("Jimmy", "John");
        Assertions.assertNotNull(testName);

        //test exception case (null parameters)
        try {
            final Name testName2 = new Name(null, null);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(NullPointerException.class, e);
        }

        //test exception case (empty string parameter)
        try {
            final Name testName3 = new Name("Jimmy", "");
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
    }

    private void testNameGetters() {

        String firstName = "Ferrus";
        String lastName = "Manus";
        String fullName = "Ferrus Manus";

        Name testName = new Name("Ferrus", "Manus");
        Assertions.assertEquals(firstName, testName.getFirstName());
        Assertions.assertEquals(lastName, testName.getLastName());
        Assertions.assertEquals(fullName, testName.getFullName());
    }
}
