layui.use(['table', 'layer'], function () {
    var $ = layui.$
        , table = layui.table
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
                layer.msg("修改失败," + res.msg);
            }
        });
    };

});