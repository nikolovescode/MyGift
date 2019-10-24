package web;

/*
Objektet Gift (gåvan som skickas mellan användare)
 */
/**
 *
 * @author nikolaj
 */
public class Gift {

    private String id;
    private String amount;
    private String giftTaker;
    private String giver;

    public Gift() {
    }

    public Gift(String id, String amount, String giftTaker, String giver) {
        this.id = id;
        this.amount = amount;
        this.giftTaker = giftTaker;
        this.giver = giver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGiftTaker() {
        return giftTaker;
    }

    public void setGiftTaker(String giftTaker) {
        this.giftTaker = giftTaker;
    }

    public String getGiver() {
        return giver;
    }

    public void setGiver(String giver) {
        this.giver = giver;
    }

    @Override
    public String toString() {
        return this.amount;
    }
}
