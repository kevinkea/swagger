package com.zyqt.platform.item.service.impl;

import com.zyqt.platform.item.entity.TProjectInfo;
import com.zyqt.platform.item.mapper.TProjectInfoMapper;
import com.zyqt.platform.item.service.ITProjectInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author zgz
 * @since 2019-05-05
 */
@Service
public class TProjectInfoServiceImpl extends ServiceImpl<TProjectInfoMapper, TProjectInfo> implements ITProjectInfoService {
    @Autowired
    private TProjectInfoMapper tProjectInfoMapper;


    @Override
    public TProjectInfo queryProjectInfo(String proNo) throws Exception {
        return tProjectInfoMapper.queryProjectInfo(proNo);
    }
    @Override
    public List<TProjectInfo> queryProjectInfos(Map params){
        return tProjectInfoMapper.queryProjectInfos(params);
    }
}
