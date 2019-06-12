package com.zyqt.platform.item.service;

import com.zyqt.platform.item.entity.SysOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyqt.platform.util.BasePage;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行政区划表 服务类
 * </p>
 *
 * @author zgz
 * @since 2019-05-22
 */
public interface ISysOrgService extends IService<SysOrg> {
//	/**
//	 * 组织机构分页查询
//	 *
//	 * @param [params, page]
//	 * @return com.zyqt.platform.util.BasePage
//	 * @author zhouchen
//	 * @date 2018/9/14 14:27
//	 */
//	BasePage getSysOrgPage(Map params, BasePage page);
//
//	/**
//	 * 组织机构树形查询
//	 *
//	 * @param [params]
//	 * @return java.util.List
//	 * @author zhouchen
//	 * @date 2018/9/14 15:19
//	 */
//	List getSysOrgTree(Map params, SysOrg loginOrg);
//	/**
//	 * 获取全部组织机构树形
//	 * @author zhouchen
//	 * @date  2019/3/27 9:57
//	 * @param [params, loginOrg]
//	 * @return com.zyqt.platform.sys.entity.SysOrg
//	 */
//	List<SysOrg> getAllSysOrgTree(Map params, SysOrg loginOrg);
//
//	/**
//	 * 新增组织机构
//	 *
//	 * @param [sysOrg]
//	 * @return boolean
//	 * @author zhouchen
//	 * @date 2018/9/17 10:24
//	 */
//	int addSysOrg(SysOrg sysOrg);
//
//	/**
//	 * 更新组织机构
//	 *
//	 * @param [sysOrg]
//	 * @return int
//	 * @author zhouchen
//	 * @date 2018/9/17 15:27
//	 */
//	int updateSysOrg(SysOrg sysOrg);

	/**
	 * 获取全部上级与本身编码
	 *
	 * @param [orgId]
	 * @return java.util.List<java.lang.String>
	 * @author zhouchen
	 * @date 2018/9/27 15:06
	 */
	List<String> getAllSupOrgCode(String orgId) throws Exception;
//	/**
//	 * 组织机构导出
//	 * @author zhouchen
//	 * @date  2018/11/5 9:37
//	 * @param [response, params]
//	 * @return boolean
//	 */
//	boolean exportOrgList(HttpServletResponse response, Map params) throws Exception;
}
