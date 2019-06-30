layui.use(['form', 'tree'], function () {
    var $ = layui.$,
        form = layui.form,
        tree = layui.tree;

    var roleCode = $("#roleCode").val();
    $.post("/admin/menu/grant", {roleCode: roleCode}, function (res) {
        if (res.code == 200) {
            tree.render({
                elem: '#role-tree'
                , data: res.data
                , showCheckbox: true  //是否显示复选框
                , id: 'role-tree'
            });
        } else {
            layer.msg(res.msg, {icon:2});
        }
    });


    form.on('submit(role-add)', function (data) {
        $("#role-add").addClass("layui-btn-disabled");
        $("#role-add").attr('disabled', 'disabled');
        var checkData = tree.getChecked('role-tree');
        if (checkData.length == 0) {
            layer.msg('请选择权限范围', {time: 3000, icon:0});
            $("#role-add").removeClass("layui-btn-disabled");
            $("#role-add").removeAttr('disabled');
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
                $("#role-add").removeClass("layui-btn-disabled");
                $("#role-add").removeAttr('disabled');
            }
        });
    });

});
