package com.xzq.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xzq.bos.domain.Region;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/*
 * ���ֲ�ͨ��ʵ��
 * */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected PageBean pageBean=new PageBean();
	//���������ύ��ѯ����
	DetachedCriteria detachedCriteria=null;
	/*protected int page;
	protected int rows;*/
	
	//��������������ҳ���ύ�ķ�ҳ����

		public void setPage(int page) {
			pageBean.setCurrentPage(page);
			//this.page = page;
		}
		public void setRows(int rows) {
			pageBean.setPageSize(rows);
			//this.rows = rows;
		}
		
		/**��ָ��java����תΪjson������Ӧ���ͻ���ҳ��
		 * @param o
		 * @param exclueds
		 */
		public void javaToJson(Object o,String[] exclueds) {
			JsonConfig jsonConfig=new JsonConfig();
			//ָ����Щ���Բ���Ҫתjson
			jsonConfig.setExcludes(exclueds);
			String json=JSONObject.fromObject(o,jsonConfig).toString();
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void javaToJson(List o,String[] exclueds) {
			JsonConfig jsonConfig=new JsonConfig();
			//ָ����Щ���Բ���Ҫתjson
			jsonConfig.setExcludes(exclueds);
			String json=JSONArray.fromObject(o,jsonConfig).toString();
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public static final String HOME="home";
	public static final String LIST="list";
	//ģ�Ͷ���
	protected T model;
	public T getModel() {
		return model;
	}
	//�ڹ��췽���ж�̬��ȡʵ�����ͣ�ͨ�����䴴��model����
	public BaseAction(){
		ParameterizedType superclass=(ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments =superclass.getActualTypeArguments();
		Class<T> entityClass= (Class<T>)actualTypeArguments[0];
		 detachedCriteria=DetachedCriteria.forClass(entityClass);
		 pageBean.setDetachedCriteria(detachedCriteria);
		//ͨ�����䴴������
		try {
			model=entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
