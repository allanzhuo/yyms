layui.use(['form', 'table'], function () {
    var $ = layui.$,
        table = layui.table
        , form = layui.form;

    table.render({
        elem: '#log-table'
        , height: 'full'
        , url: "/admin/log/list"
        , cellMinWidth: 90
        , limit: 10
        , size: 'lg'
        , method: "post"
        , cols: [[
            {type:'checkbox'}
            ,{type: 'numbers'}
            , {field: 'userName', width: '10%', title: '用户名', align:'center'}
            , {field: 'operation', width: '10%', title: '用户操作', align:'center'}
            , {field: 'responseTime', width: '8%', title: '响应时间', align:'center'}
            , {field: 'requestMethod', width: '37%', title: '请求方法', align:'center'}
            , {field: 'params', width: '15%', title: '请求参数', align:'center'}
            , {field: 'ip', width: '15%', title: 'IP地址', align:'center'}
            , {field: 'createTime', width: '17%', title: '操作时间', align:'center'}
        ]]
        , page: true
    });

    //监听提交
    form.on('submit(btn-search)', function (data) {
        //执行重载
        table.reload('log-table', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                userName: data.field.userName,
                operation: data.field.operation
            }
        });
        return false;
    });

});