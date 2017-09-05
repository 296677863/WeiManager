package com.weiweb.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weiweb.common.dao.URoleMapper;
import com.weiweb.common.dao.URolePermissionMapper;
import com.weiweb.common.dao.UUserMapper;
import com.weiweb.common.dao.UUserRoleMapper;
import com.weiweb.common.model.URole;
import com.weiweb.common.model.UUser;
import com.weiweb.common.utils.LoggerUtils;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.core.shiro.po.Message;
import com.weiweb.core.shiro.po.Message.Type;
import com.weiweb.core.shiro.token.manager.TokenManager;
import com.weiweb.permission.bo.RolePermissionAllocationBo;
import com.weiweb.permission.service.RoleService;


@Service
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends BaseMybatisDao<URoleMapper> implements RoleService {

	@Autowired
	URoleMapper roleMapper;
	@Autowired
	UUserMapper userMapper;
	@Autowired
	URolePermissionMapper rolePermissionMapper;
	@Autowired
	UUserRoleMapper userRoleMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(URole record) {
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(URole record) {
		return roleMapper.insertSelective(record);
	}

	@Override
	public URole selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(URole record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(URole record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	
	@Override
	public Pagination<URole> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}
	@Override
	public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(
			Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findRoleAndPermission", "findCount", resultMap, pageNo, pageSize);
	}
	@Override
	public Message deleteRoleById(String ids) {
		Message resultMessage=new Message();
		try {
			int count=0;
			
			String[] idArray = new String[]{};
			if(StringUtils.contains(ids, ",")){
				idArray = ids.split(",");
			}else{
				idArray = new String[]{ids};
			}
			
			for (String idx : idArray) {
				Long id = new Long(idx);
				if(new Long(1).equals(id)){
					resultMessage.setType(Type.error);
					resultMessage.setContent("操作失败，系统管理员不能删除。");
					return resultMessage;
				}else{
					List<UUser> user=userMapper.findUserByRoleId(id);
					URole role=this.selectByPrimaryKey(id);
					if(user!=null&&user.size()!=0){
						resultMessage.setType(Type.error);
						resultMessage.setContent("操作失败，"+role.getName()+"'角色下存在成员，无法删除");
						return resultMessage;
					}
				}
			}
			for(String idx : idArray ){
				Long id = new Long(idx);
				count+=this.deleteByPrimaryKey(id);
				rolePermissionMapper.deleteByRid(id);
				resultMessage.setType(Type.success);
				resultMessage.setContent("操作成功！");
			}
			return resultMessage;
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
			resultMessage.setType(Type.error);
			resultMessage.setContent("删除出现错误，请刷新后再试！");
		}
		return resultMessage;
	}

	@Override
	public Set<String> findRoleByUserId(Long userId) {
		return roleMapper.findRoleByUserId(userId);
	}

	@Override
	public List<URole> findNowAllPermission() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", TokenManager.getUserId());
		return roleMapper.findNowAllPermission(map);
	}
	/**
	 * 每20分钟执行一次
	 */
	@Override
	public void initData() {
		roleMapper.initData();
	}

	@Override
	public URole findAllPermissionById(Long roleId) {
		return roleMapper.findAllPermissionById(roleId);
	}

	
}
