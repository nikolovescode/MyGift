<%-- 
    Document   : chosengift
    Created on : Jan 7, 2019, 9:32:03 PM
    Author     : nikolaj, Victor

Description of file: Sida som annan jsp skickar till när denna vill registrera en gåva till en vän.
Sida används för att skicka konfirmationsmeddelande till inloggad användare, vilket ses via cookie.

--%>

<%@page import="bean.GiftsBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                <link rel="stylesheet" type="text/css" href="styles.css">
                <ul>
            <ul><a href="logout.jsp">Log out</a></ul>
        </ul>
        <title>Your gift</title>
    </head>
    <body>
        <header>
            <h1>Chosen gift</h1>
        

        <%
            String g = request.getParameter("giver");
            String i = request.getParameter("id");
            String a = request.getParameter("amount");
            String user = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains("user")) {
                        user = cookie.getValue();

                        if (g != null && i != null && user != null & a != null) {
                            out.println("<h3>Chosen giver at " + g + " at id " + i + " for logged in user " + user + " with amount" + a + "</h3>");

                            GiftsBean e = new GiftsBean();
                            e.activateGift(i, user, a);

                        }

                    }
                }
            }

            /* TODO output your page here. You may use following sample code. */

        %>
                

        </header>

    </body>
</html>
