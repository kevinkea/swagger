package com.zyqt.platform.util;

//import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* 自定义分页类
*
* @author zhouchen
* @date 2018-09-10 15:48
*/
public class BasePage<T> extends Page {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页" , example = "1" , position = 1)
    private int current;
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数，默认 10" , example = "10" , position = 2)
    private int size;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数" , example = "1" , position = 3)
    private int pages;
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数" , position = 4)
    private long total;
    /**
     * 查询数据列表
     */
    @ApiModelProperty(value = "查询数据列表" , position = 5)
    private List records;
    /**
     * 查询总记录数（默认 true）
     */
    @ApiModelProperty(value = "查询总记录数（默认 true）" , position = 6,hidden = true)
    private boolean searchCount;

    /**
     * 开启排序（默认 true） 只在代码逻辑判断 并不截取sql分析
     *
     * @see
     **/
    @ApiModelProperty(value = "开启排序（默认 true） 只在代码逻辑判断 并不截取sql分析" , position = 7,hidden = true)
    private boolean openSort;

    /**
     * 优化 Count Sql 设置 false 执行 select count(1) from (listSql)
     */
    @ApiModelProperty(value = "优化 Count Sql 设置 false 执行 select count(1) from (listSql)" , position = 8,hidden = true)
    private boolean optimizeCountSql;

    /**
     * <p>
     * SQL 排序 ASC 集合
     * </p>
     */
    @ApiModelProperty(value = "SQL 排序 ASC 集合" , position = 9,hidden = true)
    private List<String> ascs;
    /**
     * <p>
     * SQL 排序 DESC 集合
     * </p>
     */
    @ApiModelProperty(value = "SQL 排序 DESC 集合" , position = 10,hidden = true)
    private List<String> descs;

    /**
     * 是否为升序 ASC（ 默认： true ）
     *
     * @see #ascs
     * @see #descs
     */
    @ApiModelProperty(value = "是否为升序 ASC（ 默认： true ）" , position = 11,hidden = true)
    private boolean isAsc;

    /**
     * <p>
     * SQL 排序 ORDER BY 字段，例如： id DESC（根据id倒序查询）
     * </p>
     * <p>
     * DESC 表示按倒序排序(即：从大到小排序)<br>
     * ASC 表示按正序排序(即：从小到大排序)
     *
     * @see #ascs
     * @see #descs
     * </p>
     */
    @ApiModelProperty(value = "SQL 排序 ORDER BY 字段，例如： id DESC（根据id倒序查询）" , position = 12,hidden = true)
    private String orderByField;
    /**
     * 查询参数（ 不会传入到 xml 层，这里是 Controller 层与 service 层传递参数预留 ）
     */
    @ApiModelProperty(value = "查询参数（ 不会传入到 xml 层，这里是 Controller 层与 service 层传递参数预留 ）" , position = 13,hidden = true)
    private Map<String, Object> condition=new HashMap<>();
    /**登陆人信息*/
//    @ApiModelProperty(hidden = true)
//    private Object loginUser;
//    @ApiModelProperty(hidden = true)
//    public Object getLoginUser() {
//        return loginUser;
//    }
//    public void setLoginUser(Object loginUser) {
//        this.loginUser = loginUser;
//    }

    @ApiModelProperty(hidden = true)
    private  int offset;
    @ApiModelProperty(hidden = true)
    private  int limit;
}
