<%-- 
    Document   : logout
    Created on : Jan 9, 2019, 9:09:05 PM
    Author     : nikolaj, Victor

Description of file: Sida för att admin och kund ska kunna logga ut. 
Cookie med uppgift om inloggad användare töms.
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
        <title>Log out</title>
    </head>
    <body>
        <header>
            <h1>You have been logged out!</h1>
        <%

    	Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    	             if (cookie.getName().contains("user")) {
                        Cookie loginCookie = new Cookie(cookie.getName(), "");
                        //setting cookie to expiry in 30 mins
                        loginCookie.setMaxAge(0);
                        response.addCookie(loginCookie);

                    }
       }
        }
            %>

        </header>

    </body>
</html>
