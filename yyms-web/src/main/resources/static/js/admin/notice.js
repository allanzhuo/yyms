layui.use(['form', 'layer'], function () {
    var $ = layui.$
        , form = layui.form
        , layer = layui.layer;

    //监听提交
    form.on('submit(confirm-notice)', function(data){
        $.post("/admin/notice/save", data.field, function (res) {
            if (res.code == 200) {
                //建立连接对象（还未发起连接）
                var sock = new SockJS("/webSocketServer");
                // 获取 STOMP 子协议的客户端对象
                var stomp = Stomp.over(sock);
                var messageJson = JSON.stringify(data.field);
                stomp.connect('guest', 'guest', function(frame) {
                    stomp.send("/app/send", {}, messageJson);
                });

                layer.alert(res.msg, {icon: 1}, function (index) {
                    location.href = common.url.admin_index+'#notice_manage';
                    layer.close(index);
                    }
                )
            } else {
                layer.msg(res.msg);
            }
        });
        return false;
    });
});