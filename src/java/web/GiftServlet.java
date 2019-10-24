/*
Registerar vem som ska mottaga en gåva och sparar denna i en cookie
*/
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nikolaj
 */
@WebServlet(name = "GiftServlet", urlPatterns = {"/GiftServlet"})
public class GiftServlet extends HttpServlet {

    String login;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String chosen = (String) request.getAttribute("item");

        Cookie receiverCookie = new Cookie(("receiver"), chosen);
        //setting cookie to expiry in 30 mins
        receiverCookie.setMaxAge(30 * 60);
        response.addCookie(receiverCookie);

        RequestDispatcher rd = request.getRequestDispatcher("transaction.jsp");
        rd.forward(request, response);
    }

}
