package core;

import java.time.LocalDateTime;

/**
 * This class contains a bid object which will be set on an auction, the object will contain the date, amount and who the bidder is
 */
public class Bid {
    /**
     * Private Fields
     */
    private double amount;
    private Buyer who;
    private LocalDateTime when;

    /**
     * This constructor sets the bid
     *
     * @param amount the value of the bid as a double
     * @param who    the buyer who is setting the bid
     * @param when   the date the bid was created
     */
    public Bid(double amount, Buyer who, LocalDateTime when) {
        this.amount = amount;
        this.who = who;
        this.when = when;
    }

    /**
     * This method will return the value of the bid
     *
     * @return the bid amount as a double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This method will set the bid amount
     *
     * @param amount the valud of the bid as a double
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * This method will return who the buyer is that created this bid
     *
     * @return the buyer who set the bid
     */
    public Buyer getWho() {
        return who;
    }

    /**
     * This method will set the buyer of the bid
     *
     * @param who the buyer who is creating this bid
     */
    public void setWho(Buyer who) {
        this.who = who;
    }

    /**
     * This method will return the date this bid was set
     *
     * @return the date of the bid as a localDateTime
     */
    public LocalDateTime getWhen() {
        return when;
    }

    /**
     * This method will set the date the bid was set on the auction
     *
     * @param when the date to be set as a localDateTime
     */
    public void setWhen(LocalDateTime when) {
        this.when = when;
    }
}
