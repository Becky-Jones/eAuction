package core;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class Auction {

    private double startPrice;
    private double reservePrice;
    private Date closeDate;
    private Status status;
    Item item;
    Seller seller;
    Buyer buyer;
    private List<Bid> bids;


    public Auction() {
    }

    public Auction(double startPrice, double reservePrice, Date closeDate, Status status, Item item, Seller seller) {
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
        this.closeDate = closeDate;
        this.status = status;
        this.item = item;
        this.seller = seller;
    }

    /*

     */
    public void placeBid() {

    }

    /*

     */
    public void verify() {
        this.status = Status.ACTIVE;
    }

    /*

     */
    public void close() {

        List bids = getBids();


        if (bids.size() == 0) {
            // auction is expired
        } else {
            buyer.victory(this);
        }
        // Set auction status to closed
        this.status = Status.CLOSED;

        // TODO: Check if the auction was won or expired
        // TODO: Call victory()


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

    public Date getCloseDate() {
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