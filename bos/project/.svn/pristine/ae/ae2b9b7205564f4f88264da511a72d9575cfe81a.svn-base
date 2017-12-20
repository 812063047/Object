<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域设置</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addRegionWindow').window("open");
	}
	
	function doView(){
		$('#searchRegionWindow').window("open");
	}
	
	function doEdit(){
		var rows=$("#grid").datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert("提示信息","请选择需要修改的区域","warning");
		}else if(rows.length==1){
			//选中取派员，弹出确认框
			$.messager.confirm("修改确认","你确认要修改选中的区域吗？",function(r){
				if(r){
					//console.log(rows[0]);
					$('#editRegionWindow').window("open");
					$("#editRegionForm").form("load",rows[0]);
					/* var array=new Array();
					//确定,发送请求
					//获取所有选中取派员的id
					for(var i=0;i<rows.length;i++){
						var staff=rows[i]
						var id=staff.id;
						array.push(id);
					}
					var ids1=array.join(",");
					$.post("subareaAction_getById.action",{"ids1":ids1},function(data){
						//console.log(data);
						var dataJson=JSON.parse(data);
						//console.log(dataJson);
						$("#editSubareaForm").form("load",dataJson);
					})
					//location.href = "subareaAction_getById.action?ids1="+ids1;
					$('#editSubareaWindow').window("open"); */	
					
				}
			});
		}else{
			$.messager.alert("提示信息","不能同时选择修改两个以上区域","warning");
		}
	}
	function doDelete(){
		var rows=$("#grid").datagrid("getSelections");
		if(rows.length==0){
			$.messager.alert("提示信息","请选择需要删除的区域","warning");
		}else{
			$.messager.confirm("删除确认","你确认要删除选中的区域吗？",function(r){
				if(r){
					var array=new Array();
					//确定,发送请求
					//获取所有选中取派员的id
					for(var i=0;i<rows.length;i++){
						var staff=rows[i]
						var id=staff.id;
						array.push(id);
					}
					var ids=array.join(",");//1,2,3,4
					//alert(ids);
					location.href = "regionAction_deleteBatch.action?ids="+ids;
				}
			})
		}
	}
	
	function doExport(){
		window.location.href="regionAction_exportXls.action"
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo'
	},{
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true
	},{
		field : 'showid',
		title : '区域编号',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.id;
		}
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 120,
		align : 'center'
	},{
		field : 'shortcode',
		title : '简码',
		width : 120,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [20,30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "regionAction_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		//页面加载完成后调用OCUpload插件方法
		$("#button-import").upload({
			action:'regionAction_importXls.action',
			name:'regionFile'
		});
		
		// 添加、修改区域窗口
		$('#addRegionWindow').window({
	        title: '添加区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 修改区域窗口
		$('#editRegionWindow').window({
	        title: '修改区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询区域窗口
		$('#searchRegionWindow').window({
	        title: '查询区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 420,
	        resizable:false
	    });
		
		//定义一个工具方法，用于将指定的form表单中所有的输入项转为json数据{key:value,key:value}
		$.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();
            $(array).each(function(){  
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        };  
        
		$("#search").click(function(){
			var p=$("#searchRegionForm").serializeJson();
			$("#grid").datagrid("load",p);
			$("#searchRegionWindow").window("close");
		})
		
	});

	function doDblClickRow(rowIndex, rowData){
		$('#editRegionWindow').window("open");
		$("#editRegionForm").form("load",rowData);
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="区域添加修改" id="addRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addRegionForm" action="regionAction_addRegion.action" method="post">
			   			<script type="text/javascript">
						   $(function(){
							   $("#save").click(function(){
								   var v=$("#addRegionForm").form("validate");
								   if(v){
									   $("#addRegionForm").submit();
									   $.messager.alert("提示信息","添加区域成功！","ok");
								   }
							   });
						   });
						</script>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td>
						<input type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区域编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	<!-- 修改区域信息 -->
	<div class="easyui-window" title="区域添加修改" id="editRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editRegionForm" action="regionAction_editRegion.action" method="post">
			   			<script type="text/javascript">
						   $(function(){
							   $("#edit").click(function(){
								   var v=$("#editRegionForm").form("validate");
								   if(v){
									   $("#editRegionForm").submit();
									   $.messager.alert("提示信息","修改区域成功！","ok");
								   }
							   });
						   });
						</script>
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">修改信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td>
						<input type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
					<td>区域编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	<!-- 查询区域信息 -->
	<div class="easyui-window" title="区域添加修改" id="searchRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="searchRegionForm">
			   			<!--<script type="text/javascript">
						   $(function(){
							   $("#search").click(function(){
								   var v=$("#searchRegionForm").form("validate");
								   if(v){
									   $("#searchRegionForm").submit();
									   $.messager.alert("提示信息","查询区域成功！","ok");
								   }
							   });
						   });
						</script>
						-->
				<!--<input type="hidden" name="id">  -->
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td>
						<input type="text" name="postcode" class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" /></td>
					</tr>
					<tr>
					<td>区域编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td colspan="2"><a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>