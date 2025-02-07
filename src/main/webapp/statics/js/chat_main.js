
//控制消息闪烁,新消息是否提示
var timeClear;

layui.define(['element','layer','jquery','layedit'], function(exports){
    var layer = layui.layer,
        $ = layui.jquery,
        element = layui.element,
        layedit = layui.layedit;

    setTimeout(function () {
        if (userInfo != null) {
            openWindow()
        }else {
            layui.login();
        }
    },300);

    function openWindow(){
        layer.open({
        type: 1,
        id: 'chat', //id唯一标识
        title: '<div id="app-title" >\n' +
            '            <div>\n' +
            '                <img src="/leek_bbs/'+userInfo.picture+'" class="img-circle" style=" padding:4px;width: 42px; height: 42px;" alt="头像加载失败" title="头像">\n' +
            '                <span class="u-class">'+userInfo.another_name+'</span><span class="layui-icon layui-icon-circle-dot m-bg-color"></span>\n' +
            '            </div>\n' +
            '            <div id="test0" class="layui-row">'+userInfo.personalized_sign+'</div>\n' +
            '            <div class="layui-row" id="tag_x">\n' +
            '                <span ><a id="grouping-show" class="m-border-bm" href="javascript:;" ><i class="layui-icon m-size">&#xe612;</i></a></span>\n' +
            '                <span >&emsp;&emsp;&emsp;<a id="group-show" href="javascript:void(0);"><i class="layui-icon m-size">&#xe613;</i></a></span>\n' +
            '                <span >&emsp;&emsp;&emsp;<a id="message-list-show" href="javascript:;"><i class="layui-icon m-size">&#xe611;</i></a></span>\n' +
            '            </div>\n' +
            '        </div>',
        content: '<iframe src="/leek_bbs/skipPage/ifram_chat" frameborder="0" id="demoChat" style="width: 100%; height: 361px;"></iframe>' +
            '<div class="box">\n' +
            '        <span style="margin-left: 15px;">\n' +
            '            <span><a href="javascript:;" id="search" title="搜索"><i class="layui-icon">&#xe615;</i></a></span>\n' +
            /*'            <span><a href="javascript:;" title="消息"><i class="layui-icon">&#xe645;</i></a></span>\n' +*/
            '<span class="dropup">\n' +
            '        <a href="javascrip:;" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" title="消息">\n' +
            '            <i class="layui-icon">&#xe645;</i>\n' +
            '        </a>\n' +
            '        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">\n' +
            '            <div style="">5</div>\n' +
            '        </div>\n' +
            '</span>'+
            '            <span><a href="javascript:;" id="findBtn" title="查找"><i class="layui-icon">&#xe608;</i></a></span>\n' +
            '            <span><a href="javascript:;" title="关于"><i class="layui-icon">&#xe60b;</i></a></span>\n' +
            '        </span>\n' +
            "<div id='m-rm' class='m-is-show'>" +
            "<input type='search' class='m-input' id='m-search'>" +
            "<div class='m-close'><a href='javascript:;' id='m-show'><i style='font-size: 22px;' class='layui-icon'>&#x1007;</i></a></div>"+
            "</div>"+
            '    </div>',
        /*skin: 'm-bg-color',*/
        anim: 2,    //动画
        shade: 0,
        fixed: false,
        /*move: false,*/
        offset: ['84px', '900px'],   //出现坐标,右下角
        closeBtn: 0,    //隐藏关闭按钮
        maxmin: false,   //最大化
        resize: false,  //关闭拉伸
        scrollbar: false,   //不允许出现滚动条
        area: ['264px', '533px'],
        moveEnd: function(layero){
            /*console.log(layero);*/

        },
        success: function(layero, index){
            $("#layui-layer"+index).find(".layui-layer-title").addClass("lay-title");
            //有消息闪烁
            timeClear = setInterval(function(){
                $(".dropup").addClass("open");
                setTimeout(function(){
                    $(".dropup").removeClass("open");
                },500);
            },700);
            //清除消息闪烁
            //clearInterval(timeClear);
        }

    });
    }
    //var html;
    //查找
    $(document).on('click','#findBtn',function(){
        find_friend_group();
    });

    //打开搜索
    $(document).on('click','#search',function(){
        $(".box > span").addClass('m-is-show');
        $("#m-rm").removeClass('m-is-show');
    });
    //监听搜索
    $('#m-search').bind('input propertychange', function() {
        console.log(1111111111)
        var $doc = $('iframe').contents();
        //获取搜索框内容
        var val = $('#m-search').val();
        console.log(val);
        //清除搜索列表内容
        $doc.find("#search-list").empty();
        //移除下划线标识
        traversal_x();
        //标识是否有搜索结果
        var num = 0;
        if (val != null && val != ''){
            //遍历所有好友列表
            $doc.find("#grouping > div > ul > li").each(function () {
                $(this).find("dl > dd").each(function () {
                    var text = $(this).text();
                    if (text.includes(val)){
                        $doc.find("#search-list").append("<div>"+$(this).html()+"</div>");
                        num ++;
                    }
                })
            });
        }
        //移除其他显示主体内容(this-show类)
        traversal();
        //无搜索结果,则显示暂无搜索结果
        if (num == 0){
            $doc.find("#search-list").append('<div align="center"><br><span style="opacity: 0.8;">暂无搜索结果</span></div>');
        }
        //显示搜索结果主体内容
        $doc.find("#search-list").addClass("layui-show");
    });

    //关闭搜索
    $(document).on('click','#m-show',function(){
        //console.log("11111111");
        //$(".box").empty();
        //$("#m-rm").remove();
        //清空搜索框值
        $(".m-input").val('');
        $("#m-rm").addClass('m-is-show');
        $(".box > span").removeClass('m-is-show');
        //$(".box").html(html);

    });

    //内容主体切换
   // $(document).on('click','#grouping-show',function(){
    $(document).on('mousedown','#grouping-show',function(){
        //获取iframe页面dom
        var $doc = $('iframe').contents();
        traversal();
        $doc.find("#grouping").addClass("layui-show");
        traversal_x();
        $(this).addClass("m-border-bm");

    });
    $(document).on('mousedown','#group-show',function(){
    //$(document).on('click','#group-show',function(){
        var $doc = $('iframe').contents();
        traversal();
        $doc.find("#group").addClass("layui-show");
        //$("#group").addClass("layui-show");
        traversal_x();
        $(this).addClass("m-border-bm");

    });
    $(document).on('mousedown','#message-list-show',function(){
    //$(document).on('click','#message-list-show',function(){
        var $doc = $('iframe').contents();
        traversal();
        $doc.find("#message-list").addClass("layui-show");
        traversal_x();
        $(this).addClass("m-border-bm");

    });

    //遍历所有内容主体,移除显示主体layui-show属性
    function traversal() {
        var $doc = $('iframe').contents();
        $doc.find(".layui-tab-content div").each(function () {
            $(this).removeClass('layui-show');
        });
    }
    //遍历所有下划线标识并移除
    function traversal_x() {
        $('#tag_x a').each(function () {
            $(this).removeClass('m-border-bm');
        });
    }

    //查找弹窗方法
    function find_friend_group(){
        layer.open({
            type: 1,
            id: 'find-friend-group', //id唯一标识
            title:'<div class="layui-row"><span><i class="layui-icon" style="font-size: 18px">&#xe615;</i>&nbsp;查找</span></div>' +
                '<div class="layui-row" align="center">' +
                '<a id="layFindPerson" href="javascript:;"><span style="font-size: 18px">找人</span></a>' + '<span>&emsp;|&emsp;</span>' +
                '<a id="layFindGroup" href="javascript:;"><span style="font-size: 18px">找群</span></a>' +
                '</div>',
            content: $("#layer-2"),
            /*skin: 'm-bg-color',*/
            anim: 2,    //动画
            shade: 0,
            fixed: false,
           /* move: false,*/
            /*offset: 'rb',*/   //出现坐标
            /*closeBtn: 0,*/    //隐藏关闭按钮
            maxmin: true,   //最大化
            resize: false,  //关闭拉伸
            scrollbar: false,   //不允许出现滚动条
            area: ['620px', '496px'],
            moveEnd: function(layero){
                /*console.log(layero);*/

            },
            success: function(layero, index) {
               //console.log("#layer"+index);
                $("#layui-layer"+index).find(".layui-layer-title").addClass("m-layer-title");

            }

        });

    }

    //切换查找弹窗中的内容主体,显示找人主体区域
    $(document).on('mousedown','#layFindPerson',function(){
        console.log("111111");
    });
    //切换查找弹窗中的内容主体,显示找群主体区域
    $(document).on('mousedown','#layFindGroup',function(){
        console.log("111111");
    });

    var editIndex = null;
    var indexOpen = null;
    //暴露打开会话窗口的接口
    exports('openMessage',function(item) {
        indexOpen =  layer.open({
            type: 1,
            id: 'openMessage', //id唯一标识
            title:'<div class="layui-row" >' +
                '<div class="layui-col-sm2"><img src="/leek_bbs/'+item.picture+'" class="img-circle img-size" alt=""></div>' +
                '<div class="layui-col-sm2 m-class" style="height: 70px;margin-left: -10px;"><h4 style="line-height:32px;margin-top: 8px;font-size:18px;">'+item.another_name+'</h4></div>' +
                '</div>',
            content: `<div id="layer-3" class="layui-container-fluid" style="display: none">
                        <iframe src="/leek_bbs/skipPage/session" frameborder="0" id="chatSession-W" style="width: 100%; height: 250px;"></iframe>
                        <div class="layui-row" >
                            <textarea id="chatEdit" style="display: none;"></textarea>
                        </div>
                        <div class="layui-row">
                            <div class="layui-col-sm4 layui-col-sm-offset8">
                                <div class="m-btn">
                                    <button id="closeBtn2" class="layui-btn layui-btn-sm">关闭</button>
                                    <button id="sendBtn" class="layui-btn layui-btn-sm">发送</button>
                                </div>
                            </div>
                        </div>
                    </div>`,
            /*skin: 'm-bg-color',*/
            anim: 2,    //动画
            shade: 0,
            fixed: false,
            maxmin: true,   //最大化
            resize: false,  //关闭拉伸
            scrollbar: false,   //不允许出现滚动条
            area: ['600px', '522px'],
            moveEnd: function(layero){
                /*console.log(layero);*/
            },
            success: function(layero, index) {
                let str;
                //获取用户在线状态
                axios.get(`/leek_bbs/findUserStatus?uid=${item.id}`).then(response => {
                    let data = response.data;
                    if (data != "1"){
                        online_status = false;
                        str = `<h4 style="opacity:.9;">[离线]</h4>`;
                    } else {
                        online_status = true;
                        str = `<h4 style="color:red;">在线</h4>`;
                    }
                    $(".m-class").append(str);
                }).catch(error => {
                    console.log(error);
                });

                $("#layui-layer"+index).find(".layui-layer-title").addClass("m-layer-title");
                //显示编辑器
                $("#layer-3").css("display","block");
                //建立编辑器
                editIndex = layedit.build('chatEdit',{
                    width:560,  //设置宽度
                    height: 82, //设置编辑器高度
                    tool: [
                        'face' //表情
                        ,'image' //插入图片
                    ]
                });
                //获取聊天对象信息
                tarUser = item;
                id = item.id;
            },
            full: function(layero){ //最大化
                //调整按钮位置
                $("#layer-3 .layui-row .m-btn").css("margin-left","190px");
                //调整用户名称及状态位置
                layero.find(".layui-layer-title .m-class").css("margin-left","-90px");
            },
            restore: function(layero){    //还原
                //还原按钮初始位置
                $("#layer-3 .layui-row .m-btn").css("margin-left","30px");
                //还原用户名称及状态位置
                layero.find(".layui-layer-title .m-class").css("margin-left","-10px");
            },
            end: function () {
                //在未还原时,直接关闭弹窗,则还原按钮初始位置
                $("#layer-3 .layui-row .m-btn").css("margin-left","30px");
                //隐藏编辑器
                $("#layer-3").css("display","none");
                indexOpen = null;
            }

        });

    });

    $(document).on('click','#closeBtn',function () {
        layer.close(indexOpen)
    });

    let online_status;

    //发送消息
    $(document).on('click','#sendBtn',function () {
        if (online_status) {     //用户在线
            //获取发送内容
            var str = layedit.getContent(editIndex);
            if (str != null && str != ''){
            var date = new Date();
            //console.log(userInfo);
            ws.send(JSON.stringify({
                "type": "1",
                "tarUser": {"userId":tarUser.id},
                "srcUser": {"userId":userInfo.id},
                "img":userInfo.picture,
                "time":date.getTime(),
                "content": str
            }));
            //该方法layedit接口中未定义,修改layedit.js文件自己添加的方法
            layedit.clearContent(editIndex);
        }
        }else {      //不在线
            layer.msg('该用户不在线,请上线后再私聊!',{icon:5,time:1500})
        }

    });


});

//打开会话窗口方法,给iframe框架子页面调用
function openSessionW(item) {
    //console.log(item);
    layui.openMessage(item);
}
let id = null;
function getId() {
    if (userInfo != null){
        return id;
    }
    return 0;
}

























