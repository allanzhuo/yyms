layui.config({
    base: '/plus/lib/',
}).use(['form','treeTable'], function () {
    var $ = layui.$,
        form = layui.form,
        treeTable = layui.treeTable;

    treeTable.render({
        elem: '#menu-table'
        , url: "/admin/menu/list"
        //, data: [{"id":1,"pid":0,"title":null,"type":0,"icon":"layui-icon layui-icon-face-smile"},{"id":2,"pid":0,"title":"系统管理","type":0,"icon":"fa fa-send-o"},{"id":3,"pid":0,"title":"1-3","type":0,"icon":"layui-icon layui-icon-face-smile"},{"id":4,"pid":2,"title":"1-1-1","type":0,"icon":"layui-icon layui-icon-face-smile"},{"id":5,"pid":2,"title":"网站设置","type":1,"icon":"layui-icon layui-icon-face-smile"},{"id":6,"pid":2,"title":"1-2-1","type":1,"icon":"layui-icon layui-icon-face-smile"},{"id":7,"pid":2,"title":"1-2-3","type":1,"icon":"layui-icon layui-icon-face-smile"},{"id":8,"pid":3,"title":"1-3-1","type":1,"icon":"layui-icon layui-icon-face-smile"},{"id":9,"pid":3,"title":"1-3-2","type":1,"icon":"layui-icon layui-icon-face-smile"},{"id":10,"pid":4,"title":"1-1-1-1","type":1,"icon":"layui-icon layui-icon-face-smile"},{"id":11,"pid":4,"title":"1-1-1-2","type":1,"icon":"layui-icon layui-icon-face-smile"}]
        , icon_key: 'title'
        , is_checkbox: true
        // , checked: {
        //         key: 'id',
        //         data: [0,1,4,10,11,5,2,6,7,3,8,9],
        //     },
        ,end: function(e){
                form.render();
            }
        ,cols: [
            {
                key: 'id',
                title: '编号',
                width: '60px',
                align: 'center',
            },
            {
                key: 'title',
                title: '名称',
                template: function(item){
                    if(item.level == 0){
                        return '<span style="color:red;">'+item.title+'</span>';
                    }else if(item.level == 1){
                        return '<span style="color:green;">'+item.title+'</span>';
                    }else if(item.level == 2){
                        return '<span style="color:#aaa;">'+item.title+'</span>';
                    }
                }
            },
            {
                key: 'icon',
                title: '图标',
                width: '100px',
                align: 'center',
                template: function(item){
                    return "<i class='" + item.icon + "'></i>";
                }
            },
            {
                key: 'type',
                title: '类型',
                width: '100px',
                align: 'center',
                template: function(item){
                    if(item.type == 0){
                        return '<button class="layui-btn layui-btn-xs">目录</button>';
                    }else if(item.type == 1){
                        return '<button class="layui-btn layui-btn-xs layui-btn-normal">菜单</button>';
                    }else if(item.type == 2){
                        return '<button class="layui-btn layui-btn-xs layui-btn-warm">按钮</button>';
                    }
                }
            },
            {
                key: 'url',
                title: '地址',
                align: 'center',
            },
            {
                key: 'perms',
                title: '权限标识',
                align: 'center',
            },
            {
                key: 'status',
                title: '菜单状态',
                align: 'center',
                template: function(item){
                    if(item.status == 1){
                        return "<input type='checkbox' name='status' value='" + item.id + "' lay-filter='status' lay-skin='switch' lay-text='启用|禁用' checked>";
                    } else {
                        return "<input type='checkbox' name='status' value='" + item.id + "' lay-filter='status' lay-skin='switch' lay-text='启用|禁用'>";
                    }
                }
            },
            {
                title: '操作',
                align: 'center',
                template: function(item){
                    return '<a lay-filter="add">添加</a> | <a target="_blank" href="/detail?id='+item.id+'">编辑</a>';
                }
            }
        ]
    });

    form.on('switch(status)', function (obj) {
        var status = obj.elem.checked;
        $.post("/admin/menu/edit",{id:this.value, status: obj.elem.checked},function(res){
            if (res.code == 200) {
                layer.msg("修改状态成功");
            } else {
                layer.msg("修改失败,"+res.msg);
            }
        });
    });

    //搜索用户
    // $('#searchBtn').on('click', function() {
    //     var queryDate = $('#query-date').val();
    //     var startDate = queryDate.substring(0,10);
    //     var endDate = queryDate.substring(13,23);
    //     table.reload('menu-table', {
    //         page: {
    //             curr: 1
    //         },
    //         where: {
    //             startDate: startDate
    //             ,endDate: endDate
    //         }
    //     })
    // });
    //
    // $('#downBtn').on('click', function() {
    //     $("#queryForm").attr('action', '/analysis/repayment/download');
    //     $("#queryForm").submit();
    // });


});



