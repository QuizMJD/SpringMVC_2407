package vn.t3h.bookshop.client.dao.imp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.dao.ProductDao;
import vn.t3h.bookshop.client.model.Product;

import java.util.List;
@Repository
public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Product.class);
    }

    @Override
    public Product getProductById(Long id) {
        return getById(id);
    }
}
