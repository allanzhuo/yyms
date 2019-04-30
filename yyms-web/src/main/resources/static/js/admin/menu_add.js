layui.use(['form','laydate'], function () {
    var $ = layui.$,
        form = layui.form;


    // laydate.render({
    //     elem:'#layui-date-start',
    //     isInitValue: true,
    //     type: 'datetime'
    // });
    // laydate.render({
    //     elem:'#layui-date-end',
    //     isInitValue: true,
    //     type: 'datetime'
    // });
    // laydate.render({
    //     elem:'#layui-date-remnant',
    //     isInitValue: true,
    //     type: 'datetime'
    // });
    // laydate.render({
    //     elem:'#layui-date-appoint',
    //     isInitValue: true,
    //     type: 'datetime'
    // });
    // form.on('submit(recordAdd)',function(data){
    //     $("#submit").addClass("layui-btn-disabled");
    //     $("#submit").attr('disabled', 'disabled');
    //     $.post(data.form.action,data.field,function(res){
    //         if(res.code == 0){
    //             //larryms.msg(res.msg);
    //             //重置表单
    //            /* $('#recordAddBox')[0].reset();*/
    //             layer.alert('新增服务记录成功！',{
    //             }, function(){
    //                 //top.document.getElementById('tabCtrA').click();
    //                 var curIfr = parent.layer.getFrameIndex(window.name);
    //                 parent.layer.close(curIfr);
    //             }
    //             )
    //         }else{
    //             larryms.msg(res.msg);
    //         }
    //     });
    //
    //     return false;
    // });

    // form.on('switch(status)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".we-chat-info").removeClass("layui-hide");
    //         $('.wx-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".we-chat-info").addClass("layui-hide");
    //         $(".wx-status").removeAttr("lay-verify");
    //     }
    // });
    // form.on('switch(remnantSwitch)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".remnant-info").removeClass("layui-hide");
    //         $('.remnant-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".remnant-info").addClass("layui-hide");
    //         $(".remnant-status").removeAttr("lay-verify");
    //     }
    // });
    // form.on('switch(appointSwitch)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".appoint-info").removeClass("layui-hide");
    //         $('.appoint-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".appoint-info").addClass("layui-hide");
    //         $(".appoint-status").removeAttr("lay-verify");
    //     }
    // });
    // form.on('switch(specialSwitch)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".special-info").removeClass("layui-hide");
    //         $('.special-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".special-info").addClass("layui-hide");
    //         $(".special-status").removeAttr("lay-verify");
    //     }
    // });
    // form.on('switch(bgSwitch)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".cbg-info").removeClass("layui-hide");
    //         $('.cbg-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".cbg-info").addClass("layui-hide");
    //         $(".cbg-status").removeAttr("lay-verify");
    //     }
    // });
    // form.on('switch(wxgSwitch)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".wxg-info").removeClass("layui-hide");
    //         $('.wxg-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".wxg-info").addClass("layui-hide");
    //         $(".wxg-status").removeAttr("lay-verify");
    //     }
    // });
    // form.on('switch(complainSwitch)', function (data) {
    //     var s = data.elem.checked;
    //     if (s) {
    //         $(".complain-info").removeClass("layui-hide");
    //         $('.complain-status').attr('lay-verify', 'required');
    //     } else {
    //         $(".complain-info").addClass("layui-hide");
    //         $(".complain-status").removeAttr("lay-verify");
    //     }
    // });
});
