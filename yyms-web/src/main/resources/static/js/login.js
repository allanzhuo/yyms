layui.use('form', function () {
    var $ = layui.$,
     form = layui.form;

    form.verify({
        username: function (value) {
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
            if (value.length < 4 || value.length > 12) {
                return "用户名必须4~12位，且不能包含空格";
            }
        }
        , password: [
            /^[\S]{6,24}$/
            , '密码必须6到24位，且不能出现空格'
        ]
    });

    form.on('submit(submit)', function (data) {
        data.field.password = md5(data.field.password);
        $.post("/login", data.field, function (res) {
                if (res.code === 200) {
                    layer.msg("登录成功！");
                    setTimeout(function () {
                        location.href = '/admin';
                    }, 1000);
                } else {
                    layer.msg(res.msg);
                }
            }
        );
        return false;
    });

    check();
    function check(){
        if (window.parent.window != window) {
            window.top.location = "/login";
        }
    }

});