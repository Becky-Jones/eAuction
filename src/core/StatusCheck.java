package core;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class StatusCheck implements Runnable{

    public List<Auction> auctions = new LinkedList<>();
    Integer delay;

    @Override
    public void run(){
        for(int i = 0; i<auctions.size(); i++){
            Auction auction = auctions.get(i);

            // Only check auctions with an ACTIVE status
            if(auction.getStatus()==Status.ACTIVE){
                if(auction.getCloseDate().isBefore(LocalDateTime.now())){
                    System.out.println("Closing Auction");
                    // Close the auction if it has expired
                    auction.close();
                }
            }
        }
    }

    public StatusCheck(List<Auction> auctions, int seconds) {
        this.auctions = auctions;
        setSeconds(seconds);
    }

    private synchronized void setSeconds(int seconds) {
        this.delay = seconds * 1000;
    }

    public int getSeconds() {
        return delay;
    }
}
