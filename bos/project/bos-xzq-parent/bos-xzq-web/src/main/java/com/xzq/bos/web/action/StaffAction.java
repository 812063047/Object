package com.xzq.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Region;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.service.IStaffService;
import com.xzq.bos.utils.FileUtils;
import com.xzq.bos.utils.PageBean;
import com.xzq.bos.utils.PinYin4jUtils;
import com.xzq.bos.web.action.base.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ȡ��Ա����
 * @author D14958
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	
	@Autowired
	private IStaffService staffService;
	
	/*
	 * ���ȡ��Ա
	 * */
	public String addStaff() {
		staffService.save(model);
		return LIST;
	}
	/*
	 *��ҳ��ѯ���� 
	 * */

	public  String pageQuery(){		
		DetachedCriteria dc=pageBean.getDetachedCriteria();
		String name=model.getName();
		String telephone=model.getTelephone();
		String station=model.getStation();
		String standard=model.getStandard();
		if(StringUtils.isNotBlank(name)) {
			dc.add(Restrictions.like("name", "%"+name+"%"));
		}
		if(StringUtils.isNotBlank(telephone)) {
			dc.add(Restrictions.like("telephone", "%"+telephone+"%"));
		}
		if(StringUtils.isNotBlank(station)) {
			dc.add(Restrictions.like("station", "%"+station+"%"));
		}
		if(StringUtils.isNotBlank(standard)) {
			dc.add(Restrictions.like("standard", "%"+standard+"%"));
		}
		
		//ʹ��json-lib��pageBean����תΪjson ͨ�������д��ҳ����
		
		//JSONObject------����һ����תΪjson
		//JSONArray -----��������߼��϶���תΪjson
		staffService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"decidedzones","currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	//��������������ҳ���ύ��ids����
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/*
	 * ����ɾ��ȡ��Ա
	 * */
	@RequiresPermissions("staff-delete")
	public String deleteBatch() {	
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	/*
	 * �޸�ȡ��Ա��Ϣ
	 * */
	@RequiresPermissions("staff-edit")
	public String editStaff() {
		Staff staff=staffService.findById(model.getId());
		//����ҳ���ύ�����ݽ��и���
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staffService.update(staff);
		return LIST;
	}
	
	
	/*
	 * 
	 *��ԭȡ��Աɾ����Ϣ 
	 * */
	private String ids1;
	
	public String getIds1() {
		return ids1;
	}

	public void setIds1(String ids1) {
		this.ids1 = ids1;
	}

	
	/**��ԭ����
	 * @return
	 */
	public String restoreBatch() {
		staffService.restoreBatch(ids1);
		return LIST;
	}
	
	/**
	 * ȡ��Ա����
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
/*	private File staffFile;
	
	public void setStaffFile(File staffFile) {
		this.staffFile = staffFile;
	}

	public String importXls() throws FileNotFoundException, IOException {
		List<Staff> staffList=new ArrayList<Staff>();
		//ʹ��POI����Excel�ļ�
		//System.out.println(staffFile);
		@SuppressWarnings("resource")
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(staffFile));
		HSSFSheet hssfSheet= workbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			int rowNum=row.getRowNum();
			if(rowNum==0) {
				continue;
			}
			String name=row.getCell(0).getStringCellValue();
			String telephone=row.getCell(1).getStringCellValue();
			String haspda=row.getCell(2).getStringCellValue();
			String deltag=row.getCell(3).getStringCellValue();
			String station=row.getCell(4).getStringCellValue();
			String standard=row.getCell(5).getStringCellValue();
			//��װһ���������
			Staff staff = new Staff(null, name, telephone, haspda, deltag, station, standard, null);
			//
			staffList.add(staff);
		}
		staffService.saveBatch(staffList);
		return NONE;
	}
	*/
	/**
	 * ȡ��Ա����
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		List<Staff> staffList=staffService.findAll();
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("ȡ��Ա����");
		//����������
	    HSSFRow headRow=sheet.createRow(0);
	    headRow.createCell(0).setCellValue("����");
		headRow.createCell(1).setCellValue("�ֻ�����");
		headRow.createCell(2).setCellValue("�Ƿ���pda");
		headRow.createCell(3).setCellValue("�Ƿ�ɾ��");
		headRow.createCell(4).setCellValue("ȡ�ɱ�׼");
		headRow.createCell(5).setCellValue("������λ");
        for (Staff staff : staffList) {
        	HSSFRow dataRow=sheet.createRow(sheet.getLastRowNum()+1);
        	dataRow.createCell(0).setCellValue(staff.getName());
        	dataRow.createCell(1).setCellValue(staff.getTelephone());
        	dataRow.createCell(2).setCellValue(staff.getHaspda());
        	dataRow.createCell(3).setCellValue(staff.getDeltag());
        	dataRow.createCell(4).setCellValue(staff.getStation());
        	dataRow.createCell(5).setCellValue(staff.getStandard());
		  }
      //��������ʹ������������ļ����أ�һ����������ͷ��
		
  		String filename = "ȡ��Ա����.xls";
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
	 * ��ѯ����δɾ����ȡ��Ա������json
	 * @return
	 */
	public String listAjax() {
		List<Staff> staffList=staffService.findListNotDelete();
		this.javaToJson(staffList, new String[]{"decidedzones"});
		return NONE;
	}
}
