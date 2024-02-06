package uk.ac.ncl.CSC8014.StaffManagerClasses;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class StaffID {

    private static final Map<String, StaffID> STAFFIDS = new HashMap<String, StaffID>();
    private final String fullID;

    /**
     * StaffID constructor.
     * set to private and only accessible through the getInstance method.
     */
    private StaffID(String fullID) {
        this.fullID = fullID;
    }

    /**
     * StaffID getInstance factory method.
     * set to private and only accessible through the getInstance method.
     * a random lowercase char of any letter of the alphabet is randomly generated.
     * a random number between 111 and 999 is generated.
     * these are concatenated together to creates the fullID string.
     * this is checked for uniqueness against a Map of generated StaffID's.
     * If it is unique it is created and added to the map STAFFIDS.
     * @return id;
     */
    public static StaffID getInstance() {

        Random r = new Random();
        char randomChar = (char)(r.nextInt(26) + 'a');
        int randomThreeNumbers = r.nextInt(900) + 100;
        String fullID = String.valueOf(randomChar + randomThreeNumbers);

        StaffID id = STAFFIDS.get(fullID);
        if (id == null) {
            id = new StaffID(fullID);
            STAFFIDS.put(fullID, id);
        } else {
            getInstance();
        }
        return id;
    }

    /**
     * override of toString to return a full staffID string instead of a staffID object.
     * @return fullID string variable;
     */
    @Override
    public String toString() {
        return fullID;
    }
}
