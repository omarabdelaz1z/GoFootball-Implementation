package GoFootball;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player who can book and create favourite team.
 */
public class Player extends User
{
    private ArrayList<Booking> Bookings;
    private ArrayList<Player> FavouriteTeam;
    private String city;

    /**
     * Constructor
     * @param givenName String
     * @param givenEmail String
     * @param givenPassword String
     */
    public Player(String givenName, String givenEmail, String givenPassword) {
        super(givenName, givenEmail, givenPassword, "PLAYER");
        city = "UNSPECIFIED";

        Bookings = new ArrayList<Booking>();
        FavouriteTeam = new ArrayList<Player>();
    }

    // Setter\\Getter for manipulating player's city.

    /**
     * Changes player's city.
     * @param city String
     */
    public void setCity(String city) { this.city = city; }

    /**
     * return city of the player
     * @return String city.
     */
    public String getCity() { return city; }

    // manipulate myTeam

    /**
     * Prints team Information.
     */
    public void showMyTeamInformation() {
        int incremental = 1;
        for(Player player : FavouriteTeam) {
            System.out.println("\n- Player" + incremental);
            System.out.println("----------------------------------");
            System.out.println("- Name : " + player.getName());
            System.out.println("- Email : " + player.getEmail());
            incremental++;
        }
    }

    public int getNumberOfPlayers(){
        return FavouriteTeam.size();
    }

    /**
     * Adding player to the team.
     * @param givenPlayer
     */
    public void addPlayer(Player givenPlayer) {
        FavouriteTeam.add(givenPlayer);
        System.out.println("Player added.");
    }
    /**
     * Removing player from the team..
     * @param deletePlayer
     */
    public void removePlayer(Player deletePlayer) {
        FavouriteTeam.remove(deletePlayer);
        System.out.println("Player removed.");
    }

    /**
     * Clearing the team members.
     */
    public void deleteTeam() {
        FavouriteTeam.clear();
        System.out.println("Your team now is empty.");
    }

    // manipulate booking

    /**
     * book a playground and add it bookings.
     * @param startHour String
     * @param endHour String
     * @param PLG Playground.
     */
    public void bookPlayground(String startHour, String endHour, Playground PLG){
        Booking newBooking = new Booking(startHour,endHour,PLG);
        Bookings.add(newBooking);
    }

    /**
     * cancel booking and rolling back any changes happened during the process.
     */
    public void cancelBooking() {
        if(Bookings.size() == 0) {
            System.out.println("You need to book first.");
            return;
        }

        Booking lastBooking = getLastBooking();
        PLG_Owner owner = lastBooking.getBookedPLG().getOwner();
        Playground PLG = owner.getPLG();

        String startHour = lastBooking.getStartHour();
        String endHour = lastBooking.getEndHour();
        PLG.deleteSlots(startHour,endHour);

        double playerBalance = this.getWallet().getCurrentBalance();
        double ownerBalance  = owner.getWallet().getCurrentBalance();

        double lastBookingPrice = lastBooking.getTotalPrice();

        playerBalance = playerBalance + lastBookingPrice;
        ownerBalance = ownerBalance - lastBookingPrice;
        System.out.println("Money Transfer is  being rolled back..\n");

        owner.getWallet().setCurrentBalance(ownerBalance);
        this.getWallet().setCurrentBalance(playerBalance);

        Bookings.remove(Bookings.size() - 1);
        System.out.println("Your recent Booking has been cancelled.");

    }

    /**
     * sending feedback to the owner during booking process.
     * @param owner
     */
    public void sendFeedback(PLG_Owner owner) {
        Scanner input=new Scanner(System.in);
        System.out.println("Write your feedback");

        String feedback=input.nextLine();
        if (feedback.equals("\n")){
           return;
        }
        owner.addfeedback(feedback);
    }

    /**
     * Get last booking if available.
     * @return object of booking.
     */
    public Booking getLastBooking(){
        if(Bookings.size() == 0)
            return null;
        return Bookings.get(Bookings.size() - 1);
    }

    /**
     * show booking history if available.
     */
    public void showBookingHistory() {
        if(Bookings.size() == 0){
            System.out.println("You need to book first.");
            return;
        }
        System.out.println("- Bookings History :- \n");
        for(Booking booking : Bookings)
        {
            System.out.println("- Booking Time : " + booking.getStartHour() +"  -  " + booking.getEndHour());
            System.out.println("- Booking Price : " + booking.getTotalPrice() + " EGP");
            System.out.println("- Playground Name : " + booking.getBookedPLG().getName());
            System.out.println("- Playground Location : " + booking.getBookedPLG().getLocation());
            System.out.println("- Playground Owner : " + booking.getBookedPLG().getOwner().getName());
            System.out.println("-----------------------------------------");
        }
    }
}
