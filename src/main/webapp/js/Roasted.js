function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Roasted",
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
    let roastedJson =  JSON.parse($("#roastedJson")[0].textContent);

    for (let i = 0; i < roastedJson.length; i++) {
        // 輸出名稱和狀態
        console.log("roast name:", roastedJson[i].name);
        console.log("roast state:", roastedJson[i].state);

        $("#main").append(
            $("<button></button>")
                .attr("id", "roast"+i)
                .attr("value", roastedJson[i].name)
                .text(roastedJson[i].name)
                .on("click", choose)
        );

        if (roastedJson[i].state === 0) {
            $("#" + "roast"+i).prop("disabled", true);
        }
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});