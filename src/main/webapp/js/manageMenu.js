/*
 老闆管理 menu 的 js 檔
*/

function updateMenu() {

    var saveButton = document.getElementById('saveButton');
    let menuItemTypes = JSON.parse($('#allTypeJson')[0].textContent);
    $("#editType").empty();
    for (let i = 0; i < menuItemTypes.length; i++) {
        $("#editType").append(
            "<option class=\"form-control\">" +menuItemTypes[i].type + "</option>"
        );
    }

    $("#editState").empty();
    $("#editState").append("<option class=\"form-control\"> Open </option>");
    $("#editState").append("<option class=\"form-control\"> Close </option>");

    // init
    let productName = '';
    let price_M = '';
    let price_L = '';
    let type = menuItemTypes[0].type;
    let stack = '';
    let state = "Open";

    menuItemName = event.target.value;

    console.log("update menuItemName:", menuItemName);

    if (menuItemName.length !== 0) {
        document.getElementById('editMenuModalLabel').textContent = '編輯商品資料';
        saveButton.setAttribute('onclick', 'saveMenu("'+menuItemName+'")');
        saveButton.textContent = '儲存';

        let menuItemJson = JSON.parse($("#allMenuJson")[0].textContent);
        let menuItem = menuItemJson.find(product => product.name === menuItemName);
        productName = menuItem.name;
        price_M = menuItem.price_M;
        price_L = menuItem.price_L;
        type = menuItem.type;
        state = menuItem.state;
        stack = menuItem.stack;
    }
    else{
        document.getElementById('editMenuModalLabel').textContent = '新增商品資料';
        saveButton.setAttribute('onclick', 'saveNewMenuItem()');
        saveButton.textContent = '新增';
    }


    // 填充模態框的表單
    $("#editName").val(productName);
    $("#editPrice_M").val(price_M);
    $("#editPrice_L").val(price_L);
    $("#editType").val(type);
    $("#editState").val(state);
    $("#editStack").val(stack);

    // 顯示模態框(Click Edit)
    $("#editMenuModal").modal('show');
    // $("#editEmployeeModal").modal('show');
}


function toggleButton(button) {
    let currentMenuItemName = event.target.value;
    let currentMenuItemJson = JSON.parse($("#allMenuJson")[0].textContent);
    let currentMenuItem = currentMenuItemJson.find(product => product.name === currentMenuItemName);

    $("#editState").empty();
    $("#editState").append("<option class=\"form-control\"> Open </option>");
    $("#editState").append("<option class=\"form-control\"> Close </option>");
    $("#editState").val(currentMenuItem.state);


    if(currentMenuItem.stack===0){
        alert("Not Enough For Stack!");
        return;
    }

    console.log("button.innerHTML:",button.innerHTML);

    // 切換按鈕上的文字和背景顏色
    if (button.innerHTML === "Open") {
        button.innerHTML = "Close";
        $("#editState").val("Close")
    } else {
        button.innerHTML = "Open";
        $("#editState").val("Open");
    }
    updatetoggleButtonState(currentMenuItem);

}

function updatetoggleButtonState(old_menuItem){

    let new_menuItem = {
        name: old_menuItem.name,
        type: old_menuItem.type,
        price_M: old_menuItem.price_M,
        price_L: old_menuItem.price_L,
        state: $("#editState").val(),
        stack: old_menuItem.stack
    }

    $.ajax({
        url: 'updateMenuItem',
        type: 'POST',
        data: {
            oldData: JSON.stringify(old_menuItem),
            newData: JSON.stringify(new_menuItem)
        },
        success: function (data) {
            // alert("Update Status Successfully");
            // window.location.href = "manageMenu";
            $("#allMenuJson").text(JSON.stringify(data));
            searchMenu();
        }
    });
}

function saveMenu(menuItemName) {
    let newProductName = $("#editName").val();
    let newPrice_M =  $("#editPrice_M").val();
    let newPrice_L = $("#editPrice_L").val();
    let newType = $("#editType").val();
    let newState = $("#editState").val();``
    let newStack = $("#editStack").val();``

    let menuItemJson = JSON.parse($("#allMenuJson")[0].textContent);
    let old_menuItem = menuItemJson.find(product => product.name === menuItemName);
    let newMenuItemName_Check = menuItemJson.find(product => product.name === newProductName);

    if(newProductName.trim() === ''){
        alert("Product Name is Empty!")
        $("#editName").val('');
        return;
    }

    if(newMenuItemName_Check !== undefined && newMenuItemName_Check.name !== menuItemName) {
        alert("This Product Name Already Exists!");
        return;
    }

    console.log("newPrice_M:",newPrice_M);
    console.log("newPrice_L:",newPrice_L);
    if(!checkPriceAndStackCorrect(newPrice_M, newPrice_L, newStack, newState))
        return;

    let new_menuItem = {
        name: newProductName,
        price_M: newPrice_M,
        price_L: newPrice_L,
        type: newType,
        state: newState,
        stack: newStack
    }

    $.ajax({
        url: 'updateMenuItem',
        type: 'POST',
        data: {
            oldData: JSON.stringify(old_menuItem),
            newData: JSON.stringify(new_menuItem)
        },
        success: function (data) {
            $("#allMenuJson").text(JSON.stringify(data));
            searchMenu();
            $("#editMenuModal").modal('hide');
        }
    });
}

function saveNewMenuItem() {
    let newProductName = $("#editName").val();
    let newPrice_M =  $("#editPrice_M").val();
    let newPrice_L = $("#editPrice_L").val();
    let newType = $("#editType").val();
    let newState = $("#editState").val();
    let newStack = $("#editStack").val();

    let MenuItemJson = JSON.parse($("#allMenuJson")[0].textContent);
    let newMenuItemName_Check = MenuItemJson.find(product => product.name === newProductName);

    if(newProductName.trim() === ''){
        alert("Product Name is Empty!")
        $("#editName").val('');
        return;
    }

    if(newMenuItemName_Check !== undefined) {
        alert("This Product Name Already Exists!");
        return;
    }

    if(!checkPriceAndStackCorrect(newPrice_M, newPrice_L, newStack, newState))
        return;

    console.log("new productName:",newProductName);
    console.log("new price_M:",newPrice_M);
    console.log("new price_L:",newPrice_L);
    console.log("new type:",newType);
    console.log("new state:",newState);
    console.log("new Stack:",newStack);



    let new_menuItem = {
        name: newProductName,
        price_M: newPrice_M,
        price_L: newPrice_L,
        type: newType,
        state: newState,
        stack: newStack
    }


    $.ajax({
        url: 'addNewMenuItem',
        type: 'POST',
        data: {
            newData: JSON.stringify(new_menuItem)
        },
        success: function (response) {
            // alert("Add Menu Successfully")
            // window.location.href = "manageMenu";
            $("#editMenuModal").modal('hide');
            searchMenu();
        }
    });
}

function checkPriceAndStackCorrect(newPrice_M, newPrice_L, newStack, newState) {
    if(newPrice_M === ''){
        alert("Size(M) Price is Empty!");
        return false;
    }
    if(newPrice_L === ''){
        alert("Size(L) Price is Empty!");
        return false;
    }
    if(newPrice_M !== '' && newPrice_L !== '')
    {
        console.log("check newPrice_M:",newPrice_M);
        console.log("check newPrice_L:",newPrice_L);
        console.log("M>=L:",parseInt(newPrice_M) >= parseInt(newPrice_L));
        if(parseInt(newPrice_M) >= parseInt(newPrice_L)) {
            alert("Size(M) Price Should Be Cheaper than Size(L)!")
            return false;
        }
    }
    if(newStack === '')
    {
        alert("Stack is Empty!");
        return false;
    }else if(newStack === '0' && newState === 'Open') {
        alert("Not Enough For Stack!");
        $("#editState").val('Close');
        return false;
    }

    return true;
}

function showMenuList() {
    let menuJson = JSON.parse($("#allMenuJson")[0].textContent);
    console.log("menuJson:",menuJson);

    $("#allMenuTable").empty();

    if(menuJson.length === 0)
    {
        $("#allMenuTable").append(
            "<tr>" +
            "<th style='width: 332px; font-weight:bold; text-align: center; padding-left: 25px;'>Product Name</th>" +
            "<th style='width: 232px; font-weight:bold; text-align: center; padding-left: 155px;'>Type</th>" +
            "<th style='width: 140px; font-weight:bold; text-align: center; padding-left: 200px;'>Size(M)</th>" +
            "<th style='width: 140px; font-weight:bold; text-align: center; padding-left: 130px;'>Size(L)</th>" +
            "<th style='width: 140px; font-weight:bold; text-align: center; padding-left: 130px;'>Stack</th>" +
            "<th style='width: 209px; font-weight:bold; text-align: center; padding-left: 180px;'>State</th>" +
            "<th style='width: 116px; font-weight:bold; text-align: center;'>&ensp;</th>" +
            "<th style='width: 93px; font-weight:bold; text-align: center;'>&ensp;</th>" +
            "</tr>"
        );
        return;
    }

    $("#allMenuTable").append(
        "<tr>" +
        "<th style='width: 20%; font-weight:bold; text-align: center;'>Product Name</th>" +
        "<th style='width: 20%; font-weight:bold; text-align: center;'>Type</th>" +
        "<th style='width: 12%; font-weight:bold; text-align: center;'>Size(M)</th>" +
        "<th style='width: 12%; font-weight:bold; text-align: center;'>Size(L)</th>" +
        "<th style='width: 10%; font-weight:bold; text-align: center;'>Stack</th>" +
        "<th style='width: 12%; font-weight:bold; text-align: center;'>State</th>" +
        "<th style='width: 10%; font-weight:bold; text-align: center;'>&ensp;</th>" +
        "<th style='width: 10%; font-weight:bold; text-align: center;'>&ensp;</th>" +
        "</tr>"
    );

    for (let i = 0; i < menuJson.length; i++) {
        let row = $("<tr>").append(
            $("<td>").css("text-align", "center").text(menuJson[i].name),
            $("<td>").css("text-align", "center").text(menuJson[i].type),
            $("<td>").css("text-align", "center").text('$' + menuJson[i].price_M),
            $("<td>").css("text-align", "center").text('$' + menuJson[i].price_L),
            $("<td>").css("text-align", "center").text(menuJson[i].stack),
            $("<td>").css("text-align", "center").append(
                $("<button>").addClass("button-state").attr({
                    name: 'toggleButton',
                    value: menuJson[i].name,
                    onclick: 'toggleButton(this)'
                }).text(menuJson[i].state)
            ),
            $("<td>").css("text-align", "center").append(
                $("<button>").addClass("button-delete").attr({
                    value: menuJson[i].name,
                    onclick: 'deleteMenuItem()'
                }).text('刪除')
            ),
            $("<td>").css("text-align", "center").append(
                $("<button>").addClass("button-edit").attr({
                    value: menuJson[i].name,
                    onclick: 'updateMenu()'
                }).text('編輯')
            )
        );

        // Append the row to the table
        $("#allMenuTable").append(row);

        // Immediately modify the button based on its state
        let button = row.find("button.button-state"); // Select the button within this row
        if (button.text() === "Close") {
            button.addClass("toggleOff");
        } else {
            button.addClass("toggleOn");
        }
    }
}

function searchMenuItemInfoInit(){
    let menuItemTypes = JSON.parse($('#allTypeJson')[0].textContent);
    $("#searchType").empty();
    for (let i = -1; i < menuItemTypes.length; i++) {
        if(i===-1)
            $("#searchType").append("<option class=\"form-control\">    </option>");
        else
            $("#searchType").append("<option class=\"form-control\">" + menuItemTypes[i].type + "</option>");
    }
    $("#searchType").val("    ");

    $("#searchState").empty();
    $("#searchState").append("<option class=\"form-control\" style='background-color: #bfc5bf;'>   </option>");
    $("#searchState").append("<option class=\"form-control\" style='background-color: #9deea0; font-weight: 500;'> Open </option>");
    $("#searchState").append("<option class=\"form-control\" style='background-color: #ef9994; font-weight: 500'> Close </option>");
    $("#searchState").val("   ");

    $("#searchStack").empty();
    $("#searchStack").append("<option class=\"form-control\" style='background-color: #bfc5bf;'>   </option>");
    $("#searchStack").append("<option class=\"form-control\" style='background-color: #d75454; color: white'> <=5 </option>");
    $("#searchStack").append("<option class=\"form-control\" style='background-color: #afa21b; color: white'> <=10</option>");
    $("#searchStack").append("<option class=\"form-control\" style='background-color: #32a43e; color: white'> <=20 </option>");
    $("#searchStack").append("<option class=\"form-control\" style='background-color: #617ff8; color: white'> >=21 </option>");
    $("#searchStack").val("   ");

    $("#searchProductName").val("");
    $("#searchPrice_M").val("");
    $("#searchPrice_L").val("");

    $("#searchProductName").val("");
    $("#searchPrice_M").val("");
    $("#searchPrice_L").val("");

    searchMenu();
}

function searchMenu(){
    let getProductName = $("#searchProductName").val().trim();
    let getType = $("#searchType").val();
    let getPrice_M = $("#searchPrice_M").val();
    let getPrice_L = $("#searchPrice_L").val();
    let getState = $("#searchState").val();
    let getStack = $("#searchStack").val();

    console.log("getStack:",getState);

    let searchProductName = getProductName.length>0?getProductName:null;
    let searchType = getType!==null && getType.length>0?getType:null;
    let searchPrice_M = getPrice_M.length!==0?getPrice_M:null;
    let searchPrice_L = getPrice_L.length!==0?getPrice_L:null;
    let searchState = getState!==null && getState.length>0?getState:null;
    let searchStack = getStack!==null && getStack.length>0?getStack.match(/[0-9]+/g).join(''):null;
    let searchSymbol = searchStack!==null ? getStack.match(/[^0-9]+/g).join(''):null;

    console.log("searchProductName:", searchProductName);
    console.log("searchType:", searchType);
    console.log("searchPrice_M:", searchPrice_M);
    console.log("searchPrice_L:", searchPrice_L);
    console.log("searchState:", searchState);
    console.log("searchStack:", searchStack);
    console.log("searchSymbol:", searchSymbol);


    let searchMenuItem = {
        name: searchProductName,
        price_M: searchPrice_M,
        price_L: searchPrice_L,
        type: searchType,
        state: searchState,
        stack: searchStack
    }

    $.ajax({
        url: 'searchMenuItem',
        type: 'POST',
        data: {
            searchData: JSON.stringify(searchMenuItem),
            searchSymbol: searchSymbol
        },
        success: function (data) {
            // alert("Menu Update Successfully");
            var menuData = JSON.parse(data.menuItems);
            var totalItems = data.totalItems;
            $("#allMenuJson").text(JSON.stringify(menuData));
            $("#totalAmount").text("Total: " + totalItems);
            showMenuList();
        }
    });

}

function deleteMenuItem(){
    let deleteMenuItemName = event.target.value;
    console.log("deleteMenuItemName:", deleteMenuItemName);

    if (confirm("確定刪除菜單【" + deleteMenuItemName + "】嗎?")) {
        $.ajax({
            url: 'deleteMenuItem',
            type: 'POST',
            data: {
                deletData: deleteMenuItemName
            },
            success: function (response) {
                // alert("Delete MenuItem Successfully")
                // window.location.href = "manageMenu";
                searchMenu();
            }
        });
    }
}

function init() {
    showMenuList();
    searchMenuItemInfoInit();
}


$(document).ready(function () {
    init();
});


window.onload = function() {
    window.scrollTo(0, document.body.scrollHeight);
};
