package com.zyqt.platform.item.service.impl;

import com.zyqt.platform.item.entity.RegisterUser;
import com.zyqt.platform.item.entity.SysRole;
import com.zyqt.platform.item.entity.SysUser;
import com.zyqt.platform.item.mapper.ShiroMapper;
import com.zyqt.platform.item.mapper.SysRoleMapper;
import com.zyqt.platform.item.mapper.SysUserMapper;
import com.zyqt.platform.item.service.ISysOrgService;
import com.zyqt.platform.item.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyqt.platform.util.BasePage;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author zgz
 * @since 2019-05-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    private ShiroMapper shiroMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysOrgService iSysOrgService;
//    @Autowired
//    private SysUserRoleMapper sysUserRoleMapper;
//    @Autowired
//    private SysUserSuperMapper sysUserSuperMapper;
//    @Autowired
//    private SysOrgMapper sysOrgMapper;
//    @Autowired
//    private ISysUserService iSysUserService;
//    @Autowired
//    private ISysUserRoleService iSysUserRoleService;
//    @Autowired
//    private FileBusinessMapper rFileBusinessMapper;
    //    @Value("${user.defaultHeadImgId}")
//    private String defaultHeadImgId; //默认头像id
//    @Value("${user.defaultPassword}")
//    private String defaultPassword;//默认用户密码

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SysUser registerUser(@NonNull SysUser sysUser, RegisterUser registerUser) {
        //密码加密
        String newPs = getEncryptionPassword(sysUser.getUserName(), sysUser.getPassword());
        sysUser.setPassword(newPs);
        sysUser.createSysUser();
        //判断用户名是否存在
        if (shiroMapper.getUserByOneName(sysUser.getUserName()) == null) {
            sysUserMapper.insert(sysUser);
            //给用户添加角色与上级
//            updateUserRoleAndSuperUser(sysUser.getId(), registerUser);
            return sysUser;
        }
        return null;
    }
//
//    @Transactional(rollbackFor = {Exception.class})
//    @Override
//    public int updateUserIsSuccess(@NonNull RegisterUser sysUser) {
//        //判断用户头像id是否存在,并修改用户头像
//        Optional.ofNullable(sysUser.getHeadImgId()).ifPresent((headImgId) -> {
//            rFileBusinessMapper.delete(new EntityWrapper<FileBusiness>().eq("business_id", sysUser.getId()));
//            rFileBusinessMapper.insert(new FileBusiness(headImgId, sysUser.getId()));
//        });
//        //判断是否更新用户名与密码
//        if (StringUtils.isNoneBlank(sysUser.getUserName())) {
//            if (StringUtils.isNoneBlank(sysUser.getPassword())) {
//                //密码加密
//                String newPs = getEncryptionPassword(sysUser.getUserName(), sysUser.getPassword());
//                sysUser.setPassword(newPs);
//            }
//            //判断更新账号名与原有账号名是否改变
//            if (!sysUserMapper.selectById(sysUser.getId()).getUserName().equals(sysUser.getUserName())) {
//                //判断更新账号名是否与其他账号名相同
//                if (shiroMapper.getUserByOneName(sysUser.getUserName()) != null) {
//                    return 2;
//                }
//            }
//        }
//
//        //判断是否更新成功
//        if (sysUserMapper.updateById(sysUser) == 1) {
//            //更新用户角色与上级
//            updateUserRoleAndSuperUser(sysUser.getId(), sysUser);
//            return 1;
//        }
//        return 0;
//    }

    @Override
    public BasePage getUserInfoPage(BasePage page, Map params) {
        List<SysUser> sysUsersList = sysUserMapper.getUserInfoList(page, params);
        for (SysUser sysUser : sysUsersList) {
            //获取组织机构信息
//            sysUser.setSysOrg(iSysOrgService.getAllSupOrgCode(sysUser.getOrgId()));
            //获取角色信息
            sysUser.setRoleCodes(sysRoleMapper.getRolesByUserId(sysUser.getId()));
        }
        return (BasePage) page.setRecords(sysUsersList);
    }

//    @Override
//    public BasePage getSuperUserList(SysUser sysUser, BasePage page, Map params) throws Exception {
//        //获取全部直属上级组织机构
////        List supOrgCodes = iSysOrgService.getAllSupOrgCode(sysUser.getOrgId());
//        //获取最高级的组织机构编码
//        String topOrgCode = getTopCode(sysUser.getOrgId());
//        params.put("topOrgCode", topOrgCode);
//        List<SysRole> roles = sysUser.getRoleCodes();
//        page.setRecords(getSupUsers(roles, params, page));
//        return page;
//    }
//
//    @Override
//    public List<SysUser> getNextUserList(SysUser sysUser) throws Exception {
//        List<SysRole> roles = sysUser.getRoleCodes();
//        return getNextUsers(sysUser.getSysOrg().getOrgCode(), roles);
//    }
//
//    private List<SysUser> getSupUsers(@NonNull List<SysRole> roles, Map params, BasePage page) {
//        List<SysUser> sysUsers = new ArrayList<>();
////        for (SysRole role : roles) {
////            int supStation = role.getStation() - 1;
////            //循环找出上级用户
////            for (int i = supStation; i > 0; i--) {
////                params.put("supStation", i);
////                //暂时取消分页20181123
////                List<SysUser> newSysUsers = sysUserMapper.getSuperUserList(params);
////                if (newSysUsers.size() != 0) {
////                    sysUsers.addAll(newSysUsers);
////                    break;
////                }
////            }
////        }
//        return sysUsers;
//    }

//    private List<SysUser> getNextUsers(@NonNull String OrgCodes, @NonNull List<SysRole> roles) {
//        List<SysUser> sysUsers = new ArrayList<>();
//        SysRole lastRole = sysRoleMapper.selectList(new EntityWrapper<SysRole>()
//            .orderBy(true, "station", false)).get(0);
////        for (SysRole role : roles) {
////            int nextStation = role.getStation() + 1;
////            //循环找出下级用户
////            for (int i = nextStation; i <= lastRole.getStation(); i++) {
////                List<SysUser> newSysUsers = sysUserMapper.getNextUserList(i, OrgCodes);
////                if (newSysUsers.size() != 0) {
////                    sysUsers.addAll(newSysUsers);
////                    break;
////                }
////            }
////        }
//        return sysUsers;
//    }

    /**
     * 获取加密密码
     *
     * @param [userName, password]
     * @return java.lang.String
     * @author zhouchen
     * @date 2018/8/31 15:47
     */
    private String getEncryptionPassword(@NonNull String userName, @NonNull String password) {
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(userName);
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        return new SimpleHash("MD5", password, salt, 1024).toHex();
    }
//
//    /**
//     * 给用户添加角色与上级
//     *
//     * @param [userId, registerUser]
//     * @return void
//     * @author zhouchen
//     * @date 2018/9/19 9:36
//     */
//    private void updateUserRoleAndSuperUser(@NonNull String userId, RegisterUser registerUser) {
//        if (null != registerUser.getRoleIds()
//            && registerUser.getRoleIds().size() > 0) {
//            //删除用户原角色
//            sysUserRoleMapper.delete(new EntityWrapper<SysUserRole>().eq("user_id", userId));
//            for (String roleId : registerUser.getRoleIds()) {
//                if (StringUtils.isNoneBlank(roleId)) {
//                    SysUserRole newSysUserRole = new SysUserRole(userId, roleId);
//                    newSysUserRole.createDefaultInfo();
//                    sysUserRoleMapper.insert(newSysUserRole);
//                }
//            }
//        }
////        if (StringUtils.isNoneBlank(registerUser.getSuperUserId())) {
//////            SysUserSuper newSysUserSuper = new SysUserSuper(userId, registerUser.getSuperUserId());
//////            newSysUserSuper.createDefaultInfo();
//////            //删除用户原上级
//////            sysUserSuperMapper.delete(new EntityWrapper<SysUserSuper>().eq("user_id" , userId));
//////            sysUserSuperMapper.insert(newSysUserSuper);
//////        }
//
//        //判断是否有上级id
////        if (null != registerUser.getSuperUserIds()
////                && registerUser.getSuperUserIds().size() > 0) {
////            //删除用户原上级
////            sysUserSuperMapper.delete(new EntityWrapper<SysUserSuper>().eq("user_id", userId));
////            for (String superId : registerUser.getSuperUserIds()) {
////                SysUserSuper newSysUserSuper = new SysUserSuper(userId, superId);
////                newSysUserSuper.createDefaultInfo();
////                sysUserSuperMapper.insert(newSysUserSuper);
////            }
////        }
//        //判断是否为最高级
////        if ("1".equals(registerUser.getIsTop())) {
////            //删除用户原上级
////            sysUserSuperMapper.delete(new EntityWrapper<SysUserSuper>().eq("user_id", userId));
////        }
//    }
//
//    @Override
//    public List<SysUser> getAllUserList(Map params) {
//        List<SysUser> sysUsersList = sysUserMapper.getUserInfoList(params);
//        for (SysUser sysUser : sysUsersList) {
//            //获取组织机构信息
//            sysUser.setSysOrg(iSysOrgService.selectById(sysUser.getOrgId()));
//            //获取角色信息
//            sysUser.setRoleCodes(sysRoleMapper.getRolesByUserId(sysUser.getId()));
//            //获取上级用户信息
//            Map up = new HashMap();
//            up.put("userId", sysUser.getId());
////            sysUser.setSuperUsers(sysUserMapper.getSuperUserListByUserId(up));
//        }
//        return sysUsersList;
//    }
//
//    @Override
//    public boolean exportUserList(HttpServletResponse response, Map params) throws Exception {
//        List<SysUser> sysUsersList = sysUserMapper.getAllUserInfo(params);
//        ExcelTool.exportExcel(sysUsersList, "用户信息", "用户信息", SysUser.class, "用户信息", true, response);
//        return true;
//    }
//
//    @Override
//    public boolean exportUserTemplate(HttpServletResponse response) throws Exception {
//        SysUser userTemplate = new SysUser("hezhang", "河长", "123456@qq.com", "13123456789"
//            , "13123456789", "成都市河长办", "河长", "锁定"
//            , "省级河长", "四川省", "510000000000", "示例行必须删除,所有导入用户密码还原为Pass@2018");
//        Map<String, Object> param = new HashMap();
//        //对象转map
//        BaseTool.objectToMap(userTemplate, param);
//        List params = new ArrayList();
//        params.add(param);
//        ExcelTool.downLoadExcelTemplate("用户信息模板", "exportTemplates/用户信息录入模板.xls", params, response);
////        String result= ExcelTool.downLoadExcelTemplateToZip("用户录入模板", "classpath:/exportTemplates/用户录入模板.zip",response);
////        System.out.println(result);
////        List<SysUser> sysUsersList =new ArrayList<>();
////        sysUsersList.add(userTemplate);
////        ExcelTool.exportExcel(sysUsersList,"用户信息模板","用户信息模板",SysUser.class,"用户信息模板",true,response);
//        return true;
//    }
//
//    @Override
//    @Transactional(rollbackFor = {Exception.class})
//    public ImportResult<SysUser> importUserList(MultipartFile file, SysUser loginUser) throws Exception {
////        List<SysUser> importUsers=ExcelTool.importExcel(file,1,1,SysUser.class);
//        int userSuccessNum = 0, updateSuccessNum = 0;
//        //导入用户列表
//        List<SysUser> errorUsers = new ArrayList<>();
//        List<SysUser> successUsers = new ArrayList<>();
//        List<SysUserRole> successUserRoles = new ArrayList<>();
//        SysUserRole newSysUserRole;
//        SysUser midUser;
//        //导入数据(导入校验开启)
//        ExcelImportResult excelImportResult = ExcelTool.importExcel(file, 1, 1, true, SysUser.class);
//        List<SysUser> importUsers = excelImportResult.getList();
//        errorUsers.addAll(excelImportResult.getFailList());
//        if (importUsers != null && importUsers.size() > 0) {
//            for (SysUser importUser : importUsers) {
//                try {
//                    //密码加密(导入初始密码Pass@2018)
//                    String newPs = getEncryptionPassword(importUser.getUserName(), DigestUtils.md5Hex(defaultPassword));
//                    //用户导入初始化
//                    importUser.importSysUser(newPs, loginUser.getRealName(), loginUser.getRealName());
//                    String orgId = sysOrgMapper.selectOrgIdByNumber(importUser.getOrgCode());
//                    String roleId = sysRoleMapper.getRoleIdByRoleName(importUser.getRoleName());
//                    //判断组织机构是否存在
//                    if (StringUtils.isNotBlank(orgId)) {
//                        importUser.setOrgId(orgId);
//                    } else throw new MissingFormatWidthException("组织机构代码错误或数据库没有此组织机构编码!");
//                    //判断用户名是否存在,存在则更新
//                    if ((midUser = shiroMapper.getUserByOneName(importUser.getUserName())) != null) {
//                        importUser.setId(midUser.getId());
//                        updateSuccessNum++;
//                    } else {
//                        importUser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//                        userSuccessNum++;
//                    }
//                    //判断角色是否存在
//                    if (StringUtils.isNotBlank(roleId)) {
//                        newSysUserRole = new SysUserRole(importUser.getId(), roleId);
//                        newSysUserRole.createDefaultInfo();
//                    } else throw new MissingFormatWidthException("角色名错误或数据库没有此角色名!");
//                    //如果用户更新,删除原本的用户角色关系
//                    if (midUser != null) {
//                        sysUserRoleMapper.delete(new EntityWrapper<SysUserRole>().eq("user_id", importUser.getId()));
//                    }
//                    //添加用户信息与角色
//                    iSysUserService.insertOrUpdate(importUser);
//                    iSysUserRoleService.insertOrUpdate(newSysUserRole);
////                    successUserRoles.add(newSysUserRole);
////                    successUsers.add(importUser);
//                } catch (NullPointerException e) {
//                    importUser.setRemark("有空数据,用户角色或组织机构错误或数据库中不存在!");
//                    errorUsers.add(importUser);
//                } catch (MissingFormatWidthException e) {
//                    importUser.setRemark(e.getMessage());
//                    errorUsers.add(importUser);
//                } catch (Exception e) {
//                    importUser.setRemark("导入发生未知性错误!");
//                    errorUsers.add(importUser);
//                }
//            }
////            //用户信息与对应角色持久化
////            iSysUserService.insertOrUpdateBatch(successUsers);
////            iSysUserRoleService.insertOrUpdateBatch(successUserRoles);
//        }
//        return new ImportResult(userSuccessNum, updateSuccessNum, errorUsers);
//    }
//
//    @Override
//    public String getTopCode(@NonNull String orgId) {
//        List<String> topCodes = sysUserMapper.getTopCode();
//        String orgCode = iSysOrgService.selectById(orgId).getOrgCode();
//        for (String topCode : topCodes) {
//            if (orgCode.matches(topCode + "(.*)")) {
//                return topCode;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public String getTopOrgId(String orgId) {
//        List<String> topCodes = sysUserMapper.getTopCode();
//        String orgCode = iSysOrgService.selectById(orgId).getOrgCode();
//        for (String topCode : topCodes) {
//            if (orgCode.matches(topCode + "(.*)")) {
//                return iSysOrgService.selectOne(new EntityWrapper<SysOrg>().eq("org_code", topCode)).getId();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Set<String> getAllNextUserList(String userId) {
//        //防止死循环变量
//        int midNum = 0;
//        Set<String> userIdResult = new HashSet();
//        Set<String> midResults = new HashSet();
//        midResults.add(userId);
//        userIdResult.add(userId);
//        while (true) {
//            Set<String> userIds = sysUserMapper.getNextUserIdByUserIds(midResults);
//            if (userIds.size() > 0) {
//                //中间量清空
//                midResults.clear();
//                midResults.addAll(userIds);
//                userIdResult.addAll(userIds);
//            } else {
//                break;
//            }
//            //判断结果是否有增加,没有跳出死循环
//            if (userIdResult.size() <= midNum) {
//                String ids = "";
//                for (String id : userIds) {
//                    ids += id + ",";
//                }
//                log.error("获取下级用户发生死循环!问题用户id集合:" + ids);
//                break;
//            } else {
//                midNum = userIdResult.size();
//            }
//        }
//        return userIdResult;
//    }
//
//    @Override
//    public void initialize(Map<String, Object> params, SysUser loginUser) {
//        //如果为普通用户,传入登陆用户下级所有用户id号集合
//        if (loginUser.getRoleCodes().get(0).getStatus() == 2) {
//            params.put("allNextUserIds", getAllNextUserList(loginUser.getId()));
//        }
//        BaseTool.setLoginRoleAndOrgCode(params, loginUser);
//    }

//    @Override
//    public SysUser getUserByUserName(String userName) {
//        return shiroMapper.getUserByName(userName);
//    }
//
//    @Override
//    public List<SysUser> querySysUsersByRealName(String realName) throws Exception {
//
//        return sysUserMapper.querySysUsersByRealName(realName);
//    }
//
//    @Override
//    public SysUser querySysUserInfoByEntity(SysUser sysUser) throws Exception {
//        return sysUserMapper.selectOne(sysUser);
//    }
}
