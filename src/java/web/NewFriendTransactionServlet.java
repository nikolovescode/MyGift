/*
Används då en användare som sedan tidigare inte har varit registrerad ska få en gåva.
Då genreras ett slumplösenord till denna användare, och en e-post med lösenord
och uppgift om gåva skickas till denna nya användare.
 */
package web;

import bean.AdminTransactionBean;
import bean.LoginBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nikolaj
 */
@WebServlet(name = "NewFriendTransactionServlet", urlPatterns = {"/NewFriendTransactionServlet"})
public class NewFriendTransactionServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String friend = null;
            friend = request.getParameter("emailReceiver");
            String amount = null;
            amount = request.getParameter("amount");
            String alias = null;
            alias = request.getParameter("alias");
            System.out.println("Give to " + friend);

            if ((amount != null && !amount.isEmpty()) && (alias != null && !alias.isEmpty())) {
                
                LoginBean lb = new LoginBean();
                lb.init();
                String password = lb.getPassword(friend);
                lb.closeConnection();
                callAdminTransactionNewUserBean(friend, amount, alias);
                callAdminTransactionExistingUserBean(friend, String.valueOf(amount), alias);
                
                
                MailService ms = new MailService();
                ms.setUSER_NAME("aneventapplication@gmail.com");
                ms.setPASSWORD("ab1234cd");
                ms.setRECIPIENT(friend);
                ms.setSubject("Your receipt");
                ms.setBody("You have a gift at MyGift.com! Use your password "+password+" to login to http://localhost:8080/MyGift/login.jsp");
                ms.sendReceipt();
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");

                request.setAttribute("message", "Money transfered to your friend");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                request.setAttribute("message", "Transfer did not go through");
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int callAdminTransactionNewUserBean(String email, String amount, String alias) {
        Random rn = new Random();

        AdminTransactionBean arb = new AdminTransactionBean(); //(TeacherInforRemRemote) Naming.lookup ("ava:global/CourseEJB/beans/TeacherInfoRem");
        arb.init();
        int password = rn.nextInt(1000);
        arb.insertNewUserStatement(email, Float.valueOf(amount), password, false, false, alias);
        arb.closeConnection();
        
        return password;
    }
        private void callAdminTransactionExistingUserBean(String emailReceiver, String amount, String alias) {
        Random rn = new Random();

        AdminTransactionBean arb = new AdminTransactionBean(); 
        arb.init();
        float giftAmount = Float.valueOf(amount);
        int password = rn.nextInt(1000);
        arb.insertExistingUserStatement(emailReceiver, giftAmount, alias);
        arb.closeConnection();
    }
}
