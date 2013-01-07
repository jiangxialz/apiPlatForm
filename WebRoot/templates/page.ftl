<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<option value="10" <#if multiData.page.numPerPage=10>selected="selected"</#if> >10</option>
			<option value="20" <#if multiData.page.numPerPage=20>selected="selected"</#if> >20</option>
			<option value="50" <#if multiData.page.numPerPage=50>selected="selected"</#if> >50</option>
		</select>
		<span>条，共${multiData.page.pageCount?c}条</span>
	</div>
	<div class="pagination" targetType="navTab" totalCount="${multiData.page.pageCount?c}" numPerPage="${multiData.page.numPerPage?c}" pageNumShown="10" currentPage="${multiData.page.pageNum?c}"></div>
</div>