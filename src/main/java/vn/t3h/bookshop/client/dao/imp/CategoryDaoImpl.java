package vn.t3h.bookshop.client.dao.imp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.dao.CategoryDao;
import vn.t3h.bookshop.client.model.Category;

import java.util.List;
@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
