<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:include page="../header/Header.jsp" />

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="js/Tea.js"></script>
</head>
<body >
<!-- Wrapper -->
    <span id="teaJson" style="display: none;">${json}</span>
    <div>
        <!-- Main -->
        <div class = "bg">
            <article id="tea">
                <div id="wrapper">
                    <h1>TEA</h1>
                    <span ><img src="img/tea.jpg" style="max-width: 100%" id="drinks"/></span>
                    <h1  align="center">今天想喝什麼茶呢?</h1>
                    <ul>
                        <div align="center" id="main"></div>
                    </ul>
                </div>

            </article>
        </div>
    </div>
</body>
</html>
