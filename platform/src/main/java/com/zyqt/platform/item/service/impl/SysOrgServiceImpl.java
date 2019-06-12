package com.zyqt.platform.item.service.impl;

import com.zyqt.platform.item.entity.SysOrg;
import com.zyqt.platform.item.mapper.SysOrgMapper;
import com.zyqt.platform.item.service.ISysOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyqt.platform.item.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 行政区划表 服务实现类
 * </p>
 *
 * @author zgz
 * @since 2019-05-22
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private ISysUserService iSysUserService;

	@Override
	public List<String> getAllSupOrgCode(String orgId) throws Exception {
		List<String> orgCodeList = new ArrayList<>();
		SysOrg sysOrg;
		String nowOrgCode, oldOrgCode = "" ;
		while (true) {
			sysOrg = sysOrgMapper.selectById(orgId);
			if (sysOrg != null) {
				nowOrgCode = sysOrg.getOrgCode();
				//判断组织机构编码是否依次递减,防止数据库错误死循环
				if (oldOrgCode != "" && nowOrgCode.length() >= oldOrgCode.length()) {
					throw new Exception("数据库组织机构父级与子级编码错误!!!错误组织机构id为:"
						+ orgId + ";名字为:" + sysOrg.getOrgName());
				}
				orgCodeList.add(nowOrgCode);
				orgId = sysOrg.getParentOrgId();
				oldOrgCode = nowOrgCode;
			} else {
				break;
			}
		}
		return orgCodeList;
	}
}
