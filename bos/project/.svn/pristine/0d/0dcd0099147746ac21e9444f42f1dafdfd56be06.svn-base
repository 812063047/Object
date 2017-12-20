package com.xzq.bos.service;

import com.xzq.bos.domain.User;
import com.xzq.bos.utils.PageBean;

public interface IUserService {

	public User login(User model);
	/*修改密码*/
	public void editPassword(String id, String password);
	
	/**
	 *分页查询
	 * @param model
	 */
	public void pageQuery(PageBean pageBean);
	
	/**保存一个用户
	 * @param model
	 * @param roleIds
	 */
	public void save(User model, String[] roleIds);

}
