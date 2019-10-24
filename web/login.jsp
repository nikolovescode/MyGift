<%-- 
    Document   : login
    Created on : Dec 17, 2018, 9:05:29 AM
    Author     : nikolaj, Victor

Description of file: Sida för att admin och kund ska kunna logga in.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                        <link rel="stylesheet" type="text/css" href="styles.css">
          <ul>
            <ul><a href="index.html">Main</a></ul>
            
        </ul>
        <title>MyGift Login Page</title>
    </head>
    <body>
        <header>
            <h1>MyGift Login</h1>
          <form action="LoginServlet" method="POST">
              <label>Login</label><br>
              <input type="text" name="login" value="" /><br>
              <label>Password</label><br>
              <input type="text" name="password" value="" /><br>
              <input type="submit" value="Login" name="submit" />
          </form>
        </header>

    </body>
</html>
