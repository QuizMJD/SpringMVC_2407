<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <link href="/static/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

    <!-- Bootstrap v5.0.1 -->
    <link href="/static/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="/static/js/bootstrap.bundle.js" type="text/javascript"></script>

    <!-- Custom Styles -->
    <link href="/static/css/styles.css" type="text/css" rel="stylesheet">

    <title>Chi tiết sản phẩm</title>
</head>

<body>
<jsp:include page="./layout/section-header.jsp" />

<section class="section-content">
    <div class="container">
        <div class="row">
            <!-- Thông tin chi tiết sản phẩm -->
            <div class="col-md-8">
                <header class="section-heading py-4 d-flex justify-content-between">
                    <h3 class="section-title">Sách Toán lớp mẫu giáo</h3>
                </header>

                <div class="row">
                    <div class="col-md-5">
                        <!-- Hình ảnh sản phẩm -->
                        <img src="/static/img/${product.imageName}" alt="${product.name}" class="img-fluid" />
                    </div>

                    <div class="col-md-7">
                        <!-- Thông tin sản phẩm -->
                        <h4>${product.name}</h4>
                        <p><strong>Giá:</strong> ${product.price} VND</p>
                        <p><strong>Tác giả:</strong> ${product.author}</p>
                        <p><strong>Số trang:</strong> ${product.pages}</p>
                        <p><strong>Nhà xuất bản:</strong> ${product.publisher}</p>
                        <p><strong>Năm phát hành:</strong> ${product.yearPublishing}</p>
                        <p><strong>Số lượng:</strong> ${product.quantity}</p>

                        <div class="d-grid gap-2">
                            <button class="btn btn-primary">Mua ngay</button>
                            <button class="btn btn-secondary">Thêm vào giỏ hàng</button>
                        </div>
                    </div>
                </div>

                <!-- Mô tả sản phẩm -->
                <div class="mt-4">
                    <h5>Mô tả sản phẩm</h5>
                    <p>${product.description}</p>
                </div>

                <!-- Đánh giá sản phẩm -->
                <div class="mt-4">
                    <h5>Thêm đánh giá</h5>
                    <p>Vui lòng đăng nhập để đánh giá sản phẩm.</p>
                </div>
            </div>

            <!-- Sản phẩm liên quan -->
            <div class="col-md-4">
                <h5>Sản phẩm liên quan</h5>
                <div class="row">
                    <c:forEach var="relatedProduct" items="${relatedProducts}">
                        <div class="col-md-12 mb-4">
                            <div class="card">
                                <img src="/static/img/${relatedProduct.imageName}" class="card-img-top">
                                <div class="card-body">
                                    <h5 class="card-title">${relatedProduct.name}</h5>
                                    <p class="card-text">${relatedProduct.price} VND</p>
                                    <a href="/product/${relatedProduct.id}" class="btn btn-sm btn-primary">Xem chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="./layout/section-footer.jsp" />
</body>
</html>
