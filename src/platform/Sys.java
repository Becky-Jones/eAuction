package platform;

import core.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Sys {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static ArrayList<User> users;
    private final Scanner scanner = new Scanner(java.lang.System.in);
    List<Auction> auctions = new LinkedList<>();
    Seller loggedInUser;

    public void placeAuction() {
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

        System.out.println(activeAuctions.toString());
    }

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
                sellerMenu();
            } else {
                users.add(new Buyer(username, password));
                System.out.println("Your account has been successfully created");
                buyerMenu();
            }
        } else {
            System.out.println("Sorry there was a problem creating your account, please try again");
        }

    }

    public void startMenu() throws ParseException {
        // is user logged in?
        // yes
        // userMenu();

        // no
        // guest menu();
        System.out.println(" which would you like?");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Browse Auctions");
        System.out.println("4. Quit");

        String option = scanner.nextLine();

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

    private void sellerMenu() throws ParseException {
        System.out.println(" which would you like?");
        System.out.println("1. Create Auction");
        System.out.println("2. Verify Auction");
        System.out.println("3. Logout");
        System.out.println("4. Quit");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                createAuction();
                break;
            case "2":
                verifyAuction();
                break;
            case "3":
                logout();
                break;
            default:
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

        switch (option) {
            case "1": /* set up account*/
                browseAuction(auctions);
                break;
            case "2":
                placeBid();
                break;
            case "3":
                logout();
                break;
            default:
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            // check if the user exists
            if (user.getUsername().equals(username)) {
                // user exists
                return user;
            }
        }
        // user doesn't exist
        return null;
    }

    private boolean login() {
        User user;

        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        user = getUserByUsername(username);

        // user doesn't exist
        if (user == null) return false;

        // now we need to check their password!!
        System.out.println("Please enter your password: ");

        String password = scanner.nextLine();

        return user.getPassword().equals(password);
    }

    private void createAuction() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Please enter the item description for your auction");
        String itemDesc = scanner.nextLine();

        System.out.println("Please enter the start price for your auction");
        double startPrice = scanner.nextDouble();

        System.out.println("Please enter the reserve price for your auction");
        double reservePrice = scanner.nextDouble();

        System.out.println("Please enter the close date for your auction");
        String closeDate = scanner.nextLine();

        Auction auction = new Auction(startPrice, reservePrice, format.parse(closeDate), Status.PENDING, new Item(itemDesc), loggedInUser);
        auctions.add(auction);

        System.out.println("Auction created, The auction is now in a pending state and must first be verified");
    }

    private void verifyAuction() {

        List<Auction> pendingAuctions = auctions.stream().filter(o -> o.getStatus().equals(Status.PENDING)).collect(Collectors.toList());

        if (pendingAuctions.size() == 0) {
            System.out.println("There are no pending auctions for your account");
        } else {
            System.out.println("Which auction would you like to verify?");
            int i = 0;
            for (Auction auction : pendingAuctions) {
                System.out.println("Auction " + i + " " + auction.getItemDescription());
            }

            int choice = scanner.nextInt();

            auctions.get(choice).verify();
        }
    }

    private void placeBid() {
    }

    private void logout() {
    }

}