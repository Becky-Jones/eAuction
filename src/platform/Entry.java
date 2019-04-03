package platform;

import core.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Entry {

    public static void main(String[] args) throws ParseException {
        System.out.print("Welcome to the auction site,");

        Sys sys = new Sys();
        ArrayList<Auction> auctions;
        ArrayList<User> users = new ArrayList<>();

        users.add(new Seller("Becky", "1234"));
        users.add(new Seller("Sean", "1234"));
        users.add(new Seller("Test", "1234"));


        //TODO: FORMAT DATE/TIME in YYYY-MM-DD HH-MM-SS
        auctions = new ArrayList<>(Arrays.asList(new Auction(10000, 15000, LocalDateTime.parse("2019-01-01 12:00:00"), Status.ACTIVE, new Item("A car"), (Seller) users.get(0)),
                new Auction(10, 15, LocalDateTime.parse("2019-01-01 12:00:00"), Status.ACTIVE, new Item("A book"), (Seller) users.get(1)), new Auction(30, 70,
                        LocalDateTime.parse("2019-01-01 12:00:00"), Status.ACTIVE, new Item("A pair of shoes"), (Seller) users.get(0))));



        final StatusCheck statusCheck = new StatusCheck(auctions, 5);
        Runnable timer = new Runnable() {
            @Override
            public void run() {
                statusCheck.run();
            }
        };

        // 1 thread for the timer
        ExecutorService es = Executors.newFixedThreadPool(1);

        // start timer thread
        es.submit(timer);

        // Start Menu Sequence
        sys.startMenu(users, auctions);

        // Close all threads
        es.shutdown();
    }
}