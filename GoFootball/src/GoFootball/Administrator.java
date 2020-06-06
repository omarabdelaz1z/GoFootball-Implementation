package GoFootball;
/**
 * administrator is managed by the system.
 */
public class Administrator
{
    Administrator() { }

    /**
     * Validate playground information.
     * @param givenPLG , Playground object
     * @return boolean
     */
    public boolean validateInfo(Playground givenPLG) { return givenPLG.getLocation().checkFormat(); }

    /**
     * Validate owner's entered location when he edits his playground
     * @param location , Location object
     * @return boolean
     */
    public boolean validateInfo(Location location) { return location.checkFormat(); }

    /**
     * A function grouped of three function each calculate owner reputation.
     * the owner's playground, may get activated, suspended or deleted.
     * @param givenPLG ,Playground Object
     * @return int value if's 3, playground is deleted.
     */
    public int manipulatePLG(Playground givenPLG) {
        // active = 3 ;
        // suspended = 2;
        // delete = 1;

        PLG_Owner ownerOfPlG = givenPLG.getOwner();
        double ownerReputation = ownerOfPlG.getReputation();

        if(ownerReputation == 0.0)
            return -1;

        else {
            if (ownerReputation < 1) {
                ownerOfPlG.setPLG_NULL();
                return 1;
            }

            if (ownerReputation < 2.50) {
                givenPLG.setStatus("SUSPENDED");
                return 2;
            }

            if (ownerReputation >= 2.50) {
                givenPLG.setStatus("Active");
                return 3;
            }
        }
        return -1;
    }
}
