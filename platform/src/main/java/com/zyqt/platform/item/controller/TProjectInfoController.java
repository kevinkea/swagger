package com.zyqt.platform.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyqt.platform.item.entity.TProjectInfo;
import com.zyqt.platform.item.service.ITProjectInfoService;
import com.zyqt.platform.util.BaseForm;
import com.zyqt.platform.util.BasePage;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 项目表 前端控制器
 * </p>
 *
 * @author zgz
 * @since 2019-05-05
 */
@Api(tags = "工时项目管理")
@RestController
@RequestMapping("/item/t-project-info")
public class TProjectInfoController {
    @Autowired
    private ITProjectInfoService iTProjectInfoService;

    @ApiOperation(value = "获取全部项目表数据")
    @RequestMapping(value = "/allProjectInfo", method = {RequestMethod.GET})
    public BaseForm<BasePage<TProjectInfo>> getAllProjectInfo() {
        BaseForm result = new BaseForm();
        result.setData(iTProjectInfoService.list(new QueryWrapper<>()));
        return result;
    }

    @ApiOperation(value = "分页获取项目表数据")
    @RequestMapping(value = "/projectInfoByPage", method = {RequestMethod.POST})
    public BaseForm<BasePage<TProjectInfo>> getProjectInfoByPage(BasePage page, @RequestParam Map params) {
        BaseForm result = new BaseForm();
        result.setData(iTProjectInfoService.page(page, new QueryWrapper<>()));
        return result;
    }

    @ApiOperation(value = "添加项目表数据")
    @ApiResponses(value = {@ApiResponse(code = 1005, message = "操作失败")})
    @RequestMapping(value = "/addProjectInfo", method = {RequestMethod.POST})
    public BaseForm addProjectInfo(@RequestBody TProjectInfo tProjectInfo) {
        BaseForm result = new BaseForm();
        //projectInfo.createDefaultInfo((SysUser) SecurityUtils.getSubject().getPrincipal());
        if (!iTProjectInfoService.save(tProjectInfo)) {
            result.setStatus(BaseForm.Status.FAILURE);
        }
        return result;
    }

    @ApiOperation(value = "更改项目表数据"
            , extensions = @Extension(properties = {@ExtensionProperty(name = "resourceType", value = "button")
            , @ExtensionProperty(name = "parentCode", value = "")}))
    @ApiImplicitParams({@ApiImplicitParam(name = "id", required = true)})
    @ApiResponses(value = {@ApiResponse(code = 1005, message = "操作失败")})
    @RequestMapping(value = "/updateProjectInfo", method = {RequestMethod.PUT})
    @RequiresPermissions("manHour_management_project:updateProjectInfo")
    public BaseForm updateProjectInfo(TProjectInfo tProjectInfo) {
        BaseForm result = new BaseForm();
        if (!iTProjectInfoService.updateById(tProjectInfo)) {
            result.setStatus(BaseForm.Status.FAILURE);
        }
        return result;
    }

    @ApiOperation(value = "删除项目表数据"
            , extensions = @Extension(properties = {@ExtensionProperty(name = "resourceType", value = "button")
            , @ExtensionProperty(name = "parentCode", value = "")}))
    @ApiImplicitParams({@ApiImplicitParam(name = "id", required = true)})
    @ApiResponses(value = {@ApiResponse(code = 1005, message = "操作失败")})
    @RequestMapping(value = "/delProjectInfo", method = {RequestMethod.DELETE})
    @RequiresPermissions("manHour_management_project:delProjectInfo")
    public BaseForm delProjectInfo(@RequestBody String id) {
        BaseForm result = new BaseForm();
        if (!iTProjectInfoService.removeById(id)) {
            result.setStatus(BaseForm.Status.FAILURE);
        }
        return result;
    }

    /**
     * 通过项目名称或项目编号查询与某条待办记录有关的项目信息
     * @param proNo
     * @return
     */
    @ApiOperation(value = "查询待办表关联的项目信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "proNo"),@ApiImplicitParam(name="proName")})
    @ApiResponses(value = {@ApiResponse(code = 1005, message = "操作失败")})
    @GetMapping("/queryProjectInfo")
    public BaseForm queryProjectInfo(String proNo,String proName) {
        BaseForm result = new BaseForm();
        TProjectInfo info = new TProjectInfo();
        info.setProNo(proNo);
        try {
            result.setData(iTProjectInfoService.getOne(new QueryWrapper<>(info)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

