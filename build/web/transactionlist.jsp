<%-- 
    Document   : transactionlist
    Created on : Jan 1, 2019, 8:11:50 PM
    Author     : nikolaj, Victor

Description of file: Sida för att visa en lista över alla transaktioner som har skett på sidan.
Hämtar uppgift från db (via bean).
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="bean.AdminGiftBean"%>
<%@page import="bean.AdminTransactionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                        <link rel="stylesheet" type="text/css" href="styles.css">
          <ul>
            <ul><a href="index.html">Main</a></ul>
            <ul><a href="logout.jsp">Log out</a></ul>
            <ul><a href="menuadmin.jsp">Back to Admin Menu</a></ul>
        </ul>
        <title>Transaction list</title>
    </head>
    <body>
        <header>
            <h1>List of Transactions</h1>
            
                


   
<%  

AdminGiftBean e = new AdminGiftBean();
e.init();
ArrayList<String> tList = e.getTransactionsStatement();
e.closeConnection();


for(int i = 0; i < tList.size(); i++){
    out.print("<p>");
    out.print(tList.get(i));
    out.print("</p>");
}

%>  

        </header>

    </body>
</html>
