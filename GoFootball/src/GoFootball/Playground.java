package GoFootball;
//-- Time Slots
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 *Stores the various information of a playground and available time slots of the playground
 */

public class Playground
{
    private String Name;
    private Location location;
    private PLG_Owner owner;
    private double priceRent;
    private int startHour;
    private int endHour;
    private double cancellationPeriod;
    private String status;

    private Map <LocalTime,Boolean> TimeSlots;

    // Creating a playground..

    /**
     * Add the playground information and create timeslots for it.
     * @param Name
     * @param location
     * @param priceRent
     * @param startHour
     * @param endHour
     * @param cancellationPeriod
     * @param owner
     */
    public Playground(String Name,Location location, double priceRent, int startHour, int endHour, double cancellationPeriod, PLG_Owner owner) {
        this.Name = Name;
        this.location = location;
        this.priceRent = priceRent;
        this.startHour = startHour;
        this.endHour = endHour;
        this.cancellationPeriod = cancellationPeriod;
        this.owner = owner;

        TimeSlots = new HashMap<LocalTime, Boolean>();
        createSlots();
    }

    // TimeSlot Methods
    /**
     * this method shows all the avaiable slots in the playground
     */
    public boolean checkAvailableSlots() {
        boolean checked = false;

        System.out.println("- Available Slots :- ");
        System.out.println("---------------------");
        for(Map.Entry<LocalTime,Boolean> entry : TimeSlots.entrySet()) {
            if(entry.getValue()) {
                System.out.println("-\t" + entry.getKey());
                checked = true;
            }
        }
        System.out.println("\n");
        return checked;
    }

    /**
     * this method shows all the booked slots in the playground
     */
    public void checkBookedSlots() {
        System.out.println("- Booked Slots :- ");
        System.out.println("---------------------");

        for(Map.Entry<LocalTime,Boolean> entry : TimeSlots.entrySet()) {
            if (!entry.getValue()) {
                System.out.println("-\t" + entry.getKey());
            }
        }
        System.out.println("\n");
    }
    /**
     * this method deletes selected slots in the playground
     * @param startHour String
     * @param endHour String
     */
    public void deleteSlots(String startHour, String endHour) {
        LocalTime start = LocalTime.parse(startHour);
        LocalTime end = LocalTime.parse(endHour);

        while (start.isBefore(end)) {
            TimeSlots.put(start,true);
            TimeSlots.put(end, true);

            start = start.plusHours(1);
        }
    }

    /**
     * Checking User givenHours to book a playground.
     * @param startHour  String
     * @param endHour  String
     * @return boolean if it's a expression.
     */
    public boolean checkAvailableSlotsGivenHours(String startHour, String endHour) {
        LocalTime start = LocalTime.parse(startHour);
        LocalTime end = LocalTime.parse(endHour);

        boolean available = true;

        while (start.isBefore(end))
        { // Start of while loop
            if(!TimeSlots.get(start).booleanValue()) {
                available = false;
                break;
            }

            start = start.plusHours(1);
        } // End of while loop.
        return available;
    }
    /**
     * this method books a selected slot in the playground
     * @param startHour String
     * @param endHour String
     * @return the total of booked hours
     */
    public Integer bookASlot(String startHour, String endHour) {
        LocalTime start = LocalTime.parse(startHour);
        LocalTime end = LocalTime.parse(endHour);

        while (start.isBefore(end)) {
            TimeSlots.put(start,false);
            TimeSlots.put(end, false);
            start = start.plusHours(1);
        }
        return calculateBookingHours(startHour,endHour);
    }
    /**
     * First clear TimeSlots then changes TimeSlots.
     */
    public void changeTimeSlots(){
        TimeSlots.clear();
        createSlots();
    }
    /**
     * calculates price for a booked slot given hours.
     * @param bookedHours double
     */
    public double calculatePrice(double bookedHours) { return bookedHours*priceRent; }

    // Setters
    /**
     * this method sets a value to the cancellation period variable
     * @param cancellationPeriod double
     */
    public void setCancellationPeriod(double cancellationPeriod) { this.cancellationPeriod = cancellationPeriod; }
    /**
     * this method sets a value to the status variable
     * @param status String
     */
    public void setStatus(String status) { this.status = status; }
    /**
     * this method sets a value to the end hour variable
     * @param endHour int
     */
    public void setEndHour(int endHour) { this.endHour = endHour; }
    /**
     * this method sets a value to the name variable
     * @param name String
     */
    public void setName(String name) { Name = name; }
    /**
     * this method sets a value to the price rent variable
     * @param priceRent double
     */
    public void setPriceRent(double priceRent) { this.priceRent = priceRent; }
    /**
     * this method sets a value to the start hour variable
     * @param startHour integer
     */
    public void setStartHour(int startHour) { this.startHour = startHour; }

    /**
     * this method sets a value to the location variable
     * @param location Location Object
     */
    public void setLocation(Location location) { this.location = location; }

    // Getters
    /**
     * this method return the status variable
     * @return status
     */
    public String getStatus() { return status; }
    /**
     * this method return the owner variable
     * @return owner
     */
    public PLG_Owner getOwner() { return owner; }
    /**
     * this method return the location object
     * @return location object contains all the various information of a playground.
     */
    public Location getLocation() { return location; }
    /**
     * this method return the cancellation period variable
     * @return cancellationPeriod
     */
    public double getCancellationPeriod() { return cancellationPeriod; }
    /**
     * this method return the end hour variable
     * @return endHour double
     */
    public int getEndHour() { return endHour; }
    /**
     * this method return the price rent variable
     * @return priceRent double
     */
    public double getPriceRent() { return priceRent; }
    /**
     * this method return the start hour variable
     * @return startHour int
     */
    public int getStartHour() { return startHour; }
    /**
     * this method return the name of the playground.
     * @return name String
     */
    public String getName() { return Name; }

    // Private Methods

    // Create the TimeSlots based on the Booking hours interval of the PLG
    /**
     * this method creates slots based on the booking hours interval of the playground
     */
    private void createSlots() {
        LocalTime Slot = LocalTime.of(startHour,0);
        TimeSlots.put(Slot,true);

        for(int i = 0; i <= endHour - startHour; i++)
            TimeSlots.put(Slot.plusHours(i),true);
    }
    /**
     * Calculates the total of booking hours.
     * @param start String
     * @param end String
     * return IntegerBookingHours as integer.
     */
    public Integer calculateBookingHours(String start,String end) {
        start = start.substring(0, 2);
        end = end.substring(0, 2);
        return Integer.parseInt(end) - Integer.parseInt(start);
    }
}
