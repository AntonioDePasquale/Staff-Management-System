package uk.ac.ncl.CSC8014.StaffManagerClasses;


public final class Name {

    private final String firstName;
    private final String lastName;
    private final String fullName;

    /**
     * Constructor for the name Object.
     * Takes in following variables as parameters and sets them to the appropriate variables
     * on creation of new Name instance.
     * fullName is created by concatenating firstName and lastName.
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

    /**
     * Getter for the first name of the Name instance
     * @return the firstName variable
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name of the Name instance
     * @return the lastName variable
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the full name of the Name instance
     * @return the fullName variable
     */
    public String getFullName() {
        return fullName;
    }
}
