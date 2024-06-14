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
    <!-- Bootstrap CSS -->
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
            <span><img id="pic5" src="img/pic05.ico" style="width:80px;height:80px;border-radius:100%;"></span>
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
                <li><a href="addEmployee" style="font-size:30px; text-decoration: none;">新增員工</a></li>
                <li><a href="manageMenu" style="font-size:30px; text-decoration: none;">管理菜單</a></li>
            </ul>
        </nav>
    </header>

    <!-- Main -->
    <div id="main">
        <div class="container mt-5">
            <h2>菜單管理</h2>
            <table class="table table-striped mt-4">
                <thead>
                <tr>
                    <th>品項</th>
                    <th>分類</th>
                    <th>價格</th>
                    <th>狀態</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="menuTable">
                <!-- 菜單項目將通過JavaScript動態生成 -->
                </tbody>
            </table>
        </div>
    </div>

    <!-- Footer -->
    <footer id="footer">
        <div class="container">
            <span>&copy; 2024 TWO SHOTS. All rights reserved.</span>
        </div>
    </footer>
</div>

<!-- BG -->
<div id="bg"></div>

<!-- Scripts -->
<script src="lib/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="js/admin.js"></script>
<script>
    // 示例菜單數據
    const menuItems = [
        { name: '綠茶', category: '茶類', price: 30, status: 'on' },
        { name: '紅茶', category: '茶類', price: 30, status: 'off' },
        { name: '牛奶', category: '牛奶', price: 50, status: 'on' },
        { name: '拿鐵', category: '拿鐵', price: 60, status: 'off' },
        { name: '手沖咖啡', category: '手沖', price: 70, status: 'on' },
        { name: '可可', category: '可可', price: 40, status: 'on' }
    ];

    function populateMenuTable() {
        const tableBody = document.getElementById('menuTable');
        tableBody.innerHTML = '';
        menuItems.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.name}</td>
                <td>${item.category}</td>
                <td>${item.price}</td>
                <td>${item.status == 'on' ? '販賣中' : '已停賣'}</td>
                <td>
                    <button class="btn btn-${item.status == 'on' ? 'danger' : 'success'} btn-sm" onclick="toggleStatus('${item.name}')">
                        ${item.status == 'on' ? '停賣' : '啟用'}
                    </button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    }

    function toggleStatus(itemName) {
        const item = menuItems.find(i => i.name == itemName);
        item.status = item.status == 'on' ? 'off' : 'on';
        populateMenuTable();
    }

    $(document).ready(function () {
        populateMenuTable();
    });
</script>
</body>
</html>
