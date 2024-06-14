<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>member information</title>
    <link rel="stylesheet" href="css/member.css">
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/Member.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
<header>
    <h1>我的帳戶</h1>
</header>
<span id="ordersJson" style="display: none;">${ordersJson}</span>
<div class="member-container">
    <div class="member-detailss">
    <div class="member-details">

        <h2>會員資料</h2>
        <p><strong id="account" data-value=${sessionScope.account}>帳號：${sessionScope.account}</strong> </p>
        <p>
            <strong>TEL：</strong>
            <strong id="memberTEL"></strong>
        </p>
    </div>

    <div class="member-details">
        <h2>會員等級</h2>
        <p><strong>等級：</strong> <span class="membership-level">${sessionScope.levels}</span></p>
        <button class="update-button" type="button" value=${sessionScope.account} onclick="updateMemberInfo()">修改個人資料</button>
    </div>
    <div class="member-details">
        <p><a class="return-link" href="CustomerMenu">返回</a></p></div>
    </div>
    <div class="member-details">
        <h2>訂單紀錄</h2>
        <table id="orderList">
            <thead>
            <tr>
                <th style="text-align: center">訂單編號</th>
                <th style="text-align: center">金額</th>
                <th style="text-align: center">日期</th>
                <th></th>
            </tr>
            </thead>

        </table>
        <div class="block">
            <span id="userInfoJson" style="display: none;">${userInfoJson}</span>
        </div>
    </div>
</div>

<!-- Edit Personal Modal -->
<div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editMenuModalLabel">編輯個人資料</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editEmployeeForm">
                    <div class="form-group">
                        <label for="editAccount">Account</label>
                        <input type="text" class="form-control" id="editAccount" disabled>
                    </div>
                    <div class="form-group">
                        <label for="editPwd">Password</label>
                        <input type="text" class="form-control" id="editPwd">
                    </div>
                    <div class="form-group">
                        <label for="editPwd_Confirm">Confirm Password</label>
                        <input type="text" class="form-control" id="editPwd_Confirm">
                    </div>
                    <div class="form-group">
                        <label for="editLevel">Level</label>
                        <input type="text" class="form-control" id="editLevel" disabled>
                    </div>
                    <div class="form-group">
                        <label for="editphone">Telephone</label>
                        <input type="text" class="form-control" id="editphone">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" id="saveButton" class="btn btn-primary" onclick="saveInfo()">儲存</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>

