<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
<input type="hidden" id="searchProperty" name="searchProperty" value="${page.searchProperty}" />
<input type="hidden" id="orderProperty" name="orderProperty" value="${page.orderProperty}" />
<input type="hidden" id="orderDirection" name="orderDirection" value="${page.orderDirection}" />


<div class="row">
	<%--<div class="col-sm-2" style="width: 18%">--%>
		<%--<div class="dataTables_length" id="DataTables_Table_0_length">--%>
			<%--<label>每页 <select name="DataTables_Table_0_length" id="pageSizeSelect"--%>
				<%--aria-controls="DataTables_Table_0" class="form-control input-sm">--%>
				    <%--<option value="10" <c:if test="${page.pageable.pageSize == 10}">selected="selected"</c:if>>10</option>--%>
					<%--<option value="20" <c:if test="${page.pageable.pageSize == 20}">selected="selected"</c:if>>20</option>--%>
					<%--<option value="50" <c:if test="${page.pageable.pageSize == 50}">selected="selected"</c:if>>50</option>--%>
					<%--<option value="100" <c:if test="${page.pageable.pageSize == 100}">selected="selected"</c:if>>100</option></select> 条记录--%>
			<%--</label>--%>
		<%--</div>--%>
	<%--</div>--%>
	<%----%>
	<%--<div class="col-sm-4" style="width: 20%">--%>
		<%--<p style="padding-top: 5px;">--%>
			<%--<c:set value="${(page.pageable.pageNumber-1) * page.pageable.pageSize + 1}" var="first"></c:set>--%>
			<%--<c:set value="${page.pageable.pageNumber * page.pageable.pageSize}" var="end"></c:set>--%>
			<%--显示 ${first } 到 ${end } 项，共 ${page.total } 项--%>
		<%--</p>--%>
	<%--</div>--%>
	<div class="col-sm-12" style="width: 62%">
		<div class="dataTables_paginate paging_simple_numbers"
			id="DataTables_Table_0_paginate">
			<ul class="pagination">
				
				<li 
					<c:if test="${page.data.hasPrevious==false}">class="paginate_button previous disabled"</c:if>
					<c:if test="${page.data.hasPrevious==true}">class="paginate_button previous"</c:if>
						aria-controls="DataTables_Table_0" tabindex="0"
						id="DataTables_Table_0_previous">
						<a 
						<c:if test="${page.data.hasPrevious==false}">href="javascript: void(0);"</c:if>
						<c:if test="${page.data.hasPrevious==true}">href="javascript: $.pageSkip(${page.pageable.pageNumber-1});"</c:if>
						
						><span aria-hidden="true">«</span></a></li>

						
				<c:forEach items="${page.data.segment}" var="segmentPageNumber" varStatus="status"  >
					<li 
					<c:if test="${page.pageable.pageNumber ==segmentPageNumber  }">class="active"</c:if>
					<c:if test="${page.pageable.pageNumber !=segmentPageNumber  }"></c:if>
					>
					<a href="javascript: $.pageSkip(${segmentPageNumber});">
						${segmentPageNumber}</a>
					</li>
				</c:forEach>	
				
				<li 
						<c:if test="${page.data.hasNext==false}">class="paginate_button next disabled"</c:if>
						<c:if test="${page.data.hasNext==true}">class="paginate_button next"</c:if>
					class="paginate_button next disabled" aria-controls="DataTables_Table_0"
						tabindex="0" id="DataTables_Table_0_next">
						<a 
						<c:if test="${page.data.hasNext==false}">href="javascript: void(0);"</c:if>
						<c:if test="${page.data.hasNext==true}">href="javascript: $.pageSkip(${page.pageable.pageNumber+1});"</c:if>
						><span aria-hidden="true">»</span></a></li>
			</ul>
		</div>
	</div>
</div>



