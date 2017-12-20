package com.xzq.bos.service.impl;

import java.sql.Timestamp;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IDecidedzoneDao;
import com.xzq.bos.dao.INoticebillDao;
import com.xzq.bos.dao.IWorkbillDao;
import com.xzq.bos.domain.Decidedzone;
import com.xzq.bos.domain.Noticebill;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.domain.User;
import com.xzq.bos.domain.Workbill;
import com.xzq.bos.service.INoticebillService;
import com.xzq.bos.utils.BOSUtils;
import com.xzq.crm.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
	@Autowired
	private INoticebillDao noticebillDao;
	
	@Autowired
	private ICustomerService proxy;
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	
	@Autowired
	private IWorkbillDao workbillDao;
	/* 保存一个业务通知单，并尝试自动分单
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.INoticebillService#save(com.xzq.bos.domain.Noticebill)
	 */
	@Override
	public void save(Noticebill model) {
		User user=BOSUtils.getLoginUser();
		model.setUser(user);//设置当前用户
		noticebillDao.save(model);
		//获取客户的取件地址
		String pickaddress=model.getPickaddress();
		String decidedzoneId=proxy.findDecidedzoneIdByAddress(pickaddress);
		if(decidedzoneId!=null) {
			//查询到了地区ID 可以完成自动分单
			Decidedzone decidedzone=decidedzoneDao.findById(decidedzoneId);
			Staff staff=decidedzone.getStaff();
			model.setStaff(staff);//业务通知单关联取派员对象
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);//设置自动分单
			//为取派员产生一个工单
			Workbill workbill=new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间
			workbill.setNoticebill(model);//让我们工单关联页面通知单
			workbill.setPickstate(workbill.PICKSTATE_NO);//取件状态
			workbill.setRemark(model.getRemark());//备注信息
			workbill.setStaff(staff);//工单关联取派员
			workbill.setType(workbill.TYPE_1);//工单类型
			workbillDao.save(workbill);
			//调用短信平台，发送短信
			
		}else {
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}
}
