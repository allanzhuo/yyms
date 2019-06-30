layui.use(['form', 'tree'], function () {
    var $ = layui.$,
        form = layui.form,
        tree = layui.tree;

    tree.render({
        elem: '#role-tree'
        , data: [{
            title: '一级1'
            , id: 1
            , checked: true
            , spread: true
            , children: [{
                title: '二级1-1 可允许跳转'
                , id: 3
                , href: 'https://www.layui.com/'
                , children: [{
                    title: '三级1-1-3'
                    , id: 23
                    , children: [{
                        title: '四级1-1-3-1'
                        , id: 24
                        , children: [{
                            title: '五级1-1-3-1-1'
                            , id: 30
                        }, {
                            title: '五级1-1-3-1-2'
                            , id: 31
                        }]
                    }]
                }, {
                    title: '三级1-1-1'
                    , id: 7
                    , children: [{
                        title: '四级1-1-1-1 可允许跳转'
                        , id: 15
                        , href: 'https://www.layui.com/doc/'
                    }]
                }, {
                    title: '三级1-1-2'
                    , id: 8
                    , children: [{
                        title: '四级1-1-2-1'
                        , id: 32
                    }]
                }]
            }, {
                title: '二级1-2'
                , id: 4
                , spread: true
                , children: [{
                    title: '三级1-2-1'
                    , id: 9
                    , disabled: true
                }, {
                    title: '三级1-2-2'
                    , id: 10
                }]
            }, {
                title: '二级1-3'
                , id: 20
                , children: [{
                    title: '三级1-3-1'
                    , id: 21
                }, {
                    title: '三级1-3-2'
                    , id: 22
                }]
            }]
        }, {
            title: '一级2'
            , id: 2
            , spread: true
            , children: null
        }, {
            title: '一级3'
            , id: 16
            , children: [{
                title: '二级3-1'
                , id: 17
                , fixed: true
                , children: [{
                    title: '三级3-1-1'
                    , id: 18
                }, {
                    title: '三级3-1-2'
                    , id: 19
                }]
            }, {
                title: '二级3-2'
                , id: 27
                , children: [{
                    title: '三级3-2-1'
                    , id: 28
                }, {
                    title: '三级3-2-2'
                    , id: 29
                }]
            }]
        }]
        , showCheckbox: true  //是否显示复选框
        , id: 'role-tree'
        , isJump: true //是否允许点击节点时弹出新窗口跳转
    });

    form.on('submit(role-add)', function (data) {
        $("#role-add").addClass("layui-btn-disabled");
        $("#role-add").attr('disabled', 'disabled');
        var checkData = tree.getChecked('role-tree');
        if (checkData.length == 0) {
            layer.msg('请选择权限范围', {time: 3000, icon:0});
            return;
        }
        var menuIds = new Array();
        var childrenIds = new Array();
        var buttonIds = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        for (var i = 0; i < checkData.length; i++) {
            menuIds[i] = checkData[i].id;
            if(checkData[i].children != null){
                for (var j = 0; j < checkData[i].children.length; j++) {
                    childrenIds[j] = checkData[i].children[j].id;
                    if(checkData[i].children[j].children != null){
                        for (var k = 0; k < checkData[i].children[j].children.length; k++) {
                            buttonIds[k] = checkData[i].children[j].children[k].id;
                        }
                    }
                }
            }
        }
        data.field.menuIds = menuIds.concat(childrenIds).concat(buttonIds);
        $.post(data.form.action, data.field, function (res) {
            if (res.code == 200) {
                layer.alert(res.msg, {}, function () {
                        var curIfr = parent.layer.getFrameIndex(window.name);
                        parent.layui.table.reload('role-table',{page: {curr: 1}});
                        parent.layer.close(curIfr);
                    }
                )
            } else {
                layer.msg(res.msg, {icon:2});
                $("#submit").removeClass("layui-btn-disabled");
                $("#submit").removeAttr('disabled');
            }
        });
    });

});
