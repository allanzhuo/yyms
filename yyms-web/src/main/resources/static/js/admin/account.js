layui.use(['layer', 'form', 'upload'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload;

    //监听提交
    form.on('submit(set-account)', function (data) {
        data.field.avatar = $("#avatar").find("img").attr("src");
        $.post('/admin/account/edit', data.field, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon: 1});
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        });
        return false;
    });

    upload.render({
        elem: '#avatar' //绑定元素
        , url: '/admin/account/avatar/upload/' //上传接口
        , done: function (res) {
            if (res.code === 200) {
                $("#avatar").find("img").attr("src", res.data);
            }
            layer.msg(res.msg, {icon: 1});
        }
        , error: function () {
            layer.msg("上传失败！", {icon:2});
        }
    });
});
