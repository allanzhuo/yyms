layui.use(['form', 'table', 'layer', 'laydate'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , layer = layui.layer
        , laydate = layui.laydate;

    laydate.render({
        elem: '#createTime'
        , type: 'datetime'
        , range: true
    });

    table.render({
        elem: '#notice-table'
        , url: "/admin/notice/list"
        , cellMinWidth: 80
        , method: "post"
        , cols: [[
            {type: 'checkbox'}
            , {type: 'numbers'}
            , {field: 'noticeTitle', width: 150, title: '标题', align: 'center'}
            , {field: 'noticeContent', width: '40%', title: '内容', align: 'center'}
            , {title: '状态', width: 100, align: 'center', toolbar: '#statusBar'}
            , {field: 'createUser', width: 150, title: '创建人', align: 'center'}
            , {field: 'createTime', width: 200, title: '创建时间', align: 'center'}
            , {field: 'updateUser', width: 150, title: '修改人', align: 'center'}
            , {field: 'updateTime', width: 200, title: '修改时间', align: 'center'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#noticeBar'}
        ]]
        , page: true
    });

    //监听提交
    form.on('submit(btn-search)', function (data) {
        //执行重载
        table.reload('notice-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                startDate: data.field.queryDate.substring(0, 19),
                endDate: data.field.queryDate.substring(22, 41),
                noticeTitle: data.field.noticeTitle
            }
        });
        return false;
    });

    form.on('switch(noticeStatus)', function (obj) {
        $.post("/admin/notice/edit/status", {id: this.value, noticeStatus: obj.elem.checked}, function (res) {
            if (res.code == 200) {
                layer.msg("修改状态成功");
            } else {
                layer.msg("修改失败," + res.msg);
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
                        obj.del();
                        layer.msg('删除成功！')
                    } else {
                        layer.msg(res.msg);
                    }
                });
                layer.close(index);
            });
        }
    });

    $("#remove-batch").on("click", function () {
        var checkStatus = table.checkStatus('notice-table');
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
            $.post("/admin/notice/removeBatch", {ids: ids}, function (res) {
                if (res.code == 200) {
                    layer.msg(res.msg, {icon:1});
                    //执行重载
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
    });

});