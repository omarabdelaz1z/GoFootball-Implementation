package GoFootball;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores the various information of a User, His Role and his wallet
 */
public class User
{
    /**
     *Class Attributes
     */
    private String Name;
    private String Email;
    private String Password;
    private String phoneNumber;
    private eWallet wallet;
    private String role;

    /**
     * Create User account and give him a role.
     * @param givenName String
     * @param givenEmail String
     * @param givenPassword String
     * @param givenRole String
     */
    public User(String givenName,String givenEmail,String givenPassword,String givenRole) {
        setName(givenName);
        setEmail(givenEmail);
        setPassword(givenPassword);
        phoneNumber = "UNSPECIFIED";
        this.role = givenRole;

        wallet = new eWallet();
    }

    // User wallet manipulation

    /**
     * Printing Your Current Balance
     */
    public void checkMoney(){
        System.out.println("\n- Current Balance : " + this.wallet.getCurrentBalance());
    }
    /**
     * Transfer Money To another User
     * @param givenTotalPrice , Double
     * @param Recipient , User
     * @return True If this transaction done or false if there is something wrong
     */
    public boolean transferMoney(double givenTotalPrice, User Recipient) {
        if(Recipient == null) {
            System.out.println("User doesn't exist");
            return false;
        }

        if (givenTotalPrice < 0 || givenTotalPrice > this.getWallet().getCurrentBalance() ) {
            System.out.println("Check your given price please!");
            return false;
        }

        else {

            double balance = this.wallet.getCurrentBalance() - givenTotalPrice;
            this.wallet.setCurrentBalance(balance);

            balance = Recipient.getWallet().getCurrentBalance() + givenTotalPrice;
            Recipient.wallet.setCurrentBalance(balance);

            return true;
        }
    }

    // Setters

    /**
     * this method sets a value to the PhoneNumber
     * @param givenPhoneNumber , String
     */
    public void setPhoneNumber(String givenPhoneNumber){
        Scanner input = new Scanner(System.in);
        String regexOfPhoneNumber = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regexOfPhoneNumber);
        Matcher matcher = pattern.matcher(givenPhoneNumber);

        if(matcher.matches())
            phoneNumber = givenPhoneNumber;

        else {
            do {
                System.out.println("Enter the Phone number in the form of: +20 1xxxxxxxxx");
                givenPhoneNumber = input.nextLine();

                matcher = pattern.matcher(givenPhoneNumber);
            } while (!matcher.matches());
            phoneNumber = givenPhoneNumber;
        }
    }
    /**
     * this method sets a value to the Password
     * @param password , String
     */
    public void setPassword(String password) {
        if(password.length() >= 6)
            Password = password;
        else
        {
            Scanner userInput = new Scanner(System.in);
            do{
                System.out.println("Password should contains at least 6 characters.\n");
                System.out.print("Password : ");
                password = userInput.nextLine();

            }while (password.length() < 6);

            Password = password;
        }
    }
    /**
     * this method sets a value to the Name
     * @param name , String
     */
    public void setName(String name) {
        Scanner input = new Scanner(System.in);
        String[] sections = null;
        sections = name.trim().split(" ");

        if (sections.length == 2)
            Name = name;

        else {
            do {
                System.out.println("Enter Name in the form of : (First Name Last Name)");
                String userInput = input.nextLine();

                sections = userInput.trim().split(" ");

            } while (sections.length != 2);
            Name = sections[0] + " " + sections[1];
        }
    }
    /**
     *  this method sets a value to the Email
     * @param givenEmail
     */
    public void setEmail(String givenEmail) {
        Scanner input = new Scanner(System.in);

        String regexOfEmail
                ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?-:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regexOfEmail);
        Matcher matcher = pattern.matcher(givenEmail);

        if(matcher.matches())
            Email = givenEmail;

        else {
            do {
                System.out.println("Enter the email in the form of: (example@domain.com)");
                givenEmail = input.nextLine();
                matcher = pattern.matcher(givenEmail);

            } while (!matcher.matches());

            Email = givenEmail;
        }
    }

    // Getters
    /**
     * this method return the User Email
     * @return Email
     */
    public String getEmail() { return Email; }
    /**
     * this method return the User Name
     * @return Name
     */
    public String getName() { return Name; }
    /**
     * this method return the User Wallet Object
     * @return wallet
     */
    public eWallet getWallet() { return wallet; }
    /**
     *  this method return the User PhoneNumber
     * @return phoneNumber
     */
    public String getPhoneNumber() { return phoneNumber; }
    /**
     * this method return the User Role
     * @return role
     */
    public String getRole() { return role; }
    /**
     * this method return the User Password
     * @return Password
     */
    public String getPassword() { return Password; }
}