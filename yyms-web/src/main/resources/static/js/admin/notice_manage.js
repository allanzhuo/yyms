layui.use(['form', 'table', 'layer','laydate'], function () {
    var $ = layui.$
        , table = layui.table
        , form = layui.form
        , layer = layui.layer
        , laydate = layui.laydate;

    laydate.render({
        elem: '#createTime'
        ,type: 'datetime'
        ,range: true
    });

    var noticeTable = table.render({
        elem: '#notice-table'
        , url: "/admin/notice/list"
        , cellMinWidth: 80
        , method: "post"
        , cols: [[
            {type:'checkbox'}
            ,{type: 'numbers'}
            ,{field: 'noticeTitle', width: 150, title: '标题', align:'center'}
            , {field: 'noticeContent', width: '40%', title: '内容', align:'center'}
            , {title: '状态', width: 100,  align:'center' , toolbar: '#statusBar'}
            , {field: 'createUser', width: 150, title: '创建人',align:'center'}
            , {field: 'createTime', width: 200, title: '创建时间',align:'center'}
            , {field: 'updateUser', width: 150, title: '修改人',align:'center'}
            , {field: 'updateTime', width: 200, title: '修改时间',align:'center'}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#noticeBar'}
        ]]
        , page: true
    });
    // var $ = layui.$, active = {
    //     reload: function () {
    //         var username = $('#log-username-search');
    //         var operation = $('#log-operation-search');
    //         //执行重载
    //         table.reload('log-table', {
    //             page: {
    //                 curr: 1 //重新从第 1 页开始
    //             }
    //             , where: {
    //                 username: username.val(),
    //                 operation: operation.val()
    //             }
    //         });
    //     },
    //     removeBatch: function () {
    //         var checkStatus = table.checkStatus('log-table');
    //         var data = checkStatus.data;
    //         if (data.length == 0) {
    //             layer.msg("请选择要删除的数据");
    //             return;
    //         }
    //         layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
    //             var ids = new Array();
    //             // 遍历所有选择的行数据，取每条数据对应的ID
    //             for (var i = 0; i < data.length; i++) {
    //                 ids[i] = data[i].id;
    //             }
    //             common.ajax(common.url.prefix + "/log/removeBatch", {ids: ids}, function (json) {
    //                 if (json.code === common.status.ok) {
    //                     layer.msg('删除成功！');
    //                     active.reload();
    //                 } else {
    //                     layer.msg("删除失败！" + json.message);
    //                 }
    //             });
    //             layer.close(index);
    //         });
    //     }
    // };
    //

    //监听提交
    form.on('submit(btn-search)', function(data){
        //执行重载
        table.reload('notice-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                startDate: data.field.createTime.substring(0,19),
                endDate: data.field.createTime.substring(22,41),
                noticeTitle: data.field.noticeTitle
            }
        });
        return false;
    });

    form.on('switch(noticeStatus)', function (obj) {
        $.post("/admin/notice/edit/status",{id:this.value, noticeStatus: obj.elem.checked},function(res){
            if (res.code == 200) {
                layer.msg("修改状态成功");
            } else {
                layer.msg("修改失败,"+res.msg);
            }
        });
    });

    // $('#log-table-search').find('.layui-btn').on('click', function () {
    //     var type = $(this).data('type');
    //     active[type] ? active[type].call(this) : '';
    // });
    //
    // //监听工具条
    // table.on('tool(log)', function (obj) {
    //     var data = obj.data;
    //     if (obj.event === 'del') {
    //         layer.confirm('确认删除吗？', function (index) {
    //             common.ajax(common.url.prefix + "/log/remove/" + data.id, {}, function (json) {
    //                 if (json.code === common.status.ok) {
    //                     obj.del();
    //                     layer.msg('删除成功！')
    //                 } else {
    //                     layer.msg("删除失败！" + json.message);
    //                 }
    //             });
    //             layer.close(index);
    //         });
    //     }
    // });

});