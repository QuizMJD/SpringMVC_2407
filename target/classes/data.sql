-- Script tạo dữ liệu mẫu cho BookShop

-- Xóa dữ liệu cũ (nếu có)
DELETE FROM cart_items;
DELETE FROM carts;
DELETE FROM user_roles;
DELETE FROM identity_cards;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM products;
DELETE FROM categories;

-- Tạo dữ liệu mẫu cho bảng roles
INSERT INTO roles (id, name, description) VALUES 
(1, 'ROLE_ADMIN', 'Quản trị viên hệ thống'),
(2, 'ROLE_USER', 'Người dùng thông thường');

-- Tạo dữ liệu mẫu cho bảng categories
INSERT INTO categories (id, category_name, description) VALUES 
(1, 'Sách Việt Nam', 'Các tác phẩm văn học Việt Nam'),
(2, 'Sách nước ngoài', 'Các tác phẩm văn học nước ngoài được dịch sang tiếng Việt'),
(3, 'Sách thiếu nhi', 'Sách dành cho trẻ em và thiếu niên'),
(4, 'Sách kỹ năng sống', 'Sách hướng dẫn phát triển kỹ năng sống'),
(5, 'Sách kinh tế', 'Sách về kinh doanh và kinh tế'),
(6, 'Sách khoa học', 'Sách về khoa học và công nghệ');

-- Tạo dữ liệu mẫu cho bảng products
INSERT INTO products (id, book_title, author, description, price, discount, genre, image, page_count, publication_year, publisher, stock_quantity, category_id) VALUES 
(1, 'Truyện Kiều', 'Nguyễn Du', 'Truyện Kiều là một truyện thơ của đại thi hào Nguyễn Du, được sáng tác bằng chữ Nôm theo thể lục bát.', 150000, 0, 'Văn học Việt Nam', 'truyen-kieu.jpg', 128, 2022, 'NXB Văn Học', 50, 1),
(2, 'Số Đỏ', 'Vũ Trọng Phụng', 'Số Đỏ là tiểu thuyết của nhà văn Vũ Trọng Phụng, phản ánh xã hội Việt Nam thời kỳ đầu thế kỷ XX.', 120000, 10, 'Văn học Việt Nam', 'so-do.jpg', 248, 2020, 'NXB Văn Học', 30, 1),
(3, 'Nhà Giả Kim', 'Paulo Coelho', 'Nhà Giả Kim là cuốn sách được xuất bản lần đầu ở Brazil năm 1988, đã được dịch ra 67 ngôn ngữ và bán ra hơn 65 triệu bản.', 90000, 5, 'Văn học nước ngoài', 'nha-gia-kim.jpg', 228, 2019, 'NXB Hội Nhà Văn', 100, 2),
(4, 'Đắc Nhân Tâm', 'Dale Carnegie', 'Đắc Nhân Tâm là quyển sách nổi tiếng nhất, bán chạy nhất và có tầm ảnh hưởng nhất của mọi thời đại.', 115000, 15, 'Kỹ năng sống', 'dac-nhan-tam.jpg', 320, 2018, 'NXB Tổng Hợp TPHCM', 80, 4),
(5, 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 'Nguyễn Nhật Ánh', 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh là một tiểu thuyết của nhà văn Nguyễn Nhật Ánh, kể về tuổi thơ nghèo khó nhưng đầy ắp niềm vui.', 125000, 0, 'Văn học Việt Nam', 'toi-thay-hoa-vang-tren-co-xanh.jpg', 378, 2018, 'NXB Trẻ', 60, 1),
(6, 'Harry Potter và Hòn Đá Phù Thủy', 'J.K. Rowling', 'Câu chuyện về cậu bé phù thủy nổi tiếng Harry Potter và những cuộc phiêu lưu tại trường Hogwarts.', 195000, 10, 'Văn học nước ngoài', 'harry-potter-1.jpg', 366, 2020, 'NXB Trẻ', 40, 2),
(7, 'Dế Mèn Phiêu Lưu Ký', 'Tô Hoài', 'Tác phẩm văn học thiếu nhi kinh điển của Việt Nam, kể về cuộc phiêu lưu của chú Dế Mèn.', 85000, 5, 'Thiếu nhi', 'de-men-phieu-luu-ky.jpg', 144, 2021, 'NXB Kim Đồng', 70, 3),
(8, 'Thói Quen Thứ 8', 'Stephen R. Covey', 'Cuốn sách nổi tiếng về phát triển bản thân và kỹ năng lãnh đạo.', 160000, 20, 'Kỹ năng sống', 'thoi-quen-thu-8.jpg', 432, 2019, 'NXB Tổng Hợp TPHCM', 25, 4),
(9, 'Bố Già', 'Mario Puzo', 'Tiểu thuyết về gia đình mafia Corleone tại Mỹ, một tác phẩm kinh điển của thế giới.', 145000, 0, 'Văn học nước ngoài', 'bo-gia.jpg', 478, 2020, 'NXB Hội Nhà Văn', 35, 2),
(10, 'Từ Điển Tiếng "Em"', 'Khotudien', 'Cuốn sách tổng hợp những ngôn từ thú vị trong tình yêu giới trẻ.', 75000, 10, 'Văn học Việt Nam', 'tu-dien-tieng-em.jpg', 212, 2021, 'NXB Phụ Nữ', 40, 1),
(11, 'Tiền Đẻ Ra Tiền', 'Napoleon Hill', 'Cuốn sách kinh điển về tài chính cá nhân và làm giàu.', 130000, 15, 'Kinh tế', 'tien-de-ra-tien.jpg', 366, 2019, 'NXB Thế Giới', 30, 5),
(12, 'Vũ Trụ Trong Vỏ Hạt Dẻ', 'Stephen Hawking', 'Cuốn sách khoa học nổi tiếng giải thích vũ trụ một cách dễ hiểu.', 180000, 5, 'Khoa học', 'vu-tru-trong-vo-hat-de.jpg', 248, 2018, 'NXB Trẻ', 20, 6);

-- Tạo dữ liệu mẫu cho bảng users
INSERT INTO users (id, username, password, email, created_at, updated_at) VALUES 
(1, 'admin', '$2a$10$rJf5Tv4qYiCGmgC7DpjjDeILoJjFlU29gAZY4hD6k5.azs9.ZpZqO', 'admin@bookshop.com', NOW(), NOW()),
(2, 'user1', '$2a$10$rJf5Tv4qYiCGmgC7DpjjDeILoJjFlU29gAZY4hD6k5.azs9.ZpZqO', 'user1@example.com', NOW(), NOW()),
(3, 'user2', '$2a$10$rJf5Tv4qYiCGmgC7DpjjDeILoJjFlU29gAZY4hD6k5.azs9.ZpZqO', 'user2@example.com', NOW(), NOW());

-- Tạo dữ liệu mẫu cho bảng user_roles
INSERT INTO user_roles (user_id, role_id) VALUES 
(1, 1),
(1, 2),
(2, 2),
(3, 2);

-- Tạo dữ liệu mẫu cho bảng identity_cards
INSERT INTO identity_cards (id, full_name, address, phone_number, user_id) VALUES 
(1, 'Admin BookShop', 'Hà Nội, Việt Nam', '0987654321', 1),
(2, 'Nguyễn Văn A', 'Hồ Chí Minh, Việt Nam', '0123456789', 2),
(3, 'Trần Thị B', 'Đà Nẵng, Việt Nam', '0369852147', 3);

-- Tạo dữ liệu mẫu cho bảng carts
INSERT INTO carts (id, user_id, created_at, updated_at) VALUES 
(1, 2, NOW(), NOW()),
(2, 3, NOW(), NOW());

-- Tạo dữ liệu mẫu cho bảng cart_items
INSERT INTO cart_items (id, cart_id, product_id, quantity) VALUES 
(1, 1, 3, 2),
(2, 1, 5, 1),
(3, 2, 7, 1),
(4, 2, 9, 2); 