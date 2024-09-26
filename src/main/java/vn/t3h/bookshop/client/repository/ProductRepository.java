package vn.t3h.bookshop.client.repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.domain.Product;

import java.util.List;

@Repository
public class ProductRepository {
    private JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }
    public Product getProductById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }
    public int addProductToCart(Long cartId, Long productId, int quantity) {
        String sql = "INSERT INTO cart_item (cartId, productId, quantity, createdAt, updatedAt) " +
                "VALUES (?, ?, ?, NOW(), NOW()) " +
                "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity), updatedAt = NOW()";
        return this.jdbcTemplate.update(sql, cartId, productId, quantity);
    }



}
