/*
Får order från jsp om att en användare/friend ska få en gåva, denna order skickas vidare till transaction.jsp
för att ordern ska genomföras.
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
@WebServlet(name = "FriendServlet", urlPatterns = {"/FriendServlet"})
public class FriendServlet extends HttpServlet {

    String login;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String chosenUser = (String) request.getAttribute("item");
        request.setAttribute("friend", chosenUser);
        request.getRequestDispatcher("transaction.jsp").forward(request, response);

    }

}
