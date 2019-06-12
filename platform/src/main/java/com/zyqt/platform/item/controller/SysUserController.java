package com.zyqt.platform.item.controller;

import com.zyqt.platform.item.entity.RegisterUser;
import com.zyqt.platform.item.entity.SysUser;
import com.zyqt.platform.item.service.ISysUserService;
import com.zyqt.platform.util.BaseForm;
import com.zyqt.platform.util.BasePage;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author zgz
 * @since 2019-05-08
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/item/sysUser" )
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 用户注册
     *
     * @return com.zyqt.platform.util.BaseForm
     * @Author zgz
     * @Description
     * @Date
     * @Param [sys]
     **/
    @ApiOperation(value = "新增")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户账号(必填)", required = true)
        , @ApiImplicitParam(name = "password", value = "密码(必填)", required = true)
        , @ApiImplicitParam(name = "orgId", value = "组织机构ID(必填)", required = true)
        , @ApiImplicitParam(name = "roleIds", value = "给用户添加的角色id集合(必填)", required = true)
    })
    @ApiResponses(value = {@ApiResponse(code = 1005, message = "注册失败!用户账号已存在")})
    @RequestMapping(value = "/userInfo", method = {RequestMethod.POST})
    @RequiresPermissions("system_management_user:registerUser")
    public BaseForm registerUser(@RequestBody RegisterUser registerUser) {
        BaseForm result = new BaseForm();
        SysUser loginUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //更新人与创建人为登陆人
        registerUser.setUpdateBy(loginUser.getUserName());
        registerUser.setCreateBy(loginUser.getUserName());
        SysUser newSysUser = sysUserService.registerUser(registerUser, registerUser);
        if (newSysUser != null) {
            result.setMsg("注册成功!");
        } else {
            result.setStatus(BaseForm.Status.FAILURE);
            result.setMsg("注册失败!用户账号已存在");
        }
        return result;
    }

    /**
     * 用户分页查询接口.
     *
     * @return
     */
    @ApiOperation(value = "用户分页查询接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "orgId", value = "组织机构ID(选填)")
            , @ApiImplicitParam(name = "roleId", value = "角色ID(选填)")
            , @ApiImplicitParam(name = "status", value = "状态(0:锁定;1:非锁定)")
            , @ApiImplicitParam(name = "realName", value = "真实名字(选填)")
            , @ApiImplicitParam(name = "keyword", value = "关键字(选填;用户账号/真实名字/联系方式/职务)")
    })
    @RequestMapping(value = "/allUserInfo", method = {RequestMethod.GET})
    public BaseForm<SysUser> getAllUserInfo(BasePage page, @RequestParam Map params) {
        BaseForm result = new BaseForm();
        result.setData(sysUserService.getUserInfoPage(page, params));
        return result;
    }
}
