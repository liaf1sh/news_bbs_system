
layui.define(['element','jquery'], function(exports){
    var element = layui.element,
        $ = layui.jquery;

    $(document).on("click","#userBtn",function () {
        changerClass();
        $(this).addClass("m-active");
        msgApp.isShowPrivate = true;
        msgApp.isShowPost = false;
        msgApp.isShowApply = false;
        msgApp.isShowSys = false;
        getPrivateMsg();
        msgApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-user").css("display","block");
    });
    $(document).on("click","#postsBtn",function () {
        changerClass();
        $(this).addClass("m-active");
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        msgApp.pageNo = 1;
        msgApp.getNewestReply();
        msgApp.isShowPrivate = false;
        msgApp.isShowPost = true;
        msgApp.isShowApply = false;
        msgApp.isShowSys = false;
        msgApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-posts").css("display","block");
    });
    $(document).on("click","#plateBtn",function () {
        changerClass();
        $(this).addClass("m-active");
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        msgApp.pageNo = 1;
        msgApp.getSysWarn();
        msgApp.isShowPrivate = false;
        msgApp.isShowPost = false;
        msgApp.isShowApply = false;
        msgApp.isShowSys = true;
        msgApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-plate").css("display","block");
    });
    $(document).on("click","#friendApply",function () {
        changerClass();
        $(this).addClass("m-active");
        //msgApp.isCheckBtn;
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        msgApp.pageNo = 1;
        msgApp.getFriendApply();
        msgApp.isShowPrivate = false;
        msgApp.isShowPost = false;
        msgApp.isShowApply = true;
        msgApp.isShowSys = false;
        msgApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-friendApply").css("display","block");
    });


    //移除原先已经激活的属性
    function changerClass() {
        $(".col-sm-2").find(".row").each(function () {
            $(this).removeClass("m-active");
        });
    }


});