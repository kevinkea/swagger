package com.zyqt.platform.item.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author zgz
 * @since 2019-05-08
 */
public class SysUser implements Serializable{

private static final long serialVersionUID=1L;

    /**
     * 用户账号
     */
private String userName;
    /**
     * 真实名字
     */
private String realName;
    /**
     * 密码
     */
private String password;
    /**
     * 岗位
     */
private String station;
    /**
     * 员工编号
     */
private String userNumber;
    /**
     * 邮箱
     */
private String email;
    /**
     * 手机号码
     */
private String mobilePhone;
    /**
     * 职务
     */
private String duty;
    /**
     * 行政区划ID
     */
private String orgId;
    /**
     * 有效状态(0:锁定;1:非锁定)
     */
private String status;
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
    /**
     * 是否删除(1:删除0:不删除)
     */
private String isDel;


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

public String getIsDel(){
        return isDel;
        }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
        }

        }
