package com.lindong.exception;

/**
 * 自定义枚举异常结果码
 */
public enum ResultCode {

    //登录模块 5000XX
    USERNAME_ERROR("500025", "用户不存在"),
    PASSWORD_ERROR("500021", "用户名或密码错误"),
    USER_DISABLE("500024", "用户被禁止登录,请联系管理员!"),
    USER_NO_POWER("500030","你无权限登录该系统!"),

    //注册模块 3000XX
    USER_EXIST("300022","用户已存在"),
    REGISTER_ERROR("300033","注册失败"),

    //用户更新
    USER_ALTER_ERROR("300034","用户信息保存失败"),
    SEND_FAIL("300035","验证码发送失败"),
    PASSWORD_FAILED("300036","原密码不正确"),

    //签到
    SIGN_ERROR("400011","用户签到失败"),
    UPDATE__ERROR("400012","用户积分更新失败"),

    //帖子模块
    POST_REPLY_FAILED("400035","回帖失败"),
    POST_UPDATE_FAILED("400040","主题更新失败"),
    OPERATION_ERROR("500055","操作失败"),
    POST_OPERATION_FAILED("500060","您已经评价过该主题"),
    POST_SHARE_EXIST("500062","该帖子您已经分享过了"),

    //收藏
    POST_COLLECT_FAILED("400045","帖子收藏失败"),
    PLATE_COLLECT_FAILED("400050","板块收藏失败"),
    LOG_COLLECT_FAILED("400055","日志收藏失败"),
    POST_COLLECT_ERROR("400060","帖子已收藏过了"),
    PLATE_COLLECT_ERROR("400061","板块已收藏过了"),
    LOG_COLLECT_ERROR("400062","日志已收藏过了"),

    //分享
    POST_SHARE_ERROR("600011","帖子分享失败"),

    //举报
    REPORT_EXIST("600014","您已经举报过该帖子了"),
    REPORT_ERROR("600015","举报失败"),
    REPORT_DISPOSE_ERROR("600021","举报处理未成功"),

    //关注
    CARE_FAILED("600016","该用户您已经关注了"),
    CARE_ERROR("600018","关注失败"),
    UNFOLLOW_FAILED("600019","取消关注失败"),
    CARE_NOT("600020","未关注"),

    //公共模块
    SUCCESS("500020", "success"),
    VERIFY_ERROR("500023", "验证码错误"),
    UPDATE_FAILED("500033", "更新失败"),
    DELETE_ERROR("500034","删除失败"),
    DATA_ERROR("500035","数据更新失败"),
    SAVE_FAILED("500036","添加失败"),
    AUTHORITY_ERROR("500037","您无权限做该操作"),

    //好友
    AGREE_ERROR("600033","好友添加失败"),
    FRIEND_APPLY_FILED("600001","您已发送过申请,请不要重复提交"),

    //系统未知异常 0x100
    UNKNOWN_ERROR("0x10001", "系统发生了一个未知错误");


    //结果码
    private String code;

    //结果码描述
    private String msg;


    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

