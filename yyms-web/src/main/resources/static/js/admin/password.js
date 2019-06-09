layui.use('form', function () {
    var $ = layui.$,
        form = layui.form;
    form.render();

    form.verify({
        password: [
            /^[\S]{6,24}$/
            , '密码必须6到24位，且不能出现空格'
        ]
    });

    //监听提交
    form.on('submit(set-password)', function (data) {
        var password = data.field.password;
        if (password !== null && password !== "") {
            data.field.oldPassword = md5(data.field.oldPassword);
            data.field.password = md5(data.field.password);
            data.field.repassword = md5(data.field.repassword);
        }
        $.post("/admin/password/edit", data.field, function (res) {
            if (res.code == 200) {
                layer.alert(res.msg, {icon: 1}, function (index) {
                        location.href = common.url.login;
                        layer.close(index);
                    }
                )
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });
});