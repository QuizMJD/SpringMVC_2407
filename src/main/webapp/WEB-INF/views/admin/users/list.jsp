<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <title>Quản lý Người dùng - BookShop</title>
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
                        <div class="col-md-12">
                            <h2>Quản lý Người dùng</h2>

                            <!-- Hiển thị thông báo thành công/lỗi -->
                            <c:if test="${not empty successMessage}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    ${successMessage}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                                </div>
                            </c:if>
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    ${errorMessage}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                                </div>
                            </c:if>

                            <!-- Nút thêm user mới -->
                            <div class="mb-3">
                                <a href="<c:url value='/admin/users/new'/>" class="btn btn-primary">
                                    <i class="bi bi-plus-circle"></i> Thêm người dùng mới
                                </a>
                            </div>

                            <!-- Bảng danh sách user -->
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <thead class="table-dark">
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên đăng nhập</th>
                                            <th>Email</th>
                                            <th>Ngày tạo</th>
                                            <th>Thao tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td>${user.username}</td>
                                                <td>${user.email}</td>
                                                <td>
                                                    <fmt:formatDate value="${user.createdAt}"
                                                        pattern="dd/MM/yyyy HH:mm" />
                                                </td>
                                                <td>
                                                    <div class="btn-group" role="group">
                                                        <a href="<c:url value='/admin/users/view/${user.id}'/>"
                                                            class="btn btn-info btn-sm">
                                                            <i class="bi bi-eye"></i> Xem
                                                        </a>
                                                        <a href="<c:url value='/admin/users/edit/${user.id}'/>"
                                                            class="btn btn-warning btn-sm">
                                                            <i class="bi bi-pencil"></i> Sửa
                                                        </a>
                                                        <a href="<c:url value='/admin/users/delete/${user.id}'/>"
                                                            class="btn btn-danger btn-sm"
                                                            onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?')">
                                                            <i class="bi bi-trash"></i> Xóa
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty users}">
                                            <tr>
                                                <td colspan="5" class="text-center">Không có người dùng nào</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>

                            <!-- Phân trang -->
                            <c:if test="${totalPages > 1}">
                                <nav aria-label="Phân trang">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link"
                                                href="<c:url value='/admin/users?page=${currentPage - 1}'/>"
                                                aria-label="Trước">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                <a class="page-link"
                                                    href="<c:url value='/admin/users?page=${i}'/>">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link"
                                                href="<c:url value='/admin/users?page=${currentPage + 1}'/>"
                                                aria-label="Sau">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>
                    </div>
                </div>

                <jsp:include page="../../layout/section-footer.jsp" />
            </body>

            </html>