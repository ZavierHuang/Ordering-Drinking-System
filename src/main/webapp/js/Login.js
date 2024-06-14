function login() {
    let account = $("#account").val();
    let password = $("#password").val();

    if (account !== "" && password !== "") {
        $.ajax({
            type: 'post',
            url: 'Login',
            data: {
                "Account": account,
                "Password": password,
            },
            success: function (response) {
                console.log("response.status:"+response.status);
                console.log("response:"+response);
                if (response === "1") {
                    alert("帳號密碼錯誤!");
                    window.location.href = "Login";
                }
                else {
                    window.location.href = "CustomerMenu";
                }
            }
        });
    } else {
        alert("請填寫帳號密碼!");
    }
}
