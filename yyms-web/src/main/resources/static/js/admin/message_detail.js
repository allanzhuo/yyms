layui.use('layer', function () {
    var $ = layui.$
        , layer = layui.layer;

    init();
    function init() {
        var obj = vipspa.parse();
        $.post("/admin/message/readDetail", {recordId: obj.param.recordId, noticeId: obj.param.noticeId}, function (res) {
            if (res.code == 200) {
                $("#notice-title").html(res.data[0].noticeTitle);
                $("#create-time").html(res.data[0].createTime);
                $("#notice-content").html(res.data[0].noticeContent);
            } else {
                layer.msg(res.msg, {icon:2});
            }
        });
    };

});