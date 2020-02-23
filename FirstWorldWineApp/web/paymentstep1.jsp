<%-- 
    Document   : paymentstep1
    Created on : Nov 14, 2018, 5:29:38 PM
    Author     : Chutikan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>payment</title>
  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  <meta name="viewport" content="width=device-width, initial-scale=1">-->
        <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
  <link rel="stylesheet" href="css/paymentstyle.css">
  <link rel="stylesheet" href="css/allpage.css">
  <link rel="stylesheet" href="css/bootstrap.css">
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
                            <span style="font-size: 20px;text-align: center;color: coral;margin-left: 55px;">${errorIn}</span>
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
       <div class="content-header"><h1>Payment<h1></div><br>
  
             <form action="PaymentStep1" method="post">

      <div class="step-payment">
      <span class="step-paymenton">ช่องทางการชำระเงิน</span>
         <span class="step-paymentoff">ที่อยู่ในการจัดส่ง</span>
         <span class="step-paymentoff">สรุปการสั่งซื้อ</span>
         <br>
         <br>
</div>
 <div class="form-payment form-paymentedit"> 
      <label for="methodpayment">ชำระเงินด้วย</label>
      <select class="form-control"  name="paymentmethod" id="methodpayment" required>
          <option value="Debit card">Debit card</option>
          <option value="Credit Card">Credit Card</option>
      </select><br>
      <label>หมายเลขบัตร</label><br>
         <input type="text" class="form-control" name="numberpayment" id="numberpayment" minlength="16" maxlength="16"  required><br>
      <label>ชื่อผู้ถือบัตร</label><br>
         <input type="text" class="form-control" name="nameincard" id="nameincard" required><br>
      <label>รหัส cvv</label>
         <input type="text" class="form-control" name="cvv" id="cvv" minlength="3" maxlength="3" style="width: 20%;margin-left: 20px;" required>
         <br> 
</div>
<input type="submit" class="butn editbtn" value="ถัดไป" />
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
