package com.xzq.bos.service;

import java.util.List;

import com.xzq.bos.domain.Subarea;
import com.xzq.bos.utils.PageBean;

public interface ISubareaService {

	/**��ҳ�ӿ�
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**���ӷ���
	 * @param model
	 */
	void save(Subarea model);

	/**ͨ��ID���Ҷ���
	 * @param id
	 * @return
	 */
	Subarea findById(String id);

	/**���µ���
	 * @param subarea
	 */
	void update(Subarea subarea);

	/**ɾ������
	 * @param ids
	 */
	void deleteBatch(String ids);

	/**
	 * �������ݵ���
	 * @return
	 */
	List<Subarea> findAll();

	/**
	 * ����δ�����������ķ���������json����
	 * @return
	 */
	List<Subarea> findListAssociation();

	List<Subarea> findListByDecidedzoneId(String decidedzoneId);

	List<Object> findSubareasGroupByProvince();

}