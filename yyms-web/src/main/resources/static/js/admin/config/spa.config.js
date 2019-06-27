$(function () {
    var url_prefix = '/admin/';
    var js_prefix = '/js/admin/';
    vipspa.start({
        view: '#admin-body',
        router: {
            'console': {
                templateUrl: url_prefix + 'console',
                controller: js_prefix + 'console.js'
            },
            'menu': {
                templateUrl: url_prefix + 'menu',
                controller: js_prefix + 'menu.js'
            },
            'notice': {
                templateUrl: url_prefix + 'notice',
                controller: js_prefix + 'notice.js'
            },
            'notice_add': {
                templateUrl: url_prefix + 'notice/add',
                controller: js_prefix + 'notice_add.js'
            },
            'message_center': {
                templateUrl: url_prefix + 'message/center',
                controller: js_prefix + 'message_center.js'
            },
            'message_detail': {
                templateUrl: url_prefix + 'message/detail',
                controller: js_prefix + 'message_detail.js'
            },
            'account': {
                templateUrl: url_prefix + 'account',
                controller: js_prefix + 'account.js'
            },
            'password': {
                templateUrl: url_prefix + 'password',
                controller: js_prefix + 'password.js'
            },
            'options': {
                templateUrl: url_prefix + 'options',
                controller: js_prefix + 'options.js'
            },
            'tag': {
                templateUrl: url_prefix + 'tag',
                controller: js_prefix + '/tag.js'
            },
            'about': {
                templateUrl: url_prefix + 'about',
                controller: js_prefix + '/about.js'
            },
            'keyword': {
                templateUrl: url_prefix + 'keyword',
                controller: js_prefix + '/keyword.js'
            },
            'users': {
                templateUrl: url_prefix + 'user',
                controller: js_prefix + '/user.js'
            },
            'settings': {
                templateUrl: url_prefix + 'settings',
                controller: js_prefix + '/settings.js'
            },
            'qrcode': {
                templateUrl: url_prefix + 'settings/qrcode',
                controller: js_prefix + '/qrcode.js'
            },
            'comment': {
                templateUrl: url_prefix + 'comment',
                controller: js_prefix + '/comment.js'
            },
            'message': {
                templateUrl: url_prefix + 'message',
                controller: js_prefix + '/message.js'
            },
            'aboutblog': {
                templateUrl: url_prefix + 'about/blog',
                controller: js_prefix + '/noteblog.js'
            },
            'profile': {
                templateUrl: url_prefix + 'profile',
                controller: js_prefix + '/profile.js'
            },
            'task': {
                templateUrl: url_prefix + 'task',
                controller: js_prefix + '/task.js'
            },
            'log': {
                templateUrl: url_prefix + 'log',
                controller: js_prefix + '/log.js'
            },
            'online': {
                templateUrl: url_prefix + 'online',
                controller: js_prefix + '/online.js'
            },
            'defaults': 'console' //默认路由
        },
        errorTemplateId: '#error'
    });

});
