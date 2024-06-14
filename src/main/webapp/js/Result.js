let interval;

function showTitle() {
    $.ajax({
        url : "GetTitle",
        type : "POST",
        data: {},
        success : function(response) {
            $("#votingActivityTitle").text("投票活動標題: " + response);
        }
    });
}

function showBallot() {
    $.ajax({
        url : "GetBallot",
        type : "POST",
        data: {},
        success : function(response) {
            console.log(response);
            if (response.status !== 400 && response !== 500) {
                $("#ballot").text("您投的候選人: " + response);
            }
        }
    });
}

function showList() {
    $.ajax({
        url : "GetResult",
        type : "POST",
        data: {},
        dataType : "json",
        success : function(response) {
            if (response === 1) {
                alert("投票活動尚未舉辦!");
                window.location.href="Index";
            } else if (response === 2){
                $("#ResultTable").append("<tr>" +
                    "<th>投票活動尚未開票</th>" +
                    "</tr>");
            } else {
                $("#ResultTable").append("<tr  class=\"row\">" +
                    "<th class=\"col-3\">候選人照片</th>" +
                    "<th class=\"col-3\">候選人姓名</th>" +
                    "<th class=\"col-3\">候選人得票數</th>" +
                    "<th class=\"col-3\">當選</th>" +
                    "</tr>"
                );
                let maxNum = response[0].countNum;
                for (let i = 0; i < response.length; i++) {
                    if (i === 0 || response[i].count === maxNum) {
                        $("#ResultTable").append(
                            "<tr class=\"row\">" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + "<img src=\"img/candidateIMG/" + response[i].image + "\" width=\"100\" height=\"100\"/>" + "</td>" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + response[i].name + "</td>" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + response[i].countNum + "</td>" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + "O" + "</td>" +
                            "</tr>"
                        );
                    }
                    else {
                        $("#ResultTable").append(
                            "<tr class=\"row\">" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + "<img src=\"img/candidateIMG/" + response[i].image + "\" width=\"100\" height=\"100\"/>" + "</td>" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + response[i].name + "</td>" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + response[i].countNum + "</td>" +
                            "<td class=\"col-3 align-items-center d-flex justify-content-center\">" + "X" + "</td>" +
                            "</tr>"
                        );
                    }
                }
            }
        }
    });
}

function countBallot() {
    interval = setInterval(function(){
        $.ajax({
            type : 'POST',
            url : 'CountBallot',
            success: function (response) {
                let data = JSON.parse(response);
                $("#votingRate").text("當前投票率： " + data["votingRate"]);
                $("#count").text("當前投票數： " + data["countNum"]);
                $("#totalVoting").text("總投票數： " + data["totalNum"]);
            },
        });
    }, 200);
}

clearInterval(interval)

function init() {
    countBallot();
    showBallot();
    showTitle();
    showList();
}

$(document).ready(function(){
    init();
});