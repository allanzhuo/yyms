layui.use(['form', 'layer'], function () {
    var $ = layui.$
        , form = layui.form
        , layer = layui.layer;

    //监听提交
    form.on('submit(confirm-notice)', function(data){
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
        return false;
    });
});