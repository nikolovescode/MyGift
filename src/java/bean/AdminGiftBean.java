/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package bean;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import web.GiftList;

/**
 *
 * @author nikolaj
 */
@Stateless
public class AdminGiftBean {

    Connection con;
    PreparedStatement preStmt;
    Statement stmt;

//Initierar JDBC-driver
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/mygift?autoReconnect=true&useSSL=false", "root", "root");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//Hämtar uppgift om pengar på konto motsvarar kostnad för event
    public boolean getAccountStatement(String email, String eventCost) throws SQLException {

        float amount = 0;
        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT moneyOnAccount from giftTaker where email='" + email + "';");

            while (resultSet.next()) {
                if (resultSet != null) {
                    amount = resultSet.getFloat(1);

                }

            }

        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }
        if (amount >= Float.valueOf(eventCost)) {
            return true;
        }

        return false;
    }
//Hämtar uppgift om gåva
    public List<GiftList> getGiftsStatement(String email) throws SQLException {
        List<GiftList> myList = new ArrayList<GiftList>();

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT id,amount,giftTaker_email,giftGiver_alias FROM gift WHERE giftTaker_email='" + email + "' AND activated=0;");
            String id = "test";
            String giftName = "test";
            String giftTaker = "test";
            String alias = "test";

            while (resultSet.next()) {
                if (resultSet != null) {
                    id = resultSet.getString(1);
                    giftName = resultSet.getString(2);
                    giftTaker = resultSet.getString(3);
                    alias = resultSet.getString(4);
                    GiftList gl;
                    gl = new GiftList(id, giftName, giftTaker, alias);
                    myList.add(gl);
                }
            }
        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }

        return myList;
    }
//Hämtar alla gåvor från db
    public ArrayList<String> getTransactionsStatement() throws SQLException {
        ArrayList<String> list = new ArrayList<>();

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM gift;");
            String amount = "test";
            String alias = "test";
            String friendName = "test";

            while (resultSet.next()) {
                if (resultSet != null) {
                    amount = resultSet.getString(2);
                    alias = resultSet.getString(4);
                    friendName = resultSet.getString(5);

                    list.add(String.format("%s kr sent to %s from %s", amount, alias, friendName));
                }
            }
            stmt.close();

        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }

        return list;
    }
    //Hämtar logg över alla gåvor
    public ArrayList<String> getTransactionsHistory(String email) throws SQLException {
        ArrayList<String> list = new ArrayList<>();

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM gift WHERE giftTaker_email='"+email+"';");
            String amount = "test";
  
            String friendName = "test";

            while (resultSet.next()) {
                if (resultSet != null) {
                    amount = resultSet.getString(2);
                    friendName = resultSet.getString(5);

                    list.add(String.format("%s kr sent from %s", amount, friendName));
                }
            }
            stmt.close();

        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }

        return list;
    }

//Stänger uppkoppling till db
    public void closeConnection() {
        try {
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
