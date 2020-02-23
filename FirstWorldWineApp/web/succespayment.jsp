<%-- 
    Document   : succespayment
    Created on : Nov 14, 2018, 8:10:01 PM
    Author     : Chutikan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>payment</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <meta name="viewport" content="width=device-width, initial-scale=1">-->
  <link rel="stylesheet" href="css/paymentstyle.css">
    <link rel="stylesheet" href="css/allpage.css">
    <link rel="stylesheet" href="css/container.css">
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
        

  <!--container-->
  <div class="container">
      <div class="form-payment">  
      <div class="succes"><h1>สั่งซื้อสำเร็จ !</h1></div>
      <div class="succesdetail"><h3>ทางระบบจะจัดส่งสินค้าให้ในวันถัดไป</h3></div>
      <div class="succesdetail"><h3>ขอให้คุณ Enjoy กับโลกของเรา :)</h3></div>
      
<a href="HomeProducts"><input type="button" class="butn editbtncenter" value="กลับสู่หน้าหลัก"/></a>
      </div>
  </div>
<br>
<br>
<br>
</form>

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




    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>


