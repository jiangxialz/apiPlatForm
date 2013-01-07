<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="10" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="admin/listInfo" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>员工姓名：</label>
				<input type="text" name="loginName" value=""/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		   <#--
			<li><a class="add" href="admin/add" rel="dlg_new_project" target="dialog"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="admin/delAdmin" class="delete"><span>批量删除</span></a></li>
			-->
		</ul>
	</div>
	<table class="table" width="200" layoutH="138">
		<thead>
			<tr>
				<th width="10"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="50">名称</th>
			</tr>
		</thead>
		<tbody>
		<#if multiData.data??>
		<#list multiData.data as admin>
			<tr target="pid" rel="${admin.id?c}">
				<td><input name="ids" value="${admin.id?c}" type="checkbox"></td>
				<td>${admin.loginName!}</td>
			</tr>
		</#list>
		</#if>
		</tbody>
	</table>
		<#include "../page.ftl">
	</div>
</div>
