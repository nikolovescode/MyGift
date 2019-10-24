/*
Används för att hantera inloggning gentemot db
 */
package bean;

import bean.*;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author nikolaj
 */
@Stateless
public class LoginBean {

    Connection con;
    Statement stmt;
    String correctPassword = null;
    String adminMail = null;
        boolean isAdmin = false;

//Initierar JDBC-Driver
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

    //Hämtar alla lösenord från db
    public String getPassword(String email) {

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT password from giftTaker where email='" + email + "';");

            while (resultSet.next()) {
                if (resultSet != null) {
                    this.correctPassword = resultSet.getString(1);
                    return correctPassword;
                }          }
        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }
        return "";
    }
    //Kontrollerar om email stämmer överens med givet lösenord
    public boolean checkPassword(String email, String password) {

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT password from giftTaker where email='" + email + "';");

            while (resultSet.next()) {
                if (resultSet != null) {
                    this.correctPassword = resultSet.getString(1);

                }
                if (password.equalsIgnoreCase(correctPassword)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }
        return false;
    }
//Kontrollerar om användarkonto har adminbehörighet
    public boolean checkIfAdmin(String email) {

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT isAdmin from giftTaker where email='"+email+"';");
            
            while (resultSet.next()) {
                if (resultSet != null) {
                    this.isAdmin = resultSet.getBoolean(1);
                    if (isAdmin==true) {
                        return true;
                    }
                    
                }
                
            }
        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }
        return false;
    }
    
//Stänger uppkoppling
    public void closeConnection() {
        try {
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
}
