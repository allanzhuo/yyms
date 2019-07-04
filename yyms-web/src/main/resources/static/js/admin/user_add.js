layui.use(['form', 'layer'], function () {
    var $ = layui.$
        , form = layui.form
        , layer = layui.layer;

    //监听提交
    form.on('submit(user-add)', function(data){
        $("#submit").addClass("layui-btn-disabled");
        $("#submit").attr('disabled', 'disabled');
        $.post(data.form.action, data.field, function (res) {
            if (res.code == 200) {
                layer.alert(res.msg, {}, function () {
                        var curIfr = parent.layer.getFrameIndex(window.name);
                        parent.layui.table.reload('user-table', {page: {curr: 1}});
                        parent.layer.close(curIfr);
                    }
                )
            } else {
                layer.msg(res.msg, {icon: 2});
                $("#submit").removeClass("layui-btn-disabled");
                $("#submit").removeAttr('disabled');
            }
        });
        return false;
    });
});