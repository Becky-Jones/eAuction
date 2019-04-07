package platform;

import core.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the main class to be used in the system for the user, containing the menus and options
 */
class Sys {
    /**
     * Private Fields
     */
    private static List<User> users = new LinkedList<>();
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\\R+");
    private List<Auction> auctions = new LinkedList<>();
    private User loggedInUser;
    private static final DecimalFormat df = new DecimalFormat(".##");

    /*
     * MENUS
     */

    /**
     * This method contains the start menu for the application to be read to the user
     *
     * @param users    the users for this application
     * @param auctions the auctions set on this application
     */
    void startMenu(List<User> users, List<Auction> auctions) {
        // set the users and auctions
        this.users = users;
        this.auctions = auctions;
        String option = "";

        // run menu until user hits exit option
        while (!(option.equals("4"))) {
            // check whether the user is logged in
            if (loggedInUser != null) {
                if (Seller.class.isInstance(loggedInUser)) {
                    // they are a seller
                    sellerMenu();
                } else {
                    // they are a buyer
                    buyerMenu();
                }
            } else {
                // the initial menu
                System.out.println(" which would you like?");
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("3. Browse Auctions");
                System.out.println("4. Quit");

                option = scanner.nextLine();

                switch (option) {
                    case "1": /* set up account*/
                        setUpAccount();
                        break;
                    case "2": /* Login to account */
                        login();
                        break;
                    case "3": /* Browse active auctions */
                        browseAuction(auctions);
                        break;
                    default: /* Exit system */
                        System.out.println("Goodbye");
                        System.exit(0);
                }
            }
        }
    }

    /**
     * This method contains the menu options for a seller
     */
    private void sellerMenu() {
        // initial menu
        System.out.println(" which would you like?");
        System.out.println("1. Create Auction");
        System.out.println("2. Verify Auction");
        System.out.println("3. Logout");
        System.out.println("4. Quit");

        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("")) {
            option = scanner.nextLine();
        }

        switch (option) {
            case "1": /* Create an auction */
                createAuction((Seller) loggedInUser);
                break;
            case "2": /* Verify an auction*/
                verifyAuction();
                break;
            case "3": /* Log out*/
                logout();
                break;
            case "4": /* Exit the system*/
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    /**
     * This method contains the menu options for a buyer
     */
    private void buyerMenu() {
        // initial menu
        System.out.println(" which would you like?");
        System.out.println("1. Browse Active Auctions");
        System.out.println("2. Bid On Auction");
        System.out.println("3. Logout");
        System.out.println("4. Quit");

        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("")) {
            option = scanner.nextLine();
        }

        switch (option) {
            case "1": /* Browse active auctions */
                browseAuction(auctions);
                break;
            case "2": /* Bid on an auction */
                placeBid();
                break;
            case "3": /* Log out*/
                logout();
                break;
            case "4": /* Exit the system */
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    /*
     * SET UP AND LOGIN
     */

    /**
     * This method will create an account for the user
     */
    private void setUpAccount() {
        // get username
        System.out.println("Please enter a username: ");

        String username = scanner.nextLine();

        boolean usernameValid = false;

        // check if the username is a valid name
        while (!usernameValid) {
            // if a user exists with this name then they cannot create a user with this username
            if (getUserByUsername(username) == null) usernameValid = true;
        }

        // get password
        System.out.println("Now please enter a password: ");
        String password = scanner.nextLine();

        System.out.println("Please confirm your password: ");
        String confirmPassword = scanner.nextLine();

        // get the user type
        System.out.println("Would you like to be a buyer or seller (B | S)?");
        String userType = scanner.nextLine();

        // check if the password is valid
        if (password.equals(confirmPassword)) {
            // valid! lets create this user
            if (userType.equalsIgnoreCase("S") || userType.equalsIgnoreCase("Seller")) {
                // they are a seller
                users.add(new Seller(username, password));
                System.out.println("Your account has been successfully created");
            } else {
                // they are a buyer
                users.add(new Buyer(username, password));
                System.out.println("Your account has been successfully created");
            }
        } else {
            // something went wrong!
            System.out.println("Sorry there was a problem creating your account, please try again");
        }

    }

    /**
     * This method will get the user by the username entered into the system
     *
     * @param username the username to look for
     * @return the user if they exist
     */
    private User getUserByUsername(String username) {

//        Optional<User> user = users.stream().filter(o -> o.getUsername().equalsIgnoreCase(username)).reduce((a, b) -> null);

//        if (user.isPresent()) {
//            return user.get();
//        }
        for (User user : users) {
            // check whether the user is the one specified by the username
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        // user doesn't exist
        return null;
    }

    /**
     * This method will as the user for their details and log the user into the system
     */
    private void login() {
        User user;
        // get username
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        // get the user
        user = getUserByUsername(username);

        // user doesn't exist
        if (user == null) {
            System.out.println("Sorry those details are incorrect");
            return;
        }

        // now we need to check their password!!
        System.out.println("Please enter your password: ");

        String password = scanner.nextLine();

        // check the password
        if (user.checkPassword(password)) {
            loggedInUser = user;
            // login successful

        } else {
            // password incorrect
            System.out.println("Sorry those details are incorrect");
        }
    }

    /**
     * This method will log the user out of the system
     */
    private void logout() {
        loggedInUser = null;
    }

    /*
     * SELLER FUNCTIONALITY
     */

    /**
     * This method will ask the seller for the auction details and then create the auction
     *
     * @param seller the seller creating the auction
     */
    private void createAuction(Seller seller) {
        // get item description
        System.out.println("Please enter the item description for your auction");
        String itemDesc = scanner.nextLine();

        // get start price
        System.out.println("Please enter the start price for your auction");
        double startPrice = scanner.nextDouble();

        // get reserve price
        System.out.println("Please enter the reserve price for your auction");
        double reservePrice = scanner.nextDouble();

        scanner.nextLine();

        LocalDateTime closing = LocalDateTime.now();
        boolean validDate = false;

        // loop until the seller enters a valid closing date
        while (!validDate) {
            System.out.println("Please enter the close date for your auction in the format of yyyy-MM-dd HH:mm");
            String closeDate = scanner.nextLine();

            if (!(closeDate.contains("-") && closeDate.contains(":") & closeDate.length() <= 16)) {
                // invalid!
                System.out.println("Sorry that cloe date isn't valid, please try again");
                continue;
            }
            // valid format!
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            closing = LocalDateTime.parse(closeDate, formatter);
            // check if date is valid
            if (closing.isBefore(LocalDateTime.now())) {
                // invalid!
                System.out.println("Sorry you have entered a past date, please try again");
                continue;
            }
            // the date is valid
            validDate = true;
        }

        // create the auction and add it to the list of auctions on the system
        Auction auction = new Auction(startPrice, reservePrice, closing, Status.PENDING, new Item(itemDesc), seller);
        auctions.add(auction);

        System.out.println("Auction created, The auction is now in a pending state and must first be verified");
    }

    /**
     * This method will check what auctions are pending and
     */
    private void verifyAuction() {

        List<Auction> pendingAuctions = auctions.stream().filter(o -> o.getStatus().equals(Status.PENDING)).collect(Collectors.toList());


        // check if there are any pending auctions
        if (pendingAuctions.size() == 0) {
            System.out.println("There are no pending auctions for your account");
        } else {
            // print out the pending auctions
            System.out.println("Which auction would you like to verify? (enter the auction number)");

            int i = 1;
            Map<Integer, Auction> options = new HashMap<>();

            Seller seller = (Seller)loggedInUser;
            // print out the pending auctions
            for (Auction auction : pendingAuctions) {
                // if the auction belongs to the seller
                if (auction.seller == seller) {
                    options.put(i, auction);
                    System.out.println("Auction " + i + ": " + auction.getItemDescription());
                    i++;
                }
            }

            int choice = scanner.nextInt();

            // get the chosen auction
            Auction chosen = options.get(choice);

            // verify the auction
            chosen.verify();
            System.out.println("Auction Verified");
        }

    }

    /*
     * BUYER FUNCTIONALITY
     */

    /**
     * This method will get the information from the buyer and set a bid on the chosen auction
     */
    private void placeBid() {
        // get active auctions
        List<Auction> activeAuctions = auctions.stream().filter(o -> o.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());

        if (activeAuctions.isEmpty()) {
            System.out.println("Sorry there ar currently no active auctions to bid on");
            return;
        }

        int choice = 0;
        boolean isValid = false;
        // loop until they have chosen an active auction
        while (!isValid) {
            System.out.println("Please choose from the list of active auctions:");
            browseAuction(auctions);

            choice = scanner.nextInt();

            if (choice > auctions.size() || choice == 0) {
                System.out.println("Sorry that auction doesn't exist,");
            } else {
                isValid = true;
            }
        }

        // get the chosen auction
        Auction auction = activeAuctions.get((choice - 1));

        double minimum = 0.0;

        // get the current bids from the auction
        if (auction.getBids() != null) {
            List<Bid> bids = auction.getBids();

            // get the maximum bid out of the list of bids and set it as the minimum
            if (bids.size() > 0) {
                // get the maxmimum bid on the auction and set it as the minimum bid amount
                minimum = bids.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Bid::getAmount))).get().getAmount();
            }
        }

        // set the bidding increments
        double maximum = minimum * 1.2;
        minimum *= 1.1;
        boolean validBid = false;

        if (maximum == 0) {
            maximum = auction.getReservePrice();
        }

        // loop until the bid is valid
        while (!validBid) {
            System.out.println("Please enter the amount you would like to bid between £" + df.format(minimum) + " and £" + df.format(maximum));
            // get the bid
            double bid = scanner.nextDouble();

            if (bid <= maximum && bid >= minimum) {
                // valid bid
                validBid = true;
                // create the bid
                Bid userBid = new Bid(bid, (Buyer) loggedInUser, LocalDateTime.now());

                // place the bid onto the auction
                auction.placeBid(userBid);
                System.out.println("Your bid of £" + df.format(bid) + " has successfully been placed on the auction");

            } else {
                // invalid bid
                System.out.println("Sorry that bid was invalid, lets try this again");
            }
        }
    }

    /**
     * This method will print out all of the active auctions to the user on the system
     *
     * @param auctions the auctions on the system
     */
    private void browseAuction(List<Auction> auctions) {
//        for (Auction auction : auctions) {
//            if (auction.getStatus() == 'A') {
//                stringBuffer.append("Auction ").append(counter).append("\n").append("======================================").append("\n");
//                stringBuffer.append("Start Price: £").append(auction.getStartPrice()).append("\n");
//                stringBuffer.append("Reserve Price: £").append(auction.getReservePrice()).append("\n");
//                stringBuffer.append("Item Description: ").append(auction.getItemDescription()).append("\n");
//                stringBuffer.append("Close Date: ").append(auction.getCloseDate()).append("\n");
//                stringBuffer.append("======================================").append("\n");
//                counter++;
//            }
//        }

        // filter the auctions to be just auctions with an active status
        List<Auction> activeAuctions =
                auctions.stream().filter(o -> o.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());

        int i = 1;
        StringBuffer sb = new StringBuffer();

        // loop through the active auctions and print out the details to the user
        for (Auction auction : activeAuctions) {

            sb.append("Auction ");
            sb.append(i);
            sb.append(": ");
            sb.append(auction.getItemDescription());
            sb.append("\n - closes on ");
            sb.append(auction.getCloseDate().getDayOfWeek().toString().toLowerCase());
            sb.append(" ");
            sb.append(auction.getCloseDate().getDayOfMonth());
            sb.append(" ");
            sb.append(auction.getCloseDate().getMonth().toString().toLowerCase());
            sb.append(" ");
            sb.append(auction.getCloseDate().getYear());
            sb.append(" at ");
            sb.append(auction.getCloseDate().getHour());
            sb.append(":");
            sb.append(auction.getCloseDate().getMinute());
            sb.append("\n");
            i++;
        }
        System.out.println(sb);
    }
}