package com.zyqt.platform.item.service;

import com.zyqt.platform.item.entity.TProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author zgz
 * @since 2019-05-05
 */
public interface ITProjectInfoService extends IService<TProjectInfo> {
    /**
     * 工具项目编号查询项目信息
     *
     * @param proNo
     * @return
     */
    TProjectInfo queryProjectInfo(String proNo) throws Exception;
    List<TProjectInfo> queryProjectInfos(Map params);
}
