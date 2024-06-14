function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Coco",
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
    let cocoJson =  JSON.parse($("#cocoJson")[0].textContent);

    for (let i = 0; i < cocoJson.length; i++) {
        // 輸出名稱和狀態
        console.log("coco name:", cocoJson[i].name);
        console.log("coco state:", cocoJson[i].state);

        $("#main").append(
            $("<button></button>")
                .attr("id", "coco"+i)
                .attr("value", cocoJson[i].name)
                .text(cocoJson[i].name)
                .on("click", choose)
        );

        if (cocoJson[i].state === 0) {
            $("#" + "coco"+i).prop("disabled", true);
        }
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});