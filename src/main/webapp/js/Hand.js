function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Hand",
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
    let handJson =  JSON.parse($("#handJson")[0].textContent);

    for (let i = 0; i < handJson.length; i++) {
        // 輸出名稱和狀態
        console.log("hand name:", handJson[i].name);
        console.log("hand state:", handJson[i].state);

        $("#main").append(
            $("<button></button>")
                .attr("id", "hand"+i)
                .attr("value", handJson[i].name)
                .text(handJson[i].name)
                .on("click", choose)
        );

        if (handJson[i].state === 0) {
            $("#" + "hand"+i).prop("disabled", true);
        }
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});