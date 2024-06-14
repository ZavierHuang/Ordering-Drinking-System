<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>order record</title>
    <link rel="stylesheet" href="css/orderRecord.css">
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/orderRecord.js"></script>

</head>

<body>
<div class="bg-img">
<header>
    <h1>訂單紀錄</h1>
</header>
<span id="productInorderJson" style="display: none;">${productInorderJson}</span>

<div class="order-container">
    <div class="order-detailss">
    <div class="order-details">

        <h2>訂單內容</h2>
        <p><strong>日期：${date}</strong></p>


        <p><strong>狀態：${status}</strong>  <button type="button" class="btn btn-primary" onclick="cancelOrders()">取消訂單</button> </p>


    </div>

    <div class="order-details">
        <a class="return-link" href="Member"><p>返回</p></a>
    </div>
    </div>

        <div class="order-details">
            <table id="productList">
                <thead>
                <tr>
                    <th>商品名稱</th>
                    <th>甜度</th>
                    <th>冰塊</th>
                    <th>L/M</th>
                    <th>價格</th>
                    <th>數量</th>
                    <th>小計</th>
                </tr>
                </thead>
            </table>
            <p class="total" id="total"><strong>合計:NT$250</strong></p>
        </div>
</div>
</div>
</body>
</html>