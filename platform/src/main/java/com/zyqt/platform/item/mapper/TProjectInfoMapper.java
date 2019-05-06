package com.zyqt.platform.item.mapper;

import com.zyqt.platform.item.entity.TProjectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author zgz
 * @since 2019-05-05
 */
@Mapper
public interface TProjectInfoMapper extends BaseMapper<TProjectInfo> {

    /**
     * 工具项目编号查询项目信息
     *
     * @param proNo
     * @return
     */
    TProjectInfo queryProjectInfo(String proNo);

    List<TProjectInfo> queryProjectInfos(Map params);
}
