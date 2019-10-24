<%-- 
    Document   : menucustomer
    Created on : Jan 9, 2019, 10:05:24 PM
    Author     : nikolaj

Description of file: Sida för att visa meny för kund.

Visar hur mycket som finns på användarens konto från db (via bean).
Länkar till: Ta emot gåva, se historik över gåvor till denna kund, och till att ge en gåva till en vän.
--%>

<%@page import="java.io.File"%>
<%@page import="java.util.Scanner"%>
<%@page import="bean.AdminTransactionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>,
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                        <link rel="stylesheet" type="text/css" href="styles.css">
          <ul>
            <ul><a href="logout.jsp">Log out</a></ul>
        </ul>
        <title>Menu Customer</title>
    </head>
    <body>
        <header>
            <h1>Menu Customer</h1>
        <%
AdminTransactionBean agb = new AdminTransactionBean();
            agb.init();
                   Scanner scanner = new Scanner(new File("loggedIn.txt"), "UTF-8");
        String user = scanner.useDelimiter("\\A").next();
        scanner.close(); 
            float accountValue = agb.getAccountValueStatement(user);
            
            out.println(String.format("Money on you account %s<br><br>", String.valueOf(accountValue)));

                    

            
            %>
        
            <a href="accounthistory.jsp">View history of gifts given to you</a><br><br>
            <a href="friendlist.jsp">Give a gift to a friend</a><br><br>
            <a href="giftlist.jsp">Cash in gifts</a><br><br>
      
        </header>

    </body>
</html>
