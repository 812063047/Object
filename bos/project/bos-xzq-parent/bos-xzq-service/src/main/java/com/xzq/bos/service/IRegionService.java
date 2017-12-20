package com.xzq.bos.service;

import java.util.List;

import com.xzq.bos.domain.Region;
import com.xzq.bos.utils.PageBean;

public interface IRegionService {

	void pageQuery(PageBean pageBean);

	void save(Region model);

	void deleteBatch(String ids);

	public Region findById(String id);

	public void update(Region region);

	/**导入数据
	 * @param regionList
	 */
	public void saveBatch(List<Region> regionList);

	public List<Region> findAll();

	public List<Region> findListByQ(String q);

}
