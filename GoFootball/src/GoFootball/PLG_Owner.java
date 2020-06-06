package GoFootball;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * stores the owner's playground , reputation , user rates based on their experience while booking
 */
public class PLG_Owner extends User
{
    private Playground PLG;
    private double reputation;
    private ArrayList<String> receivedFeedback;
    private ArrayList<Integer> userRates;
    private String city;

    /**
     * The Constructor
     * @param givenName String
     * @param givenEmail String
     * @param givenPassword String
     */
    public PLG_Owner(String givenName,String givenEmail,String givenPassword) {
        super(givenName, givenEmail, givenPassword,"OWNER");
        receivedFeedback=new ArrayList<String>();
        userRates = new ArrayList<Integer>();
        reputation = 5.0;

        addInformationToPLG();
        city = PLG.getLocation().getCity();
    }

    // Feedback part
    /**
     * This method add integer to the array userRates that stores all the rates
     * @param rate Integer value
     * @return a boolean that indicates if the operation succeded or failed
     */
    public boolean addRate(Integer rate) {
        if(rate > 5 || rate < 1) {
            System.out.println("Your rate is out of range.");
            return false;
        }
            userRates.add(rate);
            return  true;
    }
    /**
     * This method add string to the array receivedFeedback that stores the feedback
     * @param complaint String.
     */
    public void addfeedback(String complaint){ receivedFeedback.add(complaint); }

    // Edits
    /**
     * this method sets a value to the PLG variable
     * @param PLG playground object.
     */
    public void setPLG(Playground PLG) {
        this.PLG = PLG;
    }
    /**
     * this method updates the value of the city variable
     */
    public void updateCity() { this.city = PLG.getLocation().getCity(); }
    /**
     * this method updates the value of the startHour and endHour variables
     * param startHour integer
     * param endHour integer
     */
    public void changeBookingHours(int startHour, int endHour) {
        PLG.setStartHour(startHour);
        PLG.setEndHour(endHour);

        PLG.changeTimeSlots();
    }

    // Delete PLG Object if the PLayground location is wrong.
    /**
     * this method delete PLG Object if the PLayground location is wrong.
     */
    public void setPLG_NULL() { PLG = null; }

    //Getters
    /**
     * this method return the PLG variable
     * @return PLG
     */
    public Playground getPLG() { return PLG; }
    /**
     * this method return the reputation average
     * @return the reputation array mean
     */
    public double getReputation() {
        return calculateReputationAverage();
    }
    /**
     * this method return the city variable
     * @return city
     */
    public String getCity() {
        return city;
    }
    /**
     * this method prints playground owner info
     */
    public void printMyPLG(){
        System.out.println("\n- Playground Name : " + PLG.getName());
        System.out.println("\n- Owner Name : "+ this.getName());
        System.out.println("\n- Status : " + PLG.getStatus());
        System.out.println("\n- Location : " + PLG.getLocation());
        System.out.println("\n- Price rent for Hour : " + PLG.getPriceRent());
        System.out.println("\n- Start Hour : " + PLG.getStartHour());
        System.out.println("\n- End Hour : " + PLG.getEndHour());
        System.out.println("\n- Allowable Cancellation Period  : " + PLG.getCancellationPeriod());
    }

    // Private Methods
    /**
     * this method where we enter a playground owner info
     */
    private void addInformationToPLG() {
        Scanner userInput = new Scanner(System.in);
        double priceRent,cancellationPeriod;
        int startHour,endHour;
        System.out.print("Enter Playground name : ");
        String Name = userInput.nextLine();

        Location location = new Location();

        System.out.print("Rent price per hour : ");
        priceRent = userInput.nextDouble();

        System.out.println("Booking hours : ");

        System.out.print("- Start Hour : ");
        startHour = userInput.nextInt();

        System.out.print("- End Hour : ");
        endHour = userInput.nextInt();

        System.out.print("Allowable Cancellation Period : ");
        cancellationPeriod = userInput.nextDouble();

        PLG = new Playground(Name,location,priceRent,startHour,endHour,cancellationPeriod,this);
}
    /**
     * this method calculates playground owner the reputation.
     */
    private double calculateReputationAverage() {
        if(userRates.size() < 5){
            return reputation;
        }

        double sum = 0.0;

        for (Integer userRate : userRates)
            sum += userRate;

        reputation = sum / userRates.size();
        return reputation;
    }
}
