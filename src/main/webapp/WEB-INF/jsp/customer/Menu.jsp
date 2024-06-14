<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <link href="img/pic05.ico" rel="shortcut icon"/>
    <title><<TWO SHOTS>></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="css/main.css" />
    <noscript><link rel="stylesheet" href="css/noscript.css" /></noscript>
</head>
<body class="is-preload">

<!-- Wrapper -->
<div id="wrapper">
    <!-- Header -->
    <header id="header">
            <div style="margin-right:1rem;">
                <center>
                    <h1>
                        <span id="account" style="display: none;">${account}</span>
                        歡迎, ${sessionScope.account}
                        <a href="Member"><button style="font-size: 80%;">我的帳號</button></a>
                        <button onclick="logout()" style="font-size: 80%;">登出</button>
                    </h1>
                </center>
            </div>


        <div class="logo">

            <span><img id = "pic5" src="img/pic05.ico" style="width:80px;height:80px;border-radius:100%;"></span>
        </div>
        <div class="content">
            <div class="inner">
                <h1>WELCOME TO TWO SHOTS</h1>
                <h2>顧客需要的不只是一杯咖啡兩杯濃縮提供高CP值、高品質</h2>
                <h2>堅持最好的咖啡口感呈現原物料的選擇上更是精挑細選</h2>
            </div>
        </div>

        <h1>菜單 Menu</h1>
        <nav>
            <ul>
                <li><a href="Tea" style="font-size:30px;">茶類</a></li>
                <li><a href="Milk" style="font-size:30px;">牛奶</a></li>
                <li><a href="Latte" style="font-size:30px;">拿鐵</a></li>
                <li><a href="Hand" style="font-size:30px;">手沖</a></li>
                <li><a href="Roasted" style="font-size:30px;">烘培</a></li>
                <li><a href="Coco" style="font-size:30px;">可可</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->

    <div id="main">
    </div>

    <footer id="footer">
        <!--留言板寫在這 -->
        <!-- 從這裡開始放的 -->
        <div id="wrapper2">
            <h1>SHOPPING CART</h1>
                <div class="block">
                    <span id="customerCartJson" style="display: none;">${customerCart}</span>
                    <table id="customerCart">
                        <tr>
                            <th style="width: 23%" align="left">名稱</th>
                            <th style="width: 27%">&ensp;</th>
                            <th>數量</th>
                            <th>金額</th>
                            <th style="width: 15%">&ensp;</th> <!-- ensp 是一半的空格 -->
                        </tr>
                    </table>
                </div>

            <span>
                <span id="amount" style="display: none;">${totalPrice}</span>
                <h3 style="font-size: 150%;">Total: ${totalPrice}</h3>
                <button style="font-size: 25px" onclick="send()">送出訂單</button>
            </span>
        </div>
    </footer>
</div>

<!-- BG -->
<div id="bg"></div>

<!-- Scripts -->
<script src="lib/jquery.min.js"></script>
<script src="lib/browser.min.js"></script>
<script src="lib/breakpoints.min.js"></script>
<script src="js/util.js"></script>
<script src="js/Main.js"></script>
<script src="js/Header.js"></script>
<script src="js/Menu.js"></script>
</body>
</html>
