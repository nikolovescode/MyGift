<%-- 
    Document   : transaction
    Created on : Dec 28, 2018, 6:16:27 PM
    Author     : nikolaj, Victor

Description of file: Sida för att ta emot order om att gåva ska skickas till vän, vilken senare skickas vidare
till db, via en servlet som sedan ger ordern till db via en bean.
Användare ska här ange vilket alias som denna ska skicka pengar som, och hur mycket pengar som skickas.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                        <link rel="stylesheet" type="text/css" href="styles.css">
          <ul>
            <ul><a href="menucustomer.jsp">Menu</a></ul>
            <ul><a href="logout.jsp">Log out</a></ul>
        </ul>
        <title>Send gift to friend</title>
    </head>
    <body>
        <header>
            <h1>Send gift to friend</h1>
        

                              <form action="TransactionServlet" method="POST">
<%
            String t = request.getParameter("taker");
            String user = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains("user")) {
                        user = cookie.getValue();

                        if (t != null && user != null) {
                                          out.println("<input type=\"text\" name=\"friend\" value=\""+t+"\" /><br>");

                            out.println("<h3>Receiver "+t+" for logged in user "+user+"</h3>");

                   

                        }

                    }
                }
            }


        %>

              <label>Amount</label><br>
              <input type="text" name="amount" value="" /><br>
              <label>Alias</label><br>
              <input type="text" name="alias" value="" /><br>
              <input type="submit" value="Ok" name="submit" />
          </form>
        </header>

    </body>
</html>
