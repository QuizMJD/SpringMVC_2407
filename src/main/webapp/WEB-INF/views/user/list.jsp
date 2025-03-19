<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Quản lý người dùng</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>Danh sách người dùng</h2>

                    <div class="mb-3">
                        <a href="/api/users/add" class="btn btn-primary">Thêm mới</a>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Avatar</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Họ tên</th>
                                    <th>Số điện thoại</th>
                                    <th>Trạng thái</th>
                                    <th>Ngày tạo</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>
                                            <c:if test="${not empty user.avatarUrl}">
                                                <img src="${user.avatarUrl}" alt="Avatar"
                                                    style="width: 50px; height: 50px; border-radius: 50%;">
                                            </c:if>
                                        </td>
                                        <td>${user.username}</td>
                                        <td>${user.email}</td>
                                        <td>${user.fullName}</td>
                                        <td>${user.phoneNumber}</td>
                                        <td>
                                            <span class="badge ${user.active ? 'bg-success' : 'bg-danger'}">
                                                ${user.active ? 'Hoạt động' : 'Vô hiệu'}
                                            </span>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${user.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <a href="/api/users/view/${user.id}" class="btn btn-info btn-sm">Xem</a>
                                                <a href="/api/users/edit/${user.id}"
                                                    class="btn btn-warning btn-sm">Sửa</a>
                                                <button onclick="deleteUser(${user.id})"
                                                    class="btn btn-danger btn-sm">Xóa</button>
                                                <button onclick="toggleActive(${user.id})"
                                                    class="btn btn-secondary btn-sm">
                                                    ${user.active ? 'Vô hiệu' : 'Kích hoạt'}
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Phân trang -->
                    <c:if test="${totalPages > 1}">
                        <nav>
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="?page=${currentPage - 1}">Trước</a>
                                </li>

                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                    <a class="page-link" href="?page=${currentPage + 1}">Sau</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                <script>
                    function deleteUser(id) {
                        if (confirm('Bạn có chắc chắn muốn xóa người dùng này?')) {
                            fetch(`/api/users/${id}`, {
                                method: 'DELETE'
                            }).then(response => {
                                if (response.ok) {
                                    location.reload();
                                } else {
                                    alert('Có lỗi xảy ra khi xóa người dùng');
                                }
                            });
                        }
                    }

                    function toggleActive(id) {
                        fetch(`/api/users/${id}/toggle-active`, {
                            method: 'PATCH'
                        }).then(response => {
                            if (response.ok) {
                                location.reload();
                            } else {
                                alert('Có lỗi xảy ra khi thay đổi trạng thái người dùng');
                            }
                        });
                    }
                </script>
            </body>

            </html>