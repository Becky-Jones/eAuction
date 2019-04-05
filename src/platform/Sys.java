package platform;

import core.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Sys {

    private static List<User> users = new LinkedList<>();
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\\R+");
    private List<Auction> auctions = new LinkedList<>();
    private User loggedInUser;

    /**
     * MENUS
     */

    void startMenu(List<User> users, List<Auction> auctions) throws ParseException {
        this.users = users;
        this.auctions = auctions;
        String option = "";
        while (!(option.equals("4"))) {
            if (loggedInUser != null) {
                if (Seller.class.isInstance(loggedInUser)) {
                    // they are a seller
                    sellerMenu();
                } else {
                    // they are a buyer
                    buyerMenu();
                }
            } else {
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
                    case "2":
                        login();
                        break;
                    case "3":
                        browseAuction(auctions);
                        break;
                    default:
                        System.out.println("Goodbye");
                        System.exit(0);
                }
            }
        }
    }

    private void sellerMenu() throws ParseException {
        System.out.println(" which would you like?");
        System.out.println("1. Create Auction");
        System.out.println("2. Verify Auction");
        System.out.println("3. Logout");
        System.out.println("4. Quit");
        String option = scanner.nextLine();

        if(option.equalsIgnoreCase("")){
            option = scanner.nextLine();
        }

        switch (option) {
            case "1":
                createAuction((Seller) loggedInUser);
                break;
            case "2":
                verifyAuction();
                break;
            case "3":
                logout();
                break;
            case "4":
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    private void buyerMenu() {
        System.out.println(" which would you like?");
        System.out.println("1. Browse Active Auctions");
        System.out.println("2. Bid On Auction");
        System.out.println("3. Logout");
        System.out.println("4. Quit");

        String option = scanner.nextLine();

        if(option.equalsIgnoreCase("")){
            option = scanner.nextLine();
        }
        switch (option) {
            case "1":
                browseAuction(auctions);
                break;
            case "2":
                placeBid();
                break;
            case "3":
                logout();
                break;
            case "4":
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    /**
     * SET UP AND LOGIN
     */

    private void setUpAccount() throws ParseException {
        System.out.println("Please enter a username: ");

        String username = scanner.nextLine();

        boolean usernameValid = false;

        // check if the username is a valid name
        while (!usernameValid) {
            // if a user exists with this name then they cannot create a user with this username
            if (getUserByUsername(username) == null) usernameValid = true;
        }

        System.out.println("Now please enter a password: ");
        String password = scanner.nextLine();

        System.out.println("Please confirm your password: ");
        String confirmPassword = scanner.nextLine();

        System.out.println("Would you like to be a buyer or seller (B | S)?");
        String userType = scanner.nextLine();

        if (password.equals(confirmPassword)) {
            // valid! lets create this user
            if (userType.equalsIgnoreCase("S") || userType.equalsIgnoreCase("Seller")) {
                users.add(new Seller(username, password));
                System.out.println("Your account has been successfully created");
            } else {
                users.add(new Buyer(username, password));
                System.out.println("Your account has been successfully created");
            }
        } else {
            System.out.println("Sorry there was a problem creating your account, please try again");
        }

    }

    private User getUserByUsername(String username) {

//        Optional<User> user = users.stream().filter(o -> o.getUsername().equalsIgnoreCase(username)).reduce((a, b) -> null);

//        if (user.isPresent()) {
//            return user.get();
//        }
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        // user doesn't exist
        return null;
    }

    private void login() {
        User user;

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        user = getUserByUsername(username);

        // user doesn't exist
        if (user == null) {
            System.out.println("Sorry those details are incorrect");
            return;
        }

        // now we need to check their password!!
        System.out.println("Please enter your password: ");

        String password = scanner.nextLine();

        if (user.getPassword().equals(password)) {
            loggedInUser = user;
            // login successful

        } else {
            // password incorrect
            System.out.println("Sorry those details are incorrect");
        }
    }

    private void logout() {
        loggedInUser = null;
    }

    /**
     * SELLER FUNCTIONALITY
     */

    private void createAuction(Seller seller) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Please enter the item description for your auction");
        String itemDesc = scanner.nextLine();

        System.out.println("Please enter the start price for your auction");
        double startPrice = scanner.nextDouble();

        System.out.println("Please enter the reserve price for your auction");
        double reservePrice = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Please enter the close date for your auction in the format of yyyy-MM-dd HH:mm");
        String closeDate = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime closing = LocalDateTime.parse(closeDate, formatter);

        Auction auction = new Auction(startPrice, reservePrice, closing, Status.PENDING, new Item(itemDesc), seller);
        auctions.add(auction);

        System.out.println("Auction created, The auction is now in a pending state and must first be verified");
    }

    private void verifyAuction() {

        List<Auction> pendingAuctions = auctions.stream().filter(o -> o.getStatus().equals(Status.PENDING)).collect(Collectors.toList());

        if (pendingAuctions.size() == 0) {
            System.out.println("There are no pending auctions for your account");
        } else {
            System.out.println("Which auction would you like to verify? (enter the auction number)");

            int i = 1;
            Map<Integer, Auction> options = new HashMap<>();

            for (Auction auction : pendingAuctions) {
                options.put(i, auction);
                System.out.println("Auction " + i + ": " + auction.getItemDescription());
                i++;
            }

            int choice = scanner.nextInt();

            Auction chosen = options.get(choice);

            chosen.verify();
            System.out.println("Auction Verified");
        }

    }

    /**
     * BUYER FUNCTIONALITY
     */

    private void placeBid() {
        List<Auction> activeAuctions = auctions.stream().filter(o -> o.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());

        if (activeAuctions.isEmpty()) {
            System.out.println("Sorry there ar currently no active auctions to bid on");
            return;
        }

        System.out.println("Please choose from the list of active auctions:");
        browseAuction(auctions);

        int choice = scanner.nextInt();

        Auction auction = activeAuctions.get((choice - 1));

        double minimum = 0;

        if (auction.getBids() != null) {
            List<Bid> bids = auction.getBids();

            // get the maximum bid out of the list of bids and set it as the minimum
            if(bids.size() > 0) {
                minimum = bids.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Bid::getAmount))).get().getAmount();
            }
        }

        double maximum = minimum * 1.2;
        minimum *= 1.1;
        boolean validBid = false;

        if (maximum == 0) {
            maximum = auction.getReservePrice();
        }

        while (!validBid) {
            System.out.println("Please enter the amount you would like to bid between £" + minimum + " and £" + maximum);

            double bid = scanner.nextDouble();

            if (bid <= maximum && bid >= minimum) {
                // valid bid
                validBid = true;
                Bid userBid = new Bid(bid, (Buyer) loggedInUser, LocalDateTime.now());

                auction.placeBid(userBid);
                System.out.println("Your bid of £" + bid + " has successfully been placed on the auction");

            } else {
                // invalid bid
                System.out.println("Sorry that bid was invalid, lets try this again");
            }
        }
    }

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
        List<Auction> activeAuctions =
                auctions.stream().filter(o -> o.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());

        int i = 1;
        for (Auction auction : activeAuctions) {
            System.out.print("Auction " + i + ": ");
            System.out.println(auction.getItemDescription());
            i++;
        }
        System.out.println();
    }


}