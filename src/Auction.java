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
}