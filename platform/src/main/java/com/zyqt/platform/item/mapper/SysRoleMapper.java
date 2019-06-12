package com.zyqt.platform.item.mapper;

import com.zyqt.platform.item.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyqt.platform.util.BasePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author zgz
 * @since 2019-05-22
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 根据用户账号获取角色信息
	 *
	 * @param [userName]
	 * @return java.util.List<com.zyqt.platform.sys.entity.SysRole>
	 * @author zhouchen
	 * @date 2018/9/4 17:14
	 */
	List<SysRole> getRolesByUserName(@Param("userName" ) String userName);

	/**
	 * 根据用户id获取角色信息
	 *
	 * @param [userId]
	 * @return java.util.List<com.zyqt.platform.sys.entity.SysRole>
	 * @author zhouchen
	 * @date 2018/9/18 16:05
	 */
	List<SysRole> getRolesByUserId(@Param("userId" ) String userId);
	/**
	 * 通过角色名获取角色id
	 * @author zhouchen
	 * @date  2018/11/26 16:32
	 * @param [roleName]
	 * @return java.lang.String
	 */
	String getRoleIdByRoleName(String roleName);

	List getRoleListByPage (BasePage page, Map params);
}