<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <title>Chi tiết Người dùng - BookShop</title>
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
                                <div class="card-header bg-info text-white">
                                    <h3 class="card-title mb-0">Chi tiết Người dùng</h3>
                                </div>
                                <div class="card-body">
                                    <div class="row mb-3">
                                        <div class="col-md-4 fw-bold">ID:</div>
                                        <div class="col-md-8">${user.id}</div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-4 fw-bold">Tên đăng nhập:</div>
                                        <div class="col-md-8">${user.username}</div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-4 fw-bold">Email:</div>
                                        <div class="col-md-8">${user.email}</div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-4 fw-bold">Ngày tạo:</div>
                                        <div class="col-md-8">
                                            <fmt:formatDate value="${user.createdAt}" pattern="dd/MM/yyyy HH:mm:ss" />
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-4 fw-bold">Cập nhật lần cuối:</div>
                                        <div class="col-md-8">
                                            <fmt:formatDate value="${user.updatedAt}" pattern="dd/MM/yyyy HH:mm:ss" />
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-4 fw-bold">Vai trò:</div>
                                        <div class="col-md-8">
                                            <c:forEach var="role" items="${user.roles}" varStatus="status">
                                                <span class="badge bg-primary">${role.name}</span>
                                                <c:if test="${!status.last}">&nbsp;</c:if>
                                            </c:forEach>
                                            <c:if test="${empty user.roles}">
                                                <span class="text-muted">Chưa được gán vai trò</span>
                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="row mb-4">
                                        <div class="col-md-4 fw-bold">Thông tin cá nhân:</div>
                                        <div class="col-md-8">
                                            <c:if test="${not empty user.identityCard}">
                                                <p><strong>Họ tên:</strong> ${user.identityCard.fullName}</p>
                                                <p><strong>Địa chỉ:</strong> ${user.identityCard.address}</p>
                                                <p><strong>Số điện thoại:</strong> ${user.identityCard.phoneNumber}</p>
                                            </c:if>
                                            <c:if test="${empty user.identityCard}">
                                                <span class="text-muted">Chưa cập nhật thông tin cá nhân</span>
                                            </c:if>
                                        </div>
                                    </div>

                                    <!-- Buttons -->
                                    <div class="d-flex justify-content-end">
                                        <a href="<c:url value='/admin/users'/>" class="btn btn-secondary me-2">
                                            <i class="bi bi-arrow-left"></i> Quay lại
                                        </a>
                                        <a href="<c:url value='/admin/users/edit/${user.id}'/>"
                                            class="btn btn-warning me-2">
                                            <i class="bi bi-pencil"></i> Sửa
                                        </a>
                                        <a href="<c:url value='/admin/users/delete/${user.id}'/>" class="btn btn-danger"
                                            onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?')">
                                            <i class="bi bi-trash"></i> Xóa
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="../../layout/section-footer.jsp" />
            </body>

            </html>