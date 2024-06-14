function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Tea",
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
    let teaJson =  JSON.parse($("#teaJson")[0].textContent);

    for (let i = 0; i < teaJson.length; i++) {
        console.log("teaJson.name:", teaJson[i].name);
        console.log("teaJson.state:", teaJson[i].state);

        $("#main").append(
            $("<button></button>")
                .attr("id","Tea"+i)
                .attr("value", teaJson[i].name)
                .text(teaJson[i].name)
                .on("click", choose)
        );

        if (teaJson[i].state === 0) {
            $("#" + "Tea"+i).prop("disabled", true);
        }
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});