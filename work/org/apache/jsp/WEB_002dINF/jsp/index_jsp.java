/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.52
 * Generated at: 2015-08-21 02:25:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Index Page</title>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tExt.onReady(function(){\r\n");
      out.write("\t\tExt.create('Ext.panel.Panel', {\r\n");
      out.write("\t\t    title: 'Hello',\r\n");
      out.write("\t\t    html: '<p>Index page!</p>',\r\n");
      out.write("\t\t  //  renderTo: Ext.getBody()\r\n");
      out.write("\t\t    renderTo: 'ext'\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tFusionCharts.ready(function(){\r\n");
      out.write("\t\tvar revenueChart = new FusionCharts({\r\n");
      out.write("\t\t\ttype: \"column2d\",\r\n");
      out.write("\t\t\trenderAt: \"chartContainer\",\r\n");
      out.write("\t\t\twidth: \"500\",\r\n");
      out.write("\t\t\theight: \"300\",\r\n");
      out.write("\t\t\tdataFormat: \"json\",\r\n");
      out.write("\t\t\tdataSource: {\r\n");
      out.write("\t\t\t\t\"chart\": {\r\n");
      out.write("\t\t\t\t  \"caption\": \"Monthly revenue for last year\",\r\n");
      out.write("\t\t\t\t  \"subCaption\": \"Harry's SuperMart\",\r\n");
      out.write("\t\t\t\t  \"xAxisName\": \"Month\",\r\n");
      out.write("\t\t\t\t  \"yAxisName\": \"Revenues (In USD)\",\r\n");
      out.write("\t\t\t\t  \"theme\": \"zune\"\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t\"data\": [\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Jan\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"420000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Feb\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"810000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Mar\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"720000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Apr\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"550000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"May\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"910000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Jun\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"510000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Jul\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"680000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Aug\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"620000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Sep\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"610000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Oct\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"490000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Nov\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"900000\"\r\n");
      out.write("\t\t\t\t  },\r\n");
      out.write("\t\t\t\t  {\r\n");
      out.write("\t\t\t\t\t \"label\": \"Dec\",\r\n");
      out.write("\t\t\t\t\t \"value\": \"730000\"\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t\t]\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\trevenueChart.render(\"chartContainer\");\r\n");
      out.write("\t}); \r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id='ext'></div>\r\n");
      out.write("\t<div id=\"chartContainer\">FusionCharts XT will load here!</div>\r\n");
      out.write("\t<div id=\"chartContainer2\">FusionCharts 22 XT will load here!</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
