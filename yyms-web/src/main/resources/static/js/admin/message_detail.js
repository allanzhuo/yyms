layui.use(['table', 'layer'], function () {
    var $ = layui.$
        , table = layui.table
        , layer = layui.layer;


    read();
    function read() {
        var obj = vipspa.parse();
        console.log(obj);
        $.post("/admin/message/read", {recordId: obj.param.recordId, noticeId: obj.param.noticeId}, function (res) {
            if (res.code == 200) {
                $("#notice-title").html(res.data[0].noticeTitle);
                $("#create-time").html(res.data[0].createTime);
                $("#notice-content").html(res.data[0].noticeContent);
            } else {
                layer.msg("修改失败," + res.msg);
            }
        });
    };
    //监听提交
    // form.on('submit(btn-search)', function (data) {
    //     //执行重载
    //     table.reload('notice-table', {
    //         page: {
    //             curr: 1 //重新从第 1 页开始
    //         }
    //         , where: {
    //             startDate: data.field.queryDate.substring(0, 19),
    //             endDate: data.field.queryDate.substring(22, 41),
    //             noticeTitle: data.field.noticeTitle
    //         }
    //     });
    //     return false;
    // });
    //
    // form.on('switch(noticeStatus)', function (obj) {
    //     $.post("/admin/notice/edit/status", {id: this.value, noticeStatus: obj.elem.checked}, function (res) {
    //         if (res.code == 200) {
    //             layer.msg("修改状态成功");
    //         } else {
    //             layer.msg("修改失败," + res.msg);
    //         }
    //     });
    // });
    //

    //
    // $("#remove-batch").on("click", function () {
    //     var checkStatus = table.checkStatus('notice-table');
    //     var data = checkStatus.data;
    //     if (data.length == 0) {
    //         layer.msg('请先选择要删除的数据', {time: 3000, icon:0});
    //         return;
    //     }
    //     layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
    //         var ids = new Array();
    //         // 遍历所有选择的行数据，取每条数据对应的ID
    //         for (var i = 0; i < data.length; i++) {
    //             ids[i] = data[i].id;
    //         }
    //         $.post("/admin/notice/removeBatch", {ids: ids}, function (res) {
    //             if (res.code == 200) {
    //                 layer.msg(res.msg, {icon:1});
    //                 //执行重载
    //                 var queryDate = $("#queryDate").val();
    //                 var noticeTitle = $("#noticeTitle").val();
    //                 table.reload('notice-table', {
    //                     page: {
    //                         curr: 1 //重新从第 1 页开始
    //                     }
    //                     , where: {
    //                         startDate: queryDate.substring(0, 19),
    //                         endDate: queryDate.substring(22, 41),
    //                         noticeTitle: noticeTitle
    //                     }
    //                 });
    //             } else {
    //                 layer.msg(res.msg, {icon:2});
    //             }
    //         });
    //         layer.close(index);
    //     });
    // });

});