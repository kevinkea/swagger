package com.zyqt.platform.item.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author zgz
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_project_info")
public class TProjectInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 创建基本信息
     **/
    public void createDefaultInfo(SysUser user) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    @ApiModelProperty(value = "", position = 1)
    private String id;

    /**
     * 项目编号，如20190319000001
     */
    private String proNo;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 责任部门id
     */
    private String deptNo;

    /**
     * 责任部门
     */
    private String deptName;

    /**
     * 项目类别
     */
    private String type;

    /**
     * 项目状态(默认延续项目）
     */
    private String status;

    /**
     * 项目属性(默认重大重点)
     */
    private String proAttr;

    /**
     * 合作单位
     */
    private String joinUnit;

    /**
     * 项目负责人id
     */
    private String userId;

    /**
     * 项目负责人
     */
    private String userName;

    /**
     * 项目立项时间
     */
    private Date createTime;

    /**
     * 项目描述
     */
    private String proDesc;

    /**
     * 预算金额
     */
    private BigDecimal preMoney;

    /**
     * 采购方式
     */
    private String buyMean;

    /**
     * 自研预算金额
     */
    private BigDecimal preMoneyZy;

    /**
     * 委派物业金额
     */
    private String preMoneyWp;

    /**
     * 费用测算说明
     */
    private String costDesc;

    /**
     * 研发内容
     */
    private String studyConent;

    /**
     * 研发成果
     */
    private String studyResult;

    /**
     * 备注
     */
    private String marks;

    /**
     * 合同签订时间
     */
    private Date signDate;

    /**
     * 项目结项时间
     */
    private Date offDate;

    /**
     * 状态(未立项，已立项，已采购，已结项，已取消)
     */
    private String flowStatus;

    /**
     * 合同修订时间
     */
    private Date contractDate;


}
