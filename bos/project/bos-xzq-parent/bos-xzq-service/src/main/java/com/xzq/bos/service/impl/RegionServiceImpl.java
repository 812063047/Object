package com.xzq.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.IRegionDao;
import com.xzq.bos.domain.Region;
import com.xzq.bos.service.IRegionService;
import com.xzq.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	@Autowired
	private IRegionDao regionDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
		
	}

	@Override
	public void save(Region model) {
		regionDao.save(model);		
	}
	
	/*删除区域*/
	
	
	@Override
	public void deleteBatch(String ids) {
		if(StringUtils.isNoneBlank(ids)) {
			String [] staffIds=ids.split(",");
			for (String id : staffIds) {
				regionDao.delete1(id);
			}
		}
	}

	/* 
	 * 通过id查找
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IRegionService#findById(java.lang.String)
	 */
	@Override
	public Region findById(String id) {
		Region region=regionDao.findById(id);
		return region;
	}

	/* 修改区域信息
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IRegionService#update(com.xzq.bos.domain.Region)
	 */
	@Override
	public void update(Region region) {
		regionDao.update(region);
	}

	/* 区域数据批量保存
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IRegionService#saveBatch(java.util.List)
	 */
	@Override
	public void saveBatch(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
		
	}

	/* 查询所有的数据
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IRegionService#findAll()
	 */
	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	/* 根据页面输入进行模糊查询
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.IRegionService#findListByQ(java.lang.String)
	 */
	@Override
	public List<Region> findListByQ(String q) {
		return regionDao.findListByQ(q);
	}
	
}
