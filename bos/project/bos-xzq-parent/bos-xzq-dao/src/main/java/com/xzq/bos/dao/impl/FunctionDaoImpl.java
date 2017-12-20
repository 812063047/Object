package com.xzq.bos.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xzq.bos.dao.IFunctionDao;
import com.xzq.bos.dao.base.impl.BaseDaoImpl;
import com.xzq.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
	public List<Function> findAll(){
		String hql="FROM Function f WHERE f.parentFunction IS NULL";
		List<Function> list=(List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	/*�����û�id��ѯ��Ӧ��Ȩ�� 
	 * (non-Javadoc)
	 * @see com.xzq.bos.dao.IFunctionDao#findFunctionListByUserId()
	 */
	@Override
	public List<Function> findFunctionListByUserId(String id) {
		String hql="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<Function> list=(List<Function>) this.getHibernateTemplate().find(hql, id);		
		return list;
	}
    //��ѯ���в˵�
	/*@Override
	public List<Function> findAllMenu() {
		String hql="FROM Function f WHERE f.generatemenu ='1' ORDER BY f.zindex DESC";
		List<Function> list=(List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}
   //�����û�id��ѯ�˵�
	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu ='1' "
				+ "ORDER BY f.zindex DESC";                          
		List<Function> list=(List<Function>) this.getHibernateTemplate().find(hql, id);		
		return list;
	}*/
	
	// ��ѯ���в˵�
		public List<Function> findAllMenu() {
			String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex DESC";
			List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
			return list;
		}
		
		//�����û�id��ѯ�˵�
		public List<Function> findMenuByUserId(String userId) {
			String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
					+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' "
					+ "ORDER BY f.zindex DESC";
			List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, userId);
			return list;
		}
}