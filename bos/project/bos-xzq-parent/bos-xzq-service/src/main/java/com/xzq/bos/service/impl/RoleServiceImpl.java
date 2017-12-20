package com.xzq.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IRoleDao;
import com.xzq.bos.domain.Function;
import com.xzq.bos.domain.Role;
import com.xzq.bos.service.IRoleService;
import com.xzq.bos.utils.PageBean;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;

	/* 保存一个角色，
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IRoleService#save(com.xzq.bos.domain.Role, java.lang.String[])
	 */
	@Override
	public void save(Role model, String functionIds) {
		roleDao.save(model);
		if(StringUtils.isNotBlank(functionIds)) {
			String [] fIds=functionIds.split(",");
			for (String functionId : fIds) {
				//手动构造一个权限对象，设置id，对象状态为托管状态
				Function function=new Function(functionId);				
				//角色关联
				model.getFunctions().add(function);
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
		
	}

	@Override
	public List<Role> findAll() {
		
		return roleDao.findAll();
	}
}
