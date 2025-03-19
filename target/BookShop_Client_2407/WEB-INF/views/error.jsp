<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="vi">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <title>Lỗi - BookShop</title>

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
            <jsp:include page="layout/section-header.jsp" />

            <section class="section-content py-5">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-8 text-center">
                            <div class="error-template">
                                <h1>Oops!</h1>
                                <h2>Đã xảy ra lỗi</h2>
                                <div class="error-details my-4">
                                    Xin lỗi, có lỗi đã xảy ra. Vui lòng thử lại sau.
                                </div>
                                <div class="error-actions">
                                    <a href="<c:url value='/home'/>" class="btn btn-primary btn-lg">
                                        <i class="bi bi-house"></i> Trở về trang chủ
                                    </a>
                                    <a href="#" class="btn btn-secondary btn-lg ms-2">
                                        <i class="bi bi-envelope"></i> Liên hệ hỗ trợ
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <jsp:include page="layout/section-footer.jsp" />
        </body>

        </html>