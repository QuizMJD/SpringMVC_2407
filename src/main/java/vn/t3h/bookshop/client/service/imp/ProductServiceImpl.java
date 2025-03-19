package vn.t3h.bookshop.client.service.imp;

import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.dao.ProductDao;
import vn.t3h.bookshop.client.model.Product;
import vn.t3h.bookshop.client.service.ProductService;

/**
 * Implementation của ProductService
 */
@Service
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        super(productDao);
        this.productDao = productDao;
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
