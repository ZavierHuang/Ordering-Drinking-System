function updateMemberInfo() {

    account = event.target.value;

    console.log("update account:", account);

    let userInfoJson = JSON.parse($("#userInfoJson")[0].textContent);
    console.log("userInfoJson:", userInfoJson);
    let userInfo = userInfoJson.find(user => user.account === account);

    // 填充模態框的表單
    $("#editAccount").val(userInfo.account);
    $("#editPwd").val('');
    $("#editPwd_Confirm").val('');
    $("#editLevel").val(userInfo.level);
    $("#editphone").val(userInfo.phone);

    // 顯示模態框(Click Edit)
    $("#editUserModal").modal('show');
}

function saveInfo() {
    let account = $("#editAccount").val();
    let password =  $("#editPwd").val();
    let password_confirm = $("#editPwd_Confirm").val();
    let level = $("#editLevel").val();
    let telephone = $("#editphone").val();


    if (password === "" || password_confirm === "" || telephone === "") {
        alert("請填寫完整的資料")
        return;
    }

    if (password !== password_confirm) {
        alert("請確認填寫相同的密碼");
        return;
    }

    if (telephone.length !== 10) {
        alert("請確認填寫正確的【10位數】手機號碼");
        return;
    }

    let newUserInfo = {
        account: account,
        password: password,
        level: level,
        phone: telephone
    }

    $.ajax({
        url: 'updateUserInfo',
        type: 'POST',
        data: {
            newUserInfoData: JSON.stringify(newUserInfo)
        },
        success: function (data) {
            window.location.href = "Member";
        }
    });
}

function query() {
    let date = event.target.value;

    $.ajax({
        url : "OrderRecord",
        type : "POST",
        data: {
            ud: "query",
            date : date
        },
        success : function(response) {
            console.log(response);
            window.location.href = "OrderRecord";
        }
    });
}

function showList() {
    let ordersJson =  JSON.parse($("#ordersJson")[0].textContent);

    for (let i = 1; i < ordersJson.length+1; i++) {
        $("#orderList").append(
            "<tbody>" +
            "<tr>" +
            "<td>" + i
            + "</td>" +
            "<td>" + ordersJson[i-1].amount + "</td>" +
            "<td>" + ordersJson[i-1].dates + "</td>" +
            "<td>" +
            "<strong><button class=\"check-order-btn\" type=\"button\"value='" + ordersJson[i-1].dates + "'onclick=\"query()\">" +
            "查閱</button></strong>" +
            "</td>" +
            "</tr>" +
            "</tbody>"
        );
    }
}

function getMemberTEL(){
    let userInfoJson = JSON.parse($("#userInfoJson")[0].textContent);
    let account = document.getElementById('account').getAttribute('data-value');
    let userInfo = userInfoJson.find(user => user.account === account);
    $("#memberTEL").text(userInfo.phone);
}

function init() {
    showList();
    getMemberTEL();
}

$(document).ready(function () {
    init();
});