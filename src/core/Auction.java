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
    private char status;
    Item item;
    Seller seller;
    Buyer buyer;
    private List<Bid> bids;


    public Auction() {
    }

    public Auction(double startPrice, double reservePrice, Date closeDate, char status, Item item, Seller seller) {
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

    }

    /*

     */
    public void close() {

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
        switch (status) {
            case 'A':
                return Status.ACTIVE;
            case 'C':
                return Status.CLOSED;
            case 'P':
                return Status.PENDING;
            case 'B':
                return Status.BLOCKED;
        }
        return Status.CLOSED;
    }

    public String getItemDescription() {
        return item.getDescription();
    }

    @Override
    public String toString() {
        return getItemDescription();
    }
}