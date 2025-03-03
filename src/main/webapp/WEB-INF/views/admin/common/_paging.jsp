<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 8/29/2024
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${requestScope.list.totalPage != 0}">
    <nav class="mt-3 mb-5">
        <ul class="pagination justify-content-center">
            <li class="page-item ${requestScope.list.page == 1 ? 'disabled' : ''}">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/${requestScope.list.path}?page=${requestScope.list.page - 1}">
                    Trang trước
                </a>
            </li>

            <c:forEach begin="1" end="${requestScope.list.totalPage}" var="i">
                <c:choose>
                    <c:when test="${requestScope.list.page == i}">
                        <li class="page-item active">
                            <a class="page-link">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/admin/${requestScope.list.path}?page=${i}">
                                    ${i}
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <li class="page-item ${requestScope.list.page == requestScope.list.totalPage ? 'disabled' : ''}">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/${requestScope.list.path}?page=${requestScope.list.page + 1}">
                    Trang sau
                </a>
            </li>
        </ul>
    </nav>
</c:if>
