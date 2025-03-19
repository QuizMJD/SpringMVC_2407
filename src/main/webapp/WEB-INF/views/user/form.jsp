<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>${user.id == null ? 'Thêm mới' : 'Chỉnh sửa'} người dùng</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>
                <div class="container mt-4">
                    <h2>${user.id == null ? 'Thêm mới' : 'Chỉnh sửa'} người dùng</h2>

                    <form id="userForm" class="needs-validation" novalidate enctype="multipart/form-data">
                        <c:if test="${user.id != null}">
                            <input type="hidden" name="id" value="${user.id}">
                        </c:if>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="username" class="form-label">Tên đăng nhập *</label>
                                    <input type="text" class="form-control" id="username" name="username"
                                        value="${user.username}" required>
                                    <div class="invalid-feedback">
                                        Vui lòng nhập tên đăng nhập
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label for="email" class="form-label">Email *</label>
                                    <input type="email" class="form-control" id="email" name="email"
                                        value="${user.email}" required>
                                    <div class="invalid-feedback">
                                        Vui lòng nhập email hợp lệ
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label for="password" class="form-label">
                                        ${user.id == null ? 'Mật khẩu *' : 'Mật khẩu (để trống nếu không đổi)'}
                                    </label>
                                    <input type="password" class="form-control" id="password" name="password"
                                        ${user.id==null ? 'required' : '' }>
                                    <div class="invalid-feedback">
                                        Vui lòng nhập mật khẩu
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label for="firstName" class="form-label">Họ</label>
                                    <input type="text" class="form-control" id="firstName" name="firstName"
                                        value="${user.firstName}">
                                </div>

                                <div class="mb-3">
                                    <label for="lastName" class="form-label">Tên</label>
                                    <input type="text" class="form-control" id="lastName" name="lastName"
                                        value="${user.lastName}">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="phoneNumber" class="form-label">Số điện thoại</label>
                                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber"
                                        value="${user.phoneNumber}">
                                </div>

                                <div class="mb-3">
                                    <label for="dateOfBirth" class="form-label">Ngày sinh</label>
                                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth"
                                        value="<fmt:formatDate value=" ${user.dateOfBirth}" pattern="yyyy-MM-dd" />">
                                </div>

                                <div class="mb-3">
                                    <label for="address" class="form-label">Địa chỉ</label>
                                    <textarea class="form-control" id="address" name="address"
                                        rows="3">${user.address}</textarea>
                                </div>

                                <div class="mb-3">
                                    <label for="avatar" class="form-label">Avatar</label>
                                    <input type="file" class="form-control" id="avatar" name="avatar" accept="image/*">
                                    <c:if test="${not empty user.avatarUrl}">
                                        <div class="mt-2">
                                            <img src="${user.avatarUrl}" alt="Current Avatar"
                                                style="width: 100px; height: 100px; border-radius: 50%;">
                                        </div>
                                    </c:if>
                                </div>

                                <c:if test="${user.id != null}">
                                    <div class="mb-3">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="active" name="active"
                                                ${user.active ? 'checked' : '' }>
                                            <label class="form-check-label" for="active">
                                                Kích hoạt tài khoản
                                            </label>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Lưu</button>
                            <a href="/api/users" class="btn btn-secondary">Hủy</a>
                        </div>
                    </form>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                <script>
                    // Form validation
                    (function () {
                        'use strict'
                        var forms = document.querySelectorAll('.needs-validation')
                        Array.prototype.slice.call(forms).forEach(function (form) {
                            form.addEventListener('submit', function (event) {
                                event.preventDefault()
                                if (!form.checkValidity()) {
                                    event.stopPropagation()
                                } else {
                                    submitForm()
                                }
                                form.classList.add('was-validated')
                            })
                        })
                    })()

                    // Submit form
                    function submitForm() {
                        const form = document.getElementById('userForm')
                        const formData = new FormData(form)
                        const userId = formData.get('id')
                        const method = userId ? 'PUT' : 'POST'
                        const url = userId ? `/api/users/${userId}` : '/api/users'

                        fetch(url, {
                            method: method,
                            body: formData
                        }).then(response => {
                            if (response.ok) {
                                window.location.href = '/api/users'
                            } else {
                                alert('Có lỗi xảy ra khi lưu thông tin người dùng')
                            }
                        })
                    }
                </script>
            </body>

            </html>