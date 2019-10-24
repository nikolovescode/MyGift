<%-- 
    Document   : transaction
    Created on : Dec 28, 2018, 6:16:27 PM
    Author     : nikolaj, Victor

Description of file: Sida för att ge en gåva till en vän som inte sedan tidigare är registrerad på webbtjänsten.
Mail kommer att skickas till denna vän, med en länk till sidan och ett lösenord för att hämta ut sin gåva.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles.css">

        <title>Gift to a new user</title>
        <ul>
            <ul><a href="logout.jsp">Log out</a></ul>
            
        </ul>
    </head>
    <body>
        

        <header>
            <h1>Gift to a new user</h1>
          <form action="NewFriendTransactionServlet" method="POST">
              <label>Your friends email</label><br>
              <input type="text" name="emailReceiver" value="" /><br>
              <label>Amount to give</label><br>
              <input type="text" name="amount" value="" /><br>
              <label>Your alias</label><br>
              <input type="text" name="alias" value="" /><br><br>
              <input type="submit" value="Give" name="submit" />
        </form>
          <% //out.println("<b>"+request.getAttribute("message") + "</b>"); %>
        </header>

    </body>
</html>
