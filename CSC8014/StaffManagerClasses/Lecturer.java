package uk.ac.ncl.CSC8014.StaffManagerClasses;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Lecturer extends AbstractStaff {

    private static final Set<Module> moduleSet = new HashSet<>();

    /**
     * The constructor for the researcher class.
     * calls the constructor from AbstractStaff getInstance when stafftype is Lecturer"
     */
    Lecturer(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        super(card, employmentStatus, staffType, id);
    }

    /**
     * Returns the Lecturer module Set.
     * Module consists of a name, module code, a semester and number of credits.
     * @return module object Set.
     */
    public Set<Module> getModuleList() {
        return moduleSet;
    }

    /**
     * function to add all modules in a Set entered as a parameter to the moduleSet in researcher.
     * Iterates through set of modules, adds modules to the moduleSet in Lecturer one by one.
     * At each step the number of total credits a lecturer is teaching is checked by lecturerModuleCreditCheck function.
     * If the credit total including the credits that would be added by the module being checked is 40 or less than
     * the current module is added to moduleSet.
     */
    public static void addModuleList(Set<Module> modules) {
        Iterator<Module> modulesIterator = modules.iterator();

        while(modulesIterator.hasNext()) {
            Module currentModule = modulesIterator.next();
            if(!lecturerModuleCreditCheck(currentModule))
                moduleSet.add(currentModule);
        }
    }

    /**
     * Returns true if lecturer is teaching enough credits.
     * true if 40 credits in both semester or false otherwise.
     * @return true or false.
     */
    public Boolean lecturerModuleCreditCheck() {
        Iterator<Module> modulesIterator = moduleSet.iterator();
        Integer moduleCreditCount = 0;

        while(modulesIterator.hasNext()) {
            moduleCreditCount += modulesIterator.next().getCredits();
        }

        if (moduleCreditCount >= 40) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overloaded method of the lecturerModuleCreditCheck above which takes a parameter to be used when new modules are added.
     * returns true if lecturer is teaching enough credits.
     * true if 40 credits in both semesters including the credits of the parameter module or false otherwise.
     * @return true or false.
     */
    static Boolean lecturerModuleCreditCheck(Module module) {
        Iterator<Module> modulesIterator = moduleSet.iterator();
        Integer moduleCreditCount = 0;

        while(modulesIterator.hasNext()) {
            moduleCreditCount += modulesIterator.next().getCredits();
        }

        if (moduleCreditCount + module.getCredits() > 40) {
            return true;
        } else {
            return false;
        }
    }
}
