<%-- 
    Document   : menuadmin
    Created on : Jan 12, 2019, 9:29:23 PM
    Author     : nikolaj

Description of file: Sida för att visa meny för admin.
Länkar till logg över alla gåvor som har skickats över webbtjänster (summa, avsändare och mottagare), 
och för att ta emot gåvor.
Visar även hur mycket pengar som admin har på sitt eget konto.
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
              
            <ul><a href="index.html">Main</a></ul>
            <ul><a href="logout.jsp">Log out</a></ul>
        </ul>
        <title>Menu Admin</title>
    </head>
    <body>
        <header>
            <h1>Menu Admin</h1>
        <%
            AdminTransactionBean agb = new AdminTransactionBean();
            agb.init();
                   Scanner scanner = new Scanner(new File("loggedIn.txt"), "UTF-8");
        String user = scanner.useDelimiter("\\A").next();
        scanner.close(); 
            float accountValue = agb.getAccountValueStatement(user);
            
            out.println(String.format("Money on you account %s<br><br>", String.valueOf(accountValue)));

            out.println("<br><br>");

                
            
            
            
            %>
        
       
            <a href="transactionlist.jsp">View list of all transactions</a><br><br>
            <a href="giftlist.jsp">Cash in gifts</a>

        </header>

    </body>
</html>

