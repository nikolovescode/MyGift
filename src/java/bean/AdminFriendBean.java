/*
Hämtar alla användare från db 
 */
package bean;

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
public class AdminFriendBean {

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
//Hämtar användare från db
    public ArrayList<String> getFriendsStatement() throws SQLException {
        ArrayList<String> friendList = new ArrayList<>();

        try {
            this.stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM giftTaker;");
            String friendName = "test";

            while (resultSet.next()) {
                if (resultSet != null) {
                    friendName = resultSet.getString(1);
                    friendList.add(friendName);
                }
            }
        } catch (SQLException ex) {
            System.err.println(new java.util.Date() + " : " + ex.getMessage());
        }

        return friendList;
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
