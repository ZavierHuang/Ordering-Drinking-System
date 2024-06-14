function logout() {
    console.log("logout");
    $.ajax({
        type: 'post',
        url: 'Logout',
        data: {

        },
        success: function (data) {
            window.location.href = "Login";
        }
    });
}
