package core;

import java.util.LinkedList;
import java.util.List;

public class StatusCheck {

    public List<Auction> auctions = new LinkedList<>();
    Integer delay;

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
