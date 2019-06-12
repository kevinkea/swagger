package com.zyqt.platform.item.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 行政区划表
 * </p>
 *
 * @author zgz
 * @since 2019-05-22
 */
public class SysOrg implements Serializable{

private static final long serialVersionUID=1L;

    /**
     * 行政区划名称
     */
private String orgName;
    /**
     * 描述
     */
private String description;
    /**
     * 行政区划级别id
     */
private Integer orgRankId;
    /**
     * 父级ID
     */
private String parentOrgId;
    /**
     * 行政区划编码
     */
private String orgCode;
    /**
     * 组织状态(0--正常状态，1--锁定状态)
     */
private String status;
    /**
     * 结束生效时间
     */
private LocalDateTime endtime;
    /**
     * 开始生效时间
     */
private LocalDateTime starttime;
    /**
     * 创建时间
     */
private LocalDateTime createDate;
    /**
     * 创建人名
     */
private String createBy;
    /**
     * 更新时间
     */
private LocalDateTime updateDate;
    /**
     * 更新人名
     */
private String updateBy;


public String getOrgName(){
        return orgName;
        }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
        }

public String getDescription(){
        return description;
        }

    public void setDescription(String description) {
        this.description = description;
        }

public Integer getOrgRankId(){
        return orgRankId;
        }

    public void setOrgRankId(Integer orgRankId) {
        this.orgRankId = orgRankId;
        }

public String getParentOrgId(){
        return parentOrgId;
        }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
        }

public String getOrgCode(){
        return orgCode;
        }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        }

public String getStatus(){
        return status;
        }

    public void setStatus(String status) {
        this.status = status;
        }

public LocalDateTime getEndtime(){
        return endtime;
        }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
        }

public LocalDateTime getStarttime(){
        return starttime;
        }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
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
