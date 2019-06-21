layui.use('form', function () {
    var $ = layui.$,
        form = layui.form;

    //监听提交
    form.on('submit(website-set)', function (data) {
        $.post("/admin/options/save", data.field, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon: 1});
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });

    //监听提交
    form.on('submit(smtp-set)', function (data) {
        $.post("/admin/options/save", data.field, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon: 1});
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });

    //监听提交
    form.on('submit(storage-set)', function (data) {
        $.post("/admin/options/save", data.field, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon: 1});
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });

    //监听提交
    form.on('submit(global-set)', function (data) {
        $.post("/admin/options/save", data.field, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon: 1});
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });
});