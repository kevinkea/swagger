package com.zyqt.platform.item.mapper;

import com.zyqt.platform.item.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShiroMapper {
    SysUser getUserByName(String userName);

    SysUser getUserByOneName(String userName);

    List<String> getRoles(String userName);

    List<String> getPermissions(String roleName);

    /**
     * 获取全部权限
     *
     * @param ()
     * @return java.util.List<java.lang.String>
     * @author zhouchen
     * @date 2018/9/12 9:40
     */
    List<String> getAllPermissions();
}