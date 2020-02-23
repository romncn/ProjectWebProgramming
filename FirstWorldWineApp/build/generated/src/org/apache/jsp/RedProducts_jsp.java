package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class RedProducts_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_varStatus_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_varStatus_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_varStatus_var_items.release();
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/allpage.css\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("<a href=\"HomeProducts\"><img class=\"img-logo\" src=\"img/logo-01.png\"></a>\n");
      out.write("            ");
 if (session.getAttribute("account") == null) {
      out.write("         \n");
      out.write("        <img class=\"img-sign\" src=\"img/botton-01.png\" onclick=\"document.getElementById('myModal01').style.display = 'block'\" style=\"cursor: pointer\">\n");
      out.write("        ");
 } else { 
      out.write("\n");
      out.write("        <form action=\"Logout\" method=\"POST\">\n");
      out.write("            <a href=\"Logout\"><img class=\"img-sign\" src=\"img/botton-02.png\"  style=\"cursor: pointer\"></a>\n");
      out.write("        </form>\n");
      out.write("        ");
 }
      out.write("\n");
      out.write("        <!-- Model --> \n");
      out.write("        <div class=\"signmodal fadeSi\" id=\"myModal01\" role=\"dialog\" style=\"display: none;\">\n");
      out.write("            <div class=\"signmodal-dialog\">\n");
      out.write("                <div class=\"signmodal-content\">\n");
      out.write("                    <div class=\"signmodal-header\"> \n");
      out.write("                        <h4 class=\"signmodal-title\">World Wine</h4>\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" onclick=\"document.getElementById('myModal01').style.display = 'none'\">&times;</button>\n");
      out.write("                    </div>\n");
      out.write("                    <form action=\"Login\" method=\"POST\">\n");
      out.write("                        <div class=\"signmodal-body\">\n");
      out.write("\n");
      out.write("                            username \n");
      out.write("                            <br><input type=\"text\" name=\"username\" size=\"40\" required>\n");
      out.write("                            <br>\n");
      out.write("                            password \n");
      out.write("                            <br><input type=\"password\" name=\"password\" size=\"40\" required>\n");
      out.write("\n");
      out.write("                            <span style=\"font-size: 21px;margin-left: 45px;\">If you didn't have an account? &nbsp;<a href=\"SignUpPage\" style=\"color: #848fa5;\">Sign up</a>\n");
      out.write("                            </span>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"signmodal-footer\">\n");
      out.write("                            <input type=\"hidden\" name=\"path\" value=\"/RedProducts\">\n");
      out.write("                            <input type=\"submit\" name=\"submit\" class=\"butn signin-button\" data-dismiss=\"modal\" value=\"Sign In\">\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div> \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!--navbar-->\n");
      out.write("        <div class=\"header\">   \n");
      out.write("            <ul>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\">&nbsp;&nbsp;&nbsp;&nbsp;</a></li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"HomeProducts\">Home</a></li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"AllProducts\">Product</a></li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"MyAccount\">Suggestion</a></li>\n");
      out.write("            </ul> \n");
      out.write("        </div>\n");
      out.write("    <center><div class=\"quote-wine\">\"WorldWine เพราะโลกของไวน์คือโลกของเรา\"</center>\n");
      out.write("<br>\n");
      out.write("<br>\n");
      out.write("\n");
      out.write("<div class=\"col-12\">\n");
      out.write("    <div class=\"row-text-center\">\n");
      out.write("        <form action=\"HomeProducts\" method=\"post\">\n");
      out.write("            <input type=\"hidden\" value=\"RED\" name=\"type\">\n");
      out.write("            <input type=\"image\" src =\"imgWine/wine/p22.png\" width=\"100%\" >\n");
      out.write("        </form>      \n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!--Product & Filter-->\n");
      out.write("<div class=\"container\">\n");
      out.write("    <div class=\"col-12\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-4\">                \n");
      out.write("                <h2>Filter</h2>\n");
      out.write("                <form action=\"Filter\" method=\"post\">\n");
      out.write("                    <input type=\"text\" placeholder=\"Name wine\" name=\"name\">\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    <select name=\"type\">\n");
      out.write("                        <option value=\"TypeWine\">Type wine</option>\n");
      out.write("                        <option value=\"red\">RED</option>\n");
      out.write("                        <option value=\"rose\">ROSE</option>\n");
      out.write("                        <option value=\"white\">WHITE</option>\n");
      out.write("                        <option value=\"blue\">SPARKLING</option>\n");
      out.write("                    </select>\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    <input type=\"text\"  placeholder=\"Year\" name=\"year\">\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    <select name=\"capacity\">\n");
      out.write("                        <option value=\"capacity\">capacity</option>\n");
      out.write("                        <option value=\"375\">375</option>\n");
      out.write("                        <option value=\"500\">500</option>\n");
      out.write("                        <option value=\"750\">750</option>\n");
      out.write("                        <option value=\"1500\">1200</option>\n");
      out.write("                    </select>\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"search\"> \n");
      out.write("                </form>\n");
      out.write("\n");
      out.write("                <br>\n");
      out.write("                <br>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-8\">\n");
      out.write("                <div class=\"col-12\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Footer -->\n");
      out.write("<div class=\"footer-contact\">\n");
      out.write("    <h1>Contact Us</h1>\n");
      out.write("    <div class=\"col-contact\">\n");
      out.write("        <div class=\"location-footer\">\n");
      out.write("            <img class=\"img-footer\" src=\"img/location-logo.png\">\n");
      out.write("            <span style=\"font-size: 22px;\">CosmoMansion Prachautid 45</span></div>\n");
      out.write("        <div class=\"phone-footer\">\n");
      out.write("            <img class=\"img-footer\" src=\"img/phone-logo.png\">\n");
      out.write("            <span style=\"font-size: 22px;\">081-234567</span></div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"col-contact\">\n");
      out.write("        <div class=\"facebook-footer\">\n");
      out.write("            <img class=\"img-footer\" src=\"img/facebook-logo.png\">\n");
      out.write("            <a href=\"https://www.facebook.com/natchanon.niwedtongrirk\">\n");
      out.write("                <h2>Natchanon N.</h2></a>\n");
      out.write("            <a href=\"https://www.facebook.com/parupugunz\" >\n");
      out.write("                <h2>Chutikan K.</h2></a>\n");
      out.write("            <a href=\"https://www.facebook.com/fluke.n.bigroos\">\n");
      out.write("                <h2>Nattapong M.</h2></a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    // Get the modal\n");
      out.write("    var modal = document.getElementById('myModal01');\n");
      out.write("\n");
      out.write("    // When the user clicks anywhere outside of the modal, close it\n");
      out.write("    window.onclick = function (event) {\n");
      out.write("        if (event.target == modal) {\n");
      out.write("            modal.style.display = \"none\";\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("</body>\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_varStatus_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${Product}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("p");
    _jspx_th_c_forEach_0.setVarStatus("vs");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                            <div class=\"col-6 p-3\">\n");
          out.write("                                <div style=\"background-color: white\"> \n");
          out.write("                                    <div class=\"col-12\">\n");
          out.write("                                        <div class=\"row\">\n");
          out.write("                                            <div class=\"col-6\" >\n");
          out.write("                                                <p><img src=\"imgWine/wine/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${p.productcode}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(".jpg\" width=\"200\" border=\"1\"></p>\n");
          out.write("                                            </div>\n");
          out.write("                                            <div class=\"col-6\">                                      \n");
          out.write("                                                <p>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${p.productname}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</p>\n");
          out.write("                                                <p>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${p.type}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</p>\n");
          out.write("                                                <p>฿ ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${p.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</p>\n");
          out.write("                                                <p><input type=\"button\" value=\"Add to cart\"/></p>\n");
          out.write("                                            </div>\n");
          out.write("                                        </div>\n");
          out.write("                                    </div>\n");
          out.write("                                </div>\n");
          out.write("                            </div>\n");
          out.write("                        ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_varStatus_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
