package com.zyqt.platform.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "返回状态码与数据")
public class BaseForm <T> {
    @ApiModelProperty(value = "状态码",example = "200")
    private int status = 200;
    @ApiModelProperty(value = "返回信息",example = "200,请求成功;9999, 错误;1001,账户不存在;" +
            "1002,密码错误;1003,需要登录;1004, 未授权;1005, 操作失败;1006, 请求超时;" +
            "1007,重复数据;1008,登录成功;1009,最高角色级别;1010,最低角色级别;1011,外键约束" +
            ";1012,验签失败;1013,等待二维码验证;1014,二维码失效;1015:feign跳转失败")
    private String msg = "请求成功";
    @ApiModelProperty(value = "数据")
    private T data ;

    public BaseForm() {
    }

    public BaseForm(Status status) {
        this.status = status.status;
        this.msg = status.msg;
    }

    public BaseForm(T data) {
        this.data = data;
    }

    public enum Status {
        SUCCESS(200,"请求成功"),
        ACCOUNT_WRONG(1001, "账户不存在"),
        PASSWORD_WRONG(1002, "密码错误"),
        NEED_LOGIN(1003,"需要登录"),
        UNAUTHORIZED(1004, "未授权"),
        FAILURE(1005, "操作失败"),
        TIMEOUT(1006, "请求超时"),
        DUPLICATEDATA(1007,"重复数据"),
        LOGINSUCCESSFUL(1008,"登录成功"),
        TOPROLE(1009,"最高角色级别"),
        LOWESTROLE(1010,"最低角色级别"),
        FOREIGNKEY(1011,"外键约束"),
        VERIFICATION_FAILURE(1012,"验签失败"),
        WAITING_QRCODE_VERIFICATION(1013,"等待二维码验证"),
        QRCODE_FAIL(1014,"二维码失效"),
        FEIGN_JUMP_FAILED(1015,"feign跳转失败"),
        ERROR(9999, "错误");

        int status;
        String msg;
        Status(int status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
    public void setStatus(Status status) {
        this.status = status.status;
        this.msg = status.msg;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
