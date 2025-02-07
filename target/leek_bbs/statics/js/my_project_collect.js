
layui.use(['element','jquery'], function(){
    var element = layui.element,
        $ = layui.jquery;

    //监听TabUser切换
    /*eleOn("tabUser");
    //监听TabPosts切换
    eleOn("tabPosts");
    //监听TabPlate切换
    eleOn("tabPlate");*/

    $("#userBtn").click(function () {
        changerClass();
        $(this).addClass("m-active");
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        collectApp.getCollect(1,'');
        collectApp.isShowAll = true;
        collectApp.isShowPost = false;
        collectApp.isShowPlate = false;
        collectApp.isShowLog = false;
        collectApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-user").css("display","block");
    });
    $("#postsBtn").click(function () {
        changerClass();
        $(this).addClass("m-active");
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        collectApp.getCollect(1,1);
        collectApp.isShowAll = false;
        collectApp.isShowPost = true;
        collectApp.isShowPlate = false;
        collectApp.isShowLog = false;
        collectApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-posts").css("display","block");
    });
    $("#plateBtn").click(function () {
        changerClass();
        $(this).addClass("m-active");
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        collectApp.getCollect(1,2);
        collectApp.isShowAll = false;
        collectApp.isShowPost = false;
        collectApp.isShowPlate = true;
        collectApp.isShowLog = false;
        collectApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-plate").css("display","block");
    });
    $("#logBtn").click(function () {
        changerClass();
        $(this).addClass("m-active");
        //隐藏其他tab表数据
        //$(".layui-tab").css("display","none");
        collectApp.getCollect(1,3);
        collectApp.isShowAll = false;
        collectApp.isShowPost = false;
        collectApp.isShowPlate = false;
        collectApp.isShowLog = true;
        collectApp.collectIds = [];
        //切换右边中的tab表数据
        $("#tab-log").css("display","block");
    });

    //封装tab监听
    /*function eleOn(str) {
        element.on('tab('+str+')', function(elem){
            $(this).parents().find("li").each(function () {
                $(this).removeClass("m-active-size");
            });
            $(this).addClass("m-active-size");
        });
    }*/
    //移除原先已经激活的属性
    function changerClass() {
        $(".col-sm-2").find(".row").each(function () {
            $(this).removeClass("m-active");
        });
    }

});