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
	
	
	//属性驱动，接收页面输入的验证码
  private String checkcode;
	
  public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
    @Autowired
	private IUserService userService;
	
	/*
   *使用shiro验证用户登录 
   * */
	public String login(){
		//从session中获取正确的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode)&& checkcode.equals(validatecode)){
			Subject subject=SecurityUtils.getSubject();
			AuthenticationToken token=new UsernamePasswordToken(model.getUsername(), model.getPassword());//创建用户名密码令牌对象
			try {
				subject.login(token);
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("输入的用户名或者密码错误！");
				return LOGIN;
			}
			User user=(User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		}else{
			//输入的验证码错误，设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	/*
	 * 用户注销
	 * */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	/*
	 * 验证原密码*/
	public String oldPassword() throws IOException {
		User user=BOSUtils.getLoginUser();
		String oldPassword=user.getPassword();	
		ServletActionContext.getResponse().getWriter().print(oldPassword);
		return NONE;
	}
	
	/*修改密码*/
	public  String editPassword() throws IOException{
		String f = "1";
		//获取当前登录用户
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
	 * 添加用户
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
