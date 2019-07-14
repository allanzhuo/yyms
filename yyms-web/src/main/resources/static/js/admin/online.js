layui.use(['form', 'table'], function () {
    var $ = layui.$,
        table = layui.table
        , form = layui.form;

    table.render({
        elem: '#online-table'
        , url: "/admin/online/list"
        , cellMinWidth: 80
        , method: "post"
        , cols: [[
            {type: 'numbers'}
            , {field: 'sessionId', width: '20%', title: 'SessionID', align: 'center'}
            , {field: 'userName', width: '10%', title: '用户名', align: 'center'}
            , {field: 'ip', width: '12%', title: 'ip地址', align: 'center'}
            , {field: 'startTimestamp', width: '16%', title: '登录时间', align: 'center'}
            , {field: 'lastAccessTime', width: '16%', title: '最后访问时间', align: 'center'}
            , {field: 'timeout', width: '8%', title: '过期时间', align: 'center'}
            , {
                field: 'status', title: '状态', align: 'center', templet: function (d) {
                    return '<span class="layui-badge layui-bg-blue">在线</span>';
                }
            }
            , {title: '操作', width: 120, align: 'center', toolbar: '#onlineBar'}
        ]]
        , page: true
    });

    //监听提交
    form.on('submit(btn-search)', function (data) {
        //执行重载
        table.reload('online-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                userName: data.field.userName
            }
        });
        return false;
    });

    //监听工具条
    table.on('tool(online-table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'force') {
            layer.confirm('确认强制退出吗？', function (index) {
                $.post("/admin/online/remove/" + data.sessionId, {}, function (res) {
                    if (res.code === 200) {
                        obj.del();
                        layer.msg(res.msg, {icon: 1});
                    } else {
                        layer.msg(res.msg, {icon: 2});
                    }
                });
                layer.close(index);
            });
        }
    });

});