function addToCart() {
    let productName = document.getElementById("productName").textContent;
    let size = document.getElementById("size").value;
    let sugar = document.getElementById("sugar").value;
    let ice = document.getElementById("ice").value;
    let quantity = document.getElementById("myNumber").value;
    if(quantity > 20){
        alert("商品數量不能大於20杯");
    }
    else{
        $.ajax({
            url: "Sugarice",
            type: "POST",
            data: {
                productName: productName,
                size: size,
                sugar: sugar,
                ice: ice,
                quantity: quantity
            },
            success: function (response) {
                alert("已成功加入購物車!");
                window.location.href = "CustomerMenu";
            }
        });
    }

}

function up(max) {
    document.getElementById("myNumber").value = parseInt(document.getElementById("myNumber").value) + 1;
    if (document.getElementById("myNumber").value >= parseInt(max)) {
        document.getElementById("myNumber").value = max;
    }
}
function down(min) {
    document.getElementById("myNumber").value = parseInt(document.getElementById("myNumber").value) - 1;
    if (document.getElementById("myNumber").value <= parseInt(min)) {
        document.getElementById("myNumber").value = min;
    }
}

