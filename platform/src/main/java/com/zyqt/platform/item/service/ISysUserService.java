package com.zyqt.platform.item.service;

import com.zyqt.platform.item.entity.RegisterUser;
import com.zyqt.platform.item.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyqt.platform.util.BasePage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author zgz
 * @since 2019-05-08
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 用户注册
     *
     * @return a
     * @Author zhouchen
     * @Date 2018/8/28 20:50
     * @Param SysUser sysUser
     */
    SysUser registerUser(SysUser sysUser, RegisterUser registerUser);

//    /**
//     * 更新用户信息
//     *
//     * @return int 更新用户success
//     * @Author zhouchen
//     * @Description 由于盐值是由用户名得到的缩影不能更改用户名
//     * @Date 14:17 2018/8/29
//     * @Param SysUser sysUser
//     */
//    int updateUserIsSuccess(RegisterUser sysUser);

    /**
     * 获取分页用户信息
     *
     * @param [page, params]
     * @return com.zyqt.platform.util.BasePage
     * @author zhouchen
     * @date 2018/9/18 16:46
     */
    BasePage getUserInfoPage(BasePage page, Map params);

//    /**
//     * 获取用户上级集合
//     *
//     * @param [sysUser]
//     * @return java.util.List<com.zyqt.platform.sys.entity.SysUser>
//     * @author zhouchen
//     * @date 2018/9/28 9:55
//     */
//    BasePage getSuperUserList(SysUser sysUser, BasePage page, Map params) throws Exception;
//
//    /**
//     * 获取用户下级集合
//     *
//     * @param [sysUser]
//     * @return java.util.List<com.zyqt.platform.sys.entity.SysUser>
//     * @author zhouchen
//     * @date 2018/9/28 16:13
//     */
//    List<SysUser> getNextUserList(SysUser sysUser) throws Exception;
//
//    /**
//     * 不分页查询
//     *
//     * @param sysUser
//     * @return
//     * @throws Exception
//     */
//    List<SysUser> getAllUserList(Map params);
//
//    /**
//     * 用户导出
//     *
//     * @param [params]
//     * @return boolean
//     * @author zhouchen
//     * @date 2018/11/2 14:10
//     */
//    boolean exportUserList(HttpServletResponse response, Map params) throws Exception;
//
//    /**
//     * 导出用户模板
//     *
//     * @param [response]
//     * @return boolean
//     * @author zhouchen
//     * @date 2018/11/26 11:05
//     */
//    boolean exportUserTemplate(HttpServletResponse response) throws Exception;
//
////    /**
////     * 用户导入
////     *
////     * @param [response, params]
////     * @return boolean
////     * @author zhouchen
////     * @date 2018/11/26 10:35
////     */
////    ImportResult importUserList(MultipartFile file, SysUser loginUser) throws Exception;
//
//    /**
//     * 根据组织机构orgId获取最高级组织机构code
//     *
//     * @param [orgCode]
//     * @return java.lang.String
//     * @author zhouchen
//     * @date 2018/11/15 15:36
//     */
//    String getTopCode(String orgId);
//
//    /**
//     * 根据组织机构orgId获取最高级组织机构Id
//     *
//     * @param [orgCode]
//     * @return java.lang.String
//     * @author zhouchen
//     * @date 2018/11/15 15:36
//     */
//    String getTopOrgId(String orgId);
//
//    /**
//     * 获取用户下级全部人员id号
//     *
//     * @param [userId]
//     * @return java.util.Set
//     * @author zhouchen
//     * @date 2018/11/21 9:30
//     */
//    Set getAllNextUserList(String userId);
//
//    /**
//     * 查询范围初始化(包括组织机构与用户下级所有关联用户)
//     *
//     * @param [params, loginUser]
//     * @return void
//     * @author zhouchen
//     * @date 2018/12/13 9:54
//     */
//    void initialize(Map<String, Object> params, SysUser loginUser);
//
//    /**
//     * 根据用户名获取用户信息
//     *
//     * @return SysUser
//     * @Author qinhb
//     */
//    SysUser getUserByUserName(String userName);
//
//    /**
//     * 按账号或姓名全文检索
//     *
//     * @param userName
//     * @param realName
//     * @return
//     * @throws Exception
//     */
//    List<SysUser> querySysUsersByRealName(String realName) throws Exception;
//
//    /**
//     * 根据条件查询用户信息
//     *
//     * @param wrapper
//     * @return
//     * @throws Exception
//     */
//    SysUser querySysUserInfoByEntity(SysUser sysUser) throws Exception;
}
