function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Latte",
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
    let latteJson =  JSON.parse($("#latteJson")[0].textContent);

    for (let i = 0; i < latteJson.length; i++) {
        // 輸出名稱和狀態
        console.log("latte name:", latteJson[i].name);
        console.log("latte state:", latteJson[i].state);

        $("#main").append(
            $("<button></button>")
                .attr("id", "latte"+i)
                .attr("value", latteJson[i].name)
                .text(latteJson[i].name)
                .on("click", choose)
        );

        if (latteJson[i].state === 0) {
            $("#" + "latte"+i).prop("disabled", true);
        }
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});