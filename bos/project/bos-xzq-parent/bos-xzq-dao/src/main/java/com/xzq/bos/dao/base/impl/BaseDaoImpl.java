package com.xzq.bos.dao.base.impl;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.apache.struts2.components.Select;
import org.aspectj.weaver.ast.Var;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.xzq.bos.dao.base.IBaseDao;
import com.xzq.bos.utils.PageBean;
/*
 * �־ò�ͨ��ʵ����
 * 
 * */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	//������ĳ��ʵ�������
	private Class<T> entityClass;
	
	@Resource//��������ע��spring�����еĻỰ��������sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	//�ڸ��ࣨBaseDaoImpl���Ĺ��췽���ж�̬���entityClass
	public BaseDaoImpl() {
	  ParameterizedType superclass=(ParameterizedType) this.getClass().getGenericSuperclass();
	  Type[] actualTypeArguments =superclass.getActualTypeArguments();
	  entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	
	public List<T> findAll() {
		String hql = "FROM " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session=this.getSessionFactory().getCurrentSession();
		Query query =session.getNamedQuery(queryName);
		int i=0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		//ִ�и���
		query.executeUpdate();
	}
	/*
	 * ͨ�÷�ҳ��ѯ��
	 * */
	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage=pageBean.getCurrentPage();
		int pageSize=pageBean.getPageSize();
		DetachedCriteria detachedCriteria=pageBean.getDetachedCriteria();
		//��ѯtotal---��������
		detachedCriteria.setProjection(Projections.rowCount());//ָ��hibernate��ܷ���SQL��ʽ----��Select count(*) from bc_staff
		List<Long> countList=(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count=countList.get(0);
		pageBean.setTotal(count.intValue());
		//��ѯrows---��ǰҳ��Ҫչʾ�����ݼ���
		detachedCriteria.setProjection(null);//ָ��hibernate��ܷ���SQL��ʽ-----��Select * from bc_staff
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult=(currentPage -1)*pageSize;
		int maxResults=pageSize;
		List rows=this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}
	@Override
	public void delete1(Serializable id) {
		//String hql = "FROM " + entityClass.getSimpleName();
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(entityClass, id)); 
		//this.getHibernateTemplate().delete(this.findById(id)); 
		//this.getHibernateTemplate().delete(entity);
	}
	
	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		
	}
	/* ����������Ӳ�ѯ
	 * (non-Javadoc)
	 * @see com.xzq.bos.dao.base.IBaseDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
