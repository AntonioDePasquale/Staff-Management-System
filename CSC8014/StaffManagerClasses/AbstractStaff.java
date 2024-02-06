package uk.ac.ncl.CSC8014.StaffManagerClasses;

public abstract class AbstractStaff implements Staff {

    private final StaffID id;
    private final SmartCard card;
    private final String employmentStatus;
    private final String staffType;

    /**
     * The AbstractStaff constructor.
     * Checks the staff contract type for either fixed-term or permanent and sets it to the employmentStatus variable.
     * Is set to private and only called when getInstance() is called. Which calls either the lecturer or researcher
     * constructor. The abstractStaff constructor is never called on its own.
     */
    public AbstractStaff(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        this.card = card;
        this.staffType = staffType;
        this.id = id;

        if (employmentStatus == null) {
            throw new IllegalArgumentException("Contract type is null");
        }
        if (employmentStatus.equalsIgnoreCase("fixed-term")) {
            this.employmentStatus = employmentStatus;
        } else if (employmentStatus.equalsIgnoreCase("permanent")) {
            this.employmentStatus = employmentStatus;
        } else {
            throw new IllegalArgumentException("\"Enter either \"fixed-term\" or \"permanent\"");
        }
    }

    /**
     * The uk.ac.ncl.CSC8014_AntonioDePasquale.AbstractStaff getInstance factory method.
     * Checks the staff type for either lecturer or researcher and depending on input calls the appropriate constructor.
     * Creates a staff ID object to be used in lecturer/researcher object instance.
     * @return returns the created staff instance (either lecturer or researcher depending on input).
     */
    public static Staff getInstance(String employmentStatus, String staffType, SmartCard newCard) {

        StaffID newStaffId = StaffID.getInstance();
        Staff result = null;

        if (staffType == null) {
            throw new IllegalArgumentException("uk.ac.ncl.CSC8014_AntonioDePasquale.Staff type is null");
        }
        if (staffType.equalsIgnoreCase("lecturer")) {
            result = new Lecturer(newCard,employmentStatus, staffType, newStaffId);
        } else if (staffType.equalsIgnoreCase("researcher")) {
           result = new Researcher(newCard, employmentStatus, staffType, newStaffId);
        } else {
            throw new IllegalArgumentException("\"Enter either \"lecturer\" or \"researcher\"");
        }
        return result;
    }

    /**
     * Implementation of getStaffID outlined in the Staff interface.
     * Returns the staff ID.
     * All staff must have an ID
     * @return the StaffID object
     */
    public StaffID getStaffID() {
        return this.id;
    };

    /**
     * Implementation of getSmartCard outlined in the Staff interface.
     * Returns the smart card.
     * All staff must have a smart card
     * @return the SmartCard object
     */
    public SmartCard getSmartCard() {
        return this.card;
    }

    /**
     * Implementation of getStaffEmploymentStatus outlined in the Staff interface.
     * Returns the uk.ac.ncl.CSC8014_AntonioDePasquale.Staff employment status.
     * a uk.ac.ncl.CSC8014_AntonioDePasquale.Staff can be either on Permanent or fixed contract
     * @return a string (Permanent or fixed)
     */
    public String getStaffEmploymentStatus() {
        return this.employmentStatus;
    }

    /**
     * Implementation of getStaffType outlined in the uk.ac.ncl.CSC8014_AntonioDePasquale.Staff interface.
     * Returns the uk.ac.ncl.CSC8014_AntonioDePasquale.Staff type.
     * a uk.ac.ncl.CSC8014_AntonioDePasquale.Staff can be either a uk.ac.ncl.CSC8014_AntonioDePasquale.Lecturer or a uk.ac.ncl.CSC8014_AntonioDePasquale.Researcher which is assigned on calling the getInstance creating
     * either a researcher r lecturer.
     * @return a string (uk.ac.ncl.CSC8014_AntonioDePasquale.Lecturer or uk.ac.ncl.CSC8014_AntonioDePasquale.Researcher)
     */
    public String getStaffType() {
        return this.staffType;
    }
}
