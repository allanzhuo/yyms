layui.use(['table', 'layer'], function () {
    var table = layui.table
        , layer = layui.layer;

    table.render({
        elem: '#message-table'
        , url: "/admin/message/list"
        , cellMinWidth: 80
        , method: "post"
        , cols: [[
            {type: 'checkbox'}
            , {type: 'numbers'}
            , {field: 'noticeTitle', width: '41%', title: '标题', align: 'center', toolbar: '#titleTpl'}
            , {field: 'createTime', width: '41%', title: '发布时间', align: 'center'}
            , {title: '状态', width: 100, align: 'center', toolbar: '#messageStatus'}
        ]]
        , page: true
    });

    //监听工具条
    table.on('tool(message-table)', function (obj) {
        var data = obj.data;
        location.hash = vipspa.stringify('message_detail',{recordId: data.recordId,noticeId: data.noticeId});
    });

    var $ = layui.$, active = {
        read: function () {
            var checkStatus = table.checkStatus('message-table');
            var data = checkStatus.data;
            if (data.length == 0) {
                layer.msg('请先选择需要标记的通知', {time: 3000, icon:0});
                return;
            }
            var ids = new Array();
            // 遍历所有选择的行数据，取每条数据对应的ID
            for (var i = 0; i < data.length; i++) {
                ids[i] = data[i].recordId.substr(0,data[i].recordId.indexOf("S"));
            }
            $.post("/admin/message/read", {ids: ids}, function (res) {
                if (res.code == 200) {
                    if (res.data.length == 0){
                        $("#notice-dot").removeClass("layui-badge-dot");
                        $("#unread").removeClass("layui-badge");
                        $("#unread").html("");
                    } else {
                        $("#unread").html(res.data.length);
                    }
                    layer.msg(res.msg, {icon:1});
                    table.reload('message-table', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                } else {
                    layer.msg(res.msg, {icon:2});
                }
            });
        },
        readAll: function () {
            $.post("/admin/message/readAll", function (res) {
                if (res.code == 200) {
                    $("#notice-dot").removeClass("layui-badge-dot");
                    $("#unread").removeClass("layui-badge");
                    $("#unread").html("");
                    layer.msg(res.msg, {icon:1});
                    table.reload('message-table', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                } else {
                    layer.msg(res.msg, {icon:2});
                }
            });
        },
        del: function () {
            var checkStatus = table.checkStatus('message-table');
            var data = checkStatus.data;
            if (data.length == 0) {
                layer.msg('请先选择要删除的数据', {time: 3000, icon:0});
                return;
            }
            layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
                var ids = new Array();
                // 遍历所有选择的行数据，取每条数据对应的ID
                for (var i = 0; i < data.length; i++) {
                    ids[i] = data[i].recordId.substr(0,data[i].recordId.indexOf("S"));
                }
                $.post("/admin/message/removeBatch", {ids: ids}, function (res) {
                    if (res.code == 200) {
                        if (res.data.length == 0){
                            $("#notice-dot").removeClass("layui-badge-dot");
                            $("#unread").removeClass("layui-badge");
                            $("#unread").html("");
                        } else {
                            $("#unread").html(res.data.length);
                        }
                        layer.msg(res.msg, {icon:1});
                        //执行重载
                        table.reload('message-table', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                    } else {
                        layer.msg(res.msg, {icon:2});
                    }
                });
                layer.close(index);
            });
        },

    };

    $('#message-center-btns').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    init();
    function init() {
        $.post("/admin/message/center/unread", function (res) {
            if (res != 0){
                $("#unread").addClass("layui-badge");
                $("#unread").html(res);
            } else {
                $("#notice-dot").removeClass("layui-badge-dot");
            }
        });
    };

});