var common = {
    $: layui.$
    , layui: {}
    , status: {
        ok: 200
    }
    , url: {
        admin_index: "/admin"
    }

};

$.ajaxSetup({
    complete:function(XMLHttpRequest,textStatus){
        if(textStatus=="parsererror"){
            layer.confirm('登陆超时！请重新登陆！', {
            }, function(){
                top.location.href = '/login';
            }, function(){

            });
        } else if(textStatus=="error"){
            layer.alert('请求超时！请稍后再试！')
        }
    }
});