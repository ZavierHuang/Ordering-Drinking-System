<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <link href="img/pic05.ico" rel="shortcut icon"/>
    <title><<TWO SHOTS>></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="css/admin.css" />
    <noscript><link rel="stylesheet" href="css/noscript.css" /></noscript>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
                        歡迎來到員工介面, ${sessionScope.account}
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
                <h2>為你提供完整的訂單管理方案</h2>
            </div>
        </div>

        <h1>管理平台</h1>
        <nav>
            <ul>
                <li><a href="CustomerMenu" style="font-size:30px;">訂單管理</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->

    <div id="main">
    </div>

    <footer id="footer">
        <div id="wrapper2">
            <h1>All Order</h1>
            <div class="block">
                <span id="allOrderJson" style="display: none;">${allOrderJson}</span>
                <table id="allOrderTable">
                </table>
            </div>

            <span>
                <span id="amount" style="display: none;">${ordersSum}</span>
                <h3 style="font-size: 150%;">Total: ${ordersSum}</h3>
            </span>
        </div>
    </footer>
</div>


<!-- Edit Employee Modal -->
<div class="modal fade" id="editOrderModal" tabindex="-1" aria-labelledby="editOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editOrderModalLabel">編輯訂單狀態</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editOrderForm">
                    <div class="form-group">
                        <label for="editOrderID">訂單編號</label>
                        <input type="text" class="form-control" id="editOrderID" readonly>
                    </div>
                    <div class="form-group">
                        <label for="editCustomer">顧客</label>
                        <input type="text" class="form-control" id="editCustomer" readonly>
                    </div>
                    <div class="form-group">
                        <label for="editDate">時間</label>
                        <input type="text" class="form-control" id="editDate" readonly>
                    </div>
                    <div class="form-group">
                        <label for="editProduct">訂單內容</label>
                        <table id="editProduct" class="table table-bordered" style="text-align: center" >
                            <thead id="orderProductHeader" style="font-size: 16px">
                            <tr class="thead-dark">
                                <th >品名</th>
                                <th>Size</th>
                                <th>價格</th>
                                <th>冰塊</th>
                                <th>甜度</th>
                                <th>份數</th>
                            </tr>
                            <tbody id="orderProductBody" style="font-size: 16px"></tbody>
                        </table>
                    </div>
                    <div class="form-group" id="totalPrice"  style="text-align: right;color: black">
                        Total:0
                    </div>
                    <div class="form-group">
                        <label for="editStatus">狀態</label>
                        <select id="editStatus">
                            <option value="0">訂單已送出</option>
                            <option value="1">訂單製作中</option>
                            <option value="2">訂單已完成</option>
                            <option value="-1">訂單已取消</option>
                        </select>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveOrder()">保存</button>
            </div>
        </div>
    </div>
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
<script src="js/employee.js"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
