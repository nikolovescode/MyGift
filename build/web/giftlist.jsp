<%-- 
    Document   : eventlist
    Created on : Jan 1, 2019, 11:07:32 AM
    Author     : nikolaj, Victor

Description of file: Sida som hämtar från databas till användare om denna har fått några gåvor.
Gåvorna visas i form av länkar, med uppgift om sändare av gåva och hur mycket pengar som gåvan ligger på.
Om man klickar på en länk så skickas ett meddelande till chosengift.jsp om att transaktion ska genomföras.
--%>

<%@page import="web.Gift"%>
<%@page import="bean.GiftsBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="event" class="bean.GiftsBean"/>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                        <link rel="stylesheet" type="text/css" href="styles.css">

        <title>Gifts</title>
          <ul>
            <ul><a href="logout.jsp">Log out</a></ul>
            
        </ul>
    </head>
<header>
            <h1>Gift list</h1>
  


    <%


        try {
            GiftsBean e = new GiftsBean();
            List<Gift> list = event.getData();
            out.println("<body>");
            out.println("<h3>Got any gifts?</h3>");

            out.println("</body>");
           
 
            
            for (int i = 0; i < list.size(); i++) {

                out.println("<td>");
                out.println("<span>");
                out.println("<a href=\"chosengift.jsp?giver=" + (list.get(i).getGiver()) + "&id=" + list.get(i).getId() + "&amount=" + (list.get(i).getAmount()) + "\">" + String.format(list.get(i).getAmount() + " from " + list.get(i).getGiver()) + "</a>");
                out.println("</span>");
                out.println("</td><br>");

            }

        } catch (IndexOutOfBoundsException e) {
            out.println("<body>");

            out.println("<td>");
            out.println("<span>");
            out.println("<h1>No records available</h1>");
            out.println("</span>");
            out.println("</td><br>");
            out.println("</header>");
            out.println("</body>");

        }catch(Exception e){
              out.println("<body>");

            out.println("<td>");
            out.println("<span>");
            out.println("<h1>No records available</h1>");
            out.println("</span>");
            out.println("</td><br>");
            }
    %>  
    </header>
            </body>

</html>




