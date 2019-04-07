package core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the auction of a seller, including setting the details of the auction and holding information of the bids against it and the seller
 */
public class Auction {

    /**
     * Fields
     */
    private double startPrice;
    private double reservePrice;
    private LocalDateTime closeDate;
    private Status status;
    private Item item;
    public Seller seller;
    Buyer buyer;
    private List<Bid> bids;

    /**
     * Public constructor
     */
    public Auction() {
    }

    /**
     * COnstructor setting details for the auctions
     *
     * @param startPrice   the start price of the auction to begin with
     * @param reservePrice the reserve price of the auction to be met
     * @param closeDate    the closing date of the auction
     * @param status       the status of the auction, will be of the Status object
     * @param item         the item to be sold on the auction
     * @param seller       the seller who owns the auction
     */
    public Auction(double startPrice, double reservePrice, LocalDateTime closeDate, Status status, Item item, Seller seller) {
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
        this.closeDate = closeDate;
        this.status = status;
        this.item = item;
        this.seller = seller;
        this.bids = new ArrayList<>();
    }

    /**
     * This method will place a bid on the auction for a buyer
     *
     * @param userBid the bid to be placed, contains of the bid amount, date and the buyer who made the bid
     */
    public void placeBid(Bid userBid) {
        this.bids.add(userBid);
    }

    /**
     * this method will verify the auction and put it into an active state ready to be bid
     */
    public void verify() {
        this.status = Status.ACTIVE;
    }

    /**
     * This method will close the auction after it has finished, it will set the status as closed and if the reserve price
     * has been met it will notify the winning buyer
     */
    public void close() {

        List<Bid> bids = getBids();

        // Set auction status to closed
        this.status = Status.CLOSED;

        // Checks if anyone placed a bid on the auction
        if (bids.size() == 0) {
            // auction is expired with no bids, therefore no winner
            System.out.println(getItemDescription() + " has expired with no winner.");
            return;
        }

        // Get the max bid
        Bid maxBid = bids.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Bid::getAmount))).get();
        buyer = maxBid.getWho();

        // Check if the max bid doesn't meet the reserve price
        if (maxBid.getAmount() < getReservePrice()) {
            System.out.println(getItemDescription() + " has expired with no winner.");
            return; // Reserve price not met, thus there is no winner
        }


        System.out.println(getItemDescription() + " has ended with a winner.");
        // Inform the winning buyer of their victory
        buyer.victory(this);
    }

    /**
     * This method will check whether the auction is blocked
     *
     * @return a boolean as to whether the auction is blocked or not
     */
    public boolean isBlocked() {
        return this.status == Status.BLOCKED;
    }

    /**
     * This method will set the auction as blocked
     */
    public void setBlocked() {
        this.status = Status.BLOCKED;
    }

    /**
     * This method will return the start price of the auction
     *
     * @return the start price of the auction as a double
     */
    public double getStartPrice() {
        return startPrice;
    }

    /**
     * This method will return the reserve price of the auction
     *
     * @return the reserve price as a double
     */
    public double getReservePrice() {
        return reservePrice;
    }

    /**
     * This method will return the closing date of the auction
     *
     * @return the close date as a LocalDateTime
     */
    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    /**
     * This method will return the status of the auction
     *
     * @return the status of the auction as the Status object
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This method will return the description of the item being sold on the auction
     *
     * @return the item description from the item object
     */
    public String getItemDescription() {
        return item.getDescription();
    }

    /**
     * This method will return the bids that have been placed on the auction
     *
     * @return a list of bids
     */
    public List getBids() {
        return this.bids;
    }

    /**
     * This method will return the winning bid from the auction once the auction has been closed
     *
     * @return The winning bid from the auction if there was a winner
     */
    public Bid getWinningBid() {

        Bid winningBid = new Bid(0.00, null, null);

        for (Bid bid : this.bids) {
            if (bid.getAmount() > winningBid.getAmount()) {
                winningBid = bid;
            }
        }

        return winningBid;
    }


    @Override
    public String toString() {
        return getItemDescription();
    }
}