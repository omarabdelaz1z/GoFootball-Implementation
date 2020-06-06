package GoFootball;
import java.util.ArrayList;

/**
 * Stores the whole database of the system. Add/delete/print user info.
 */

public class GoFo_System
{
    /**
     * Class Attributes
     * Administrator of the system.
     * Database of Players, Owners and Verified Playgrounds.
     */
    private Administrator admin;
    private ArrayList<Player> Players;
    private ArrayList<PLG_Owner> Owners;
    private ArrayList<Playground> Verified_Playgrounds;

    /**
     * Initiates the system by initializing empty ArrayLists for the database.
     * @param givenAdmin : an object of class Administrator.
     */

    public GoFo_System(Administrator givenAdmin) {
        Verified_Playgrounds = new ArrayList<Playground>();
        Players = new ArrayList<Player>();
        Owners = new ArrayList<PLG_Owner>();

        admin = givenAdmin;
    }

    /**
     * Retrieve a player by its location in the arraylist.
     * @param playerIndex : Integer value
     * @return Player object at specified position.
     */

    public Player getPlayer(int playerIndex) {
        return Players.get(playerIndex);
    }

    /**
     * Retrieve an owner by its location in the arraylist.
     * @param ownerIndex : Integer value.
     * @return Owner object at specified position.
     */

    public PLG_Owner getOwner(int ownerIndex) {
        return Owners.get(ownerIndex);
    }

    /**
     * Retrieve a playground by its lcoation in the arraylist.
     * @param PLGIndex : Integer value
     * @return
     */
    public Playground getPlayground(int PLGIndex) {
        return Verified_Playgrounds.get(PLGIndex);
    }

    /**
     * Retrieve the admin of system.
     * @return admin object.
     */
    public Administrator getAdmin() {
        return admin;
    }

    /**
     * Display Player information.
     * @param playerIndex , Integer value indicates the position of the player at the ArrayList.
     */
    public void printPlayerInfo(int playerIndex) {
        System.out.print("\n- Name : " + Players.get(playerIndex).getName());
        System.out.print("\n- Email : " + Players.get(playerIndex).getEmail());
        System.out.print("\n- Phone Number : " + Players.get(playerIndex).getPhoneNumber());
        System.out.print("\n- City : " + Players.get(playerIndex).getCity());
        System.out.print("\n- Role : " + Players.get(playerIndex).getRole());

        Players.get(playerIndex).checkMoney();
    }

    /**
     * Display Owner information.
     * @param ownerIndex , Integer value indicates the position of the owner at the ArrayList.
     */

    public void printOwnerInfo(int ownerIndex) {
        System.out.print("\n- Name : " + Owners.get(ownerIndex).getName());
        System.out.print("\n- Email : " + Owners.get(ownerIndex).getEmail());
        System.out.print("\n- Phone Number : " + Owners.get(ownerIndex).getPhoneNumber());
        System.out.print("\n- City : " + Owners.get(ownerIndex).getCity());
        System.out.print("\n- Role : " + Owners.get(ownerIndex).getRole());

        Owners.get(ownerIndex).checkMoney();
    }

    /**
     * Display the active playgrounds in the system without filters.
     */
    public void printPlaygrounds() {
        int incremental = 1;
        for (Playground playground : Verified_Playgrounds)
        {
            if(playground.getStatus().equals("Active"))
            {
                System.out.println("\n\n- Playground" + "#" + (incremental));
                System.out.println("\n- Playground Name : " + playground.getName());
                System.out.println("\n- Owner Name : " + playground.getOwner().getName());
                System.out.println("\n- Location : " + playground.getLocation());
                System.out.println("\n- Price rent for Hour : " + playground.getPriceRent() + " EGP.");
                System.out.println("\n- Ratings : " + playground.getOwner().getReputation());
                System.out.println("\n- Start Hour : " + playground.getStartHour());
                System.out.println("\n- End Hour : " + playground.getEndHour());
                System.out.println("\n- Allowable Cancellation Period  : " + playground.getCancellationPeriod() + ". Minutes");

                incremental++;
            }
        }
    }

    /**
     * Display the playgrounds available filtered by city
     * @param City , String Object
     */
    public void printPlaygroundsGivenCity(String City) {
        int incremental = 1;

        for (Playground playground : Verified_Playgrounds)
        {
            if (playground.getLocation().getCity().equals(City) && playground.getStatus().equals("Active")) {
                System.out.println("\n\n- Playground" + "#" + (incremental));
                System.out.println("\n- Playground Name : " + playground.getName());
                System.out.println("\n- Owner Name : " + playground.getOwner().getName());
                System.out.println("\n- Location : " + playground.getLocation());
                System.out.println("\n- Price rent for Hour : " + playground.getPriceRent() + " EGP");
                System.out.println("\n- Ratings : " + playground.getOwner().getReputation());
                System.out.println("\nBooking Hours available per day :- ");
                System.out.println("\t-Start Hour : " + playground.getStartHour());
                System.out.println("\t-End Hour : " + playground.getEndHour());
                System.out.println("\n- Allowable Cancellation Period  : " +
                        playground.getCancellationPeriod() + " Minutes");
                incremental++;
            }
        }
    }

    /**
     * Display Playgrounds based on the start hour, end hour and city of the playground.
     * @param startHour , Double
     * @param endHour , Double
     * @param City , Double
     */
    public void printPlaygroundsGivenHoursAndCity(String startHour, String endHour, String City)
    {
        int incremental = 1;

        for (Playground playground : Verified_Playgrounds)
        {
            if (playground.getStatus().equals("Active"))
            {
                if(playground.getLocation().getCity().equals(City))
                {
                    if(playground.checkAvailableSlotsGivenHours(startHour,endHour))
                    {
                        System.out.println("\n\n- Playground" + "#" + (incremental));
                        System.out.println("\n- Playground Name : " + playground.getName());
                        System.out.println("\n- Owner Name : " + playground.getOwner().getName());
                        System.out.println("\n- Location : " + playground.getLocation());
                        System.out.println("\n- Price rent for Hour : " + playground.getPriceRent() + " EGP");
                        System.out.println("\n- Ratings : " + playground.getOwner().getReputation());
                        System.out.println("\n- Allowable Cancellation Period  : "
                                + playground.getCancellationPeriod() + " Minutes");
                        incremental++;
                    }
                }
            }
        }
    }

    /**
     * Add a player into the ArrayList
     * @param givenPlayer , Player Object.
     */
    public void addPlayer(Player givenPlayer) {
        Players.add(givenPlayer);
    }

    /**
     * Add an Owner into the ArrayList.
     * @param givenOwner , PLG_Owner Object
     */
    public void addOwner(PLG_Owner givenOwner) {
        Owners.add(givenOwner);
    }

    /**
     * Add a playground iff the location of the playground is valid. otherwise, not accepted.
     * @param PLG , Playground object
     * @return boolean value.
     */
    public boolean addPlayground(Playground PLG) {
        if (admin.validateInfo(PLG)) {
            Verified_Playgrounds.add(PLG);
            System.out.println("Information Verified.. The Playground has been added!");
            PLG.setStatus("Active");
            return true;
        }

        System.out.println("Playground information is wrong. It's not accepted.");
        PLG.getOwner().setPLG_NULL();

        return false;
    }

    /**
     * LogIn Function used to get into the system, Takes two parameters
     * @param givenEmail, String
     * @param password , String
     * @return String object specifiy if it's a Player the String will consists of P and the playerIndex else O and the ownerIndex.
     */
    public String LogIn(String givenEmail, String password) {
        return VerifyCredentials(givenEmail, password);
    }

    /**
     * Check if the user added email is already exists, takes only a parameter
     * @param givenEmail , User Email.
     * @return boolean
     */
    public boolean checkIfAlreadyExists(String givenEmail) {
        for (Player player : Players) {

            if (givenEmail.equals(player.getEmail())) {
                System.out.println("The given email is already associated with another account.");
                return true;
            }
        }

        for (PLG_Owner owner : Owners) {

            if (givenEmail.equals(owner.getEmail())) {
                System.out.println("The given email is already associated with another account.");
                return true;
            }
        }

        return false;
    }

    /**
     * Delete a playground from the ArrayList
     * @param givenPLG, Object Playground
     */

    // used to delete a playground if the owner reputation is 1;
    public void deletePlayground(Playground givenPLG) {
        Verified_Playgrounds.remove(givenPLG);
    }


    /**
     * Verified the LogIn information passed by the user.
     * @param givenEmail , String
     * @param givenPassword , String
     * @return String object
     */

    private String VerifyCredentials(String givenEmail, String givenPassword) {
        //Players = 1
        // Owners = 2;
        String Determinant;
        for (int i = 0; i < Owners.size(); i++) {
            if (Owners.get(i).getEmail().equals(givenEmail) && Owners.get(i).getPassword().equals(givenPassword))
            {
                System.out.print("\nLogged in.\n");
                Determinant = "O" + String.valueOf(i);
                return Determinant;
            }
        }

        for (int i = 0; i < Players.size(); i++)
        {
            if (Players.get(i).getEmail().equals(givenEmail) && Players.get(i).getPassword().equals(givenPassword)) {
                System.out.print("\nLogged in.\n");
                Determinant = "P" + String.valueOf(i);
                return Determinant;
            }
        }
        System.out.println("incorrect credentials.");
        return null;
    }

    /**
     * Get a playground given its name.
     * @param givenName , String object.
     * @return PLG Object or null if it's not found.
     */

    public Playground getPlaygroundbyName(String givenName) {
        for(Playground PLG : Verified_Playgrounds){
            if(PLG.getName().equals(givenName)){
                return PLG;
            }
        }
        System.out.println("Not Found!. Try Again\n");
        return null;
    }

    /**
     * Get player given it's email
     * @param Email , String
     * @return Player object or null if it's not found.
     */

    public Player getPlayerByEmail(String Email) {
        for (Player player : Players)
        {
            if (Email.equals(player.getEmail()))
                return player;
        }

        return null;
    }

    /**
     * Get Player/Owner given an Email String
     * @param givenEmail , String
     * @return Player/Owner object or null if not found.
     */
    public User getUserByEmail(String givenEmail) {
        for (Player player : Players) {
            if (givenEmail.equals(player.getEmail())) {
                return player;
            }
        }

        for (PLG_Owner owner : Owners) {

            if (givenEmail.equals(owner.getEmail())) {
                return owner;
            }
        }
        return null;
    }

    /**
     * Return the size of the players arraylist.
     * @return number of players, integer value
     */
    public int getPlayersLength() {
        return Players.size();
    }

    /**
     * Return the size of the owners arraylist.
     * @return number of owners, integer value
     */
    public int getOwnersLength() {
        return Owners.size();
    }

    /**
     * Find and return a playground index given the playground object
     * @param givenPLG : Playground Object
     * @return the location of that plg.
     */
    public int getIndexOfPlayground(Playground givenPLG){
        return Verified_Playgrounds.indexOf(givenPLG);
    }

}
