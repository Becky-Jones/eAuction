package core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Auction {

    private double startPrice;
    private double reservePrice;
    private LocalDateTime closeDate;
    private Status status;
    Item item;
    Seller seller;
    Buyer buyer;
    private List<Bid> bids;


    public Auction() {
    }

    public Auction(double startPrice, double reservePrice, LocalDateTime closeDate, Status status, Item item, Seller seller) {
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
        this.closeDate = closeDate;
        this.status = status;
        this.item = item;
        this.seller = seller;
        this.bids = new ArrayList<>();
    }

    /*

     */
    public void placeBid(Bid userBid) {
        this.bids.add(userBid);

        if (userBid.getAmount() >= reservePrice) {
            // reserve price as been met, auction must be closed
            close();
        }
    }

    /*

     */
    public void verify() {
        this.status = Status.ACTIVE;
    }

    /*

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

        // Check if the max bid doesn't meet the reserve price
        if(maxBid.getAmount() < getReservePrice()){
            System.out.println(getItemDescription() + " has expired with no winner.");
            return; // Reserve price not met, thus there is no winner
        }

        // Inform the winning buyer of their victory
        buyer.victory(this);
    }

    /*

     */
    public boolean isBlocked() {
        return false;
    }

    /*

     */
    public void setBlocked() {
    }

    public double getStartPrice() {
        return startPrice;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public Status getStatus() {
        return status;
    }

    public String getItemDescription() {
        return item.getDescription();
    }

    public List getBids() {
        return this.bids;
    }

    public Bid getWinningBid() {

        Bid winningBid = new Bid(0.00, null, null);

        for (Bid bid : this.bids) {
            if (bid.getAmount() > winningBid.getAmount()) {
                winningBid = bid;
            }
        }

        // Notifying Victorious Buyer
        winningBid.getWho().victory(this);

        return winningBid;
    }


    @Override
    public String toString() {
        return getItemDescription();
    }
}