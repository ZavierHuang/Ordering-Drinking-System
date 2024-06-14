/*
 老闆 的 js 檔
*/

function deleteEmployee() {
    let empAccount = event.target.value;
    if (confirm("確定刪除員工:" + empAccount + " 嗎?")) {
        $.ajax({
            url: "delEmp",
            type: "POST",
            data: {
                ud: "delEmp",
                empAccount: empAccount
            },
            success: function (response) {
                alert("成功刪除員工！");
                window.location.href = "CustomerMenu";
            }
        });
    } else {
        alert("取消！");
    }
}

function updateEmployee() {
    let empAccount = event.target.value;
    let empJson = JSON.parse($("#allEmpJson")[0].textContent);

    // 找到選中的員工資料
    let employee = empJson.find(emp => emp.account === empAccount);

    // 填充模態框的表單
    $("#editAccount").val(employee.account);
    $("#editPassword").val(employee.password);
    $("#editLevel").val(employee.level);
    $("#editPhone").val(employee.phone);

    // 顯示模態框
    $("#editEmployeeModal").modal('show');
}

function saveEmployee() {
    let account = $("#editAccount").val();
    let password = $("#editPassword").val();
    let level = $("#editLevel").val();
    let phone = $("#editPhone").val();

    // 發送更新請求
    $.ajax({
        url: "updateEmp",
        type: "POST",
        data: {
            ud: "updateEmp",
            account: account,
            password: password,
            level: level,
            phone: phone
        },
        success: function (response) {
            alert("員工資料更新成功！");
            window.location.href = "CustomerMenu";
        }
    });
}

function showList() {
    let empJson = JSON.parse($("#allEmpJson")[0].textContent);
    $("#allEmpTable").empty();

    $("#allEmpTable").append(
        "<tr>" +
        "<th style='width: 20%' align='left'>Account</th>" +
        "<th style='width: 20%'>Password</th>" +
        "<th style='width: 20%'>Level</th>" +
        "<th style='width: 20%'>Phone</th>" +
        "<th style='width: 10%'>&ensp;</th>" +
        "<th style='width: 10%'>&ensp;</th>" +
        "</tr>"
    );

    for (let i = 0; i < empJson.length; i++) {
        $("#allEmpTable").append(
            "<tr>" +
            "<td align=\"left\">" + empJson[i].account + "</td>" +
            "<td align=\"left\">" + empJson[i].password + "</td>" +
            "<td align=\"left\">" + empJson[i].level + "</td>" +
            "<td align=\"left\">" + empJson[i].phone + "</td>" +
            "<td>" + "<button value='" + empJson[i].account + "' onclick='deleteEmployee()'>刪除</button>" + "</td>" +
            "<td>" + "<button class='button-edit' value='" + empJson[i].account + "' onclick='updateEmployee()'>編輯</button>" + "</td>" +
            "</tr>"
        );
    }
}

function updateOrder() {
    let orderId = event.target.value;
    let empJson = JSON.parse($("#allOrderJson")[0].textContent);
    console.log(orderId)
    console.log(empJson)
    // 找到選中的訂單資料
    let order = empJson.find(ort => ort.orderID === Number(orderId));
    console.log(order);

    // 填充模態框的表單
    $("#editOrderID").val(order.orderID);
    $("#editCustomer").val(order.customerName);
    $("#editDate").val(order.dates);
    $("#editStatus").val(order.status);
    //獲取訂單內容
    getOrderContent(order.customerName,order.dates);
    // 顯示模態框
    $("#editOrderModal").modal('show');
}
function  getOrderContent(account,date){
    $.ajax({
        url: "OrderRecord",
        type: "POST",
        data: {
            ud: "queryCustomer",
            account: account,
            date: date
        },
        success: function (response) {
            console.log("查詢成功");
            let orderJson=JSON.parse(response);
            showOrderContent(orderJson)
        }
    });
}

function showOrderContent(orderJson){

    $("#orderProductBody").empty();
    let totalPrice = 0;
    for(let i=0;i<orderJson.length;i++){
        let row = $("<tr>");
        row.append($("<td>").text(orderJson[i].name));
        row.append($("<td>").text(orderJson[i].size));
        row.append($("<td>").text(orderJson[i].price));
        totalPrice += parseInt(orderJson[i].price) * parseInt(orderJson[i].quantity);
        row.append($("<td>").text(orderJson[i].ice));
        row.append($("<td>").text(orderJson[i].sugar));
        row.append($("<td>").text(orderJson[i].quantity));
        $("#orderProductBody").append(row);
    }
    $("#totalPrice").text("Total：" + totalPrice);

}

function saveOrder() {
    let orderID = $("#editOrderID").val();
    let status = $("#editStatus").val();

    // 發送更新請求
    $.ajax({
        url: "updateOrder",
        type: "POST",
        data: {
            ud: "updateOrder",
            orderID: orderID,
            status: status
        },
        success: function (response) {
            alert("訂單資料更新成功！");
            window.location.href = "CustomerMenu";
        }
    });
}

function showOrderList() {
    let empJson = JSON.parse($("#allOrderJson")[0].textContent);
    console.log("A");
    console.log(empJson);
    $("#allOrderTable").empty();

    $("#allOrderTable").append(
        "<tr>" +
        "<th style='width: 15%' align='left'>Order id</th>" +
        "<th style='width: 20%'>Customer</th>" +
        "<th style='width: 25%'>Date</th>" +
        "<th style='width: 15%'>Amount</th>" +
        "<th style='width: 15%'>Status</th>" +
        "<th style='width: 10%'>&ensp;</th>" +
        "</tr>"
    );

    for (let i = 0; i < empJson.length; i++) {
        $("#allOrderTable").append(
            "<tr>" +
            "<td align=\"left\">" + empJson[i].orderID + "</td>" +
            "<td align=\"left\">" + empJson[i].customerName + "</td>" +
            "<td align=\"left\">" + empJson[i].dates + "</td>" +
            "<td align=\"left\">" + empJson[i].amount + "</td>" +
            "<td align=\"left\">" + empJson[i].statusString + "</td>" +
            "<td>" + "<button class='button-edit' value='" + empJson[i].orderID + "' onclick='updateOrder()'>編輯</button>" + "</td>" +
            "</tr>"
        );
    }
}


function init() {
    showList();
    showOrderList();
}

$(document).ready(function () {
    init();
});
