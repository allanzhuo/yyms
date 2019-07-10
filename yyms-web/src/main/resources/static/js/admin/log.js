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
            ,{field: 'userId', width: '15%', title: '用户ID', align:'center'}
            , {field: 'username', width: '10%', title: '用户名', align:'center'}
            , {field: 'operation', width: '10%', title: '操作', align:'center'}
            , {field: 'time', width: '8%', title: '用时', align:'center'}
            , {field: 'method', width: '37%', title: '方法', align:'center'}
            , {field: 'params', width: '15%', title: '参数', align:'center'}
            , {field: 'ip', width: '15%', title: 'IP地址', align:'center'}
            , {field: 'createTime', width: '17%', title: '创建时间', align:'center'}
            , {title: '操作', width: 100, align: 'center', toolbar: '#logBar'}
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
                nickName: data.field.nickName,
                operation: data.field.operation
            }
        });
        return false;
    });

});