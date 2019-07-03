layui.use(['form', 'table', 'layer', 'tree'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , layer = layui.layer;
    
    table.render({
        elem: '#user-table'
        , url: "/admin/user/list"
        , cellMinWidth: 80
        , method: "post"
        , cols: [[
            {type: 'checkbox'}
            , {type: 'numbers'}
            , {field: 'userName', width: 150, title: '用户名', align: 'center'}
            , {field: 'nickName', width: 150, title: '昵称', align: 'center'}
            , {field: 'email', width: 150, title: '邮箱', align: 'center'}
            , {field: 'roleName', width: 150, title: '角色', align: 'center'}
            , {title: '状态', width: 100, align: 'center', toolbar: '#statusBar'}
            , {field: 'createUser', width: 150, title: '创建人', align: 'center'}
            , {field: 'createTime', width: 200, title: '创建时间', align: 'center'}
            , {field: 'updateUser', width: 150, title: '修改人', align: 'center'}
            , {field: 'updateTime', width: 200, title: '修改时间', align: 'center'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#userBar'}
        ]]
        , page: true
    });

    //监听提交
    form.on('submit(btn-search)', function (data) {
        //执行重载
        table.reload('user-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                nickName: data.field.nickName
            }
        });
        return false;
    });

    form.on('switch(status)', function (obj) {
        $.post("/admin/user/edit/status", {id: this.value, status: obj.elem.checked}, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon:1});
            } else {
                layer.msg("修改失败," + res.msg, {icon:2});
            }
        });
    });

    //监听工具条
    table.on('tool(user-table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                $.post("/admin/user/remove", {id: data.id}, function (res) {
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon:1});
                        var nickName = $("#nickName").val();
                        table.reload('user-table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                nickName: nickName
                            }
                        });
                    } else {
                        layer.msg(res.msg, {icon:2});
                    }
                });
                layer.close(index);
            });
        } else {
            layer.open({
                type: 2,
                title: '编辑角色',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                area: ['500px', '480px'], //宽高
                content: "/admin/role/edit?id="+data.id
                ,success:function(layero, index) {
                    form.render();
                }
            });
        }
    });

    var active = {
        add: function () {
            layer.open({
                type: 5,
                title: '添加用户',
                shadeClose: true,
                area: ['500px', '480px'], //宽高
                content: $("#user-edit-tpl").html()
                ,success:function(layero, index) {
                 form.render();
                }
            });
        },
        del: function () {
            var checkStatus = table.checkStatus('user-table');
            var data = checkStatus.data;
            if (data.length == 0) {
                layer.msg('请先选择要删除的数据', {time: 3000, icon:0});
                return;
            }
            layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
                var ids = new Array();
                // 遍历所有选择的行数据，取每条数据对应的ID
                for (var i = 0; i < data.length; i++) {
                    ids[i] = data[i].id;
                }
                $.post("/admin/user/removeBatch", {ids: ids}, function (res) {
                    if (res.code === 200) {
                        layer.msg(res.msg, {icon:1});
                        //执行重载
                        var nickName = $("#nickName").val();
                        table.reload('user-table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                nickName: nickName
                            }
                        });
                    } else {
                        layer.msg(res.msg, {icon:2});
                    }
                });
                layer.close(index);
            });
        }
    };

    $('#table-tools').find('.layui-btn').off('click').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        return false;
    });


});