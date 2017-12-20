package com.xzq.bos.web.action;

import org.apache.struts2.ServletActionContext;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.xzq.bos.domain.User;
import com.xzq.bos.service.IUserService;
import com.xzq.bos.utils.BOSUtils;
import com.xzq.bos.web.action.base.BaseAction;
import com.xzq.crm.Customer;
import com.xzq.crm.ICustomerService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	
	//��������������ҳ���������֤��
  private String checkcode;
	
  public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
    @Autowired
	private IUserService userService;
	
	/*
   *ʹ��shiro��֤�û���¼ 
   * */
	public String login(){
		//��session�л�ȡ��ȷ����֤��
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//У����֤���Ƿ�������ȷ
		if(StringUtils.isNotBlank(checkcode)&& checkcode.equals(validatecode)){
			Subject subject=SecurityUtils.getSubject();
			AuthenticationToken token=new UsernamePasswordToken(model.getUsername(), model.getPassword());//�����û����������ƶ���
			try {
				subject.login(token);
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("������û��������������");
				return LOGIN;
			}
			User user=(User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		}else{
			//�������֤�����������ʾ��Ϣ����ת����¼ҳ��
			this.addActionError("�������֤�����");
			return LOGIN;
		}
	}
	
	/*
	 * �û�ע��
	 * */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	/*
	 * ��֤ԭ����*/
	public String oldPassword() throws IOException {
		User user=BOSUtils.getLoginUser();
		String oldPassword=user.getPassword();	
		ServletActionContext.getResponse().getWriter().print(oldPassword);
		return NONE;
	}
	
	/*�޸�����*/
	public  String editPassword() throws IOException{
		String f = "1";
		//��ȡ��ǰ��¼�û�
		//User user = BOSUtils.getLoginUser();
		User user=BOSUtils.getLoginUser();
		try{
			userService.editPassword(user.getId(),model.getPassword());
		}catch(Exception e){
			f = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}
	
	public String pageQuery() {
		userService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"roles","noticebills"});
		return NONE;
	}
	
	/**
	 * �����û�
	 * @return
	 */
	private String [] roleIds;
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String addUser() {
		userService.save(model,roleIds);
		return LIST;
	}
}