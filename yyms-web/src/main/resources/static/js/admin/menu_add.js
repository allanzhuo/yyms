layui.use(['form', 'laydate'], function () {
    var $ = layui.$,
        form = layui.form;

    form.on('submit(submitAdd)', function (data) {
        $("#submit").addClass("layui-btn-disabled");
        $("#submit").attr('disabled', 'disabled');
        data.field.status = data.field.status == undefined ? false : data.field.status;
        $.post(data.form.action, data.field, function (res) {
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
