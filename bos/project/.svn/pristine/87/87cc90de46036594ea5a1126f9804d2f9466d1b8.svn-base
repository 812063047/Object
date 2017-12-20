package com.xzq.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IDecidedzoneDao;
import com.xzq.bos.dao.ISubareaDao;
import com.xzq.bos.domain.Decidedzone;
import com.xzq.bos.domain.Subarea;
import com.xzq.bos.service.IDecidedzoneService;
import com.xzq.bos.utils.PageBean;
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private ISubareaDao subareaDao;

	/* 
	 * 分页查询
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IDecidedzoneService#pageQuery(com.xzq.bos.utils.PageBean)
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
		
	}

	/* 
	 * 添加定区，同时关联分区
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IDecidedzoneService#save(com.xzq.bos.domain.Decidedzone, java.lang.String[])
	 */
	@Override
	public void save(Decidedzone model, String[] subareaid) {		
		decidedzoneDao.save(model);
		for (String id : subareaid) {
			Subarea subarea=subareaDao.findById(id);
			//model.getSubareas().add(subarea); 一方已经放弃维护外键的权利，多的一方负责维护
			subarea.setDecidedzone(model);//分区关联定区
		}
	}

	/* 
	 * 根据id删除地区 
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IDecidedzoneService#deleteById(java.lang.String[])
	 */
	@Override
	public void deleteById(String ids) {
		if(StringUtils.isNoneBlank(ids)) {
		    String [] decidedzone=ids.split(",");
		    for (String id : decidedzone) {
				decidedzoneDao.delete1(id);
			}
		}
	}
}
