package com.xzq.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.exception.Nestable;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Region;
import com.xzq.bos.domain.Subarea;
import com.xzq.bos.service.ISubareaService;
import com.xzq.bos.utils.FileUtils;
import com.xzq.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	
	@Autowired
	private ISubareaService subareaService;
	
	/**
	 * ��ҳaction
	 * @return
	 */
	public String pageQuery() {
		//System.out.println(model);
		DetachedCriteria dc=pageBean.getDetachedCriteria();
		//��̬���ӹ�������
		String addressKey=model.getAddresskey();
		if(StringUtils.isNotBlank(addressKey)) {
			
			dc.add(Restrictions.like("addressKey", "%"+addressKey+"%"));
		}		
		Region region=model.getRegion();
		if(region!=null) {
			String province=region.getProvince();
			String city=region.getCity();
			String district=region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)) {
				//����һ������������������������������
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)) {
				//����һ������������������������������
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)) {
				//����һ������������������������������
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		subareaService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","decidedzone",
				"subareas"});
		return NONE;
	}
	/**
	 * ���ӷ���
	 * @return
	 */
	public String addSubarea() {
		subareaService.save(model);
		return LIST;
	}
	
	/**
	 * �޸ķ���
	 * @return
	 */
	public String editSubarea() {
		Subarea subarea=subareaService.findById(model.getId());
		subarea.setAddresskey(model.getAddresskey());
		subarea.setStartnum(model.getStartnum());
		subarea.setEndnum(model.getEndnum());
		subarea.setRegion(model.getRegion());
		subarea.setSingle(model.getSingle());
		subarea.setPosition(model.getPosition());
		subareaService.update(subarea);
		return LIST;
	}
	
	/**
	 * ɾ������
	 * @return
	 */
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String deleteBatch() {
		subareaService.deleteBatch(ids);
		return LIST;
	}
	/**
	 * ������������
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		List<Subarea> subareaList=subareaService.findAll();
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("��������");
		//����������
		HSSFRow headRow=sheet.createRow(0);
		headRow.createCell(0).setCellValue("�������");
		headRow.createCell(1).setCellValue("��ʼ���");
		headRow.createCell(2).setCellValue("�������");
		headRow.createCell(3).setCellValue("λ����Ϣ");
		headRow.createCell(4).setCellValue("ʡ����");
        for (Subarea subarea : subareaList) {
        	HSSFRow dataRow=sheet.createRow(sheet.getLastRowNum()+1);
        	dataRow.createCell(0).setCellValue(subarea.getId());
        	dataRow.createCell(1).setCellValue(subarea.getStartnum());
        	dataRow.createCell(2).setCellValue(subarea.getEndnum());
        	dataRow.createCell(3).setCellValue(subarea.getPosition());
        	dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
        
           //��������ʹ������������ļ����أ�һ����������ͷ��
		
      		String filename = "��������.xls";
      		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
      		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
      		ServletActionContext.getResponse().setContentType(contentType);
      		
      		//��ȡ�ͻ������������
      		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
      		filename = FileUtils.encodeDownloadFilename(filename, agent);
      		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
      		workbook.write(out);
      		return NONE;
	}
	
	
	/**
	 * ����δ�����������ķ���������json����
	 * @return
	 */
	public String listAjax() {
		List<Subarea> subareaList=subareaService.findListAssociation();
		this.javaToJson(subareaList, new String [] {"decidedzone","region"});
		return NONE;
	}
	
	//�������������ն���id
		private String decidedzoneId;
		
		/**
		 * ���ݶ���id��ѯ�����ķ���
		 */
		public String findListByDecidedzoneId(){
			List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
			this.javaToJson(list, new String[]{"decidedzone","subareas"});
			return NONE;
		}

		public String getDecidedzoneId() {
			return decidedzoneId;
		}

		public void setDecidedzoneId(String decidedzoneId) {
			this.decidedzoneId = decidedzoneId;
		}
		
		/**
		 *��ѯ��������ֲ�ͼ����
		 * @return
		 */
		public String findSubareasGroupByProvince() {
			List<Object> list=subareaService.findSubareasGroupByProvince();
			this.javaToJson(list, new String[] {});
			return NONE;
		}
}