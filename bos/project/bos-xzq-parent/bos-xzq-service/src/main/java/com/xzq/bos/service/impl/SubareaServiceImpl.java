package com.xzq.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xzq.bos.dao.ISubareaDao;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.domain.Subarea;
import com.xzq.bos.service.ISubareaService;
import com.xzq.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	
	@Autowired
	private ISubareaDao subareaDao;

	/* ��ҳʵ���� 
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#pageQuery(com.xzq.bos.utils.PageBean)
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
		
	}

	/* 
	 * ���ӵ���
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#save(com.xzq.bos.domain.Subarea)
	 */
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);		
	}

	/* ͨ��ID���Ҷ���
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#findById(java.lang.String)
	 */
	@Override
	public Subarea findById(String id) {
		return subareaDao.findById(id);
	}

	/* ���¶���
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#update(com.xzq.bos.domain.Subarea)
	 */
	@Override
	public void update(Subarea subarea) {
		subareaDao.update(subarea);		
	}

	/* ɾ������
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#deleteBatch(java.lang.String)
	 */
	@Override
	public void deleteBatch(String ids) {
	if(StringUtils.isNoneBlank(ids)) {
		String [] subarea=ids.split(",");
		for (String id : subarea) {
			subareaDao.delete1(id);
		}
	 }
	}

	
	/* �������ݵ���
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#findAll()
	 */
	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	/* 
	 * ����δ�����������ķ���������json����
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#findListAssociation()
	 */
	@Override
	public List<Subarea> findListAssociation() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
		//��ѯ�������ˣ����������decidedzone����Ϊnull
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		
		List<Subarea> subareaList=subareaDao.findByCriteria(detachedCriteria);
		return subareaList;
	}

	@Override
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		//���ӹ�������
		detachedCriteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		return subareaDao.findByCriteria(detachedCriteria );
	}

	/* ��ѯ��������ֲ�ͼ����
	 * (non-Javadoc)
	 * @see com.xzq.bos.service.ISubareaService#findSubareasGroupByProvince()
	 */
	@Override
	public List<Object> findSubareasGroupByProvince() {
		
		return subareaDao.findSubareasGroupByProvince();
	}
}