package com.xzq.bos.dao;

import java.util.List;

import com.xzq.bos.dao.base.IBaseDao;
import com.xzq.bos.domain.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {

	List<Object> findSubareasGroupByProvince();

}
