<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
  <jsp:include page="../common/_meta.jsp"/>
  <title>Chi tiết sản phẩm</title>
</head>

<body>
<jsp:include page="../common/_header.jsp"/>

<section class="section-content">
  <div class="container">
    <header class="section-heading py-4 d-flex justify-content-between">
      <h3 class="section-title">Chi tiết sản phẩm</h3>
    </header>
    <main class="add-book-form mb-5">
      <form class="w-50" action="${pageContext.request.contextPath}/admin/product/update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${product.id}"/>
        <div class="mb-3">
          <label for="add-book-title" class="form-label">Tên sách</label>
          <input value="${product.name}" type="text" class="form-control" id="add-book-title" name="add-book-title" readonly>
        </div>
        <div class="mb-3">
          <label for="add-book-author" class="form-label">Tác giả</label>
          <input value="${product.author}" type="text" class="form-control" id="add-book-author" name="add-book-author" readonly>
        </div>
        <div class="mb-3">
          <label for="add-book-pages" class="form-label">Số trang</label>
          <input value="${product.pages}" type="number" class="form-control" id="add-book-pages" name="add-book-pages" readonly>
        </div>
        <div class="mb-3">
          <label for="add-book-publisher" class="form-label">Nhà xuất bản</label>
          <input value="${product.publisher}" type="text" class="form-control" id="add-book-publisher" name="add-book-publisher" readonly>
        </div>
        <div class="mb-3">
          <label for="add-book-yearPublishing" class="form-label">Năm phát hành</label>
          <input readonly value="${product.yearPublishing}" type="number" class="form-control" id="add-book-yearPublishing" name="add-book-yearPublishing" required>
        </div>
        <div class="mb-3">
          <label for="add-book-category" class="form-label">Thể loại</label>
          <select class="form-select" id="add-book-category" name="add-book-category" required>
            <option selected disabled>Chọn một thể loại</option>
            <c:forEach var="category" items="${requestScope.categories}">
                <c:if test="${categoryId == category.id}">
                  <option value="${category.id}" selected >${category.name}</option>
                </c:if>
              <option value="${category.id}">${category.name}</option>
            </c:forEach>
          </select>
        </div>
        <div class="mb-3">
          <label for="add-book-price" class="form-label">Giá</label>
          <div class="input-group mb-2">
            <input readonly type="text" value=<fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/> class="form-control" id="add-book-price" name="add-book-price" required>
            <div class="input-group-text">đ</div>
          </div>
        </div>
        <div class="mb-3">
          <label for="add-book-discount" class="form-label">Khuyến mãi</label>
          <div class="input-group mb-2">
            <input readonly value="${product.discount}" type="text" class="form-control" id="add-book-discount" name="add-book-discount" required>
            <div class="input-group-text">%</div>
          </div>
        </div>
        <div class="mb-3">
          <label for="add-book-quantity" class="form-label">Số lượng trong kho</label>
          <input readonly value="${product.quantity}" type="number" class="form-control" id="add-book-quantity" name="add-book-quantity" required>
        </div>
        <div class="mb-3">
          <label for="add-book-description" class="form-label">Mô tả</label>
          <textarea readonly class="form-control" id="add-book-description" name="add-book-description" rows="5">${product.description}</textarea>
        </div>
        <div class="mb-3">
          <label for="add-book-imageName" class="form-label">Hình</label>
          <input readonly type="file" class="form-control" id="add-book-imageName" name="image">
        </div>
        <div class="mb-3">
          <label class="form-label">Cho phép giao dịch</label>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trade" id="tradeYes" value="1" required>
            <label class="form-check-label" for="tradeYes">Có</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="trade" id="tradeNo" value="0" required>
            <label class="form-check-label" for="tradeNo">Không</label>
          </div>
        </div>
        <div class="mb-3">
          <label for="add-book-startDate" class="form-label">Ngày bắt đầu khuyến mãi</label>
          <input readonly type="date" class="form-control" id="add-book-startDate" name="add-book-startDate" value="${product.startsAt}">
        </div>
        <div class="mb-3">
          <label for="add-book-endDate" class="form-label">Ngày kết thúc khuyến mãi</label>
          <input readonly type="date" class="form-control" id="add-book-endDate" name="add-book-endDate" value="${product.endsAt}>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật sản phẩm</button>
        <button type="reset" class="btn btn-warning ms-2">Tẩy trống</button>
        <button type="button" class="btn btn-light ms-2" onclick="window.history.back();">Hủy</button>
      </form>
    </main>
  </div>
</section>

<jsp:include page="../common/_footer.jsp"/>
</body>
</html>