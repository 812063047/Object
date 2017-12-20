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
 * 持久层通用实现类
 * 
 * */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	//代表是某个实体的类型
	private Class<T> entityClass;
	
	@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	//在父类（BaseDaoImpl）的构造方法中动态获得entityClass
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
		//执行更新
		query.executeUpdate();
	}
	/*
	 * 通用分页查询法
	 * */
	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage=pageBean.getCurrentPage();
		int pageSize=pageBean.getPageSize();
		DetachedCriteria detachedCriteria=pageBean.getDetachedCriteria();
		//查询total---总数据量
		detachedCriteria.setProjection(Projections.rowCount());//指定hibernate框架发出SQL形式----》Select count(*) from bc_staff
		List<Long> countList=(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count=countList.get(0);
		pageBean.setTotal(count.intValue());
		//查询rows---当前页需要展示的数据集合
		detachedCriteria.setProjection(null);//指定hibernate框架发出SQL形式-----》Select * from bc_staff
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
	/* 根据任意添加查询
	 * (non-Javadoc)
	 * @see com.xzq.bos.dao.base.IBaseDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
