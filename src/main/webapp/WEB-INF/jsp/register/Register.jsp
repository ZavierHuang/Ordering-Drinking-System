<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/Register.js"></script>

</head>
<body>
<div class="bg-img">
    <div class="content">
<%--        <header>會員註冊</header>--%>
    <c:choose>
        <c:when test="${isEmployee == 'N'}">
            <header>會員註冊</header>
        </c:when>
        <c:when test="${isEmployee == 'Y'}">
            <header>註冊員工資料</header>
        </c:when>
    </c:choose>


        <form>
            <input type="hidden" id="isEmployee" value="${isEmployee}" />

            <div class="field">
                <span class="fa fa-user"></span>
                <input type="text" id="r_account" required placeholder="Username">
            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="password" id="r_password" class="pass-key" required placeholder="Password">

            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="password" id="r_password_check" class="pass-key" required placeholder="Confirm Password">

            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="tel" id="r_tel" class="pass-key" required placeholder="Phone number">

            </div>
            <br/>
            <div style="text-align:center;">
                <button type="button" onclick="register()" value="Sign_up"
                        style="text-align:center;
                                padding: 15px 100px;
                                background-color: #3f4344;
                                border-radius: 10px;
                                color: #ffffff;
                                font-size: 15px;
                                border: 1px solid #847d7d;
                                cursor: pointer;" >立即註冊</button>
            </div>
        </form>
        <div class="signup"></div>
    </div>
</div>


</body>
</html>



