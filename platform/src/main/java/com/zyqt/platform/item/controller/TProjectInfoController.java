package com.zyqt.platform.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyqt.platform.item.entity.TProjectInfo;
import com.zyqt.platform.item.service.ITProjectInfoService;
import com.zyqt.platform.util.BaseForm;
import com.zyqt.platform.util.BasePage;
import io.swagger.annotations.*;
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

}

