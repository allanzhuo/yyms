layui.use(['form', 'layer'], function () {
    var $ = layui.$
        , form = layui.form
        , layer = layui.layer;

    //监听提交
    form.on('submit(confirm-notice)', function(data){
        $.post("/admin/notice/save", data.field, function (res) {
            if (res.code == 200) {
                layer.alert(res.msg, {icon: 1}, function (index) {
                    location.href = common.url.admin_index+'#notice_manage';
                    layer.close(index);
                    }
                )
            } else {
                layer.msg(res.msg);
            }
        });
        return false;
    });
});