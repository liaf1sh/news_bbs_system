
//通讯服务器
var ws = null;
//登录用户信息
var userInfo = null;
//聊天对象
var tarUser = null;

Vue.component('head_menu_comp', {
    props:['is_login'],
    template: `<div class="row">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid nav-margin-left">
                <div class="navbar-header">
<!--                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">-->
<!--                        <span class="sr-only">Toggle navigation</span>-->
<!--                        <span class="icon-bar"></span>-->
<!--                        <span class="icon-bar"></span>-->
<!--                        <span class="icon-bar"></span>-->
<!--                    </button>-->
<!--                    <a class="navbar-brand" href="/leek_bbs/skipPage/index"><img src="/leek_bbs/statics/images/logo.png" width="70" height="35" style="margin-top: -10px" alt=""></a>-->
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li :class="{active:isActiveMenu(menuList.forum)}"><a href="/leek_bbs/skipPage/index"> 新闻分类</a></li>
                        <li :class="{active:isActiveMenu(menuList.read)}"><a href="/leek_bbs/skipPage/daodu">新闻导读</a></li>
                        <li :class="{active:isActiveMenu(menuList.dynamic)}"><a href="javascript:;" @click="openDynamic">动态</a></li>
                        <li :class="{active:isActiveMenu(menuList.ranking)}"><a href="/leek_bbs/skipPage/ranking_list">排行榜</a></li>
                        <!--<li :class="{active:isActiveMenu(menuList.aboutUs)}"><a href="#">关于我们</a></li>-->
                    </ul>
                    <div class="form-group navbar-left navbar-form" style="line-height: 32px;">
                        <input type="text" class="form-control" style="height: 30px;" id="searchParam" placeholder="搜索..." @keyup.enter="getSearchParam" >
                      </div>
                    <div class="navbar-right">
                        <div v-if="isLoginShow">
                            <span><a href="javascript:;" @click="loginBtn()">登录</a></span>
                            <span>|</span>&nbsp;
                            <span><a href="javascript:;" @click="registerBtn()">立即注册</a></span>
                        </div>
                        <div :class="[{dropdown:true},{open:isOpenActive}]" v-show="isUserShow" @mouseenter="isOpenActive=true" @mouseleave="isOpenActive=false">
                              <div class="dropdown-toggle" style="height: 42px;" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">    
                               <a id="d-photo" href="javascript:;" ></a>
                               <a href="/leek_bbs/skipPage/msg-notification">&nbsp;<i class="glyphicon glyphicon-envelope"></i><span class="badge" style="display:none;position: relative;top: -10px;left: -6px;font-size: 10px;">0</span></a>
                             </div>
                            <div class="dropdown-menu" data-stopPropagation="true">
                                <div class="row" style="margin-left:8px;padding: 10px 0;width:220px;border-bottom: 1px solid #ddd;" >
                                    <div class="col-sm-3"><a id="c-photo" href="javascript:;"></a></div>
                                    <div class="col-sm-9">      
                                       <div style="padding: 5px 3px;"><a href="/leek_bbs/skipPage/spacecp">设置</a>&emsp;<a href="javascript:;" @click="logout">退出</a></div>
                                       <div style="padding: 5px 3px;" id="u-ig"></div>
                                    </div>
                                </div>
                                <div class="row" style="margin-left:8px;padding: 10px 0;width:220px;" >
                                    <div class="col-sm-4"><div style="padding: 3px 0px;"><a href="/leek_bbs/skipPage/chat_system">我的好友</a></div></div>
                                    <div class="col-sm-4">      
                                       <div style="padding: 3px 0px;"><a href="/leek_bbs/skipPage/daodu#my_post">我的帖子</a></div>  
                                    </div>
                                    <div class="col-sm-4"><div style="padding: 3px 0px;"><a href="/leek_bbs/skipPage/my-collect">我的收藏</a></div></div>
                                   <!-- <div class="col-sm-4"><div style="padding: 3px 0px;"><a href="javascript:;">我的消息</a></div></div>-->
                                </div>
                                <div class="row" style="margin-left:8px;padding-bottom: 10px;width:220px;border-bottom: 1px solid #ddd;" >
                                  
                                    <div class="col-sm-4">      
                                       <div style="padding: 3px 0px;"><a href="/leek_bbs/skipPage/spacecp#care">我的关注</a></div>  
                                    </div>
                                    <div class="col-sm-4"><div style="padding: 3px 0px;"><a href="/leek_bbs/skipPage/msg-notification">我的消息</a></div></div>
                                </div>
                            
                            </div>
                      </div>
                    </div>

                </div><!-- /.navbar-collapse -->
            </div><!-- /.container -->
            <audio id="audioPlay" src="/leek_bbs/statics/mp3/QQ_xiaoxi.mp3" hidden></audio>
        </nav><!-- /nav -->
    </div>`,
    data:function () {
        return {
            isOpenActive:false,
            isLoginShow:this.is_login,
            isUserShow:false,
            menuList:{forum:"index",read:"read",dynamic:"dynamic",ranking:"rank",aboutUs:"about"}
        }

    },
    methods: {
        getSearchParam() {
            window.location.href="/leek_bbs/skipPage/searchPost?searchParam="+$("#searchParam").val();
        },
        isActiveMenu(path){
            if (getUrlIndexOf(path)){
                return true;
            }
        },
        registerBtn(){
           layui.register();
        },
        loginBtn(){
            layui.login();

        },
        openDynamic(){
          if (userInfo != null){
              window.location.href = "/leek_bbs/skipPage/dynamic";
          } else {
              layui.login();
          }
        },
        loginSubmit(){
            this.isLoginShow = false;
            this.isUserShow = true;
        },
        logout(){
            localStorage.removeItem("initUser");
            ws.close();
            window.location.reload();
        }
    }
});

var head_menu = new Vue({
    el: '#hmc',
    data:function () {
        return {
            isLoginShow:true
        }
    },
    methods: {
        loginSuccess(){
            this.$refs.fo.loginSubmit();
        }
    }

});


//阻止点击事件传递
$(document).on("click", "[data-stopPropagation]", function(e) {
    e.stopPropagation();
});

layui.define(['layer','form','util'],function (exports) {

    var layer = layui.layer,
        form = layui.form,
        util = layui.util;

    getUserInformation('');

    exports('register', function () {
        layer.open({
            type: 1,
            id: 'Lay-register', //id唯一标识
            title: '用户注册',
            content: `<div style="margin-top: 30px;">
            <form class="layui-form" lay-filter="registerForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">昵称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="another_name" lay-verify="required" autocomplete="off" placeholder="请输入昵称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" placeholder="请输入密码" lay-verify="required|password" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="pwd" placeholder="请再次输入密码" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">Email</label>
                    <div class="layui-input-inline">
                        <input type="email" name="email" lay-verify="required|email" autocomplete="off" placeholder="请输入邮箱地址" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-inline" style="width: 100px;">   
                            <input type="text" name="imgCode"style="height: 40px;" placeholder="输入验证码" lay-verify="required" autocomplete="off" class="layui-input">     
                        </div>
                        <div class="layui-input-inline" style="margin-left:35px;width: 100px;">   
                            <img id="captchaPic" style="width: 90px;height: 38px;" src="/leek_bbs/bbs/user/verifyCode" alt="验证码">
                        </div>
                    </div>       
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left: 252px;"><a id="toLogin" href="javascript:;" style="color: #dcdedc;">已有账号,去登陆</a></div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-bottom: 52px;margin-top: -34px;">
                        <span id="errorRes" style="margin-left:0;font-size: 18px;color: red;"></span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-top: -40px;">
                      <button type="submit" class="layui-btn btn-bg" lay-submit="" lay-filter="registerBtn">注册</button>
                      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
            </div>`,
            anim: 2,    //动画
            /*shade: 1,*/
            fixed: false,
            maxmin: false,   //最大化
            resize: false,  //关闭拉伸
            scrollbar: false,   //不允许出现滚动条
            area: ['510px', '554px'],
            success: function (layero, index) {
                $("#layui-layer"+index).find(".layui-layer-title").addClass("lay-head-title");
            }
        })

    });

    //自定义验证规则  
    form.verify({
        password: [
            /^[\S]{6,16}$/
            ,'密码必须6到16位，且不能出现空格'
        ]
        ,phone: [/^1[3|4|5|7|8]\d{9}$/, '手机号格式不对！']
        ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']
    }); 

    $(document).on("click","#toLogin",function () {
        layer.closeAll();
        layui.login();
    });

    //监听注册提交
    form.on('submit(registerBtn)', function(data){
        var user = data.field;
        if (data.field.another_name.length > 13) {
            layer.msg('昵称太长了', {icon: 5,time: 2000});
            return false;
        }
        if (data.field.password != data.field.pwd) {
            layer.msg('两次密码不一致', {icon: 5,time: 2000});
        }else {
            axios.post("/leek_bbs/bbs/user/register",user)
                .then(result => {
                    var resdata = result.data;
                    if (resdata.code == "500020") {     //用户注册成功
                        layer.msg('<img src="/leek_bbs/statics/yu-ui/images/ui-success.png">&emsp;用户注册成功', {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            layer.closeAll();
                            layui.login();
                        });
                    }else if (resdata.code == "500023") {   //验证码错误
                        $("#errorRes").text(resdata.msg);
                    }else if (resdata.code == "300022"){    //用户存在
                        $("#errorRes").text(resdata.msg);
                    } else {   //用户注册失败
                        layer.msg('<img src="/leek_bbs/statics/yu-ui/images/ui-error.png">&emsp;'+resdata.msg, {
                            time: 3000 //2秒关闭（如果不配置，默认是3秒）
                        });
                    }
                })
                .catch(error => {
                    console.log(error);
                })
        }

        return false;
    });
    //监听登录提交
    form.on('submit(loginBtn)', function(data){
        //ui.success('提示内容',时间,是否有遮蔽层);
        $.ajax({
            type: "post",
            url: "/leek_bbs/bbs/user/login",
            data:{
                "username":data.field.username,
                "password":data.field.password,
                "imgCode":data.field.imgCode
            },
            dataType:"json",
            success: function(result) {
                console.log(result);
                if (result.code == "500020"){
                    getUserInformation(data.field.username);
                    if ($("#remember").prop("checked")){
                        //写入cookie A.wc(名字,值,过期时间);
                        A.wc("username",data.field.username,24*60*60*1000);
                        A.wc("password",data.field.password,24*60*60*1000);
                    }else {
                        A.wc("username",null,0);
                        A.wc("password",null,0);
                    }
                    setTimeout(function () {
                        layer.closeAll();
                    },500);

                }else if (result.code == "500024"){
                    layer.msg(result.msg);
                } else {
                    $("#errorRes").text(result.msg);
                }
            },
            error: function(error){
                console.log(error)
            }
        })
        return false;
    });

    //登录弹窗方法
    exports('login', function () {
        layer.open({
            type: 1,
            id: 'Lay-login', //id唯一标识
            title: '用户登录',
            content: `<div style="margin-top: 30px;">
            <form class="layui-form" lay-filter="loginForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                   
                    <div class="layui-input-inline">
                        <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" placeholder="请输入密码" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div> 
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-inline" style="width: 100px;">   
                            <input type="text" style="height: 40px;" name="imgCode" placeholder="输入验证码" height="40px;" lay-verify="required" autocomplete="off" class="layui-input">     
                        </div>
                        <div class="layui-input-inline" style="margin-left:35px;width: 100px;">   
                            <img id="captchaPic" style="width: 90px;height: 38px;" src="/leek_bbs/bbs/user/verifyCode" alt="验证码">
                        </div>
                    </div>       
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left: 105px;">   
                        <input id="remember" type="checkbox" name="isRemember" title="记住我" value="1" lay-skin="primary" >
                    </div>      
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left: 280px;margin-top: -18px;">
                        <a href="javascript:;" id="forgetPassword" style="color: #fff;">忘记密码?</a>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-bottom: 40px;margin-top: -25px;">
                        <span id="errorRes" style="margin-left:0;font-size: 18px;color: red;"></span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left:95px;margin-top: -24px;">
                      <button type="submit" class="layui-btn layui-btn-fluid btn-bg" lay-submit="" lay-filter="loginBtn">登录</button>   
                    </div>
                </div>
            </form>
            </div>`,
            anim: 2,    //动画
            /*shade: 1,*/
            fixed: false,
            maxmin: false,   //最大化
            resize: false,  //关闭拉伸
            scrollbar: false,   //不允许出现滚动条
            area: ['500px', '450px'],
            success: function (layero, index) {
                setTimeout(() => {
                    $("#captchaPic").click();
                },300);
                $("#layui-layer"+index).find(".layui-layer-title").addClass("lay-head-title");
                //读取cookie A.rc(名字);
                form.val('loginForm', {
                    "username": A.rc("username")
                    ,"password": A.rc("password")
                    ,"isRemember":true
                });
            }
        })
    });

    //修改密码
    function alterPwd(){
        layer.open({
            type: 1,
            id: 'Lay-pwd', //id唯一标识
            title: '修改密码',
            content: `<div style="margin-top: 30px;">
            <form class="layui-form" lay-filter="alterPwdForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input id="user-name" type="username" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" lay-verify="required" autocomplete="off" placeholder="请输入新密码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                        <input type="pwd" id="classPwd" name="pwd" placeholder="确认密码" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div> 
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">验证码</label>
                        <div class="layui-input-inline" style="width: 100px;">   
                            <input type="text" style="height: 40px;" name="emailCode" placeholder="输入验证码" height="40px;" lay-verify="required" autocomplete="off" class="layui-input">     
                        </div>
                        <div id="sendVerify" class="layui-input-inline" style="margin-left:35px;width: 100px;">   
                            <button id="sendCode" class="btn btn-default btn-primary">发送验证码</button>
                        </div>
                    </div>       
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-bottom: 40px;margin-top: -25px;">
                        <span id="errorRes" style="margin-left:0;font-size: 18px;color: red;"></span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="margin-left:95px;margin-top: -24px;">
                      <button type="submit" class="layui-btn layui-btn-fluid btn-bg" lay-submit="" lay-filter="alterPwdBtn">提交</button>   
                    </div>
                </div>
            </form>
            </div>`,
            anim: 2,    //动画
            /*shade: 1,*/
            fixed: false,
            maxmin: false,   //最大化
            resize: false,  //关闭拉伸
            scrollbar: false,   //不允许出现滚动条
            area: ['500px', '450px'],
            success: function (layero, index) {
            }
        })
    }


    //切换验证码
    $(document).on("click","#captchaPic",function () {
        $('#captchaPic').attr('src', '/leek_bbs/bbs/user/verifyCode?img='+new Date().getTime());
    });

    $(document).on("click","#forgetPassword",function () {
        layer.closeAll();
        alterPwd();
    });
    $(document).on("click","#sendCode",function () {
        let username = $("#user-name").val();
        if (username != null && username != '') {
            countDown(username);
        }else {
            layer.msg('请先输入用户名', {icon: 5,time: 2000});
        }
        return false;
    });

    //密码修改
    form.on('submit(alterPwdBtn)', function(data){
        if (count > 0) {
            if (data.field.password != data.field.pwd) {
                layer.msg('两次密码不一致', {icon: 5,time: 2000});
                $("#classPwd").focus();
            }else {
                axios.post('/leek_bbs/bbs/user/alterUserInfo',data.field).then(response => {
                    let resData = response.data;
                    if (resData.code == "500020") {
                        layer.msg(`<img src="/leek_bbs/statics/yu-ui/images/ui-success.png">&emsp;密码修改成功`, {
                            time: 3000 //2秒关闭（如果不配置，默认是3秒）
                        },function () {
                            layer.closeAll();
                            layui.login();
                        });
                    }else if (resData.code == "0x10001") {
                        ui.error(resData.msg,2000,true);
                    }else {
                        $("#errorRes").text(resData.msg);
                    }
                }).catch(error => {
                    console.log(error);
                });
            }
        }else {
            layer.msg('请先验证邮箱再提交', {icon: 5,time: 2000});
        }
        return false;
    });

    let count = 0;

    //倒计时
    function countDown(username) {
        axios.get(`/leek_bbs/bbs/user/sendEmail?username=${username}`).then(response => {
            let data = response.data;
            if (data.msg == "success"){
                let endTime = new Date().getTime()+60*1000 //假设为结束日期
                    ,serverTime = new Date().getTime(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的

                util.countdown(endTime, serverTime, function(date, serverTime, timer){
                    let str =date[3];
                    layui.$(`#sendVerify`).html(`<button class="btn btn-default" disabled>重新发送(${str})</button>`);
                    if (str == 0){
                        layui.$(`#sendVerify`).html(`<button id="sendCode" class="btn btn-default btn-primary">发送验证码</button>`);
                    }
                });
                layer.msg(`<img src="/leek_bbs/statics/yu-ui/images/ui-success.png">&emsp;验证码发送成功,请到邮箱注意查看!`, {
                    time: 3000 //2秒关闭（如果不配置，默认是3秒）
                });
                count ++;
            }else if (data.code == "500025"){
                $("#errorRes").text(data.msg);
            } else {
                layer.msg(data.msg, {
                    time: 2000
                });
            }
        }).catch(error => {
            console.log(error);
            layer.msg('数据请求出现异常', {
                time: 2000
            });
        })

    }


});
//获取登录用户信息以及创建聊天连接
function getUserInformation(username){
    if (localStorage.getItem("initUser") == null){
        if (username != null && username != ''){
            axios.get('/leek_bbs/bbs/user/getUserInfo?username='+username)
                .then(function (result) {
                    let data = result.data;
                    if (data != null){
                        //head_menu.loginSuccess();
                        window.localStorage.setItem('initUser', JSON.stringify(data));
                        window.location.reload();
                        /*let map = JSON.parse(window.localStorage.getItem("initUser"));
                        userInfo = map.userInfo;
                        let str = `<img src="/leek_bbs/${userInfo.picture}" style="margin-top: -8px;width:40px;height:40px;" style="border-radius: 12px;">
                                    &nbsp;&nbsp;<span>${userInfo.another_name}</span>`;
                        $("#d-photo").html(str);
                        $("#c-photo").html(`<img src="/leek_bbs/${userInfo.picture}" width="40px" class="img-circle" height="40px" alt="头像">`);
                        $("#u-ig").html(`<span>积分:&nbsp;${map.total_integral}</span>&nbsp;<span>用户组:&nbsp;${map.grade.name}</span>`);
                        setTimeout(function () {
                            websocketLinkStart(userInfo);
                        },1000);*/
                    }
                })
                .catch(function (error){
                    console.log(error);
                })
        }
    }else{
        head_menu.loginSuccess();
        let map = JSON.parse(window.localStorage.getItem("initUser"));
        userInfo = map.userInfo;
        console.log(map);
        $('#d-photo').attr('href',`/leek_bbs/skipPage/personal?id=${userInfo.id}`);
        $('#c-photo').attr('href',`/leek_bbs/skipPage/personal?id=${userInfo.id}`);
        let str = `<img src="/leek_bbs/${userInfo.picture}" style="margin-top: -8px;width:40px;height:40px;" style="border-radius: 12px;">
                                &nbsp;&nbsp;<span>${userInfo.another_name}</span>`;
        $("#d-photo").html(str);
        $("#c-photo").html(`<img src="/leek_bbs/${userInfo.picture}" class="img-circle" width="40px" height="40px" alt="头像">`);
        $("#u-ig").html(`<span>积分:&nbsp;${map.total_integral}</span>&nbsp;<span>用户组:&nbsp;${map.grade.name}</span>`);
        websocketLinkStart(userInfo)
    }

}

//封装服务连接方法
function websocketLinkStart(userInfo) {
    if(WebSocket){
        //ws = new WebSocket("ws://jxz.free.qydev.com:8080/websocket/"+userInfo.id);
        //本地连接
         ws = new WebSocket("ws://127.0.0.1/leek_bbs/websocket/"+userInfo.id);
    }else {
        alert("您的浏览器不支持Websocket!");
    }
    //连接建立成功的回调方法
    ws.onopen = function () {
        console.log("连接建立成功!");
    };
    //连接发生错误的回调方法
    ws.onerror = function (e) {
        //console.error("WebSocket连接发生错误");
    };
    //接收服务器端的消息
    ws.onmessage = function (ev) {
        let data = JSON.parse(ev.data);
        /*if (data.srcUser.userId != userInfo.id){
            data.isOneself = false;
        }*/
        let loginUserId = userInfo.id;
        if ($("#chatSession-W")[0] == null){
            if (data.tarUser.userId == userInfo.id){
                $(".badge").css("display","").text(1);
                //let privateMsg = JSON.parse(window.localStorage.getItem(`privateMsg-${userInfo.id}`));
                let privateMsg = JSON.parse(window.localStorage.getItem(`msgData`));
                if (privateMsg == null){
                    privateMsg = [];
                }else if (privateMsg.length > 30){
                    privateMsg = [];
                }
                privateMsg.push({
                    srcUser:data.srcUser,
                    tarUser:data.tarUser,
                    img:data.img,
                    time:data.time,
                    content:data.content,
                    status:0
                });
                window.localStorage.setItem(`msgData`,JSON.stringify(privateMsg));
                let player = document.getElementById("audioPlay");
                player.play();
            }
            //console.log("未打开聊天窗口...");
        }else {
            //获取iframe页面的window对象,[0]将JQuery对象转成DOM对象,用DOM对象的contentWindow获取子页面window对象。
            let childWindow = $("#chatSession-W")[0].contentWindow;
            childWindow.sendMessage(data,loginUserId);  //调用子页面中的sendMessage方法。
        }

    };
    // 监听断开连接的方法.
    ws.onclose = function(event) {
        // 业务需求，例如：聊天室，当某人退出的时候，会给出提示，xxx退出了...
        websocketLinkStart(userInfo);
    };
}
// 退出系统时, 关闭建立的WebSocket链接
//离开页面,改变用户状态
/*window.onunload = function() {
    if (ws != null){
        ws.close();
    }
};*/

//获取地地址栏文件部分
function getUrlIndexOf(path) {
    let urlPathName = window.location.pathname;
    if (urlPathName.includes(path)){return true};
}

//获取url中的参数
function getUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    let r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURI(r[2]); return null; //返回参数值
}