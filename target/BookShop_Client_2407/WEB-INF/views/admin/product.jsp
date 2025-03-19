<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
<%--  <jsp:include page="../common/_meta.jsp"/>--%>
  <title>Quản Lý Sản Phẩm</title>
</head>

<body>
<jsp:include page="../layout/section-header.jsp" />

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4 d-flex justify-content-between">
      <h3 class="section-title">Quản lý sản phẩm</h3>
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/product/create" role="button" style="height: fit-content;">Thêm sản phẩm</a>
    </header> <!-- section-heading.// -->
    <main class="table-responsive-xl mb-5">
      <h3 style="color: red" class="section-title">${message}</h3>
      <table class="table table-bordered table-striped table-hover align-middle">
        <thead>
        <tr>
          <th scope="col">Hình</th>
          <th scope="col">ID</th>
          <th scope="col">Tên sách</th>
          <th scope="col">Tác giả</th>
          <th scope="col">Số trang</th>
          <th scope="col">Nhà xuất bản</th>
          <th scope="col">Thể loại</th>
          <th scope="col">Năm phát hành</th>
          <th scope="col">Số lượt mua</th>
          <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" varStatus="loop" items="${product1}">
          <tr>
<%--            <th scope="row">${loop.index + 1}</th>--%>
            <th ><img width="50px" src="${pageContext.request.contextPath}/image/${product.imageName}"/></th>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.author}</td>
            <td>${product.pages}</td>
            <td>${product.publisher}</td>
  <td>
    <c:forEach var="catId" items="${product.categoryIds}" varStatus="loop">
      <c:if test="${loop.index > 0}">
        ,
      </c:if>
      <c:forEach var="category" items="${categorys}">
        <c:if test="${category.id == catId}">
          ${category.name}
        </c:if>
      </c:forEach>
    </c:forEach>
  </td>


  <td>${product.yearPublishing}</td>
            <td>${product.totalBuy}</td>
            <td class="text-center text-nowrap">
              <a class="btn btn-primary me-2" href="${pageContext.request.contextPath}/admin/product/detail?id=${product.id}" role="button">Xem</a>
              <a class="btn btn-success me-2" href="${pageContext.request.contextPath}/admin/product/update?id=${product.id}" role="button">Sửa</a>
              <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/product/delete?id=${product.id}" role="button">Xóa</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </main> <!-- book-manager-table.// -->
<%--    <jsp:include page="../common/_paging.jsp"/>--%>
  </div> <!-- container.// -->
</section> <!-- section-content.// -->

<jsp:include page="../layout/section-footer.jsp" />
</body>

</html>