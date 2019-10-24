/*
Det här är admins terminal: där denna kan logga in, se historik över gåvor och logga ut.
 */
package web;

import bean.AdminGiftBean;
import bean.LoginBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikolaj
 */
public class AdminTerminal {

    public static void main(String[] args) {
        while(true){
        AdminTerminal a = new AdminTerminal();
        Scanner s = new Scanner(System.in);
        System.out.println("To check user transaction, enter your details");
        System.out.println("Enter email");
        String e = s.nextLine();
        System.out.println("Enter password");
        String p = s.nextLine();
        boolean checkPass = a.checkGivenPassword(e,p);
        boolean checkAdmin = a.checkIfAdmin(e);
        if(checkPass==true&&checkAdmin==true){
        
        AdminGiftBean agb = new AdminGiftBean();
        agb.init();
        ArrayList<String> tList;
        try {
            tList = agb.getTransactionsStatement();
            agb.closeConnection();

            for (int i = 0; i < tList.size(); i++) {

                System.out.println(tList.get(i));
            }
                System.out.println("To logout press any key");
        String l = s.nextLine();
        if(l==" "){
            break;
        }
        } catch (SQLException ex) {
            Logger.getLogger(AdminTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        }else{
            System.out.println("Not a correct combination of email and password");
        }
        }

    }

        private boolean checkGivenPassword(String email, String password) {

        LoginBean lb = new LoginBean(); 
        lb.init();
        boolean check = lb.checkPassword(email, password);
        lb.closeConnection();

        return check;
    }
    
    private boolean checkIfAdmin(String email) {

        LoginBean lb = new LoginBean(); 
        lb.init();
        boolean admin = lb.checkIfAdmin(email);
        lb.closeConnection();

        return admin;
    }
}
