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
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xzq.bos.domain.Region;
import com.xzq.bos.domain.Staff;
import com.xzq.bos.service.IRegionService;
import com.xzq.bos.utils.FileUtils;
import com.xzq.bos.utils.PageBean;
import com.xzq.bos.utils.PinYin4jUtils;
import com.xzq.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	@Autowired
	private IRegionService regionService;
	/*
	 *分页查询方法 
	 * */
	public String pageQuery(){
		DetachedCriteria dc=pageBean.getDetachedCriteria();
		String province=model.getProvince();
		String city=model.getCity();
		String district=model.getDistrict();
		String postcode=model.getPostcode();
		String shortcode=model.getShortcode();
		String citycode=model.getCitycode();
		String id=model.getId();
		if(StringUtils.isNotBlank(province)) {
			dc.add(Restrictions.like("province", "%"+province+"%"));
		}
		if(StringUtils.isNotBlank(city)) {
			dc.add(Restrictions.like("city", "%"+city+"%"));
		}
		if(StringUtils.isNotBlank(district)) {
			dc.add(Restrictions.like("district", "%"+district+"%"));
		}
		if(StringUtils.isNotBlank(postcode)) {
			dc.add(Restrictions.like("postcode", "%"+postcode+"%"));
		}
		if(StringUtils.isNotBlank(shortcode)) {
			dc.add(Restrictions.like("shortcode", "%"+shortcode+"%"));
		}
		if(StringUtils.isNotBlank(citycode)) {
			dc.add(Restrictions.like("citycode", "%"+citycode+"%"));
		}
		if(StringUtils.isNotBlank(id)) {
			dc.add(Restrictions.like("id", "%"+id+"%"));
		}
		regionService.pageQuery(pageBean);
		//显示subareas数据
		this.javaToJson(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","subareas"});
		return NONE;
	}
	
	/*
	 *添加区域 
	 * 
	 * */
	
	public String addRegion() {
		regionService.save(model);
		return LIST;
	}
	
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/*
	 *删除区域 
	 * */
	public String deleteBatch() {
		regionService.deleteBatch(ids);
		return LIST;
	}
	
	
	/**
	 * 修改区域信息
	 * @return
	 */
	public String editRegion() {
		Region region=regionService.findById(model.getId());
		region.setProvince(model.getProvince());
		region.setCity(model.getCity());
		region.setDistrict(model.getDistrict());
		region.setPostcode(model.getPostcode());
		region.setShortcode(model.getShortcode());
		region.setCitycode(model.getCitycode());
		regionService.update(region);
		return LIST;
	}
	
	//属性驱动，接收上传的文件
	private File regionFile;
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	/**
	 * 区域导入
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException {
		List<Region> regionList=new ArrayList<Region>();
		//使用POI解析Excel文件
		//System.out.println(regionFile);
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet hssfSheet= workbook.getSheetAt(0);
		for (Row row : hssfSheet) {
			int rowNum=row.getRowNum();
			if(rowNum==0) {
				continue;
			}
			String id=row.getCell(0).getStringCellValue();
			String province=row.getCell(1).getStringCellValue();
			String city=row.getCell(2).getStringCellValue();
			String district=row.getCell(3).getStringCellValue();
			String postcode=row.getCell(4).getStringCellValue();
			//包装一个区域对象
			Region region=new Region(id, province, city, district, postcode, null, null, null);
			//
			province=province.substring(0, province.length()-1);
			city=city.substring(0, city.length()-1);
			district=district.substring(0, district.length()-1);
			String info=province + city +district;
			//System.out.println(info);
			String [] headByString=PinYin4jUtils.getHeadByString(info);
			String shortcode=StringUtils.join(headByString);
			//System.out.println(shortcode);
			//城市编码
			String citycode=PinYin4jUtils.hanziToPinyin(city, "");
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			regionList.add(region);
		}
		regionService.saveBatch(regionList);
		return NONE;
	}
	
	/**
	 * 查询所有区域，写回json数据
	 * @return
	 */
	private String q;
	
	public void setQ(String q) {
		this.q = q;
	}

	public String listAjax() {
		List<Region> regionsList =null;//regionService.findAll();
		if(StringUtils.isNotBlank(q)) {
			regionsList=regionService.findListByQ(q);
		}else {
			regionsList=regionService.findAll();
		}
		this.javaToJson(regionsList, new String[] {"subareas"});
		return NONE;
	}
	
	/**
	 * 区域导出
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		List<Region> regionList=regionService.findAll();
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("取派员数据");
		//创建标题行
	    HSSFRow headRow=sheet.createRow(0);
	    headRow.createCell(0).setCellValue("区域编号");
		headRow.createCell(1).setCellValue("省");
		headRow.createCell(2).setCellValue("市");
		headRow.createCell(3).setCellValue("区");
		headRow.createCell(4).setCellValue("邮编");
		headRow.createCell(5).setCellValue("简码");
		headRow.createCell(6).setCellValue("城市编码");
		for (Region region : regionList) {
        	HSSFRow dataRow=sheet.createRow(sheet.getLastRowNum()+1);
        	dataRow.createCell(0).setCellValue(region.getId());
        	dataRow.createCell(1).setCellValue(region.getProvince());
        	dataRow.createCell(2).setCellValue(region.getCity());
        	dataRow.createCell(3).setCellValue(region.getDistrict());
        	dataRow.createCell(4).setCellValue(region.getPostcode());
        	dataRow.createCell(5).setCellValue(region.getShortcode());
        	dataRow.createCell(6).setCellValue(region.getCitycode());
		  }
      //第三步：使用输出流进行文件下载（一个流、两个头）
		
  		String filename = "区域数据.xls";
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
}
