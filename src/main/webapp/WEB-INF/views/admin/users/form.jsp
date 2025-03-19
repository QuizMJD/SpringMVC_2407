<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <title>${user.id == null ? 'Thêm mới' : 'Chỉnh sửa'} Người dùng - BookShop</title>
                <link href="<c:url value='/static/img/favicon.ico'/>" rel="shortcut icon" type="image/x-icon">
                <!-- Bootstrap v5.0.1 -->
                <link href="<c:url value='/static/css/bootstrap.css'/>" type="text/css" rel="stylesheet">
                <script src="<c:url value='/static/js/bootstrap.bundle.js'/>" type="text/javascript"></script>
                <!-- Bootstrap Icons v1.5.0 -->
                <link href="<c:url value='/static/css/bootstrap-icons.css'/>" type="text/css" rel="stylesheet">
                <!-- Custom Styles -->
                <link href="<c:url value='/static/css/styles.css'/>" type="text/css" rel="stylesheet">
            </head>

            <body>
                <jsp:include page="../../layout/section-header.jsp" />

                <div class="container mt-4">
                    <div class="row">
                        <div class="col-md-8 offset-md-2">
                            <div class="card">
                                <div class="card-header bg-primary text-white">
                                    <h3 class="card-title mb-0">${user.id == null ? 'Thêm mới' : 'Chỉnh sửa'} Người dùng
                                    </h3>
                                </div>
                                <div class="card-body">
                                    <form action="<c:url value='/admin/users/save'/>" method="post">
                                        <!-- ID - hidden nếu đang thêm mới -->
                                        <c:if test="${user.id != null}">
                                            <input type="hidden" name="id" value="${user.id}" />
                                        </c:if>

                                        <!-- Username -->
                                        <div class="mb-3">
                                            <label for="username" class="form-label">Tên đăng nhập <span
                                                    class="text-danger">*</span></label>
                                            <input type="text" name="username" id="username" class="form-control"
                                                value="${user.username}" required placeholder="Nhập tên đăng nhập" />
                                        </div>

                                        <!-- Password -->
                                        <div class="mb-3">
                                            <label for="password" class="form-label">Mật khẩu <span
                                                    class="text-danger">*</span></label>
                                            <input type="password" name="password" id="password" class="form-control"
                                                value="${user.password}" required placeholder="Nhập mật khẩu" />
                                        </div>

                                        <!-- Email -->
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Email <span
                                                    class="text-danger">*</span></label>
                                            <input type="email" name="email" id="email" class="form-control"
                                                value="${user.email}" required placeholder="Nhập địa chỉ email" />
                                        </div>

                                        <!-- Buttons -->
                                        <div class="d-flex justify-content-end">
                                            <a href="<c:url value='/admin/users'/>" class="btn btn-secondary me-2">
                                                <i class="bi bi-arrow-left"></i> Quay lại
                                            </a>
                                            <button type="submit" class="btn btn-primary">
                                                <i class="bi bi-save"></i> Lưu
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="../../layout/section-footer.jsp" />
            </body>

            </html>