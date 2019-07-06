layui.use(['form', 'layer'], function () {
    var $ = layui.$
        , form = layui.form
        , layer = layui.layer;

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