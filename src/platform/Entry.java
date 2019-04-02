package platform;

import core.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Welcome to the auction site,");

        Sys sys = new Sys();
        ArrayList<Auction> auctions;
        ArrayList<User> users = new ArrayList<>();

        users.add(new Seller("Becky", "1234"));
        users.add(new Seller("Sean", "1234"));
        users.add(new Seller("Test", "1234"));


        auctions = new ArrayList<>(Arrays.asList(new Auction(10000, 15000, format.parse("2019-01-01"), Status.ACTIVE, new Item("A car"), (Seller) users.get(0)),
                new Auction(10, 15, format.parse("2019-01-01"), Status.ACTIVE, new Item("A book"), (Seller) users.get(1)), new Auction(30, 70,
                        format.parse("2019-01-01"), Status.ACTIVE, new Item("A pair of shoes"), (Seller) users.get(0))));

        sys.startMenu(users, auctions);
    }
}