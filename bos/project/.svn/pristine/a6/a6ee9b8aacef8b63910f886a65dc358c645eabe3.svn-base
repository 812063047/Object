package com.xzq.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IUserDao;
import com.xzq.bos.domain.Role;
import com.xzq.bos.domain.User;
import com.xzq.bos.service.IUserService;
import com.xzq.bos.utils.MD5Utils;
import com.xzq.bos.utils.PageBean;
@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	/*
	 *用户登录
	 */
	public User login(User user) {
		//使用MD5加密
		//String password=MD5Utils.md5(user.getPassword());	
		String password=user.getPassword();
		return userDao.findUserByUsernameAndPassword(user.getUsername(),password);
	}
	
	@Override
	public void editPassword(String id, String password) {
		//使用MD5加密密码
		//password = MD5Utils.md5(password);
		userDao.executeUpdate("user.editpassword", password,id);		
	}

	/* 分页查询
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IUserService#pageQuery(com.xzq.bos.domain.User)
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);		
	}

	/* 保存用户，同时关联角色
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IUserService#save(com.xzq.bos.domain.User, java.lang.String[])
	 */
	@Override
	public void save(User model, String[] roleIds) {
		/*String password=model.getPassword();
		password=MD5Utils.md5(password);
		model.setPassword(password);*/
		userDao.save(model);
		if(roleIds !=null && roleIds.length >0) {
			for (String roleId : roleIds) {
				Role role=new Role(roleId);
				model.getRoles().add(role);
			}
		}
	}

}
