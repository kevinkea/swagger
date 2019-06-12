package com.zyqt.platform.item.entity;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author zgz
 * @since 2019-05-22
 */
public class SysRole implements Serializable{

	private static final long serialVersionUID=1L;

	@ApiModelProperty(value = "ID" )
	private String id;

	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色编码
	 */
	private String roleCode;
	/**
	 * 角色描述
	 */
	private String roleDescription;
	/**
	 * 状态(1:锁定0:非锁定)
	 */
	private String status;
	/**
	 * 组织机构级别
	 */
	private String orgRank;
	private LocalDateTime createDate;
	/**
	 * 创建人
	 */
	private String createBy;
	private LocalDateTime updateDate;
	/**
	 * 更新人
	 */
	private String updateBy;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode(){
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescription(){
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgRank(){
		return orgRank;
	}

	public void setOrgRank(String orgRank) {
		this.orgRank = orgRank;
	}

	public LocalDateTime getCreateDate(){
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy(){
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

}
