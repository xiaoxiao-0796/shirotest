package com.xiaofei.test.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface RoleMapper {

    Set<String> queryRoleNameByStaffCode(@Param("userName") String userName);
}
