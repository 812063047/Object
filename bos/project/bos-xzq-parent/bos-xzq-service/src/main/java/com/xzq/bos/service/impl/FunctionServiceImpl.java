package com.xzq.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IFunctionDao;
import com.xzq.bos.domain.Function;
import com.xzq.bos.domain.User;
import com.xzq.bos.service.IFunctionService;
import com.xzq.bos.utils.BOSUtils;
import com.xzq.bos.utils.PageBean;
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	@Autowired
	private IFunctionDao functionDao;

	/* ��ѯ����Ȩ��
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IFunctionService#findAll()
	 */
	@Override
	public List<Function> findAll() {
		return functionDao.findAll();
	}

	/* ����Ȩ��
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IFunctionService#save(com.xzq.bos.domain.Function)
	 */
	@Override
	public void save(Function model) {
		Function parentFunction=model.getParentFunction();
		if(parentFunction !=null && parentFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
		
	}

	/* ��ҳ��ѯ
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IFunctionService#pageQuery(com.xzq.bos.utils.PageBean)
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
	/**
	 * ���ݵ�ǰ��¼�˲�ѯ��Ӧ�Ĳ˵����ݣ�����json
	 * 
	 * */
	@Override
	public List<Function> findMenu() {
	   List<Function> list = null;
	   User user = BOSUtils.getLoginUser();
	   if(user.getUsername().equals("admin")){
		  //����ǳ�������Ա�����û�����ѯ���в˵�
		  list = functionDao.findAllMenu();
	   }else{
		  //�����û��������û�id��ѯ�˵�
		  list = functionDao.findMenuByUserId(user.getId());
	   }
	   return list;
	}
	
}