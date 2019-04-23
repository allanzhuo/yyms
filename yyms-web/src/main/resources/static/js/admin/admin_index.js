layui.use(['element', 'layer'], function () {
    var $ = layui.$,
        element = layui.element,
        layer = layui.layer;
    element.render();
    element.on('nav(nav-icon)', function (elem) {
        var windowWidth = window.innerWidth;
        var event = $(elem).attr("layadmin-event");
        var select = $(elem).attr("lay-select");
        if (event === 'flexible') {
            if (select === undefined) {
                $(elem).attr("lay-select", "");
                if (windowWidth >= 993) {
                    $("body").removeClass("layadmin-side-spread-sm").addClass("layadmin-side-shrink");
                    $("#left-menu-temp").addClass("side-shrink");
                } else {
                    $("body").removeClass("layadmin-side-shrink").addClass("layadmin-side-spread-sm");
                    $("#left-menu-temp").removeClass("side-shrink");
                }
                $(elem).find(".layui-icon").removeClass("layui-icon-spread-right").addClass("layui-icon-spread-left");
            } else {
                $(elem).removeAttr("lay-select");
                $("body").removeClass("layadmin-side-shrink").removeClass("layadmin-side-spread-sm");
                $("#left-menu-temp").removeClass("side-shrink");
                $(elem).find(".layui-icon").removeClass("layui-icon-spread-left").addClass("layui-icon-spread-right");
            }
        }
        if (event === 'refresh') {
            location.reload();
        }
//            if (event === "comment") {
//                location.hash = vipspa.stringifyDefault("/comment");
//            }
    });

    element.on('nav(left-menu-temp)', function (elem) {
        $("#flexible").removeAttr("lay-select");
        $("body").removeClass("layadmin-side-shrink").removeClass("layadmin-side-spread-sm");
        $("#left-menu-temp").removeClass("side-shrink");
        $("#flexible-icon").removeClass("layui-icon-spread-left").addClass("layui-icon-spread-right");
    });

    $('*[lay-tips]').on('mouseenter', function () {
        var select = $("#flexible").attr("lay-select");
        if (select != undefined) {
            var content = $(this).attr('lay-tips');
            this.index = layer.tips('<div >' + content + '</div>', this, {
                time: -1,
                maxWidth: 280,
                tips: [2, '#000']
            });
        }
    }).on('mouseleave', function () {
        layer.close(this.index);
    });

    $.ajaxSetup({
        complete: function (XMLHttpRequest, textStatus) {
            if (textStatus == "parsererror") {
                layer.confirm('登陆超时！请重新登陆！', {}, function () {
                    top.location.href = '/login';
                }, function () {

                });
            } else if (textStatus == "error") {
                layer.alert('请求超时！请稍后再试！')
            }
        }
    });

});