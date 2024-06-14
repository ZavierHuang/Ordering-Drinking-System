<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <link href="img/pic05.ico" rel="shortcut icon"/>
    <title><<TWO ADMIN>></title>
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
                    ${sessionScope.account} 老闆您好
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
                <h2>為您提供完整的員工管理解決方案。</h2>
            </div>
        </div>

        <h1>管理平台</h1>
        <nav>
            <ul>
                <li><a href="CustomerMenu" style="font-size:30px; text-decoration: none;">首頁</a></li>
                <li><a href="addEmploee" style="font-size:30px; text-decoration: none;">新增員工</a></li>
                <li><a href="manageMenu" style="font-size:30px; text-decoration: none;">管理菜單</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->

    <div id="main">
    </div>

    <footer id="footer">
        <div id="wrapper2">
            <h1>All Menu</h1>
            <div class="menuSearch">

                <input id='searchProductName' type="text" style="width: 18%; height: 60px; margin-left: 40px" class="form-control"/>

                <select id="searchType" class="form-control" style="width: 20%; height: 60px;"></select>

                <input id='searchPrice_M' type="number" min="0" style="width: 10%; height: 60px;" class="form-control" />

                <input id='searchPrice_L' type="number" min="0" style="width: 10%; height: 60px;" class="form-control" />

                <select id="searchStack" class="form-control" style="width: 10%; height: 60px;"></select>

                <select id="searchState" class="form-control" style="width: 12%; height: 60px;"></select>

                <button class="searchButton" style="width: 6%; height: 60px;" onclick="searchMenu()">搜尋</button>

                <button class="clearButton" style="width: 5%; height: 60px; margin-right: 50px; margin-left: 0;" onclick="searchMenuItemInfoInit()"> 清除 </button>


            </div>
            <div class="block">
                <span id="allMenuJson" style="display: none;">${allMenuJson}</span>
                <table id="allMenuTable">
                </table>
            </div>
            <div>
                <span id="allTypeJson" style="display: none;">${allTypeJson}</span>
                <select id="allTypeSelect">
                </select>
            </div>
            <span>
                <span id="amount" style="display: none;">${totalItem}</span>
                <h3 id='totalAmount' style="font-size: 150%;">Total: ${totalItem}</h3>
                <button class="addNewItemButton" onclick="updateMenu()">新增菜單</button>
            </span>
        </div>
    </footer>
</div>


<!-- Edit Menu Modal -->
<div class="modal fade" id="editMenuModal" tabindex="-1" aria-labelledby="editMenuModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editMenuModalLabel">編輯商品資料</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editEmployeeForm">
                    <div class="form-group">
                        <label for="editName">Product Name</label>
                        <input type="text" class="form-control" id="editName">
                    </div>
                    <div class="form-group">
                        <label for="editType">Type</label>
                        <select type="text" class="form-control" id="editType">
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="editPrice_M">Size(M) Price</label>
                        <input type="number" min="0" required class="form-control" id="editPrice_M">
                    </div>
                    <div class="form-group">
                        <label for="editPrice_L">Size(L) Price</label>
                        <input type="number" min="0" required class="form-control" id="editPrice_L">
                    </div>
                    <div class="form-group">
                        <label for="editStack">Stack</label>
                        <input type="number" min="0" required class="form-control" id="editStack">
                    </div>
                    <div class="form-group">
                        <label for="editState">State</label>
                        <select type="text" class="form-control" id="editState"></select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" id="saveButton" class="btn btn-primary" onclick="saveMenu(null)">保存</button>
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
<script src="js/manageMenu.js"></script>

<!-- Bootstrap JS and dependencies -->
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>




</body>
</html>
