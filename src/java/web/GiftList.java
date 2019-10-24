/*
Objekt listar alla gåvor som skickas mellan användare (sändare - antal kronor - mottagare)
 */
package web;

/**
 *
 * @author nikolaj
 */
public class GiftList {

    String id;
    String amount;
    String giftTaker;
    String giver;

    public GiftList(String v1, String v2, String v3, String v4) {
        id = v1;
        amount = v2;
        giftTaker = v3;
        giver = v4;
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

}
