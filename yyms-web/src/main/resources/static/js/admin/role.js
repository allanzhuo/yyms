layui.use(['form', 'table', 'layer', 'laydate'], function () {
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
    table.on('tool(notice-table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                $.post("/admin/notice/remove", {id: data.id}, function (res) {
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon:1});
                        var queryDate = $("#queryDate").val();
                        var noticeTitle = $("#noticeTitle").val();
                        table.reload('notice-table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                            , where: {
                                startDate: queryDate.substring(0, 19),
                                endDate: queryDate.substring(22, 41),
                                noticeTitle: noticeTitle
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
                type: 1,
                title: '编辑通知',
                shadeClose: true,
                btnAlign: 'c',
                area: ['700px', '480px'], //宽高
                content: $("#notice-edit-tpl").html()
                ,success:function(layero, index) {
                    layero.find("#notice-title").val(data.noticeTitle);
                    layero.find("#notice-content").val(data.noticeContent);
                }
                ,btn: ['修改', '取消']
                ,yes: function(index, layero){
                    var noticeTitle = layero.find("#notice-title").val();
                    var noticeContent = layero.find("#notice-content").val();
                    $.post('/admin/notice/edit',{id: data.id,"noticeTitle": noticeTitle,"noticeContent": noticeContent,"noticeStatus": data.noticeStatus},function(res){
                        if (res.code == 200) {
                            layer.msg(res.msg, {icon:1});
                            layer.close(index);
                            var queryDate = $("#queryDate").val();
                            var noticeTitle = $("#noticeTitle").val();
                            table.reload('notice-table', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                                , where: {
                                    startDate: queryDate.substring(0, 19),
                                    endDate: queryDate.substring(22, 41),
                                    noticeTitle: noticeTitle
                                }
                            });
                        } else {
                            layer.msg(res.msg, {icon:2});
                        }
                    });
                }
            });
        }
    });

    var active = {
        add: function () {
            layer.open({
                type: 1,
                title: '添加菜单',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                area: ['500px', '480px'], //宽高
                content: $("#notice-edit-tpl").html()
                ,success:function(layero, index) {
                    form.render('select');
                }
            });
        },
        del: function () {
            var ids = treeTable.checked(table)
            if (ids.length == 0) {
                layer.msg('请先选择要删除的数据', {time: 3000, icon:0});
                return;
            }
            layer.confirm("确认要删除选中的【" + ids.length + "】条数据吗?", function (index) {
                $.post("/admin/menu/remove", {ids: ids}, function (res) {
                    if (res.code === 200) {
                        layer.msg(res.msg, {icon:1});
                        refresh();
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