package core;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is for periodically checking for expired auctions to close
 */
public class StatusCheck implements Runnable {
    private final List<Auction> auctions;

    @Override
    public void run() {
        for (Auction auction : auctions) {
            // Only check auctions with an ACTIVE status
            if (auction.getStatus() == Status.ACTIVE) {
                // Close the auction if it has expired
                if (auction.getCloseDate().isBefore(LocalDateTime.now())) {
                    auction.close();
                }
            }
        }
    }

    /**
     * This constructor will set the list of auctions for this Class.
     *
     * @param auctions the list of auctions
     */
    public StatusCheck(List<Auction> auctions) {
        this.auctions = auctions;
        //setSeconds(seconds);
    }
}
