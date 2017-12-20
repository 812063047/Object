package com.xzq.bos.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Decidedzone;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.service.IDecidedzoneService;
import com.xzq.bos.web.action.base.BaseAction;
import com.xzq.crm.Customer;
import com.xzq.crm.ICustomerService;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	public String pageQuery() {
		DetachedCriteria dc=pageBean.getDetachedCriteria();
		String id=model.getId();
		if(StringUtils.isNotBlank(id)) {
			dc.add(Restrictions.like("id", "%"+id+"%"));
		}
		Staff staff=model.getStaff();
		if(staff!=null) {
			String station=staff.getStation();
			dc.createAlias("staff", "s");
			if(StringUtils.isNotBlank(station)) {
				dc.add(Restrictions.like("s.station", "%"+station+"%"));
			}
		}
		decidedzoneService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","decidedzones","subareas"});
		return NONE;
	}
	
	/**
	 * ����һ������
	 * @return
	 */
	//�������������ն������id
	private String [] subareaid;
	
	public String [] getSubareaid() {
		return subareaid;
	}

	public void setSubareaid(String [] subareaid) {
		this.subareaid = subareaid;
	}
	
	public String addDecidedzone() {
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	//��ȡҳ�洫������id
	private String  ids;
	

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String deleteBatch() {
		decidedzoneService.deleteById(ids);
		return LIST;
	}
	
	//ע��crm��������
		@Autowired
		private ICustomerService proxy;
		
		/**
		 * Զ�̵���crm���񣬻�ȡδ�����������Ŀͻ�
		 */
		public String findListNotAssociation(){
			List<Customer> list = proxy.findListNotAssociation();
			this.javaToJson(list, new String[]{});
			return NONE;
		}
		
		/**
		 * Զ�̵���crm���񣬻�ȡ�Ѿ�������ָ���Ķ����Ŀͻ�
		 */
		public String findListHasAssociation(){
			String id = model.getId();
			List<Customer> list = proxy.findListHasAssociation(id);
			this.javaToJson(list, new String[]{});
			return NONE;
		}
		
		public String findListHasAssociation1(){
			String id = model.getId();
			List<Customer> list = proxy.findListHasAssociation(id);
			this.javaToJson(list, new String[]{});
			return NONE;
		}
		
		//��������������ҳ���ύ�Ķ���ͻ�id
		private List<Integer> customerIds;
		
		/**
		 * Զ�̵���crm���񣬽��ͻ�����������
		 */
		public String assigncustomerstodecidedzone(){
			proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
			return LIST;
		}

		public List<Integer> getCustomerIds() {
			return customerIds;
		}

		public void setCustomerIds(List<Integer> customerIds) {
			this.customerIds = customerIds;
		}
		
}