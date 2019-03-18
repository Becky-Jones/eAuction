import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private ArrayList<Item> items;
    private List<Auction> auctions;

    public boolean isBlocked() {
        return false;
    }
}
