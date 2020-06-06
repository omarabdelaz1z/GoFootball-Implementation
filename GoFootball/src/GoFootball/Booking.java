package GoFootball;
/**
 * Holds the process of booking.
 */
public class Booking
{
    private Player bookingPlayer;
    private String startHour;
    private String endHour;
    private Playground bookedPLG;
    private double totalPrice;

    /**
     * Gets the player booked hours and chosen playground.
     * @param startHour
     * @param endHour
     * @param bookedPLG
     */
    public Booking(String startHour,String endHour,Playground bookedPLG) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.bookedPLG = bookedPLG;
    }

    /**
     * calculating the booking price of the playground by hours.
     * @param bookedHours Integer value.
     * @return the total price as double value.
     */
    public double calculatePrice(int bookedHours) {
        totalPrice = bookedPLG.calculatePrice(bookedHours);
        return totalPrice;
    }

    /**
     * Transferring money to the owner.
     * @param bookingPlayer who booked the playground.
     * @return boolean of the process fails.
     */
    public boolean payMoney(Player bookingPlayer) {
        this.bookingPlayer=bookingPlayer;

        if(bookingPlayer.getWallet().getCurrentBalance() < totalPrice){
            System.out.println("You cannot book with the current balance in your account.");
            return false;
        }

        boolean checkTransaction = bookingPlayer.transferMoney(totalPrice, bookedPLG.getOwner());

        if(!checkTransaction) {
            System.out.println("Error Occurred. Try Again!");
            return false;
        }
        return true;
    }

    // Getters

    public String getEndHour() { return endHour; }
    public String getStartHour() { return startHour; }
    public Playground getBookedPLG() { return bookedPLG; }

    /**
     * Total price after multiply the booking price by booked hours.
     * @return total price.
     */
    public double getTotalPrice() { return totalPrice; }
}
