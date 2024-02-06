package uk.ac.ncl.CSC8014.StaffManagerClasses;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Researcher extends AbstractStaff {

    private static final Set<Name> studentNameSet = new HashSet<>();

    /**
     * The constructor for the researcher class.
     * calls the constructor from uk.ac.ncl.CSC8014_AntonioDePasquale.AbstractStaff when stafftype is "researcher"
     */
    Researcher(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        super(card, employmentStatus, staffType, id);
    }

    /**
     * Returns the set of student names supervised by the researcher.
     * @return Set of student name objects.
     */
    public static Set<Name> getStudentNameSet() {
        return studentNameSet;
    }

    /**
     * Returns true if supervisor is supervising enough students, false otherwise.
     * 10 students are required in total.
     * @return true or false.
     */
    public static Boolean supervisingTenStudentsCheck() {
        if (studentNameSet.size() >= 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * function to add all names in a Set entered as a parameter to the StudentNameSet in researcher.
     * Iterates through set of student names, adds names to the StudentNameSet in researcher one.
     * At each step the number of students a researcher has is checked by supervisingTenStudentsCheck function.
     * If the number of students is less than 10 it is added as a researcher may only supervise 10 students.
     */
    public static void addStudentNames(Set<Name> studentNames) {
        Iterator<Name> studentNamesIterator = studentNames.iterator();

        while (studentNamesIterator.hasNext()) {
            Name currentName = studentNamesIterator.next();
            if (!supervisingTenStudentsCheck()) {
                studentNameSet.add(currentName);
            }
        }
    }
}
