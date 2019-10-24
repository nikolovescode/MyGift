/*
Används för testning av tjänst
*/
package web;

import bean.AdminGiftBean;
import web.*;
import com.mysql.jdbc.PreparedStatement;
import bean.AdminTransactionBean;
import bean.LoginBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikolaj
 */
public class UserAdmin {

    Connection con;
    PreparedStatement stmt;
    Scanner s;

    public static void main(String[] args) throws SQLException {
        UserAdmin ta = new UserAdmin();

        System.out.println(ta.checkIfEnoughMoneyOnAccountBean("nikolaj.mansson@gmail.com", "400"));
    }

    private boolean checkIfEnoughMoneyOnAccountBean(String email, String eventCost) throws SQLException {

        AdminGiftBean agb = new AdminGiftBean();
        agb.init();

        boolean check = agb.getAccountStatement(email, eventCost);
        agb.closeConnection();
        if (check == true) {
            AdminTransactionBean atb = new AdminTransactionBean();
            atb.init();
            atb.withdrawFromUserStatement(email, eventCost);
            atb.closeConnection();
            return true;
        }
        return false;
    }

    private void callAdminTransactionExistingUserBean() {
        Random rn = new Random();

        s = new Scanner(System.in);
        AdminTransactionBean arb = new AdminTransactionBean(); 
        arb.init();
        System.out.println("Enter email of gift receiver");
        String email = s.nextLine();
        System.out.println("Enter amount");
        float amount = Float.valueOf(s.nextLine());
        int password = rn.nextInt(1000);
        System.out.println("Enter your alias");
        String alias = s.nextLine();
        arb.insertExistingUserStatement(email, amount, alias);
        arb.closeConnection();
    }

    private void callAdminTransactionNewUserBean() {
        Random rn = new Random();

        s = new Scanner(System.in);
        AdminTransactionBean arb = new AdminTransactionBean(); 
        arb.init();
        System.out.println("Enter email of gift receiver");
        String email = s.nextLine();
        System.out.println("Enter amount");
        float amount = Float.valueOf(s.nextLine());
        int password = rn.nextInt(1000);
        System.out.println("Enter your alias");
        String alias = s.nextLine();
        arb.insertNewUserStatement(email, amount, password, false, false, alias);
        arb.closeConnection();
    }

    private void checkGivenPassword(String email, String password) {

        LoginBean lb = new LoginBean(); 
        lb.init();
        boolean check = lb.checkPassword(email, password);
        if (check == true) {
            System.out.println("You are logged in");
        } else {
            System.out.println("Wrong password!");
        }
        lb.closeConnection();
    }
}
