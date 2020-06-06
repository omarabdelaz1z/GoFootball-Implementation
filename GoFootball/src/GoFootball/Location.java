package GoFootball;
import java.util.Scanner;
/**
 * stores street number , street name , city of the playground
 */
public class Location
{
    private int stNumber;
    private String stName;
    private String City;

    private String location; // used as a utility.

    // Adds a location;

    /**
     * Takes a user input location and validate it.
     */
    public Location() {
        Scanner input = new Scanner(System.in);
        String[] sections = null;

        System.out.println("Enter the location in the form of: (stNumber , Street , City)");

        String userInput = input.nextLine();
        sections = userInput.trim().split(" , ");

        if(sections.length != 3) {
            this.location = userInput;

            this.stName = null;
            this.City = null;
            this.stNumber = -1;
        }

        else {
            setStNumber(Integer.parseInt(sections[0]));
            setStName(sections[1]);
            setCity(sections[2]);

            this.location = null;
        }
    }

    // Utility function to check if the location is valid.
    /**
     * this method check if the location is valid
     */
    public boolean checkFormat(){ return location == null; }

    // Setters
    /**
     * this method sets a value to the city variable
     * @param city
     */
    public void setCity(String city) { City = city; }
    /**
     * this method sets a value to the stName variable
     * @param stName
     */
    public void setStName(String stName) { this.stName = stName; }
    /**
     * this method sets a value to the stNumber variable
     * @param stNumber
     */
    public void setStNumber(int stNumber) { this.stNumber = stNumber; }

    // Getters
    /**
     * this method return the stNumber variable
     * @return stNumber or -1
     */
    public int getStNumber() {
        if(location != null)
            return -1;

        return stNumber;
    }
    /**
     * this method return the City variable
     * @return City or null
     */
    public String getCity() {
        if(location != null)
            return "null";

        return City;
    }
    /**
     * this method return the stName variable
     * @return stName or null
     */
    public String getStName() {
        if(location != null)
            return "null";

        return stName;
    }

    // Print Location information
    /**
     * this method overides the toString method to print location information
     */
    @Override
    public String toString()
    {
        return "Location{" +
                "stNumber=" + stNumber +
                ", stName='" + stName + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}
