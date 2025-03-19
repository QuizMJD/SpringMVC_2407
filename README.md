# Ứng dụng BookShop

Đây là ứng dụng quản lý và bán sách trực tuyến được phát triển bằng Spring MVC.

## Cấu trúc dữ liệu

Ứng dụng gồm các entity chính:
- **Category**: Danh mục sách
- **Product**: Thông tin chi tiết về sách
- **User**: Người dùng hệ thống
- **Role**: Vai trò người dùng
- **Cart**: Giỏ hàng
- **CartItem**: Sản phẩm trong giỏ hàng
- **IdentityCard**: Thông tin cá nhân của người dùng

## Cài đặt và Triển khai

1. **Yêu cầu hệ thống**:
   - Java 17 trở lên
   - Apache Maven
   - MySQL Database
   - Apache Tomcat 10.1.x trở lên

2. **Thiết lập cơ sở dữ liệu**:
   - Tạo database `bookshop` trong MySQL
   - Các bảng sẽ được tự động tạo bởi Hibernate

3. **Build và triển khai**:
   - Chạy script `./rebuild.sh` để build và triển khai tự động
   - Hoặc chạy các lệnh thủ công:
     ```
     mvn clean package -DskipTests
     cp target/BookShop_Client_2407.war [đường_dẫn_tomcat]/webapps/
     ```

4. **Dữ liệu mẫu**:
   - Dữ liệu mẫu sẽ được tự động tạo khi khởi động ứng dụng lần đầu
   - Thông tin đăng nhập mặc định:
     - Admin: username `admin`, password `admin123`
     - User: username `user1`, password `user123`

## Chức năng

1. **Người dùng thông thường**:
   - Xem danh sách sách theo danh mục
   - Tìm kiếm sách
   - Thêm sách vào giỏ hàng
   - Quản lý giỏ hàng

2. **Admin**:
   - Tất cả chức năng của người dùng thông thường
   - Quản lý sách (thêm, sửa, xóa)
   - Quản lý danh mục
   - Quản lý người dùng

## Cấu trúc Project

```
src/
├── main/
│   ├── java/
│   │   └── vn/t3h/bookshop/client/
│   │       ├── config/         # Cấu hình Spring
│   │       ├── controller/     # Xử lý request
│   │       ├── dao/            # Data Access Objects
│   │       ├── model/          # Entity classes
│   │       └── service/        # Business logic
│   ├── resources/              # Cấu hình, properties
│   └── webapp/                 # Web resources, JSP, CSS, JS
└── test/                       # Unit tests
```

## Liên hệ

Nếu có thắc mắc hoặc góp ý, vui lòng liên hệ qua email: [your-email@example.com] 