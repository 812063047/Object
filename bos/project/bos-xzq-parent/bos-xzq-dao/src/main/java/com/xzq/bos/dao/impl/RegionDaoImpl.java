package com.xzq.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xzq.bos.dao.IRegionDao;
import com.xzq.bos.dao.base.impl.BaseDaoImpl;
import com.xzq.bos.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao{

	/* 
	 * 根据Q参数进行模糊查询
	 * (non-Javadoc)
	 * @see com.xzq.bos.dao.IRegionDao#findListByQ(java.lang.String)
	 */
	@Override
	public List<Region> findListByQ(String q) {
		String hql="FROM Region r WHERE r.shortcode LIKE ? OR r.citycode LIKE ? OR r.province LIKE ?"
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list=(List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}

}
