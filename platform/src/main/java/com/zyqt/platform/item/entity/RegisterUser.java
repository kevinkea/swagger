package com.zyqt.platform.item.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 注册输入类
 *
 * @author zhouchen
 * @date 2018-09-19 09:05
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RegisterUser extends SysUser {
	@ApiModelProperty(value = "给用户添加的角色id(必填)" , position = 1, required = true)
	private List<String> roleIds;
	//    @ApiModelProperty(value = "上级用户id" , position = 2)
//    private String superUserId;
	@ApiModelProperty(value = "上级用户id集合" , position = 3)
	private List<String> superUserIds;


}