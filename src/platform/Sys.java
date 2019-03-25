package platform;

import core.Auction;
import core.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sys {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static ArrayList<User> users;
    Scanner scanner = new Scanner(java.lang.System.in);

    public void placeAuction() {
    }

    private void browseAuction(List<Auction> auctions) {
        int counter = 1;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("====================================== \n");
        for (Auction auction : auctions) {
            if (auction.getStatus() == 'A') {
                stringBuffer.append("Auction ").append(counter).append("\n").append("======================================").append("\n");
                stringBuffer.append("Start Price: £").append(auction.getStartPrice()).append("\n");
                stringBuffer.append("Reserve Price: £").append(auction.getReservePrice()).append("\n");
                stringBuffer.append("Item Description: ").append(auction.getItemDescription()).append("\n");
                stringBuffer.append("Close Date: ").append(auction.getCloseDate()).append("\n");
                stringBuffer.append("======================================").append("\n");
                counter++;
            }
        }
        java.lang.System.out.println(stringBuffer);
    }

    public void setUpAccount() {

    }

    public void startMenu(List<Auction> auctions) {
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

}