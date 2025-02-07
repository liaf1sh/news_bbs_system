
var editor;
var editor2;
//多图片上传
function createEdit(str){

    var E = window.wangEditor;
    editor = new E('#'+str);
    // 或者 var editor = new E( document.getElementById('editor') )

    // 自定义菜单配置
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        //'fontSize',  // 字号
        //'fontName',  // 字体
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        //'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销

    ];

    editor.customConfig.uploadImgServer = "/leek_bbs/bbs/file/uploadImage"; // 上传图片到服务器
    editor.customConfig.uploadFileName = "files";   //文件名称 也就是你在后台接受的 参数值
    /*editor.customConfig.uploadImgHeaders = {  //header头信息‘Accept‘: ‘text/x-json‘
        'Accept': 'text/x-json'
}*/
    // 将图片大小限制为 3M
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024 ; //默认为5M
    editor.customConfig.uploadImgShowBase64 = false;  // 使用 base64 保存图片
    // editor.customConfig.customAlert = function (info) { //自己设置alert错误信息
    //   // info 是需要提示的内容
    //   alert(‘自定义提示：‘ + ‘图片上传失败，请重新上传‘)
    // };
    editor.customConfig.debug = true; //是否开启Debug 默认为false 建议开启 可以看到错误
    // editor.customConfig.debug = location.href.indexOf(‘wangeditor_debug_mode=1‘) > 0; // 同上 二选一
    //图片在编辑器中回显
    editor.customConfig.uploadImgHooks = {
        error: function (xhr, editor) {
            console.log(xhr);
            console.log(editor);
            alert("2：" + xhr + "请查看你的json格式是否正确，图片并没有上传");
            // 图片上传出错时触发 如果是这块报错 就说明文件没有上传上去，直接看自己的json信息。是否正确
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        fail: function (xhr, editor, result) {
            // 如果在这出现的错误 就说明图片上传成功了 但是没有回显在编辑器中，我在这做的是在原有的json 中添加了
            // 一个url的key（参数）这个参数在 customInsert也用到
            console.log(result);
            alert("1：" + xhr + "请查看你的json格式是否正确，图片上传了，但是并没有回显");
        },
        success:function(xhr, editor, result){
            //成功 不需要alert 当然你可以使用console.log 查看自己的成功json情况
            //console.log(result)
            // insertImg(‘https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png‘)
        },
        customInsert: function (insertImg, result, editor) {
            console.log(result);
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // 举例：假如上传图片成功后，服务器端返回的是 {url:‘....‘} 这种格式，即可这样插入图片：
            for (let j = 0; j < result.data.length; j++) {
                insertImg('/leek_bbs/uploadfiles/'+result.data[j]);
            }
            //insertImg('/leek_bbs/uploadfiles/'+result.data);
        }
    };
    editor.customConfig.showLinkImg = true; //是否开启网络图片，默认开启的。

    editor.customConfig.emotions = [
        {
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: [
                {
                    alt: '[坏笑]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
                },
                {
                    alt: '[舔屏]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
                },
                {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif",
                    alt: "[草泥马]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif",
                    alt: "[神马]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif",
                    alt: "[浮云]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif",
                    alt: "[给力]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif",
                    alt: "[围观]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif",
                    alt: "[威武]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/panda_thumb.gif",
                    alt: "[熊猫]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/81/rabbit_thumb.gif",
                    alt: "[兔子]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/otm_thumb.gif",
                    alt: "[奥特曼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif",
                    alt: "[囧]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/hufen_thumb.gif",
                    alt: "[互粉]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c4/liwu_thumb.gif",
                    alt: "[礼物]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ac/smilea_thumb.gif",
                    alt: "[呵呵]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0b/tootha_thumb.gif",
                    alt: "[嘻嘻]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/laugh.gif",
                    alt: "[哈哈]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/14/tza_thumb.gif",
                    alt: "[可爱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/kl_thumb.gif",
                    alt: "[可怜]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a0/kbsa_thumb.gif",
                    alt: "[挖鼻屎]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f4/cj_thumb.gif",
                    alt: "[吃惊]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/shamea_thumb.gif",
                    alt: "[害羞]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c3/zy_thumb.gif",
                    alt: "[挤眼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/29/bz_thumb.gif",
                    alt: "[闭嘴]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/71/bs2_thumb.gif",
                    alt: "[鄙视]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/lovea_thumb.gif",
                    alt: "[爱你]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9d/sada_thumb.gif",
                    alt: "[泪]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/19/heia_thumb.gif",
                    alt: "[偷笑]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8f/qq_thumb.gif",
                    alt: "[亲亲]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/b6/sb_thumb.gif",
                    alt: "[生病]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/mb_thumb.gif",
                    alt: "[太开心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/17/ldln_thumb.gif",
                    alt: "[懒得理你]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/98/yhh_thumb.gif",
                    alt: "[右哼哼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/zhh_thumb.gif",
                    alt: "[左哼哼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a6/x_thumb.gif",
                    alt: "[嘘]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/cry.gif",
                    alt: "[衰]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/73/wq_thumb.gif",
                    alt: "[委屈]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9e/t_thumb.gif",
                    alt: "[吐]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f3/k_thumb.gif",
                    alt: "[打哈欠]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/27/bba_thumb.gif",
                    alt: "[抱抱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7c/angrya_thumb.gif",
                    alt: "[怒]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/5c/yw_thumb.gif",
                    alt: "[疑问]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a5/cza_thumb.gif",
                    alt: "[馋嘴]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/88_thumb.gif",
                    alt: "[拜拜]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/e9/sk_thumb.gif",
                    alt: "[思考]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/24/sweata_thumb.gif",
                    alt: "[汗]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7f/sleepya_thumb.gif",
                    alt: "[困]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6b/sleepa_thumb.gif",
                    alt: "[睡觉]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/90/money_thumb.gif",
                    alt: "[钱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0c/sw_thumb.gif",
                    alt: "[失望]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/cool_thumb.gif",
                    alt: "[酷]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8c/hsa_thumb.gif",
                    alt: "[花心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/49/hatea_thumb.gif",
                    alt: "[哼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/36/gza_thumb.gif",
                    alt: "[鼓掌]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/dizzya_thumb.gif",
                    alt: "[晕]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1a/bs_thumb.gif",
                    alt: "[悲伤]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/62/crazya_thumb.gif",
                    alt: "[抓狂]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/h_thumb.gif",
                    alt: "[黑线]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/yx_thumb.gif",
                    alt: "[阴险]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/nm_thumb.gif",
                    alt: "[怒骂]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/hearta_thumb.gif",
                    alt: "[心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ea/unheart.gif",
                    alt: "[伤心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/pig.gif",
                    alt: "[猪头]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d6/ok_thumb.gif",
                    alt: "[ok]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/ye_thumb.gif",
                    alt: "[耶]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/good_thumb.gif",
                    alt: "[good]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c7/no_thumb.gif",
                    alt: "[不要]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d0/z2_thumb.gif",
                    alt: "[赞]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/come_thumb.gif",
                    alt: "[来]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/sad_thumb.gif",
                    alt: "[弱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/lazu_thumb.gif",
                    alt: "[蜡烛]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/cake.gif",
                    alt: "[蛋糕]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d3/clock_thumb.gif",
                    alt: "[钟]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1b/m_thumb.gif",
                    alt: "[话筒]"
                }

            ]
        }
    ];
    editor.customConfig.zIndex = 50;
    editor.create();

}

function createEdit2(str){

    var E = window.wangEditor;
    editor2 = new E('#'+str);
    // 或者 var editor = new E( document.getElementById('editor') )

    // 自定义菜单配置
    editor2.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        //'fontSize',  // 字号
        //'fontName',  // 字体
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        //'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销

    ];

    editor2.customConfig.uploadImgServer = "/leek_bbs/bbs/file/uploadImage"; // 上传图片到服务器
    editor2.customConfig.uploadFileName = "files";   //文件名称 也就是你在后台接受的 参数值
    /*editor.customConfig.uploadImgHeaders = {  //header头信息‘Accept‘: ‘text/x-json‘
        'Accept': 'text/x-json'
}*/
    // 将图片大小限制为 3M
    editor2.customConfig.uploadImgMaxSize = 3 * 1024 * 1024 ; //默认为5M
    editor2.customConfig.uploadImgShowBase64 = false;  // 使用 base64 保存图片
    // editor.customConfig.customAlert = function (info) { //自己设置alert错误信息
    //   // info 是需要提示的内容
    //   alert(‘自定义提示：‘ + ‘图片上传失败，请重新上传‘)
    // };
    editor2.customConfig.debug = true; //是否开启Debug 默认为false 建议开启 可以看到错误
    // editor.customConfig.debug = location.href.indexOf(‘wangeditor_debug_mode=1‘) > 0; // 同上 二选一
    //图片在编辑器中回显
    editor2.customConfig.uploadImgHooks = {
        error: function (xhr, editor) {
            console.log(xhr);
            console.log(editor);
            alert("2：" + xhr + "请查看你的json格式是否正确，图片并没有上传");
            // 图片上传出错时触发 如果是这块报错 就说明文件没有上传上去，直接看自己的json信息。是否正确
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        fail: function (xhr, editor, result) {
            // 如果在这出现的错误 就说明图片上传成功了 但是没有回显在编辑器中，我在这做的是在原有的json 中添加了
            // 一个url的key（参数）这个参数在 customInsert也用到
            console.log(result);
            alert("1：" + xhr + "请查看你的json格式是否正确，图片上传了，但是并没有回显");
        },
        success:function(xhr, editor, result){
            //成功 不需要alert 当然你可以使用console.log 查看自己的成功json情况
            //console.log(result)
            // insertImg(‘https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png‘)
        },
        customInsert: function (insertImg, result, editor) {
            console.log(result);
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // 举例：假如上传图片成功后，服务器端返回的是 {url:‘....‘} 这种格式，即可这样插入图片：
            for (let j = 0; j < result.data.length; j++) {
                insertImg('/leek_bbs/uploadfiles/'+result.data[j]);
            }
            //insertImg('/leek_bbs/uploadfiles/'+result.data);
        }
    };
    editor2.customConfig.showLinkImg = true; //是否开启网络图片，默认开启的。
    editor2.customConfig.emotions = [
        {
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: [
                {
                    alt: '[坏笑]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
                },
                {
                    alt: '[舔屏]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
                },
                {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif",
                    alt: "[草泥马]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif",
                    alt: "[神马]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif",
                    alt: "[浮云]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif",
                    alt: "[给力]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif",
                    alt: "[围观]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif",
                    alt: "[威武]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/panda_thumb.gif",
                    alt: "[熊猫]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/81/rabbit_thumb.gif",
                    alt: "[兔子]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/otm_thumb.gif",
                    alt: "[奥特曼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif",
                    alt: "[囧]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/hufen_thumb.gif",
                    alt: "[互粉]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c4/liwu_thumb.gif",
                    alt: "[礼物]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ac/smilea_thumb.gif",
                    alt: "[呵呵]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0b/tootha_thumb.gif",
                    alt: "[嘻嘻]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/laugh.gif",
                    alt: "[哈哈]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/14/tza_thumb.gif",
                    alt: "[可爱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/kl_thumb.gif",
                    alt: "[可怜]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a0/kbsa_thumb.gif",
                    alt: "[挖鼻屎]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f4/cj_thumb.gif",
                    alt: "[吃惊]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/shamea_thumb.gif",
                    alt: "[害羞]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c3/zy_thumb.gif",
                    alt: "[挤眼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/29/bz_thumb.gif",
                    alt: "[闭嘴]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/71/bs2_thumb.gif",
                    alt: "[鄙视]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/lovea_thumb.gif",
                    alt: "[爱你]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9d/sada_thumb.gif",
                    alt: "[泪]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/19/heia_thumb.gif",
                    alt: "[偷笑]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8f/qq_thumb.gif",
                    alt: "[亲亲]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/b6/sb_thumb.gif",
                    alt: "[生病]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/mb_thumb.gif",
                    alt: "[太开心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/17/ldln_thumb.gif",
                    alt: "[懒得理你]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/98/yhh_thumb.gif",
                    alt: "[右哼哼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/zhh_thumb.gif",
                    alt: "[左哼哼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a6/x_thumb.gif",
                    alt: "[嘘]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/cry.gif",
                    alt: "[衰]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/73/wq_thumb.gif",
                    alt: "[委屈]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9e/t_thumb.gif",
                    alt: "[吐]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f3/k_thumb.gif",
                    alt: "[打哈欠]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/27/bba_thumb.gif",
                    alt: "[抱抱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7c/angrya_thumb.gif",
                    alt: "[怒]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/5c/yw_thumb.gif",
                    alt: "[疑问]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a5/cza_thumb.gif",
                    alt: "[馋嘴]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/88_thumb.gif",
                    alt: "[拜拜]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/e9/sk_thumb.gif",
                    alt: "[思考]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/24/sweata_thumb.gif",
                    alt: "[汗]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7f/sleepya_thumb.gif",
                    alt: "[困]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6b/sleepa_thumb.gif",
                    alt: "[睡觉]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/90/money_thumb.gif",
                    alt: "[钱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0c/sw_thumb.gif",
                    alt: "[失望]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/cool_thumb.gif",
                    alt: "[酷]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8c/hsa_thumb.gif",
                    alt: "[花心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/49/hatea_thumb.gif",
                    alt: "[哼]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/36/gza_thumb.gif",
                    alt: "[鼓掌]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/dizzya_thumb.gif",
                    alt: "[晕]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1a/bs_thumb.gif",
                    alt: "[悲伤]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/62/crazya_thumb.gif",
                    alt: "[抓狂]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/h_thumb.gif",
                    alt: "[黑线]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/yx_thumb.gif",
                    alt: "[阴险]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/nm_thumb.gif",
                    alt: "[怒骂]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/hearta_thumb.gif",
                    alt: "[心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ea/unheart.gif",
                    alt: "[伤心]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/pig.gif",
                    alt: "[猪头]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d6/ok_thumb.gif",
                    alt: "[ok]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/ye_thumb.gif",
                    alt: "[耶]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/good_thumb.gif",
                    alt: "[good]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c7/no_thumb.gif",
                    alt: "[不要]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d0/z2_thumb.gif",
                    alt: "[赞]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/come_thumb.gif",
                    alt: "[来]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/sad_thumb.gif",
                    alt: "[弱]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/lazu_thumb.gif",
                    alt: "[蜡烛]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/cake.gif",
                    alt: "[蛋糕]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d3/clock_thumb.gif",
                    alt: "[钟]"
                }, {
                    src: "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1b/m_thumb.gif",
                    alt: "[话筒]"
                }

            ]
        }
    ];
    editor2.customConfig.zIndex = 50;
    editor2.create();

}
/*
function createEditor() {
    var E = window.wangEditor;
    var editor = new E(document.getElementById('editor'));
    //自定义文件上传
    editor.customConfig.customUploadImg = function(files, insert) {
        var daw = new FormData();
        for (var i = 0; i < files.length; i++) {
            daw.append("files", files[i]);
        }
        //发送异步请求
        $.ajax({
            url : "/leek_bbs/bbs/file/uploadImage",
            type : "POST",
            data : daw,
            async : false,
            cache : false,
            contentType : false,
            processData : false,
            success : function(da) {
                if (da.errno == 0) {
                    for (var j = 0; j < da.data.length; j++) {
                        insert(da.data[j]);
                    }
                } else {
                    alert(da.msg);
                    return;
                }
            },
        });
    };
    editor.create()

}*/
