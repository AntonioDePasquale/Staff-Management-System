package uk.ac.ncl.CSC8014.StaffManagerClasses;

public final class Module {

    private final String moduleCode;
    private final String moduleName;
    private final Integer semester;
    private final Integer credits;

    /**
     * Constructor for the Module class
     * takes in the following parameters and sets them to variables.
     */

    public Module(String moduleCode, String moduleName, Integer semester, Integer credits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;

        if (semester >= 1 && semester <= 3) {
            this.semester = semester;
        } else {
            throw new IllegalArgumentException("Invalid semester");
        }
        if (credits >= 10 && credits <= 60) {
            this.credits = credits;
        } else {
            throw new IllegalArgumentException("Invalid credits");
        }
    }

    /**
     * Getter for the credits of the Module
     * @return the credits variable
     */
    public Integer getCredits() {
        return this.credits;
    }

    /**
     * Getter for the moduleCode of the Module
     * @return the moduleCode variable
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Getter for the moduleName of the Module
     * @return the moduleName variable
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Getter for the semester of the Module
     * @return the semester variable
     */
    public Integer getSemester() {
        return semester;
    }

    /**
     * toString function that takes a module and concatenates its variables into a string.
     * @return the module variables as a full string.
     */
    @Override
    public String toString() {
         String moduleCode = this.getModuleCode();
         String moduleName = this.getModuleName();
         String semester = String.valueOf(this.getSemester());
         String credits = String.valueOf(this.getCredits());

        return moduleCode + ", " + moduleName + ", " + semester + ", " + credits;
    }
}
