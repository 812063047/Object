package com.xzq.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IStaffDao;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.service.IStaffService;
import com.xzq.bos.utils.PageBean;
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	@Autowired
	private IStaffDao staffDao;
	@Override
	public void save(Staff model) {
			staffDao.save(model);
	}
	
	
	@Override
	public void pageQuery(PageBean pageBean) {
	       staffDao.pageQuery(pageBean);
	}

	
	/*
	 * ȡ��Ա����ɾ��
	 * �߼�ɾ������deltag��Ϊ1
	 * */
	@Override
	public void deleteBatch(String ids) {//1,2,3,4
		if(StringUtils.isNoneBlank(ids)) {
			String [] staffIds=ids.split(",");
			for (String id : staffIds) {
				staffDao.executeUpdate("staff.delete", id);
			}
		}
		
	}

/*
 * ����id��ѯȡ��Ա
 * */
	@Override
	public Staff findById(String id) {		
		return staffDao.findById(id);
	}

/*
 *����id�޸�ȡ��Ա 
 * */
	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
		
	}


	@Override
	public void restoreBatch(String ids1) {
		if(StringUtils.isNotBlank(ids1)) {
			String [] staff1=ids1.split(",");
			for (String id1 : staff1) {
				staffDao.executeUpdate("staff.restore", id1);
			}
		}
		
	}


	/*@Override
	public void saveBatch(List<Staff> staffList) {
		for (Staff staff : staffList) {
			staffDao.saveOrUpdate(staff);
		}
	}*/


	/* ȡ��Ա����
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IStaffService#findAll()
	 */
	@Override
	public List<Staff> findAll() {
		// TODO Auto-generated method stub
		return staffDao.findAll();
	}


	/* ��ѯ����δɾ����ȡ��Ա������json
	 * non-Javadoc)
	 * @see com.xzq.bos.service.IStaffService#findListNotDelete()
	 */
	@Override
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
		//��ӹ������� deltag����0
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		
		List<Staff> staffList=staffDao.findByCriteria(detachedCriteria);
		return staffList;
	}

}
