package com.xzq.bos.service;

import java.util.List;

import com.xzq.bos.domain.Staff;
import com.xzq.bos.utils.PageBean;

public interface IStaffService {

	void save(Staff model);

	void pageQuery(PageBean pageBean);

	void deleteBatch(String ids);

	Staff findById(String id);

	void update(Staff staff);

	void restoreBatch(String ids1);

	//void saveBatch(List<Staff> staffList);

	List<Staff> findAll();

	List<Staff> findListNotDelete();


}
