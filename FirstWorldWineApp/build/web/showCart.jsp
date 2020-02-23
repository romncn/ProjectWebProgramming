<%-- 
    Document   : showCart
    Created on : Nov 15, 2018, 8:52:56 PM
    Author     : Chutikan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/paymentstyle.css">
        <link rel="stylesheet" href="css/allpage.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <title>Your Cart</title>
    </head>
    <body>
        <a href="HomeProducts"><img class="img-logo" src="img/logo-01.png"></a>
            <% if (session.getAttribute("account") == null) {%>         
        <img class="img-sign" src="img/botton-01.png" onclick="document.getElementById('myModal01').style.display = 'block'" style="cursor: pointer">
        <% } else { %>
        <form action="Logout" method="POST">
            <input type="hidden" name="path" value="/HomeProducts">
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
                        </div>
                        <div class="signmodal-footer">
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
    <div class="container">
        <h1>Cart List : </h1>
        <script>
            console.log(${cart.lineItems});
        </script>
        ${line.product.productname}
        <div class="container">
            <div class="col-12 row">
                <div class="col">No</div>
                <div class="col">Product Code</div>
                <div class="col">Product Name</div>
                <div class="col">Units of Product</div>
                <div class="col">Price</div>
                <div class="col"></div>
                <div class="col"></div>
                <div class="col"></div>
            </div>

            <c:forEach items="${cart.lineItems}" var="p" varStatus="vs"> 
                <div class="col-12 row">
                    <div class="col">${vs.count}</div>
                    <div class="col">${p.product.productcode}</div>
                    <div class="col">${p.product.productname}</div>
                     <div class="col">${p.quantity}</div>
                     <div class="col">${p.totalPrice}</div>   
                     <div class="col">       
                        <form action="UpdateToCart" method="post">
                            <input type="hidden" value="${p.product.productcode}" name="minus">
                           <input type="image" class="imagepayment" src="img/remove.png"  style="cursor: pointer; 
                                  width: 14px; height: auto;">
                        </form></div>
                           <div class="col">
                        <form action="UpdateToCart" method="post">
                            <input type="hidden" value="${p.product.productcode}" name="plus">
                            <input type="image" class="imagepayment" src="img/add.png"  style="cursor: pointer; 
                                  width: 14px; height: auto;">
                        </form></div>
                    <div class="col">
                        <form action="DeleteItemToCart" method="post">
                            <input type="hidden" value="${p.product.productcode}" name="delete">
                            <input type="image" class="imagepayment" src="img/delete.png"  style="cursor: pointer; 
                                  width: 25px; height: auto;">
                        </form>
                    </div>
                </div>
                           
            </c:forEach>
             Total price : ${cart.totalPrice2}
            <form action="PaymentPage1" method="post">
                <input type="submit" class="butn editbtn" value="Payment" />
            </form>
        </div>
    </div>
    <p></p>
    <br>
    <br>

    <br>



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