package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class transaction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\n");
      out.write("                        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n");
      out.write("          <ul>\n");
      out.write("            <ul><a href=\"index.html\">Main</a></ul>\n");
      out.write("            <ul><a href=\"logout.jsp\">Log out</a></ul>\n");
      out.write("        </ul>\n");
      out.write("        <title>Send gift to friend</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <h1>Send gift to friend</h1>\n");
      out.write("        \n");
      out.write("\n");
      out.write("                              <form action=\"TransactionServlet\" method=\"POST\">\n");

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

            /* TODO output your page here. You may use following sample code. */

        
      out.write("\n");
      out.write("\n");
      out.write("              <label>Amount</label><br>\n");
      out.write("              <input type=\"text\" name=\"amount\" value=\"\" /><br>\n");
      out.write("              <label>Alias</label><br>\n");
      out.write("              <input type=\"text\" name=\"alias\" value=\"\" /><br>\n");
      out.write("              <input type=\"submit\" value=\"Ok\" name=\"submit\" />\n");
      out.write("          </form>\n");
      out.write("        </header>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
