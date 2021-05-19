$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function doLogin(flag) {


    if (flag) {
        var para = $('#login-form').serializeObject();
        para.username = para.usernameIn;
        para.password = para.passwordIn;
        para = JSON.stringify(para);
        $.ajax({
            url: "/user/login",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: para,
            success: function (data) {
                if (data.code == 0) window.location.href = "/";
            }
        })
    } else {
        var para = $('#sign-form').serializeObject();
        para = JSON.stringify(para);
        console.log(para);
        $.ajax({
            url: "/user/signUp",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: para,
            success: function (data) {
                if(data.code==0)alert("注册成功");
            }
        })
    }
}
