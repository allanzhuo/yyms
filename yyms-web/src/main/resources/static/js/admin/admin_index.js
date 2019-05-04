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

    init();
    function init(){
        var leftMenus;
        $.post("/admin/menu/init", function (res) {
            if (res.code == 200) {
                layer.alert(res.msg);
                for(let item in res.data) {
                    if(item.children === null){
                        leftMenus += '<li data-name="get" class="layui-nav-item">'+
                            '<a href="javascript:;" lay-href="//www.layui.com/admin/#get" lay-tips="'+item.title+'" lay-direction="2">'+
                            '<i class="layui-icon layui-icon-auz"></i>'+
                            '<cite>'+item.title+'</cite>'+
                            '</a>'+
                            '</li>';
                    } else {
                        leftMenus += '<li data-name="home" class="layui-nav-item layui-nav-itemed">'+
                            '<a href="javascript:;" lay-tips="'+item.title+'" lay-direction="2">'+
                            '<i class="layui-icon layui-icon-home"></i>'+
                            '<cite>'+item.title+'</cite>'+
                            '</a>'+
                            '<dl class="layui-nav-child">';
                            // for(let children in res.data.item.children){
                            //     leftMenus += '<dd data-name="console" class="layui-this">'+
                            //     '<a lay-href="home/console.html">'+children.title+'</a>'+
                            //     '</dd>';
                            // }
                        leftMenus += '</dl></li>';
                    }
                };
             var leftMenu = document.getElementById('left-menu-temp');
                leftMenu.innerHTML = leftMenus;
            }
        });
    }

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