<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:include page="../header/Header.jsp" />

<!DOCTYPE HTML>
<html>
<style>
    .form-group {
        width: 30%;
        margin-top: 50px;
        margin-left: 33.5%;
    }
    #myNumber {
        text-align: center;
        font-size:30px;
        margin-top:10px;
    }
</style>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body >
<!-- Wrapper -->
    <div>
        <!-- Main -->
        <div class = "bg">
            <article>
                <div id="wrapper">
                    <h1 align="center" id="productName">${ProductName}</h1>
                    <form class="custom-select">
                        <!-- M L -->
                        <h2>請選擇中、大杯</h2>
                        <select id="size">
                                <option value="大杯">大杯</option>
                                <option value="中杯">中杯</option>
                        </select>
                        <!-- ice -->
                        <h2>請選擇冰塊</h2>
                        <select id="ice">
                                <option value="正常">正常</option>
                                <option value="少冰">少冰</option>
                                <option value="去冰">去冰</option>
                                <option value="熱">熱</option>
                        </select>
                        <!-- sugar -->
                        <h2>請選擇甜度</h2>
                        <select id="sugar">
                            <optgroup label="甜度">
                                <option value="正常">正常</option>
                                <option value="半糖">半糖</option>
                                <option value="無糖">無糖</option>
                        </select>
                    </form>
                    <h2>請選擇數量</h2>
                    <span class="form-group">
                        <center>
                            <span>
                                <span>
                                    <button style="height: 50px;width: 50px" id="down" onclick=" down('1')">-</button>
                                </span>
                                <input type="text" id="myNumber" value="1" size="1"/>
                                <span>
                                    <button style="height: 50px;width: 50px" id="up" onclick="up('20')">+</button>
                                </span>
                            </span>
                        </center>
                    </span>
                    <br>
                    <center><button id="add" style="width: 30%;margin-top: 10px;" onclick="addToCart()">加入購物車</button></center>
                </div>
            </article>
        </div>
    </div>
</body>
<script src="js/Sugarice.js"></script>
</html>
