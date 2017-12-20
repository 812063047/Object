package com.xzq.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Function;
import com.xzq.bos.service.IFunctionService;
import com.xzq.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	@Autowired
	private IFunctionService functionService;
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public String listAjax() {
		List<Function> list=functionService.findAll();
		this.javaToJson(list, new String[]{"parentFunction","roles"});
		return NONE;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String addFunction() {
		functionService.save(model);
		return LIST;
	}
	
	/**分页查询
	 * @return
	 */
	public String pageQuery() {
		//属性重复问题
		String page= model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * 根据当前登录人查询对应的菜单数据，返回json
	 * 
	 * */
	/*public String findMenu() {
		List<Function>list=functionService.findMenu();
		this.javaToJson(list, new String[] {"parentFunction","roles"});
		return LIST;
	}*/
	/**
	 * 根据当前登录人查询对应的菜单数据，返回json
	 */
	public String findMenu(){
		List<Function> list = functionService.findMenu();
		this.javaToJson(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
}
