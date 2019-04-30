layui.use(['form', 'laydate'], function () {
    var $ = layui.$,
        form = layui.form;

    form.on('submit(submitAdd)', function (data) {
        $("#submit").addClass("layui-btn-disabled");
        $("#submit").attr('disabled', 'disabled');
        $.post("/admin/menu/add", data.field, function (res) {
            if (res.code == 200) {
                layer.alert(res.msg, {}, function () {
                        var curIfr = parent.layer.getFrameIndex(window.name);
                        window.parent.refresh();
                        parent.layer.close(curIfr);
                    }
                )
            } else {
                layer.msg(res.msg);
            }
        });
    });

});
