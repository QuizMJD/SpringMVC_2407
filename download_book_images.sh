#!/bin/bash

# Thư mục đích lưu ảnh
IMAGE_DIR="src/main/webapp/resources/images/products"

# Tạo thư mục nếu chưa tồn tại
mkdir -p "$IMAGE_DIR"

# Danh sách URL ảnh sách từ internet (placeholder images)
declare -a BOOK_IMAGES=(
  "https://m.media-amazon.com/images/I/81cV2JPfNeL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/81XR8wJ-p5L._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/71bu5tY9QCL._SL1380_.jpg"
  "https://m.media-amazon.com/images/I/61ItRb1o7nL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/51tioM+Om4L._SL1328_.jpg"
  "https://m.media-amazon.com/images/I/81-QC8bJ-xL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/71CwYWl16ML._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/71uIZaMEUlL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/91aDh3DW1TL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/91bYsX41DVL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/71ObliqhGlL._SL1500_.jpg"
  "https://m.media-amazon.com/images/I/81xT2mdyL7L._SL1500_.jpg"
)

# Tên file ảnh tương ứng trong ứng dụng
declare -a IMAGE_NAMES=(
  "truyen-kieu.jpg"
  "so-do.jpg"
  "nha-gia-kim.jpg"
  "dac-nhan-tam.jpg"
  "toi-thay-hoa-vang-tren-co-xanh.jpg"
  "harry-potter-1.jpg"
  "de-men-phieu-luu-ky.jpg"
  "thoi-quen-thu-8.jpg"
  "bo-gia.jpg"
  "tu-dien-tieng-em.jpg"
  "tien-de-ra-tien.jpg"
  "vu-tru-trong-vo-hat-de.jpg"
)

echo "Bắt đầu tải ảnh sách..."

# Tải các ảnh từ URL
for i in "${!BOOK_IMAGES[@]}"; do
  echo "Đang tải ${IMAGE_NAMES[$i]}..."
  curl -s -o "$IMAGE_DIR/${IMAGE_NAMES[$i]}" "${BOOK_IMAGES[$i]}"
  echo "Đã tải xong ${IMAGE_NAMES[$i]}"
done

echo "Đã hoàn thành tải tất cả ảnh sách!" 