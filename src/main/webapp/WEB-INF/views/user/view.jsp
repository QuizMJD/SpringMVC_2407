<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Chi tiết người dùng</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>Chi tiết người dùng</h2>

                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4 text-center">
                                    <c:if test="${not empty user.avatarUrl}">
                                        <img src="${user.avatarUrl}" alt="Avatar"
                                            style="width: 200px; height: 200px; border-radius: 50%; margin-bottom: 20px;">
                                    </c:if>
                                    <h4>${user.fullName}</h4>
                                    <p class="text-muted">${user.username}</p>
                                    <span class="badge ${user.active ? 'bg-success' : 'bg-danger'}">
                                        ${user.active ? 'Hoạt động' : 'Vô hiệu'}
                                    </span>
                                </div>

                                <div class="col-md-8">
                                    <table class="table">
                                        <tr>
                                            <th style="width: 200px;">Email:</th>
                                            <td>${user.email}</td>
                                        </tr>
                                        <tr>
                                            <th>Họ và tên:</th>
                                            <td>${user.firstName} ${user.lastName}</td>
                                        </tr>
                                        <tr>
                                            <th>Số điện thoại:</th>
                                            <td>${user.phoneNumber}</td>
                                        </tr>
                                        <tr>
                                            <th>Ngày sinh:</th>
                                            <td>
                                                <fmt:formatDate value="${user.dateOfBirth}" pattern="dd/MM/yyyy" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Địa chỉ:</th>
                                            <td>${user.address}</td>
                                        </tr>
                                        <tr>
                                            <th>Ngày tạo:</th>
                                            <td>
                                                <fmt:formatDate value="${user.createdAt}"
                                                    pattern="dd/MM/yyyy HH:mm:ss" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Cập nhật lần cuối:</th>
                                            <td>
                                                <fmt:formatDate value="${user.updatedAt}"
                                                    pattern="dd/MM/yyyy HH:mm:ss" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Vai trò:</th>
                                            <td>
                                                <c:forEach items="${user.roles}" var="role" varStatus="status">
                                                    ${role.name}${!status.last ? ', ' : ''}
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <a href="/api/users" class="btn btn-secondary">Quay lại</a>
                            <a href="/api/users/edit/${user.id}" class="btn btn-primary">Chỉnh sửa</a>
                            <button onclick="deleteUser(${user.id})" class="btn btn-danger">Xóa</button>
                            <button onclick="toggleActive(${user.id})" class="btn btn-warning">
                                ${user.active ? 'Vô hiệu hóa' : 'Kích hoạt'}
                            </button>
                        </div>
                    </div>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                <script>
                    function deleteUser(id) {
                        if (confirm('Bạn có chắc chắn muốn xóa người dùng này?')) {
                            fetch(`/api/users/${id}`, {
                                method: 'DELETE'
                            }).then(response => {
                                if (response.ok) {
                                    window.location.href = '/api/users';
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