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
 * 取锟斤拷员锟斤拷锟斤拷
 * @author D14958
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	
	@Autowired
	private IStaffService staffService;
	
	/*
	 * 添加取派员
	 * */
	public String addStaff() {
		staffService.save(model);
		return LIST;
	}
	/*
	 *分页查询方法 
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
		
		//使用json-lib将pageBean对象转为json 通过输出流写回页面中
		
		//JSONObject------将单一对象转为json
		//JSONArray -----将数组或者集合对象转为json
		staffService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"decidedzones","currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	//属性驱动，接收页面提交的ids参数
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/*
	 * 批量删除取派员
	 * */
	@RequiresPermissions("staff-delete")
	public String deleteBatch() {	
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	/*
	 * 修改取派员信息
	 * */
	@RequiresPermissions("staff-edit")
	public String editStaff() {
		Staff staff=staffService.findById(model.getId());
		//根据页面提交的数据进行覆盖
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
	 *还原取派员删除信息 
	 * */
	private String ids1;
	
	public String getIds1() {
		return ids1;
	}

	public void setIds1(String ids1) {
		this.ids1 = ids1;
	}

	
	/**还原设置
	 * @return
	 */
	public String restoreBatch() {
		staffService.restoreBatch(ids1);
		return LIST;
	}
	
	/**
	 * 取派员导入
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
/*	private File staffFile;
	
	public void setStaffFile(File staffFile) {
		this.staffFile = staffFile;
	}

	public String importXls() throws FileNotFoundException, IOException {
		List<Staff> staffList=new ArrayList<Staff>();
		//使用POI解析Excel文件
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
			//包装一个区域对象
			Staff staff = new Staff(null, name, telephone, haspda, deltag, station, standard, null);
			//
			staffList.add(staff);
		}
		staffService.saveBatch(staffList);
		return NONE;
	}
	*/
	/**
	 * 取派员导出
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		List<Staff> staffList=staffService.findAll();
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("取派员数据");
		//创建标题行
	    HSSFRow headRow=sheet.createRow(0);
	    headRow.createCell(0).setCellValue("姓名");
		headRow.createCell(1).setCellValue("手机号码");
		headRow.createCell(2).setCellValue("是否有pda");
		headRow.createCell(3).setCellValue("是否删除");
		headRow.createCell(4).setCellValue("取派标准");
		headRow.createCell(5).setCellValue("所属单位");
        for (Staff staff : staffList) {
        	HSSFRow dataRow=sheet.createRow(sheet.getLastRowNum()+1);
        	dataRow.createCell(0).setCellValue(staff.getName());
        	dataRow.createCell(1).setCellValue(staff.getTelephone());
        	dataRow.createCell(2).setCellValue(staff.getHaspda());
        	dataRow.createCell(3).setCellValue(staff.getDeltag());
        	dataRow.createCell(4).setCellValue(staff.getStation());
        	dataRow.createCell(5).setCellValue(staff.getStandard());
		  }
      //第三步：使用输出流进行文件下载（一个流、两个头）
		
  		String filename = "取派员数据.xls";
  		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
  		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
  		ServletActionContext.getResponse().setContentType(contentType);
  		
  		//获取客户端浏览器类型
  		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
  		filename = FileUtils.encodeDownloadFilename(filename, agent);
  		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
  		workbook.write(out);
		return NONE;
	}
	
	/**
	 * 查询所有未删除的取派员，返回json
	 * @return
	 */
	public String listAjax() {
		List<Staff> staffList=staffService.findListNotDelete();
		this.javaToJson(staffList, new String[]{"decidedzones"});
		return NONE;
	}
}
