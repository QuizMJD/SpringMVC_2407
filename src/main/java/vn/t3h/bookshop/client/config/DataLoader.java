package vn.t3h.bookshop.client.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Lớp này chịu trách nhiệm nạp dữ liệu mẫu khi ứng dụng khởi động
 */
@Component
public class DataLoader {

    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());

    @Autowired
    private DataSource dataSource;

    /**
     * Phương thức này sẽ được gọi sau khi bean được khởi tạo
     * để nạp dữ liệu mẫu vào database
     */
    @PostConstruct
    public void loadData() {
        logger.info("Bắt đầu nạp dữ liệu mẫu...");
        try {
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
            resourceDatabasePopulator.addScript(new ClassPathResource("data.sql"));
            resourceDatabasePopulator.execute(dataSource);
            logger.info("Nạp dữ liệu mẫu thành công!");
        } catch (Exception e) {
            logger.severe("Lỗi khi nạp dữ liệu mẫu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}