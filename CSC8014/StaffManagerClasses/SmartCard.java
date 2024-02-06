package uk.ac.ncl.CSC8014.StaffManagerClasses;

import java.util.Calendar;
import java.util.Date;

public final class SmartCard implements Comparable<SmartCard> {

    private final Name staffName;
    private final Date dateOfBirth;
    private final SmartCardNumber smartCardNumber;
    private final Date dateIssued;
    private Date expiryDate;

    private final String employmentStatus;

    /**
     * Constructor for SmartCard which takes a Name obj, smartCardNumber, date issued and dat of birth as parameters.
     * sets variables to appropriate parameters.
     * constructor is private, only called in getInstance method.
     */
    private SmartCard(Name staffName, SmartCardNumber smartCardNumber, Date dateIssued, Date dateOfBirth, String employeeStatus) {
        this.staffName = staffName;
        this.smartCardNumber = smartCardNumber;
        this.dateIssued = dateIssued;
        this.dateOfBirth = dateOfBirth;
        this.employmentStatus = employeeStatus;
        setExpiryDate();
    }

    /**
     * getInstance factory method to get instance of SmartCard using constructor.
     * creates a new SmartCardNumber object, gets the issued date from current date.
     * SmartCardNumber is only created when a new is so SmartCard instances are always unique
     * @return new instance of SmartCard.
     */
    public static SmartCard getInstance(Name staffName, Date dateOfBirth, String employmentStatus) {

        Calendar newCal = Calendar.getInstance();
        Date issuedDate = newCal.getTime();

        SmartCardNumber smartCardNumber = SmartCardNumber.getInstance(staffName);

        return new SmartCard(staffName, smartCardNumber, issuedDate, dateOfBirth, employmentStatus);
    }

    /**
     * Getter for the staffName of the SmartCard instance
     * @return the fullName String from the Name object.
     */
    public String getStaffName() {
        return staffName.getFullName();
    }

    /**
     * Getter for the date of birth of the SmartCard instance
     * @return the Date of birth Date variable from SmartCard.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Getter for the Smart card number of the SmartCard instance
     * @return the full card number String from SmartCardNumber object using the getFullCardNumber() method.
     */
    public String getSmartCardNumber() {
        return smartCardNumber.getFullCardNumber();
    }

    /**
     * Getter for the date issued of the SmartCard instance
     * @return the dateIssued Date varible.
     */
    public Date getDateIssued() {
        return dateIssued;
    }

    private void setExpiryDate() {
        Calendar expiryCal = StaffManager.dateToCalendar(getDateIssued());

        if (employmentStatus.equals("fixed-term")) {
            expiryCal.add(Calendar.YEAR, 2);
            expiryDate = expiryCal.getTime();
        } else if (employmentStatus.equals("permanent")) {
            expiryCal.add(Calendar.YEAR, 10);
            expiryDate = expiryCal.getTime();
        }
    }

    public Date getExpiryDate() {
        if (expiryDate == null) {
            throw new IllegalArgumentException("expiryDate is null and has not been set");
        } else {
            return expiryDate;
        }
    }
    @Override
    public int compareTo(SmartCard staffCard) {
        if (this.getStaffName().compareTo(staffCard.getStaffName()) == 0) {
            return this.getDateOfBirth().compareTo(staffCard.getDateOfBirth());
        } else {
            return this.getStaffName().compareTo(staffCard.getStaffName());
        }
    }
}
