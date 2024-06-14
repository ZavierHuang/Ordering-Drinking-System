/*
 員工 的 js 檔
*/
function updateOrder() {
    let orderId = event.target.value;
    let orderJson = JSON.parse($("#allOrderJson")[0].textContent);
    // 找到選中的訂單資料
    let order = orderJson.find(ort => ort.orderID === Number(orderId));
    console.log(order);
    // 填充模態框的表單
    $("#editOrderID").val(order.orderID);
    $("#editCustomer").val(order.customerName);
    $("#editDate").val(order.dates);
    $("#editStatus").val(order.status);

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

function showList() {
    let empJson = JSON.parse($("#allOrderJson")[0].textContent);
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
}

$(document).ready(function () {
    init();
});
