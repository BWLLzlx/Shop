<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/15
  Time: 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
  <div style="width: 800px;height: 40px;line-height: 40px;margin: 0 auto">
    <c:if test="${requestScope.page.pageNo > 1}">
      <a href="${requestScope.page.url}&pageNo=1">首页</a>
      <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <c:choose>
      <c:when test="${requestScope.page.pageTotal <= 5}">
        <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
          <c:if test="${requestScope.page.pageNo == i}">
            【${i}】
          </c:if>
          <c:if test="${requestScope.page.pageNo != i}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
          </c:if>
        </c:forEach>
      </c:when>
      <c:when test="${requestScope.page.pageTotal > 5}">
        <c:choose>
          <c:when test="${requestScope.page.pageNo <= 3}">
            <c:forEach begin="1" end="5" var="i">
              <c:if test="${requestScope.page.pageNo == i}">
                【${i}】
              </c:if>
              <c:if test="${requestScope.page.pageNo != i}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
              </c:if>
            </c:forEach>
          </c:when>
          <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal-2}">
            <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
              <c:if test="${requestScope.page.pageNo == i}">
                【${i}】
              </c:if>
              <c:if test="${requestScope.page.pageNo != i}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
              </c:if>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
              <c:if test="${requestScope.page.pageNo == i}">
                【${i}】
              </c:if>
              <c:if test="${requestScope.page.pageNo != i}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
              </c:if>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </c:when>
    </c:choose>


    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
      <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
      <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录，到第 <input name="pn" id="pn_input" value="${param.pageNo==null?1:param.pageNo}">页
    <input id="searchPageBtn" type="button" value="确定" class="pav_btn">
    <script>
      $(function (){
        $("#searchPageBtn").click(function (){
          var pageNo = $("#pn_input").val();
          if (pageNo <= ${requestScope.page.pageTotal} && pageNo > 0){
            location.href ="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
          }else {
            alert("输入页码不在有效范围内！");
          }
        })
      })
    </script>
  </div>
</div>