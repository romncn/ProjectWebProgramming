<%-- 
    Document   : RedProducts
    Created on : Nov 14, 2018, 3:30:15 PM
    Author     : Miki
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/allpage.css">
        <title>JSP Page</title>
    </head>
<style>
        .block-filter input[type=text]{
            width: 60%;
            padding: 5px 10px;
            font-size: 20px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }.block-filter select{
            width: 40%;
            padding: 5px 10px;
            font-size: 20px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
    </style>
    <body>
<a href="HomeProducts"><img class="img-logo" src="img/logo-01.png"></a>
            <% if (session.getAttribute("account") == null) {%>         
        <img class="img-sign" src="img/botton-01.png" onclick="document.getElementById('myModal01').style.display = 'block'" style="cursor: pointer">
        <% } else { %>
        <form action="Logout" method="POST">
            <input type="hidden" name="path" value="/RedProducts">
            <a href="Logout"><input type="image" class="img-sign" src="img/botton-02.png"  style="cursor: pointer"></a>
        </form>
        <% }%>
        <!-- Model --> 
        <div class="signmodal fadeSi" id="myModal01" role="dialog" style="display: none;">
            <div class="signmodal-dialog">
                <div class="signmodal-content">
                    <div class="signmodal-header"> 
                        <h4 class="signmodal-title">World Wine</h4>
                        <button type="button" class="close" data-dismiss="modal" onclick="document.getElementById('myModal01').style.display = 'none'">&times;</button>
                    </div>
                    <form action="Login" method="POST">
                        <div class="signmodal-body">

                            username 
                            <br><input type="text" name="username" size="40" required>
                            <br>
                            password 
                            <br><input type="password" name="password" size="40" required>

                            <span style="font-size: 21px;margin-left: 45px;">If you didn't have an account? &nbsp;<a href="SignUpPage" style="color: #848fa5;">Sign up</a>
                            </span>
                            <span style="font-size: 20px;text-align: center;color: coral;margin-left: 55px;">${errorIn}</span>
                        </div>
                        <div class="signmodal-footer">
                            <input type="hidden" name="path" value="/RedProducts">
                            <input type="submit" name="submit" class="butn signin-button" data-dismiss="modal" value="Sign In">
                        </div>
                    </form>
                </div> 
            </div>
        </div>

        <!--navbar-->
        <div class="header">   
            <ul>
                <li class="nav-item">
                    <a class="nav-link">&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="HomeProducts">Home</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="AllProducts">Product</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="MyAccount">MyAccount</a></li>
            </ul> 
        </div>
    <center><div class="quote-wine">"WorldWine เพราะโลกของไวน์คือโลกของเรา"</center>
<br>
<br>

<!--Body-->
<div class="before-container">
    <span style="
    font-size: 22px;
    color: #02111B;
/*    background-color: #02111b;*/
    padding: 5px;
    border-radius: 4px;">
        <c:choose>
            <c:when test="${sessionScope.account != null}">
                Hello ${sessionScope.account.firstname}
        </c:when>
        <c:otherwise>
                Hello Guest
        </c:otherwise>
        </c:choose>
    </span>
    <span style="
          font-size: 22px;
          margin-left: 1000px;">
      <%--<c:if test="${cart != null}">--%>
                <a href="ShowCartPage">Your Cart: ${cart.totalQuantity}</a>
            <%--</c:if>--%>
    </span>
</div>
<div class="col-12">
    <div class="row-text-center">
        <form action="RedProducts" method="post">
            <input type="hidden" value="RED" name="type">
            <input type="image" src ="imgWine/wine/p22.png" width="100%" >
        </form>      
    </div>
</div>

<!--Product & Filter-->
<div class="container">
    <div class="col-12">
        <div class="row">
            <div class="col-4">                
                <h2>Filter</h2>
                <form action="Filter" method="post">
                    <div class="block-filter">
                    <input type="text" placeholder="Name wine" name="name">
                    <br>
                    <br>
                    <select name="type">
                        <option value="TypeWine">Type wine</option>
                        <option value="all">ALL</option>
                        <option value="red">RED</option>
                        <option value="rose">ROSE</option>
                        <option value="white">WHITE</option>
                        <option value="sparkling">SPARKLING</option>
                    </select>
                    <br>
                    <br>
                    <!--input type="text"  placeholder="Year" name="year">-->
                    <select name="year">
                        <option value="year">year</option>
                        <option value="2012">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                        <option value="2018">2018</option>
                    </select>
                    <br>
                    <br>
                    <select name="price">
                        <option value="price">Price</option>
                        <option value="1">399 - 499</option>
                        <option value="2">499 - 599</option>
                        <option value="3">599 - 699</option>
                        <option value="4">699 - 799</option>
                        <option value="5">799 - 899</option>
                        <option value="6">899 - 999</option>
                        <option value="7">999+</option>
                        <option value="8">1999+</option>
                    </select>
                    <br>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="butn" value="search" value="search" style=" padding: 3.2px 5px;margin-left: -20px;"> 
                </div>
                    </form>
                <br>
                <br>

            </div>
            <div class="col-8">
                <div class="col-12">
                    <div class="row">
                        <c:forEach items="${Product}" var="p" varStatus="vs">
                            <div class="col-6 p-3">
                                <a href="ShowProducts?namechoose=${p.productcode}" style="text-decoration: none;color: #02111b">
                                <div style="background-color: white"> 
                                    <div class="col-12">
                                        <div class="row">
                                            <div class="col-6" >
                                                <p><img src="imgWine/wine/${p.productcode}.jpg" width="200" border="1"></p>
                                            </div>
                                            <div class="col-6">                                      
                                                <p>${p.productname}</p>
                                                <p>${p.type}</p>
                                                <p>฿ ${p.price}</p>
                                                <form action="AddItemToCart" method="post">
                                                    <input type="hidden" value="/RedProducts" name="pathproduct">
                                                    <input type="hidden" value="${p.productcode}" name="add">
                                                    <p><input type="submit" class="butn" value="Add to cart" style=" padding: 3.2px 5px;"/></p>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- Footer -->
<div class="footer-contact">
    <h1>Contact Us</h1>
    <div class="col-contact">
        <div class="location-footer">
            <img class="img-footer" src="img/location-logo.png">
            <span style="font-size: 22px;">CosmoMansion Prachautid 45</span></div>
        <div class="phone-footer">
            <img class="img-footer" src="img/phone-logo.png">
            <span style="font-size: 22px;">081-234567</span></div>
    </div>

    <div class="col-contact">
        <div class="facebook-footer">
            <img class="img-footer" src="img/facebook-logo.png">
            <a href="https://www.facebook.com/natchanon.niwedtongrirk">
                <h2>Natchanon N.</h2></a>
            <a href="https://www.facebook.com/parupugunz" >
                <h2>Chutikan K.</h2></a>
            <a href="https://www.facebook.com/fluke.n.bigroos">
                <h2>Nattapong M.</h2></a>
        </div>
    </div>
</div>


<script>
    // Get the modal
    var modal = document.getElementById('myModal01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>
