package com.xzq.bos.service;

import java.util.List;

import com.xzq.bos.domain.Function;
import com.xzq.bos.utils.PageBean;

public interface IFunctionService {

	public List<Function> findAll();

	public void save(Function model);

	public void pageQuery(PageBean pageBean);

	public List<Function> findMenu();

}
