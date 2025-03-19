package vn.t3h.bookshop.client.service;
import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.model.Product;
import java.util.List;

/**
 * Service interface cho việc quản lý sản phẩm
 */
public interface ProductService extends BaseService<Product> {

        /**
         * Lấy sản phẩm theo ID
         * 
         * @param id ID của sản phẩm cần tìm
         * @return Sản phẩm tìm được hoặc null nếu không tồn tại
         */
        Product getProductById(Long id);
}
