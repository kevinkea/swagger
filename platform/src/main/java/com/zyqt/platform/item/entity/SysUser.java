package com.zyqt.platform.item.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 系统用户表getId
 * </p>
 *
 * @author zgz
 * @since 2019-05-08
 */
@Data
public class SysUser implements Serializable{

	private static final long serialVersionUID=1L;

	public SysUser() {
	}

	public SysUser(String id, String isDel) {
		this.id = id;
		this.isDel = isDel;
	}

	public SysUser(String userName, String realName, String email, String mobilePhone, String officePhone, String department, String duty
		, String status, String roleName, String orgName, String orgCode, String remark) {
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.duty = duty;
		this.userName = userName;
		this.realName = realName;
		this.status = status;
		this.orgCode = orgCode;
		this.roleName = roleName;
		this.orgName = orgName;
		this.remark = remark;
	}

	/**
	 * 创建用户基本信息
	 * @Author zhouchen
	 * @Description
	 * @Date 14:42 2018/8/29
	 * @Param
	 * @return
	 **/
	public void createSysUser(){
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.status = "1";
		this.isDel = "0";
	}

	public void importSysUser(String password, String createBy, String updateBy){
		this.isDel = "0";
		this.password=password;
		this.createBy=createBy;
		this.updateBy=updateBy;
		//用户状态只能为0或1
		if(!"1".equals(this.status) && !"0".equals(this.status))
			this.status= "1";
	}

	/**
	 * 通过角色id获取角色
	 * @author zhouchen
	 * @date  2018/11/8 15:50
	 * @param id
	 * @return com.zyqt.platform.sys.entity.SysRole
	 */
	public SysRole getRoleById(String id){
		for (SysRole roleCode : roleCodes) {
			if (id.equals(roleCode.getId()))
				return roleCode;
		}
		return null;
	}
	/**
	 * ID
	 */
	@ApiModelProperty(value = "ID")
	private String id;

	/**
	 * 用户账号
	 */
	@Length(max = 15,message = "账号长度不能超过15")
	@Length(min = 8,message = "账号长度不能短于8")
	@NotBlank(message="账号不能为空")
	@Pattern(regexp="^[^\\u4E00-\\u9FA5]*$",message="账号不能输入汉字")
	@ApiModelProperty(value = "用户账号",position = 1)
	@NonNull
	@TableField("user_name")
	private String userName;
	/**
	 * 真实名字
	 */
	@Length(max = 15,message = "姓名长度不能超过15")
	@ApiModelProperty(value = "真实名字",position = 2)
	@TableField("real_name")
	private String realName;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码",position = 3)
	@NonNull
	private String password;
	/**
	 * 邮箱
	 */
	@Email(message = "邮箱格式错误")
	@ApiModelProperty(value = "邮箱",position = 4)
	private String email;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码",position = 5)
	@TableField("mobile_phone")
	private String mobilePhone;
	/**
	 * 职务
	 */
	@Length(max = 30,message = "职务长度不能超过30")
	@ApiModelProperty(value = "职务",position = 8)
	private String duty;
	/**
	 * 员工编号
	 */
	@Length(max = 15,message = "员工编号长度不能超过15")
	@ApiModelProperty(value = "员工编号",position = 8)
	private String userNumber;
	/**
	 * 岗位
	 */
	@ApiModelProperty(value = "岗位" , position = 7)
	@TableField("station" )
	private String station;
	/**
	 * 组织机构
	 */
	@ApiModelProperty(value = "组织机构ID",position = 9)
	@TableField("org_id")
	private String orgId;
	/**
	 * 有效状态(0:锁定;1:非锁定)
	 */
	@ApiModelProperty(value = "有效状态(0:锁定;1:非锁定)",position = 10)
	private String status;
	@ApiModelProperty(value = "创建时间")
	@TableField("create_date")
	private Date createDate;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人",position = 11)
	@TableField("create_by")
	private String createBy;

	@ApiModelProperty(value = "更新时间",position = 12)
	@TableField("update_date")
	private Date updateDate;
	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人",position = 13)
	@TableField("update_by")
	private String updateBy;
	/**
	 * 是否删除(1:删除0:不删除)
	 */
	@ApiModelProperty(value = "是否删除(1:删除0:不删除)",position = 14)
	@TableField("is_del")
	private String isDel;

	/**身份验证令牌*/
	@ApiModelProperty(value = "身份验证令牌",position = 15)
	@TableField(exist = false)
	private String authToken;
	/**用户角色集合*/
	@ApiModelProperty(value = "用户角色集合",position = 16)
	@TableField(exist = false)
	private List<SysRole> roleCodes;

	/**用户组织机构信息*/
	@ApiModelProperty(value = "用户组织机构信息",position = 18)
	@TableField(exist = false)
	private SysOrg sysOrg;

	@NotBlank(message="角色名不能为空")
	@ApiModelProperty(value = "角色名",position = 19)
	@TableField(exist = false)
	private String roleName;

	@ApiModelProperty(value = "用户组织机构名",position = 20)
	@TableField(exist = false)
	private String orgName;

	@NotBlank(message="用户组织机构编码不能为空")
	@ApiModelProperty(value = "用户组织机构编码",position = 20)
	@TableField(exist = false)
	private String orgCode;

	@ApiModelProperty(value = "备注",position = 20)
	@TableField(exist = false)
	private String remark;

	@ApiModelProperty(value = "极光推送别名",position = 21)
	@TableField(exist = false)
	private String alias;
	@ApiModelProperty(value = "用户头像id" , position = 22)
	@TableField(exist = false)
	private String headImgId;
	@ApiModelProperty(value = "用户头像地址" , position = 23)
	@TableField(exist = false)
	private String headImgPath;
	/**导入检验问题信息*/
	@TableField(exist = false)
	private String errorMsg;

//	@Override
//	protected Serializable pkVal() {
//		return this.id;
//	}
//
//	@Override
//	public String getErrorMsg() {
//		return this.errorMsg;
//	}
//
//	@Override
//	public void setErrorMsg(String errorMsg) {
//		this.errorMsg=errorMsg;
//		this.remark=errorMsg;
//	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStation(){
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getUserNumber(){
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone(){
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDuty(){
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getOrgId(){
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy(){
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getIsDel(){
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}
