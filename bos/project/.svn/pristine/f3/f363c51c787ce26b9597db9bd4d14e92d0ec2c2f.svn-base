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
	 * 取派员批量删除
	 * 逻辑删除，将deltag改为1
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
 * 根据id查询取派员
 * */
	@Override
	public Staff findById(String id) {		
		return staffDao.findById(id);
	}

/*
 *根据id修改取派员 
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


	/* 取派员导出
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IStaffService#findAll()
	 */
	@Override
	public List<Staff> findAll() {
		// TODO Auto-generated method stub
		return staffDao.findAll();
	}


	/* 查询所有未删除的取派员，返回json
	 * non-Javadoc)
	 * @see com.xzq.bos.service.IStaffService#findListNotDelete()
	 */
	@Override
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
		//添加过滤条件 deltag等于0
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		
		List<Staff> staffList=staffDao.findByCriteria(detachedCriteria);
		return staffList;
	}

}
