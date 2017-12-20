package com.xzq.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Noticebill;
import com.xzq.bos.service.INoticebillService;
import com.xzq.bos.web.action.base.BaseAction;
import com.xzq.crm.Customer;
import com.xzq.crm.ICustomerService;

/**
 * 业务通知单管理
 * @author D14958
 *
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	
	@Autowired
	private INoticebillService noticebillService;
	//注入远程crm
	@Autowired
	private ICustomerService proxy;
	
	/**
	 * 远程调用crm服务，根据手机号码查询
	 * @return
	 */
	public String findCustomerByTelephone() {
		String telephone=model.getTelephone();
		Customer customerList=proxy.findCustomerByTelephone(telephone);
		this.javaToJson(customerList,new String[]{});
		return NONE;
	}
	
	/**保存一个业务通知单，并尝试自动分单
	 * 
	 * @return
	 */
	public String addNoticebill() {
		noticebillService.save(model);
		return "noticebill_add";
	}
}
