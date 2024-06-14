




function cancelOrders() {
    // let Account = event.target.value;
    if (confirm("確定取消訂單嗎?")) {
        $.ajax({
            url: "OrderRecord",
            type: "POST",
            data: {
                ud: "cancelOrders"
            },
            success: function (response) {
                alert(response);

                window.location.href = "Member";


            }
        });
    } else {
        alert("取消！");
    }
}







function showList() {
    let productInorderJson =  JSON.parse($("#productInorderJson")[0].textContent);
    let count = 0;
    for (let i = 0; i < productInorderJson.length; i++) {
        let total = productInorderJson[i].price * productInorderJson[i].quantity;
        count += total;
        $("#productList").append(
            "<tbody>" +
            "<tr>" +
            "<td>" + productInorderJson[i].name + "</td>" +
            "<td>" + productInorderJson[i].sugar + "</td>" +
            "<td>" + productInorderJson[i].ice + "</td>" +
            "<td>" + productInorderJson[i].size + "</td>" +
            "<td> NT$" + productInorderJson[i].price + "</td>" +
            "<td>" + productInorderJson[i].quantity + "</td>" +
            "<td> NT$" + total + "</td>" +
            "</tr>" +
            "</tbody>"
        );
    }
    document.getElementById("total").innerHTML = "NT$" + count;
}

function init() {
    showList();
}

$(document).ready(function () {
    init();
});