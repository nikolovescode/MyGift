<%-- 
    Document   : eventlist
    Created on : Jan 1, 2019, 11:07:32 AM
    Author     : nikolaj, Victor

Description of file: Sida för att visa lista över användare av denna webbtjänst. Hämtas från db (via bean).
Användare kan välja ett konto att ge sin gåva till, genom att klicka på en länk,
vilket skickar meddelande till transaction.jsp om vilka användare som det gäller.
Därmed skickas en order om att genomföra skickande av gåva.
--%>

<%@page import="web.Friend"%>
<%@page import="bean.FriendsBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="event" class="bean.FriendsBean"/>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>Friends</title>
                <ul>
            <ul><a href="logout.jsp">Log out</a></ul>
            <ul><a href="transactionnewuser.jsp">Give a gift to a new user</a>  </ul>
        </ul>        
    </head>
    <body>
        <header>
            <h1>Pick a friend!</h1>


                <%

                    FriendsBean e = new FriendsBean();
                    List<Friend> list = event.getData();

           for (int i = 0; i < list.size(); i++) {

                out.println("<td>");
                out.println("<span>");
                out.println("<a href=\"transaction.jsp?taker="+list.get(i).getName()+" \">"+list.get(i).getName()+"</a>");
                out.println("</span>");
                out.println("</td><br>");

            }

                %>  
        </body>
         


        </header>

    </body>
</html>

