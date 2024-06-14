function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Milk",
        type : "POST",
        data: {
            productName : productName
        },
        success : function(response) {
            console.log(response);
            window.location.href = "Sugarice";
        }
    });
}

function showList() {

    let milkJson =  JSON.parse($("#milkJson")[0].textContent);

    for (let i = 0; i < milkJson.length; i++) {
        // 輸出名稱和狀態
        console.log("milk name:", milkJson[i].name);
        console.log("milk state:", milkJson[i].state);

        $("#main").append(
            $("<button></button>")
                .attr("id", "milk"+i)
                .attr("value", milkJson[i].name)
                .text(milkJson[i].name)
                .on("click", choose)
        );

        if (milkJson[i].state === 0) {
            $("#" + "milk"+i).prop("disabled", true);
        }
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});