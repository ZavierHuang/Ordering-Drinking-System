<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/Login.js"></script>

</head>
<body>
<div class="bg-img">
    <div class="content">
        <header>會員登入</header>
        <form>
            <div class="field">
                <span class="fa fa-user"></span>
                <input type="text" id="account" required placeholder="Username">
            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="password" id="password" class="pass-key" required placeholder="Password">
            </div>
            <br>
            <div>
                <button type="button" onclick="login()" value="LOGIN"
                style="text-align:center;
                        padding: 15px 100px;
                        background-color: #0c99c5;
                        border-radius: 10px;
                        color: #ffffff;
                        font-size: 15px;
                        border: 1px solid #847d7d;
                        cursor: pointer;">登入</button>
            </div>
            <div style="text-align:center;margin-top: 25px;">
                <a href="Register" style="padding: 16px 83px;
                                            text-decoration: none;
                                            background-color: #3f4344;
                                            border-radius: 10px;
                                            color: #ffffff;
                                            font-size: 15px;
                                            border: 1px solid #847d7d;">立即註冊</a>
            </div>
        </form>

    </div>
</div>


</body>
</html>



