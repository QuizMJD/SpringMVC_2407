package vn.t3h.bookshop.client.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.t3h.bookshop.client.dao.ProductDao;
import vn.t3h.bookshop.client.model.Product;
import vn.t3h.bookshop.client.service.ProductService;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

}
