layui.use('form', function () {
    var $ = layui.$,
        form = layui.form;

    //监听提交
    form.on('submit(website-set)', function (data) {
        $.post("/admin/options/save", data.field, function (res) {
            if (res.code == 200) {

            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });
});