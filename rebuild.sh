#!/bin/bash

# Đường dẫn Tomcat
TOMCAT_HOME=~/Downloads/apache-tomcat-10.1.34

# Dừng Tomcat nếu đang chạy
echo "Dừng Tomcat nếu đang chạy..."
if [ -f $TOMCAT_HOME/bin/shutdown.sh ]; then
  $TOMCAT_HOME/bin/shutdown.sh
  sleep 3
else  
  TOMCAT_PID=$(ps aux | grep catalina | grep -v grep | awk '{print $2}')
  if [ -n "$TOMCAT_PID" ]; then
    echo "Đang dừng Tomcat (PID: $TOMCAT_PID)..."
    kill -9 $TOMCAT_PID
    sleep 2
  fi
fi

# Xóa các file triển khai cũ
echo "Xóa các file triển khai cũ..."
rm -rf $TOMCAT_HOME/webapps/BookShop_Client_2407*
rm -rf $TOMCAT_HOME/work/Catalina/localhost/BookShop_Client_2407
rm -rf $TOMCAT_HOME/conf/Catalina/localhost/BookShop_Client_2407.xml

# Clean và build project
echo "Clean và build project..."
mvn clean package -DskipTests

# Copy WAR file sang Tomcat
echo "Copy WAR file sang Tomcat..."
cp target/BookShop_Client_2407.war $TOMCAT_HOME/webapps/

# Tạo thư mục cho resources trước khi triển khai (để tránh lỗi 404 cho ảnh)
echo "Chuẩn bị thư mục resources..."
mkdir -p $TOMCAT_HOME/webapps/BookShop_Client_2407/resources/images/products

# Copy ảnh vào thư mục webapp
echo "Copy ảnh vào thư mục resources..."
if [ -d "src/main/webapp/resources/images/products" ]; then
  cp -r src/main/webapp/resources/images/products/* $TOMCAT_HOME/webapps/BookShop_Client_2407/resources/images/products/
else
  echo "Thư mục ảnh không tồn tại, bỏ qua bước này."
fi

# Khởi động lại Tomcat
echo "Khởi động lại Tomcat..."
$TOMCAT_HOME/bin/catalina.sh start

echo "Đã hoàn tất việc triển khai."
echo "Ứng dụng sẽ có sẵn tại: http://localhost:8080/BookShop_Client_2407"
echo "Đợi khoảng 10-15 giây để ứng dụng khởi động hoàn tất." 