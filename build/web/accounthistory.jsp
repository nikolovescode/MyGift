<%-- 
    Document   : accounthistory
    Created on : Jan 10, 2019, 4:53:02 PM
    Author     : nikolaj, Victor

Description of file: Sida för att visa lista över gåvor till inloggad användare.
Kollar först vilken användare som är inloggad enligt cookie.
Presenterar sedan resultat från db (via bean), om vilka gåvor som getts till denna användare.
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="bean.AdminGiftBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="styles.css">

                <ul>
            <ul><a href="logout.jsp">Log out</a></ul>
            <ul><a href="menucustomer.jsp">Menu</a></ul>

                </ul>
        <title>Account History</title>

    </head>
    <body>
        <header>
            <h1>Account History</h1>
        <%
            String email = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    
                    if (cookie.getName().contains("user")) {
                        email =  cookie.getValue();
            AdminGiftBean agb = new AdminGiftBean();
            agb.init();
            ArrayList<String> list = agb.getTransactionsHistory(email);
            agb.closeConnection();
            
            for(int i = 0; i < list.size(); i++){
                out.println(list.get(i));
                out.println("<br><br>");
            }

                    

                }
            }
            }
            
            %>
               

        </header>

        </body>
</html>
