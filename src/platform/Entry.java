package platform;

import core.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is the entry point for this application, it will contain the thread to be run throughout
 */
class Entry {

    public static void main(String[] args) {
        System.out.print("Welcome to the auction site,");

        Sys sys = new Sys();
        ArrayList<Auction> auctions;
        ArrayList<User> users = new ArrayList<>();

        users.add(new Seller("Becky", "1234"));
        users.add(new Seller("Sean", "1234"));
        users.add(new Seller("Test", "1234"));


        auctions = new ArrayList<>(Arrays.asList(new Auction(10000, 15000, LocalDateTime.now().plusSeconds(40), Status.ACTIVE, new Item("A car"), (Seller) users.get(0)),
                new Auction(10, 15, LocalDateTime.now().plusSeconds(60), Status.ACTIVE, new Item("A book"), (Seller) users.get(1)), new Auction(30, 70,
                        LocalDateTime.now().plusSeconds(200), Status.ACTIVE, new Item("A pair of shoes"), (Seller) users.get(0))));


        final StatusCheck statusCheck = new StatusCheck(auctions);

        // Scheduled Executor Service to run one thread
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        // Execute run method in the StatusCheck class every 30 seconds
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // Check for expired auctions
                statusCheck.run();
            }
        }, 0, 10, TimeUnit.SECONDS);  // execute every 30 seconds

        // Start Menu Sequence
        sys.startMenu(users, auctions);

        // Close all threads
        ses.shutdown();
    }
}