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

    private Item item;
    private Seller seller;
    private Buyer buyer;

    private List<Bid> bids;


    public Auction(Item item, Seller seller, Buyer buyer) {
        this.item = item;
        this.seller = seller;
        this.buyer = buyer;
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


    class Buyer {

        Buyer() {

        }
    }

    class Seller {

        Seller() {

        }
    }


    class Item {

        Item() {

        }
    }


}