package com.zyqt.platform.item.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginUser {
	@ApiModelProperty(value = "登陆名",position = 1)
	private String userName;
	@ApiModelProperty(value = "登陆密码(Md5加密)",position = 2)
	private String password;
	@ApiModelProperty(value = "时间戳(毫秒)",position = 3)
	private long timestamp;
	@ApiModelProperty(value = "签名串(SHA1加密:用户名+sw+密码+sw+时间戳)",position = 4)
	private String signature;
	@ApiModelProperty(value = "是否开启记住我(cookie失效时间为7天)",position = 5)
	private Boolean isRememberMe = true;
}
