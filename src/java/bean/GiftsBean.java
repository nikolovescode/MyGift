/*
Används för att processa användarens order gentemot lista av gåvor
 */
package bean;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import javax.faces.event.ActionEvent;
import web.Gift;
import web.GiftList;

@ManagedBean(name = "giftbean")
@SessionScoped
public class GiftsBean implements Serializable {

    private List<Gift> data; 
    private String gift; 
    private String msg; 
    Gift g;
    List<GiftList> giftList;
    GiftList chosen;

    public GiftsBean() throws SQLException, FileNotFoundException {
try{
        this.giftList = getGiftList();
        loadData();
        // select the first item in the list
        this.g = data.get(0);
        setGift(g.getAmount());
        setMessage(String.format("%s from %s selected", g.getAmount(), g.getGiver()));
}catch(IndexOutOfBoundsException e){
            setMessage("No gifts for you");

}
    }

    private void loadData() {


        data = new ArrayList<>();

        for (int i = 0; i < giftList.size(); i++) {
            Gift g = new Gift(giftList.get(i).getId(), giftList.get(i).getAmount(), giftList.get(i).getGiftTaker(), giftList.get(i).getGiver());
            data.add(g);
        }
    
    }

    public List<Gift> getData() {
        return data;
    }

    public void setGift(String t) {
        this.gift = t;
    }

    public String getGift() {
        return gift;
    }

    public void Gift(String t) {
        this.gift = t;
    }

    public void valueChanged(ValueChangeEvent v) {
        this.chosen = (GiftList) v.getNewValue();
        setMessage(chosen + " selected.");
    }

    public void saveListener(ActionEvent a) throws FileNotFoundException, SQLException {
        setMessage(chosen.getAmount() + " added to cache.");
        activateGift(chosen.getId(), chosen.getGiftTaker(), chosen.getAmount());
        giftList.remove(chosen);

    }

    public void setMessage(String s) {
        msg = s;
    }

    public String getMessage() {
        return msg;
    }

    public void activateGift(String id, String giftTaker, String amount) throws SQLException, FileNotFoundException {
        AdminTransactionBean atb = new AdminTransactionBean(); 
        atb.init();
        atb.activateGift(id, giftTaker, amount);
        atb.closeConnection();

    }

    public List<GiftList> getGiftList() throws SQLException, FileNotFoundException {
        Scanner scanner = new Scanner(new File("loggedIn.txt"), "UTF-8");
        String user = scanner.useDelimiter("\\A").next();
        scanner.close(); // Put this call in a finally block
        AdminGiftBean agb = new AdminGiftBean(); //(TeacherInforRemRemote) Naming.lookup ("ava:global/CourseEJB/beans/TeacherInfoRem");
        agb.init();
        List<GiftList> giftList = agb.getGiftsStatement(user);
        for (int i = 0; i < giftList.size(); i++) {
            System.out.println(giftList.get(i).getAmount());
        }
        agb.closeConnection();
        this.chosen = giftList.get(0);
        return giftList;

    }
}
