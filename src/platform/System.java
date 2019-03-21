package platform;

import core.Auction;
import core.Item;
import core.Seller;
import core.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class System {
    private static ArrayList<User> users;

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<Auction> auctions;

        auctions = new ArrayList<>(Arrays.asList(new Auction(10000, 15000, format.parse("2019-01-01"), 'A', new Item("A car"), new Seller()),
                new Auction(10, 15, format.parse("2019-01-01"), 'A', new Item("A book"), new Seller()), new Auction(30, 70,
                        format.parse("2019-01-01"), 'A', new Item("A pair of shoes"), new Seller())));

        java.lang.System.out.print("Welcome to the auction site,");

        // is user logged in?
        // yes
        // userMenu();

        // no
        // guest menu();
        java.lang.System.out.println(" which would you like?");
        java.lang.System.out.println("1. Create Account");
        java.lang.System.out.println("2. Login");
        java.lang.System.out.println("3. Browse Auctions");
        java.lang.System.out.println("4. Quit");

        Scanner scanner = new Scanner(java.lang.System.in);

        String option = scanner.nextLine();

        switch (option) {
            case "1": /* set up account*/
                break;
            case "2":
                break;
            case "3":
                browseAuction(auctions);
                break;
            default:
                java.lang.System.out.println("Goodbye");
                java.lang.System.exit(0);
        }
    }

    public void placeAuction() {

    }

    public static void browseAuction(List<Auction> auctions) {
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

}