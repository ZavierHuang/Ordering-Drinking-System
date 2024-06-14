<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
    <head>
        <title><<TWO SHOTS>></title>
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <script src="lib/jquery.min.js"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/Header.js"></script>
    </head>
    <header>
        <div align="center">
            <h1 class="header">
                &emsp; 歡迎, ${sessionScope.account}
                <button onclick="window.location.href = 'Member';">會員介面</button>
                <button onclick="window.location.href = 'CustomerMenu';">主頁</button>
                <button onclick=logout()>登出</button>
            </h1>
        </div>
    </header>




