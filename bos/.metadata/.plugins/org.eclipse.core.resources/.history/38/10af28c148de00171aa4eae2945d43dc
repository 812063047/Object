package cn.itcast.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {

	/**
	 * 获得树形结构的部门树列表
	 * @param list
	 * @return
	 */
	public static List<Department> getTreeList(List<Department> topList,Long removeId) {
		List<Department> treeList = new ArrayList<Department>();
		walkTree(topList,treeList,"┣",removeId);
		return treeList;
	}

	private static void walkTree(Collection<Department> topList, List<Department> treeList,String prefix,Long removeId) {
		for(Department dept : topList){
			Department copy = new Department();
			copy.setName(prefix + dept.getName());
			copy.setId(dept.getId());
			
			//如果当前循环出的部门id等于要去掉的部门分支的id，就提前结束本次循环
			if(removeId != null && dept.getId().equals(removeId)){
				continue;
			}
			
			treeList.add(copy);
			
			Set<Department> children = dept.getChildren();
			walkTree(children,treeList,"　" + prefix,removeId);
		}
		
	}

}
