package com.xzq.bos.service;

import com.xzq.bos.domain.Decidedzone;
import com.xzq.bos.utils.PageBean;

public interface IDecidedzoneService {

	/**��ҳ��ѯ
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * ��Ӷ���
	 * @param model
	 * @param subareaid
	 */
	void save(Decidedzone model, String[] subareaid);

	/**����idɾ������
	 * @param ids
	 */
	void deleteById(String ids);

}
