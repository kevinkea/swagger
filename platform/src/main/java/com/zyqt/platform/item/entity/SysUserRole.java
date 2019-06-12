package com.zyqt.platform.item.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户角色中间表
 * </p>
 *
 * @author zgz
 * @since 2019-05-22
 */
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID=1L;

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 角色ID
	 */
	private String roleId;


	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

//	@Override
//	protected Serializable pkVal() {
//		return this.id;
//	}

}
