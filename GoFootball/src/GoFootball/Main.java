package GoFootball;
import java.util.Scanner;

/**
 * Implementation of GoFo System.
 */
public class Main
{
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        // Initiating Our system and passing an ADMIN object.

        GoFo_System GoFoSystem = new GoFo_System(new Administrator());

        // un-comment to test.
        // note: for each owner object, you need to add playground information.


        // 4 Players
        GoFoSystem.addPlayer(new Player("Omar Aziz","me1@gmail.com","GGEZPZ"));
        //GoFoSystem.addPlayer(new Player("Abdallah Mohamed","me2@gmail.com","GGEZPZ"));
        //GoFoSystem.addPlayer(new Player("Youssef Gamal","me3@gmail.com","GGEZPZ"));
        //GoFoSystem.addPlayer(new Player("Mohamed Yasser","me4@gmail.com","GGEZPZ"));

        // Three owners.

        PLG_Owner OwnerOne = new PLG_Owner("Skawy Ahmed","me5@gmail.com","GGEZPZ");
        //PLG_Owner OwnerTwo = new PLG_Owner("Eslam Elbassel","me6@gmail.com","GGEZPZ");
        //PLG_Owner OwnerThree = new PLG_Owner("Mostafa Badri","me7@gmail.com","GGEZPZ");

        GoFoSystem.addOwner(OwnerOne);
        //GoFoSystem.addOwner(OwnerTwo);
        //GoFoSystem.addOwner(OwnerThree);

        GoFoSystem.addPlayground(OwnerOne.getPLG());
        //GoFoSystem.addPlayground(OwnerTwo.getPLG());
        //GoFoSystem.addPlayground(OwnerThree.getPLG());

        int userChoice;
        int userIndex;

        do {
            System.out.println("\n1. Sign In.");
            System.out.println("2. Sign Up.");
            System.out.println("3. Exit.\n");
            System.out.print("- Choice : ");

            userChoice = userInput.nextInt();
            // Workaround that fixes skipping input

            userInput.nextLine();

            switch (userChoice)
            {
                // Sign In.
                case 1:
                    {
                        System.out.print("Enter Email : ");
                        String Email = userInput.nextLine();

                        System.out.print("Enter Password : ");
                        String Password = userInput.nextLine();

                        String Determinant = GoFoSystem.LogIn(Email, Password);

                        if (Determinant == null) {

                        }
                        else if(Determinant.startsWith("O"))
                        {
                            userIndex = Character.getNumericValue(Determinant.charAt(1));
                            PLG_Owner owner = GoFoSystem.getOwner(userIndex);
                            System.out.println("Welcome " + owner.getName());

                            OwnerProfileMenu(GoFoSystem, userIndex, GoFoSystem.getIndexOfPlayground(owner.getPLG()) != -1);
                        }

                        else if(Determinant.startsWith("P"))
                        {
                            userIndex = Character.getNumericValue(Determinant.charAt(1));

                            System.out.println("Welcome " + GoFoSystem.getPlayer(userIndex).getName());
                            PlayerProfileMenu(GoFoSystem, userIndex);
                        }
                 break;
                }
                // Sign Up.
                case 2:
                    {
                            System.out.print("- Enter Name : ");
                            String Name = userInput.nextLine();

                            String Email;

                            do {
                                System.out.print("- Enter Email : ");
                                Email = userInput.nextLine();

                            } while (GoFoSystem.checkIfAlreadyExists(Email));

                            System.out.print("- Enter Password : ");
                            String Password = userInput.nextLine();

                            int role;
                            do {
                                System.out.println("\nPick a role : \n");
                                System.out.println("1.Player\n2.Playground Owner");

                                System.out.print("- Choice : ");
                                role = userInput.nextInt();
                            } while (role != 1 && role != 2);

                            System.out.print("Enter Verification Code : ");
                            userInput.nextInt();

                            System.out.println("\nAccount Verified Successfully..!\n");

                            if (role == 1)
                            {
                                GoFoSystem.addPlayer(new Player(Name, Email, Password));
                                userIndex = GoFoSystem.getPlayersLength() - 1;

                                PlayerProfileMenu(GoFoSystem, userIndex);
                            }

                            else
                                {
                                    GoFoSystem.addOwner(new PLG_Owner(Name, Email, Password));
                                    userIndex = GoFoSystem.getOwnersLength() - 1;

                                    boolean added = GoFoSystem.addPlayground(GoFoSystem.getOwner(userIndex).getPLG());
                                    OwnerProfileMenu(GoFoSystem, userIndex, added);
                                }
                            break;
                }
                // Exit.
                case 3:
                    {
                        System.out.print("Rate our program (0 1 2 3 4 5) : ");
                        userInput.nextInt();

                        System.out.println("\n\nThanks for using the program. See you soon.\n");
                        userChoice = 3;
                        break;
                    }
                default:
                    System.out.println("\nNo Such Option.\n");
            }
        } while (userChoice != 3);
    }

    public static void EditProfile(GoFo_System GoFoSystem, final int userIndex,boolean role) {
        // if role - true - player
        // if role - false - plg owner
        Scanner userInput = new Scanner(System.in);

        Player player = GoFoSystem.getPlayer(userIndex);
        PLG_Owner owner = GoFoSystem.getOwner(userIndex);

        int userMenuChoice;

        do {
            if(role)
                GoFoSystem.printPlayerInfo(userIndex);
            else
                GoFoSystem.printOwnerInfo(userIndex);

            System.out.println("\n1. Edit Name.");
            System.out.println("2. Edit Email.");
            System.out.println("3. Edit Password.");
            System.out.println("4. Edit Phone Number.");
            System.out.println("5. Edit City.");
            System.out.println("6. Back.");

            System.out.print("\n- Choice : ");
            userMenuChoice = userInput.nextInt();

            switch (userMenuChoice)
            {
                // Edit Name
                case 1:
                    {
                        System.out.print("Enter your name : ");
                        String Name = userInput.nextLine();
                        if (role)
                            player.setName(Name);
                        else
                            owner.setName(Name);
                        break;
                    }
                // Edit Email
                case 2:
                    {
                        System.out.print("Enter your new email : ");
                        String Email = userInput.nextLine();

                        boolean exists = GoFoSystem.checkIfAlreadyExists(Email);

                        if (role && !exists)
                            player.setEmail(Email);

                        else if (!role && !exists)
                            owner.setEmail(Email);

                        break;
                    }
                // Edit Password
                case 3:
                    {
                        System.out.print("Enter your old password : ");
                        userInput.nextLine();
                        String oldPassword = userInput.nextLine();

                        if (role)
                        {
                            if (oldPassword.equals(player.getPassword()))
                            {
                                System.out.print("Enter new password : ");
                                oldPassword = userInput.nextLine();

                                System.out.print("Confirm new password : ");
                                String matchPassword = userInput.nextLine();

                                if (matchPassword.equals(oldPassword))
                                    player.setPassword(oldPassword);

                                else
                                    System.out.println("\nIncorrent entry. try again");
                            }
                            else
                                System.out.print("\nYour given password doesn't match your old password.\n");
                        }

                        else {
                                if (oldPassword.equals(owner.getPassword()))
                                {
                                    System.out.print("Enter new password : ");
                                    oldPassword = userInput.nextLine();

                                    System.out.print("Confirm new password : ");
                                    String matchPassword = userInput.nextLine();

                                    if (matchPassword.equals(oldPassword))
                                        owner.setPassword(oldPassword);

                                    else
                                        System.out.println("\nIncorrent entry. try again");
                                }

                                else
                                    System.out.print("\nYour given password doesn't match your old password.\n");
                            }
                        break;
                    }
                // Edit Phone Number
                case 4:
                    {
                        System.out.print("Enter your Phone Number : ");
                        String PN = userInput.nextLine();

                        if (role)
                            player.setPhoneNumber(PN);
                        else
                            owner.setPhoneNumber(PN);

                        break;
                    }
                    // Edit City
                case 5:
                    {
                        if (role)
                        {
                            System.out.print("Enter your City : ");
                            userInput.nextLine();
                            String City = userInput.nextLine();

                            player.setCity(City);
                        }

                        else
                            owner.updateCity();
                        break;
                    }
                    // Back
                case 6:
                    userMenuChoice = 6;
                    break;

            }
        } while (userMenuChoice != 6);
    }

    public static void EditPlayground(GoFo_System GoFoSystem, final int userIndex) {
        int editChoice;
        PLG_Owner owner = GoFoSystem.getOwner(userIndex);

        Scanner userInput = new Scanner(System.in);

        do {
            owner.printMyPLG();

            System.out.println("\n1. Edit Playground Name.");
            System.out.println("2. Edit Location.");
            System.out.println("3. Edit Price Rent.");
            System.out.println("4. Edit Booking Hours.");
            System.out.println("5. Edit Cancellation Period.");
            System.out.println("6. Back");

            System.out.print("\n- Choice : ");
            editChoice = userInput.nextInt();

            switch (editChoice)
            {
                case 1:
                    {
                        System.out.print("- Set Playground name : ");
                        userInput.nextLine();
                        String PLGName = userInput.nextLine();
                        owner.getPLG().setName(PLGName);

                        break;
                     }
                case 2:
                    {
                        System.out.print("- Set new location : ");
                        Location newLocation = new Location();

                        if (GoFoSystem.getAdmin().validateInfo(newLocation))
                            owner.getPLG().setLocation(newLocation);
                        else
                            System.out.print("Your new location information is wrong.");

                        break;
                    }
                case 3:
                    {
                        System.out.print("- Set price rent : ");
                        owner.getPLG().setPriceRent(userInput.nextDouble());

                        break;
                    }
                case 4:
                    {
                        System.out.print("- Set start hour : ");
                        int startHour = userInput.nextInt();

                        System.out.print("- Set end hour : ");
                        int endHour = userInput.nextInt();

                        owner.changeBookingHours(startHour, endHour);
                        break;
                    }
                case 5:
                    {
                        System.out.print("- Set cancellation period : ");
                        owner.getPLG().setCancellationPeriod(userInput.nextDouble());

                        break;
                     }
                case 6:
                    editChoice = 6;
                    break;

            }
        } while (editChoice != 6);
    }

    public static void PlayerProfileMenu(GoFo_System GoFoSystem, final int userIndex) {
        Scanner userInput = new Scanner(System.in);
        int userMenuChoice;
        Player player = GoFoSystem.getPlayer(userIndex);
        do {
            System.out.println("(1) Show/Edit Profile.");
            System.out.println("(2) Check eWallet.");
            System.out.println("(3) Transfer Money.");
            System.out.println("(4) Playgrounds.");
            System.out.println("(5) MyTeam.");
            System.out.println("(6) Book a Playground.");
            System.out.println("(7) MyBookings.");
            System.out.println("(8) Cancel Booking.");
            System.out.println("(9) Log Out.");

            System.out.print("- Choice : ");
            userMenuChoice = userInput.nextInt();

            switch (userMenuChoice) {
                case 1:
                    EditProfile(GoFoSystem, userIndex,true);
                    break;
                case 2: {
                    System.out.print("Enter your password to proceed : ");
                    userInput.nextLine();
                    String password = userInput.nextLine();

                    if (!password.equals(player.getPassword()))
                        System.out.println("Incorrect Password. Try Again");

                    else {
                        System.out.println("- WalletID : " + player.getWallet().getTransactionID());
                        GoFoSystem.getPlayer(userIndex).checkMoney();
                    }
                    break;
                }
                case 3:
                    System.out.print("Enter Amount of money to transfer : ");
                    double givenAmount = userInput.nextDouble();
                    System.out.print("- Recipient Email : ");
                    userInput.nextLine();
                    String Email = userInput.nextLine();
                    player.transferMoney(givenAmount,GoFoSystem.getUserByEmail(Email));

                    break;
                case 4:
                    displayPlaygrounds(GoFoSystem);
                    break;
                case 5:
                    myTeamMenu(GoFoSystem,userIndex);
                    break;
                case 6:
                    Playground checkedPLG = doBooking(GoFoSystem, userIndex);
                    if(checkedPLG != null) {
                        if (GoFoSystem.getAdmin().manipulatePLG(checkedPLG) == 1)
                            GoFoSystem.deletePlayground(checkedPLG);
                    }
                    // if checkedPLG is null, then the booking process is stopped for some reasons.
                    break;
                case 7:
                    player.showBookingHistory();
                    break;
                case 8:
                    player.cancelBooking();

                    break;
                case 9:
                    userMenuChoice = 9;
                    break;
            }
        } while (userMenuChoice != 9);
    }

    public static void OwnerProfileMenu(GoFo_System GoFoSystem, final int userIndex,boolean added) {
        Scanner userInput = new Scanner(System.in);
        int userMenuChoice;

        PLG_Owner owner = GoFoSystem.getOwner(userIndex);

        do {
            System.out.println("(1) Show/Edit Profile.");
            System.out.println("(2) Check eWallet.");
            System.out.println("(3) Transfer Money.");

            if (added)
                System.out.println("(4) Edit Playground.");
            else
                System.out.println("(4) Add Playground.");

            System.out.println("(5) Available Slots.");
            System.out.println("(6) Booked Slots.");
            System.out.println("(7) Delete Slots.");
            System.out.println("(8) Log Out");

            System.out.print("- Choice : ");
            userMenuChoice = userInput.nextInt();

            switch (userMenuChoice) {
                case 1:
                    EditProfile(GoFoSystem, userIndex,false);
                    break;
                case 2: {
                    System.out.print("Enter your password to proceed : ");
                    userInput.nextLine();
                    String password = userInput.nextLine();

                    if (!password.equals(owner.getPassword()))
                        System.out.println("Incorrect Password. Try Again\n");

                    else {
                        System.out.println("- WalletID : " + owner.getWallet().getTransactionID());
                        owner.checkMoney();
                    }
                    break;
                }
                case 3:
                    System.out.print("Enter Amount of money to transfer : ");
                    break;
                case 4: {
                    if (added)
                        EditPlayground(GoFoSystem, userIndex);
                    else
                        added = addPlayground(GoFoSystem, userIndex);
                    break;
                }
                case 5:
                    owner.getPLG().checkAvailableSlots();
                    break;
                case 6:
                    owner.getPLG().checkBookedSlots();
                    break;
                case 7:
                    owner.getPLG().checkBookedSlots();

                    System.out.println("- Enter booking hours in the form of (HH:MM)\n");
                    userInput.nextLine();

                    System.out.print("- Start hour : ");
                    String StartHour = userInput.nextLine();

                    System.out.print("- End hour : ");
                    String EndHour = userInput.nextLine();

                    owner.getPLG().deleteSlots(StartHour,EndHour);
                    break;
                case 8:
                    userMenuChoice = 8;
                    break;
            }
        } while (userMenuChoice != 8);
    }

    public static boolean addPlayground(GoFo_System GoFoSystem, final int userIndex) {
        Scanner userInput = new Scanner(System.in);
        PLG_Owner owner = GoFoSystem.getOwner(userIndex);

        System.out.print("Enter Playground name : ");

        userInput.nextLine();
        String Name = userInput.nextLine();

        Location location = new Location();

        System.out.print("Rent price per hour : ");
        double priceRent = userInput.nextDouble();

        System.out.println("Booking hours : ");

        System.out.print("- Start Hour : ");
        int startHour = userInput.nextInt();

        System.out.print("- End Hour : ");
        int endHour = userInput.nextInt();

        System.out.print("Allowable Cancellation Period : ");
        double cancellationPeriod = userInput.nextDouble();

        Playground newPLG = new Playground(Name, location, priceRent, startHour, endHour, cancellationPeriod, owner);
        owner.setPLG(newPLG);
        return GoFoSystem.addPlayground(newPLG);
    }

    public static void displayPlaygrounds(GoFo_System GoFoSystem) {
        Scanner userInput = new Scanner(System.in);
        int displayChoice;
        String City;

        do {
            System.out.println("1. Display Playgrounds.");
            System.out.println("2. Display Playgrounds by City.");
            System.out.println("3. Display Playgrounds by Hours and City.");
            System.out.println("4. Back.");

            System.out.print("- Choice : ");
            displayChoice = userInput.nextInt();
            switch (displayChoice){
                case 1:
                    GoFoSystem.printPlaygrounds();
                    break;
                case 2:
                    System.out.print("- Filter City : ");
                    userInput.nextLine();
                    City = userInput.nextLine();
                    GoFoSystem.printPlaygroundsGivenCity(City);

                    break;
                case 3:
                    System.out.print("- Filter City : ");
                    userInput.nextLine();
                    City = userInput.nextLine();
                    System.out.println("Filter Hours : ");
                    System.out.print("\t- From : ");
                    String startHour = userInput.nextLine();
                    System.out.print("\n");
                    System.out.print("\t- To : ");
                    String endHour = userInput.nextLine();

                    GoFoSystem.printPlaygroundsGivenHoursAndCity(startHour,endHour,City);
                    break;
                case 4:
                    displayChoice = 4;
                    break;
            }
        }while (displayChoice!=4);
    }

    public static Playground doBooking(GoFo_System GoFoSystem,final int userIndex)
    {
        Scanner userInput = new Scanner(System.in);

        Playground chosenPLG;
        Player bookingPlayer = GoFoSystem.getPlayer(userIndex);

        displayPlaygrounds(GoFoSystem);

        System.out.print("- Enter Playground Name : ");
        String PLGName = userInput.nextLine();
        chosenPLG = GoFoSystem.getPlaygroundbyName(PLGName);

        if (chosenPLG == null)
            return null;

        boolean checked = chosenPLG.checkAvailableSlots();
        if (!checked)
            return null;

        System.out.println("- Enter Booking Hours in the form of ( HH:MM )");
        userInput.nextLine();

        System.out.print("- Start Hour : ");
        String startHour = userInput.nextLine();

        System.out.print("- End Hour : ");
        String endHour = userInput.nextLine();

        bookingPlayer.bookPlayground(startHour, endHour, chosenPLG);
        Integer bookedHours = chosenPLG.bookASlot(startHour, endHour);

        double totalPrice = bookingPlayer.getLastBooking().calculatePrice(bookedHours);
        System.out.println("- Total Price => : " + totalPrice + "EGP.");
        int confirm;

        do {
            System.out.println("1.Confirm Booking Process");
            System.out.println("2.Stop Booking Process");
            System.out.print("\n- Choice : ");

            confirm = userInput.nextInt();

        } while (confirm != 1 && confirm != 2);

        if (confirm == 1) {
            boolean canPay = bookingPlayer.getLastBooking().payMoney(bookingPlayer);

            if (!canPay) {
                System.out.println("ERROR OCCURRED");
                return null;
            }

            System.out.println("- Successful booking, enjoy!");
            userInput.nextLine();

            System.out.println("- Send Invites :");

            userInput.nextLine();

            System.out.print("- Invite sent successfully.");

            int rate;
            do {
                System.out.print("- Add your rate (From scale 1 to 5) : ");
                rate = userInput.nextInt();

            } while (rate > 5 || rate < 0);

            chosenPLG.getOwner().addRate(rate);
            bookingPlayer.sendFeedback(chosenPLG.getOwner());

            System.out.println("\nYou have booked the playground successfully. You can invite team members!\n");
            return chosenPLG;
        }
        else
            return null;
    }

    public static void myTeamMenu(GoFo_System GoFoSystem, final int userIndex) {
        Scanner userInput = new Scanner(System.in);

        int userChoice;
        do{
            System.out.println("(1) Show myTeam.");
            System.out.println("(2) Add Player.");
            System.out.println("(3) Remove Player.");
            System.out.println("(4) Delete myTeam.");
            System.out.println("(5) Send Invites.");
            System.out.println("(6) Back.");

            System.out.print("- Choice : ");
            userChoice = userInput.nextInt();

            switch (userChoice) {
                case 1:
                    Player player = GoFoSystem.getPlayer(userIndex);
                    if(player.getNumberOfPlayers() == 0)
                        System.out.println("Add Players first.");
                    else
                        player.showMyTeamInformation();
                    break;
                case 2:

                    System.out.print("- Send invite to : ");
                    userInput.nextLine();
                    String sentEmail = userInput.nextLine();

                    Player invitedPlayer = GoFoSystem.getPlayerByEmail(sentEmail);

                    if(invitedPlayer != null)
                        GoFoSystem.getPlayer(userIndex).addPlayer(invitedPlayer);
                    else
                        System.out.println("Player doesn't exist");

                    break;
                case 3:
                    System.out.print("- Enter player email : ");
                    userInput.nextLine();
                    String playerEmail = userInput.nextLine();

                    Player deletePlayer = GoFoSystem.getPlayerByEmail(playerEmail);

                    if(deletePlayer != null)
                        GoFoSystem.getPlayer(userIndex).removePlayer(deletePlayer);
                    else
                        System.out.println("Player doesn't exist");

                    break;
                case 4:
                    GoFoSystem.getPlayer(userIndex).deleteTeam();

                    break;
                case 5:
                    System.out.print("- Send invite to : " + userInput.nextLine());
                    System.out.println("Invite sent successfully.");

                    break;
                case 6:
                    userChoice = 6;
                    break;
            }
        }while (userChoice != 6);
    }
}