package uk.ac.ncl.CSC8014.TestClasses;

/** 
 * Assertions - a set of static utility methods to assert the value 
 * of objects and conditions etc. for testing purposes. All methods throw an 
 * AssertionError if the given values or conditions are not as asserted.
 * 
 * See JUnit <http://www.junit.org/> for a more systematic framework for
 * unit testing that is integrated with most IDEs.
 * 
 * @author Nick Cook &lt;nick.cook@ncl.ac.uk&gt;
 * @version svn: $Revision: 4037 $<br>
 * $Date: 2015-10-28 14:58:39 +0000 (Wed, 28 Oct 2015) $<br>
 * Copyright (C) 2011 Newcastle University, UK
 */

public class Assertions {
    private Assertions() { }

    /**
     * Assert that the given boolean value is true.
     *
     * @param b the value to test
     * @throws AssertionError if <code>b</code> is <code>false</code>
     */
    public static void assertTrue(boolean b) {
        if (!b)
            throw new AssertionError(buildMessage(true, b));
    }
    
    /**
     * Assert that the given boolean value is false.
     *
     * @param b the value to test
     * @throws AssertionError if <code>b</code> is <code>true</code>
     */
    public static void assertFalse(boolean b) {
        if (b)
            throw new AssertionError(buildMessage(false, b));
    }
    
    /**
     * Assert that a given int is equal to the expected value for the
     * int.
     *
     * @param expected the expected value of an int for equality testing
     * @param actual the actual value of the int to compare with the 
     * expected value
     * @throws AssertionError if <code>expected</code> is not equal to
     * <code>actual</code>, i.e. throws AssertionError if
     * <code>expected == actual</code> is <code>false</code>
     */
    public static void assertEquals(int expected, int actual) {
        if (expected != actual)
            throw new AssertionError(buildMessage(expected, actual));
    }

    /**
     * Assert that a given object is equal to the expected value for the
     * object.
     *
     * @param expected the expected value of an object for equality testing
     * @param actual the actual value of the object to compare with the 
     * expected value
     * @throws AssertionError if <code>expected</code> is not equal to
     * <code>actual</code>, i.e. throws AssertionError if
     * <code>expected.equals(actual)</code> is <code>false</code>
     */
    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual))
            throw new AssertionError(buildMessage(expected, actual));
    }

    /**
     * Assert that the two objects are not equal.
     *
     * @param obj1 an object to test for not being equal to another
     * @param obj2 an object to test for not being equal to another
     * @throws AssertionError if <code>obj1</code> is  equal to
     * <code>obj2</code>, i.e. throws AssertionError if
     * <code>obj1.equals(obj2)</code> is <code>true</code>
     */
    public static void assertNotEquals(Object obj1, Object obj2) {
        if (obj1.equals(obj2))
            throw new AssertionError(
                "\"" + obj1 + "\" and \"" + obj2 + "\" should not be equal");
    }

    /**
     * Assert that an object is <code>null</code>.
     *
     * @param o the object to test for nullity
     * @throws AssertionError if <code>o</code> is not <code>null</code>
     */
    public static void assertNull(Object o) {
        if (o != null)
            throw new AssertionError(buildMessage(null, o));
    }
    
    /**
     * Assert that an object is not <code>null</code>.
     *
     * @param o the object to test for non-nullity
     * @throws AssertionError if <code>o</code> is <code>null</code>
     */
    public static void assertNotNull(Object o) {
        if (o == null)
            throw new AssertionError(buildMessage("not null", o));
    }
    
    /**
     * Assert that a given <code>Throwable</code> is of the same type as the 
     * given expected <code>Throwable</code> class.
     *
     * @param expectedClass the expected class of the <code>Throwable</code>
     * @param t the <code>Throwable</code> to compare type against 
     * <code>expectedClass</code>
     * @throws AssertionError if <code>t</code> is not an instance of
     * <code>expectedClass</code>
     */
    public static void assertExpectedThrowable(
        Class<? extends Throwable> expectedClass, Throwable t) {
        if (!expectedClass.isInstance(t)) {
            AssertionError e = new AssertionError(
                buildMessage(expectedClass.getName(), t));
            
            // update cause of assertion error with t
            e.initCause(t);
            
            throw e;
        }
    }
    
    /**
     * Asserts that a line of code should not be reached.
     *
     * @throws AssertionError if reach line in code that should not be 
     * executed
     */
    public static void assertNotReached() {
        throw new AssertionError("Reached code that should not be reached");
    }

    private static String buildMessage(Object expected, 
        Object actual) {
        final StringBuilder sb = new StringBuilder("expected ");
        sb.append("\"").append(expected).append("\"");
        sb.append(", actual value is ");
        sb.append("\"").append(actual).append("\"");
        
        return sb.toString();
    }
}
