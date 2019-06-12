package com.zyqt.platform.item.mapper;

import com.zyqt.platform.item.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyqt.platform.util.BasePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author zgz
 * @since 2019-05-08
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 获取用户信息列表
     *
     * @param [page, params]
     * @return java.util.List
     * @author zhouchen
     * @date 2018/9/18 16:27
     */
    List getUserInfoList(BasePage page, Map params);
    /**
     * 条件获取全部用户信息列表
     * @author zhouchen
     * @date  2018/11/2 14:41
     * @param [Params]
     * @return java.util.List
     */
    List getAllUserInfo(Map Params);

    /**
     * 获取用户上级集合
     *
     * @param []
     * @return java.util.List
     * @author zhouchen
     * @date 2018/9/19 9:53
     */
    List<SysUser> getSuperUserList(Map params);
    List<SysUser> getSuperUserList(Map params, BasePage page);

    /**
     * 获取用户绑定上级集合
     *
     * @param [userId]
     * @return java.util.List<com.zyqt.platform.sys.entity.SysUser>
     * @author zhouchen
     * @date 2018/10/15 15:33
     */
    List<SysUser> getSuperUserListByUserId(Map params);

    List<SysUser> getSuperUserListByUserId(@Param("userId" )String userId);
    /**
     * 获取用户绑定下级集合
     *
     * @param [userId]
     * @return java.util.List<com.zyqt.platform.sys.entity.SysUser>
     * @author zhouchen
     * @date 2018/10/15 15:33
     */
    List<SysUser> getNextUserListByUserId(Map params);
    /**
     *获取用户绑定下级id集合
     * @author zhouchen
     * @date  2018/11/8 9:20
     * @param [userId]
     * @return java.util.List<java.lang.String>
     */
    List<String> getNextUserIdByUserId(String userId);
    /**
     * 获取多个用户绑定下级id集合
     * @author zhouchen
     * @date  2018/11/21 9:42
     * @param [userIds]
     * @return java.util.List<java.lang.String>
     */
    Set<String> getNextUserIdByUserIds(@Param("userIds" ) Set<String> userIds);

    /**
     * 获取用户下级集合
     *
     * @param [nextStation, orgCode]
     * @return java.util.List<com.zyqt.platform.sys.entity.SysUser>
     * @author zhouchen
     * @date 2018/9/28 16:09
     */
    List<SysUser> getNextUserList(@Param("nextStation" ) int nextStation, @Param("orgCode" ) String orgCode);

    /**
     * 获取用户信息列表
     *
     * @param [page, params]
     * @return java.util.List
     * @author qinhb
     * @date 2018/9/29 16:27
     */
    List getUserInfoList(Map params);

    List<String> getUserNamesByOrgCode(String orgCode);
    List<String> getUserIdsByOrgId(String orgId);
    /**
     * 获取全部最高级用户的组织机构编码
     * @author zhouchen
     * @date  2018/11/21 9:35
     * @param []
     * @return java.util.List<java.lang.String>
     */
    List<String> getTopCode();
    /**
     * 通过账号查询用户id
     * @author zhouchen
     * @date  2018/11/27 15:58
     * @param [userName]
     * @return java.lang.String
     */
    String getUserIdByUserName(String userName);

    List<SysUser> querySysUsersByRealName(String realName);
}