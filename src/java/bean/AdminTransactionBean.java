/*
Hanterar transaktioner mellan användare i db
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
public class AdminTransactionBean {

    Connection con;
    PreparedStatement stmt;
    Statement qstmt;
    Statement searchStmt;

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
//Mottagare visar att denna vill ta en gåva
    public void activateGift(String idOfGift, String giftTaker, String amount) throws SQLException {
        this.stmt = (PreparedStatement) con.prepareStatement("UPDATE gift SET activated=1 WHERE id=?;");
        stmt.setInt(1, Integer.valueOf(idOfGift));
        stmt.executeUpdate();

        this.stmt = (PreparedStatement) con.prepareStatement("UPDATE giftTaker SET moneyOnAccount=moneyOnAccount+? WHERE email=?;");
        stmt.setFloat(1, Float.valueOf(amount));
        stmt.setString(2, giftTaker);
        stmt.executeUpdate();

    }
//Registrerar gåva till person som är använd sen tidigare
    public void insertExistingUserStatement(String email, float amount, String alias) {
        try {

            //Creates alias
            this.stmt = (PreparedStatement) con.prepareStatement("INSERT INTO giftGiver VALUES(?);");
            stmt.setString(1, alias);
            stmt.executeUpdate();
            //Creates gift
            this.stmt = (PreparedStatement) con.prepareStatement("INSERT INTO gift(amount, activated, giftTaker_email, giftGiver_alias) VALUES(?, ?, ?, ?);");
            stmt.setFloat(1, amount);
            stmt.setBoolean(2, false);
            stmt.setString(3, email);
            stmt.setString(4, alias);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }
//Tar bort pengar från konto vid köp
    public void withdrawFromUserStatement(String email, String amount) {
        try {
            //Creates new customer account
            this.stmt = (PreparedStatement) con.prepareStatement("UPDATE giftTaker SET moneyOnAccount=moneyOnAccount-? WHERE email=?;");
            stmt.setFloat(1, Float.valueOf(amount));
            stmt.setString(2, email);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
//Lägger in en ny användare
    public void insertNewUserStatement(String email, float amount, int password, boolean isAdmin, boolean isInactive, String alias) {
        try {
            //Creates new customer account
            this.stmt = (PreparedStatement) con.prepareStatement("INSERT INTO giftTaker VALUES(?,?,?,?,?);");
            stmt.setString(1, email);
            stmt.setFloat(2, 0);
            stmt.setString(3, String.valueOf(password));
            stmt.setBoolean(4, isAdmin);
            stmt.setBoolean(5, isInactive);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
    //Hämtar uppgift om hur mycket pengar som finns på konto
    public float getAccountValueStatement(String email) {
        float accountValue = 0;

        try {

            this.qstmt = con.createStatement();
            ResultSet resultSet = qstmt.executeQuery("SELECT moneyOnAccount FROM giftTaker WHERE email='" + email + "'");
            while (resultSet.next()) {
                accountValue = resultSet.getFloat(1);
            }
            qstmt.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return accountValue;
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

