package com.weiweb.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.weiweb.common.dao.UUserMapper;
import com.weiweb.common.dao.UUserRoleMapper;
import com.weiweb.common.model.UUser;
import com.weiweb.common.model.UUserRole;
import com.weiweb.common.utils.LoggerUtils;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.session.CustomSessionManager;
import com.weiweb.core.shiro.token.manager.TokenManager;
import com.weiweb.permission.bo.URoleBo;
import com.weiweb.permission.bo.UserRoleAllocationBo;
import com.weiweb.user.service.UUserService;

@Service
public class UUserServiceImpl extends BaseMybatisDao<UUserMapper> implements UUserService {
	/***
	 * 用户手动操作Session
	 * */
	@Autowired
	CustomSessionManager customSessionManager;
	@Autowired
	UUserMapper userMapper;
	@Autowired
	UUserRoleMapper userRoleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public UUser insert(UUser entity) {
		userMapper.insert(entity);
		return entity;
	}

	@Override
	public UUser insertSelective(UUser entity) {
		userMapper.insertSelective(entity);
		return entity;
	}

	@Override
	public UUser selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(UUser entity) {
		return userMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByPrimaryKeySelective(UUser entity) {
		return userMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public UUser login(String email ,String pswd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("pswd", pswd);
		UUser user = userMapper.login(map);
		return user;
	}

	@Override
	public List<UUser> findUserByEmail(String email) {
		return userMapper.findUserByEmail(email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<UUser> findByPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}

	@Override
	public boolean deleteUserById(String ids) {
		String currentId=TokenManager.getUserId().toString();
		boolean isHasCurrent = false;
		int count=0;
		String[] idArray = new String[]{};
		if(StringUtils.contains(ids, ",")){
			idArray = ids.split(",");
		}else{
			idArray = new String[]{ids};
		}
		
		for (String id : idArray) {
			if(id.equals(currentId)){
				isHasCurrent = true;
			}else{
				count+=this.deleteByPrimaryKey(new Long(id));
				userRoleMapper.deleteByUserId(new Long(id));
			}
			
		}
		return isHasCurrent;
	}

	@Override
	public Map<String, Object> updateForbidUserById(Long id, Long status) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			UUser user = selectByPrimaryKey(id);
			user.setStatus(status);
			updateByPrimaryKeySelective(user);
			
			//如果当前用户在线，需要标记并且踢出
			customSessionManager.forbidUserById(id,status);
			
			
			resultMap.put("status", 200);
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "操作失败，请刷新再试！");
			LoggerUtils.fmtError(getClass(), "禁止或者激活用户登录失败，id[%s],status[%s]", id,status);
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage("findUserAndRole", "findCount", modelMap, pageNo, pageSize);
	}

	@Override
	public List<URoleBo> selectRoleByUserId(Long id) {
		return userMapper.selectRoleByUserId(id);
	}

	@Override
	public Map<String, Object> addRole2User(Long userId, String ids) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		int count = 0;
		try {
			//先删除原有的。
			userRoleMapper.deleteByUserId(userId);
			//如果ids,role 的id 有值，那么就添加。没值象征着：把这个用户（userId）所有角色取消。
			if(StringUtils.isNotBlank(ids)){
				String[] idArray = null;
				
				//这里有的人习惯，直接ids.split(",") 都可以，我习惯这么写。清楚明了。
				if(StringUtils.contains(ids, ",")){
					idArray = ids.split(",");
				}else{
					idArray = new String[]{ids};
				}
				//添加新的。
				for (String rid : idArray) {
					//这里严谨点可以判断，也可以不判断。这个{@link StringUtils 我是重写了的} 
					if(StringUtils.isNotBlank(rid)){
						UUserRole entity = new UUserRole(userId,new Long(rid));
						count += userRoleMapper.insertSelective(entity);
					}
				}
			}
			resultMap.put("status", 200);
			resultMap.put("message", "操作成功");
		} catch (Exception e) {
			resultMap.put("status", 200);
			resultMap.put("message", "操作失败，请重试！");
		}
		//清空用户的权限，迫使再次获取权限的时候，得重新加载
		TokenManager.clearUserAuthByUserId(userId);
		resultMap.put("count", count);
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteRoleByUserIds(String userIds) {

		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("userIds", userIds);
			userRoleMapper.deleteRoleByUserIds(resultMap);
			resultMap.put("status", 200);
			resultMap.put("message", "操作成功");
		} catch (Exception e) {
			resultMap.put("status", 200);
			resultMap.put("message", "操作失败，请重试！");
		}
		return resultMap;
	
	}

	@Override
	public List<UUser> getUserByNickname(String nickname) {
		return userMapper.findUserByNickname(nickname);
	}

	@Override
	public Pagination<UUser> findUserByroleId(ModelMap modelMap, int pageNo, int pageSize) {
		return super.findPage("findUserByroleId", "findUserCountByroleId", modelMap, pageNo, pageSize);
	}


	
	
	
}
