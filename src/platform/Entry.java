package platform;

import core.Auction;
import core.Item;
import core.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Welcome to the auction site,");

        Sys sys = new Sys();
        ArrayList<Auction> auctions;

        auctions = new ArrayList<>(Arrays.asList(new Auction(10000, 15000, format.parse("2019-01-01"), 'A', new Item("A car"), new Seller()),
                new Auction(10, 15, format.parse("2019-01-01"), 'A', new Item("A book"), new Seller()), new Auction(30, 70,
                        format.parse("2019-01-01"), 'A', new Item("A pair of shoes"), new Seller())));


        sys.startMenu(auctions);
    }
}
