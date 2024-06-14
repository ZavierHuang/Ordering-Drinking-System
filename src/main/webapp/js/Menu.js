function deletePrdouct(){
    let productID = event.target.value;
    $.ajax({
        url : "CustomerMenu",
        type : "POST",
        data: {
            ud: "delete",
            productID : productID
        },
        success : function(response) {
            alert("成功刪除商品！")
            window.location.href = "CustomerMenu";
        }
    });
}

function send(){
    let account = document.getElementById("account").innerText;
    let amount = document.getElementById("amount").innerText;

    if(amount === "0") {
        alert("請選擇商品！");
    }
    else {
        console.log("SEND ORDER");
        $.ajax({
            url : "CustomerMenu",
            type : "POST",
            data: {
                ud: "sendOrder",
                account : account,
                amount : amount
            },
            success : function(response) {
                alert("成功送出訂單");
                window.location.href = "CustomerMenu";
            }
        });
    }
}

function showList() {
    let customerCartJson = JSON.parse($("#customerCartJson")[0].textContent);

    for (let i = 0; i < customerCartJson.length; i++) {
        $("#customerCart").append(
            "<tr>" +
            "<td align=\"left\">" + customerCartJson[i].name + "</td>" +
            "<td align=\"center\"> ● " + customerCartJson[i].size + " / " + customerCartJson[i].sugar + " / " + customerCartJson[i].ice + "</td>" +
            "<td>" + customerCartJson[i].quantity + "</td>" +
            "<td>" + customerCartJson[i].price + "</td>" +
            "<td>" + "<button value='" + customerCartJson[i].id + "' onclick='deletePrdouct()'>刪除</button>" + "</td>" +
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

window.onload = function() {
    window.scrollTo(0, document.body.scrollHeight);
};