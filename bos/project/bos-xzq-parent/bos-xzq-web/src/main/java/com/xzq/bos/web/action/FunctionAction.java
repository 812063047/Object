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
	 * ��ѯ����Ȩ��
	 * @return
	 */
	public String listAjax() {
		List<Function> list=functionService.findAll();
		this.javaToJson(list, new String[]{"parentFunction","roles"});
		return NONE;
	}
	
	/**
	 * ����Ȩ��
	 * @return
	 */
	public String addFunction() {
		functionService.save(model);
		return LIST;
	}
	
	/**��ҳ��ѯ
	 * @return
	 */
	public String pageQuery() {
		//�����ظ�����
		String page= model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * ���ݵ�ǰ��¼�˲�ѯ��Ӧ�Ĳ˵����ݣ�����json
	 * 
	 * */
	/*public String findMenu() {
		List<Function>list=functionService.findMenu();
		this.javaToJson(list, new String[] {"parentFunction","roles"});
		return LIST;
	}*/
	/**
	 * ���ݵ�ǰ��¼�˲�ѯ��Ӧ�Ĳ˵����ݣ�����json
	 */
	public String findMenu(){
		List<Function> list = functionService.findMenu();
		this.javaToJson(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
}