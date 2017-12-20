package com.xzq.bos.web.action;

import java.util.List;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Role;
import com.xzq.bos.service.IRoleService;
import com.xzq.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	@Autowired
	private IRoleService roleService;
	
	private String functionIds;

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	/**
	 *添加角色
	 * @return
	 */
	public String addRole() {
		roleService.save(model,functionIds);
		return LIST;
	}
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"functions","users"});
		return NONE;
	}
	
	/**
	 * 查询所有的角色数据，返回json
	 * @return
	 */
	public String listAjax() {
		List<Role> list=roleService.findAll();
		this.javaToJson(list, new String[] {"functions","users"});
		return NONE;	
	}
}
