package com.weiweb.common.dao;

import java.util.List;
import java.util.Map;

import com.weiweb.admin.permission.bo.URoleBo;
import com.weiweb.common.model.UUser;


public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

	UUser login(Map<String, Object> map);

	List<UUser> findUserByEmail(String email);

	List<URoleBo> selectRoleByUserId(Long id);

	List<UUser> findUserByNickname(String nickname);

	List<UUser> findUserByRoleId(Long roleId);


}