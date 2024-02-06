package uk.ac.ncl.CSC8014.TestClasses;
import uk.ac.ncl.CSC8014.StaffManagerClasses.Module;
import uk.ac.ncl.CSC8014.StaffManagerClasses.Name;

public class ModuleTesting {

    public static void main(String[] args) {
        ModuleTesting moduleTesting = new ModuleTesting();
        moduleTesting.createModule();
        moduleTesting.testGetModuleCode();
        moduleTesting.testGetModuleName();
        moduleTesting.testGetModuleCode();
        moduleTesting.testGetSemester();
        moduleTesting.testGetModuleCredits();
        moduleTesting.testToString();
    }

    private void createModule() {
        //test normal case
        Module testModule = new Module("csc8014", "Advanced Techniques", 2, 10);
        Assertions.assertNotNull(testModule);

        //test exception case (null parameters)
        try {
            Module testModule2 = new Module(null, "Advanced Techniques", 2, 10);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(NullPointerException.class, e);
        }

        //test exception case (empty string parameter)
        try {
            Module testModule3 = new Module("", "Advanced Techniques", 2, 10);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }

        //test border case (lower/upper end parameters credits and semester)
        Module testModule4 = new Module("csc8014", "Advanced Techniques", 1, 10);
        Assertions.assertNotNull(testModule4);
        Module testModule5 = new Module("csc8014", "Advanced Techniques", 3, 60);
        Assertions.assertNotNull(testModule5);

        //test exceptions (lower/upper exception parameters)
        try {
            Module testModule6 = new Module("csc8014", "Advanced Techniques", 4, 70);
        } catch (Throwable e) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, e);
        }
        try {
            Module testModule7 = new Module("csc8014", "Advanced Techniques", 0, 9);
        } catch (Throwable t) {
            Assertions.assertExpectedThrowable(IllegalArgumentException.class, t);
        }
    }

    private void testGetModuleCode() {
        Module testModule = new Module("csc8014", "Advanced Techniques", 2, 10);
        String expected = "csc8014";
        Assertions.assertEquals(expected, testModule.getModuleCode());
    }

    private void testGetModuleName() {
        Module testModule = new Module("csc8014", "Advanced Techniques", 2, 10);
        String expected = "Advanced Techniques";
        Assertions.assertEquals(expected, testModule.getModuleName());
    }

    private void testGetSemester() {
        Module testModule = new Module("csc8014", "Advanced Techniques", 2, 10);
        Integer expected = 2;
        Assertions.assertEquals(expected, testModule.getSemester());
    }

    private void testGetModuleCredits() {
        Module testModule = new Module("csc8014", "Advanced Techniques", 2, 10);
        Integer expected = 10;
        Assertions.assertEquals(expected, testModule.getCredits());
    }

    private void testToString() {
        Module testModule = new Module("csc8014", "Advanced Techniques", 2, 10);
        String expected = "csc8014, Advanced Techniques, 2, 10";
        Assertions.assertEquals(expected,testModule.toString());
    }
}
