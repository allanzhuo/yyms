layui.config({
    base: '/plus/lib/',
}).use(['form','treeTable','layer'], function () {
    var form = layui.form,
        treeTable = layui.treeTable,
        layer = layui.layer;

    var	table = treeTable.render({
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
                key: 'sort',
                title: '排序',
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
                    return '<a class="layui-btn layui-btn-normal layui-btn-sm" lay-filter="add">添加</a>'
                    +'<a class="layui-btn layui-btn-warm layui-btn-sm" lay-filter="edit">编辑</a>';
                }
            }
        ]
    });

    form.on('switch(status)', function (obj) {
        var status = obj.elem.checked;
        $.post("/admin/menu/edit/status",{id:this.value, status: obj.elem.checked},function(res){
            if (res.code == 200) {
                layer.msg("修改状态成功");
            } else {
                layer.msg("修改失败,"+res.msg);
            }
        });
    });

    // 监听自定义
    treeTable.on('tree(add)',function(data){
        layer.open({
            type: 2,
            title: '添加菜单',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['700px', '480px'], //宽高
            content: "/admin/menu/add?pid="+data.item.id+"&title="+data.item.title
            ,success:function(layero, index) {
                form.render('select');
            }
        });
    });

    treeTable.on('tree(edit)',function(data){
        layer.open({
            type: 2,
            title: '编辑菜单',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['700px', '480px'], //宽高
            content: "/admin/menu/edit?id="+data.item.id
            ,success:function(layero, index) {
                form.render('select');
            }
        });
    });

    var $ = layui.$, active = {
        add: function () {
            layer.open({
                type: 2,
                title: '添加菜单',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                area: ['700px', '480px'], //宽高
                content: "/admin/menu/add?pid=0&title=顶级菜单"
                ,success:function(layero, index) {
                    form.render('select');
                }
            });
        },
        del: function () {
            var ids = treeTable.checked(table)
            layer.confirm("确认要删除选中的【" + ids.length + "】条数据吗?", function (index) {
                $.post("/admin/menu/remove", {ids: ids}, function (res) {
                    if (res.code === 200) {
                        layer.msg(res.msg);
                        refresh();
                    } else {
                        layer.msg(res.msg);
                    }
                });
                layer.close(index);
            });
        }
    };

    $('#table-tools').find('.layui-btn').off('click').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        return false;
    });

    function refresh(){
        treeTable.render(table);
    }

    window.refresh = refresh;
});



