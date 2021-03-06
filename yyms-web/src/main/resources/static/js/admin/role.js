layui.use(['form', 'table', 'layer', 'tree'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , layer = layui.layer;
    
    table.render({
        elem: '#role-table'
        , url: "/admin/role/list"
        , cellMinWidth: 80
        , method: "post"
        , cols: [[
            {type: 'checkbox'}
            , {type: 'numbers'}
            , {field: 'roleName', width: 150, title: '角色名称', align: 'center'}
            , {field: 'roleCode', width: 150, title: '角色编码', align: 'center'}
            , {field: 'remark', width: '40%', title: '备注', align: 'center'}
            , {title: '状态', width: 100, align: 'center', toolbar: '#statusBar'}
            , {field: 'createUser', width: 150, title: '创建人', align: 'center'}
            , {field: 'createTime', width: 200, title: '创建时间', align: 'center'}
            , {field: 'updateUser', width: 150, title: '修改人', align: 'center'}
            , {field: 'updateTime', width: 200, title: '修改时间', align: 'center'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#roleBar'}
        ]]
        , page: true
    });

    //监听提交
    form.on('submit(btn-search)', function (data) {
        //执行重载
        table.reload('role-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                roleName: data.field.roleName
            }
        });
        return false;
    });

    form.on('switch(roleStatus)', function (obj) {
        $.post("/admin/role/edit/status", {id: this.value, roleStatus: obj.elem.checked}, function (res) {
            if (res.code == 200) {
                layer.msg(res.msg, {icon:1});
            } else {
                layer.msg("修改失败," + res.msg, {icon:2});
            }
        });
    });

    //监听工具条
    table.on('tool(role-table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                $.post("/admin/role/remove", {id: data.id}, function (res) {
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon:1});
                        var roleName = $("#roleName").val();
                        table.reload('role-table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                roleName: roleName
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
                type: 2,
                title: '添加角色',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                area: ['500px', '480px'], //宽高
                content: "/admin/role/add"
                ,success:function(layero, index) {
                 form.render();
                }
            });
        },
        del: function () {
            var checkStatus = table.checkStatus('role-table');
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
                $.post("/admin/role/removeBatch", {ids: ids}, function (res) {
                    if (res.code === 200) {
                        layer.msg(res.msg, {icon:1});
                        //执行重载
                        var roleName = $("#roleName").val();
                        table.reload('role-table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                roleName: roleName
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