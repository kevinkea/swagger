package com.zyqt.platform.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyqt.platform.item.entity.SysOrg;
import com.zyqt.platform.item.entity.SysUser;
import com.zyqt.platform.item.mapper.ShiroMapper;
import com.zyqt.platform.item.mapper.SysRoleMapper;
import com.zyqt.platform.item.service.ISysOrgService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;


@Component
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private ShiroMapper shiroMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysOrgService iSysOrgService;
//    @Autowired
//    private FileInfoMapper fileInfoMapper;
//    @Value("${jPush.serverIp}")
//    private String serverIp;
//    @Value("${user.defaultHeadImgId}")
//    private String defaultHeadImgId; //默认头像id
//
//
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证————" );
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;//获取用户输入的token
        String userName = token.getUsername();
        SysUser sysUser = shiroMapper.getUserByName(userName);
        if (sysUser == null) {
            throw new AccountException();
        } else {
            //获取用户行政区信息
            sysUser.setSysOrg(iSysOrgService.getOne(new QueryWrapper<SysOrg>()
                    .eq("id" , sysUser.getOrgId())));
            //获得该用户角色
            sysUser.setRoleCodes(sysRoleMapper.getRolesByUserName(userName));
//            //设置极光推送别名
//            sysUser.setAlias(serverIp+"_"+sysUser.getUserName());
        /*    //获取用户头像地址
             List<FileSelect> fileSelects=rProbPhotoMapper.getFilePathByTypes(sysUser.getId(),"17");
            sysUser.setHeadImgPath(fileSelects.isEmpty() ?
                    Optional.ofNullable(fileInfoMapper.selectById(defaultHeadImgId))
                            .map(path->path.getFullPath()).orElse("")
                    : fileSelects.get(0).getFilePath());*/
            //如果身份认证验证成功，返回一个AuthenticationInfo实现；
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    sysUser, //用户名
                    sysUser.getPassword(), //密码
                    ByteSource.Util.bytes(userName),//salt值
                    getName()  //Realm name
            );

            return authenticationInfo;
        }
    }

    /**
     * 通过用户获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————" );
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissionCodes = new ArrayList<>();
        //获得该用户角色
        List<String> roleCodes = shiroMapper.getRoles(sysUser.getUserName());
        simpleAuthorizationInfo.addRoles(roleCodes);
        for (String roleCode : roleCodes) {
            //如果角色是管理员,开放全部授权
            if ("admin".equals(roleCode)) {
                //获取所有权限
                permissionCodes = shiroMapper.getAllPermissions();
            } else {
                //根据角色获取权限
                permissionCodes = shiroMapper.getPermissions(roleCode);
            }
            simpleAuthorizationInfo.addStringPermissions(permissionCodes);
        }
        return simpleAuthorizationInfo;
    }
}
