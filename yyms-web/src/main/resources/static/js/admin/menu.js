layui.config({
    base: '../../../larryms/',
}).extend({
    larry: 'js/base'
}).use('larry');
layui.use(['form', 'table'], function () {
    var $ = layui.$,
    form = layui.form,
    table = layui.table;

    laydate.render({
        elem: '#query-date'
        ,range: true
    });

    var queryDate = $('#query-date').val();
    var startDate = queryDate.substring(0,10);
    var endDate = queryDate.substring(13,23);
    table.render({
        elem: '#menu-table'
        , url: "/analysis/repayment/list"
        , cellMinWidth: 80
        , method: 'post'
        , where: {
            startDate: startDate
            ,endDate: endDate
        }
        ,cols: [ [
            {field:'returnDate', title: '日期',width: 150}
            ,{field:'customerName', title: '客户姓名',width: 150}
            ,{field:'celphone', title: '手机号',width: 150}
            ,{field:'login', title: '账号',width: 150}
            ,{field:'sex', title: '性别',width: 80}
            ,{field:'age', title: '年龄',width: 80}
            ,{field:'channelCode', title: '注册渠道',width: 150}
            ,{field:'createTime', title: '注册时间',width: 200}
            ,{field:'recommendName', title: '推荐人',width: 150}
            ,{field:'recommendCelphone', title: '推荐人手机号',width: 150}
            ,{field:'recommendLevel', title: '推荐级数',width: 150}
            ,{field:'customerType', title: '客户类别',width: 150}
            ,{field:'userType', title: '客户类型',width: 150}
            ,{field:'vip', title: '客户等级',width: 150}
            ,{field:'highestInvestAmount', title: '最高在投本金',width: 150, templet:'<span>{{moneyFormatter(d.highestInvestAmount)}}</span>'}
            ,{field:'presentShare', title: '在投本金',width: 150, templet:'<span>{{moneyFormatter(d.presentShare)}}</span>'}
            ,{field:'teamName', title: '所属业务团队',width: 150}
            ,{field:'investCode', title: '所属业务团队',width: 150}
            ,{field:'returnCapital', title: '回款本金',width: 200, templet:'<span>{{moneyFormatter(d.returnCapital)}}</span>'}
            ,{field:'returnProfit', title: '回款利息',width: 250, templet:'<span>{{moneyFormatter(d.returnProfit)}}</span>'}
            ,{field:'lastDueFlg', title: '是否最后一笔回款',width: 200}
        ] ]
        , page: true
    });

    //搜索用户
    $('#searchBtn').on('click', function() {
        var queryDate = $('#query-date').val();
        var startDate = queryDate.substring(0,10);
        var endDate = queryDate.substring(13,23);
        table.reload('menu-table', {
            page: {
                curr: 1
            },
            where: {
                startDate: startDate
                ,endDate: endDate
            }
        })
    });

    $('#downBtn').on('click', function() {
        $("#queryForm").attr('action', '/analysis/repayment/download');
        $("#queryForm").submit();
    });


});



