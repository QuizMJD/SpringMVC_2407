package vn.t3h.bookshop.client.dao.imp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.t3h.bookshop.client.dao.CategoryDao;
import vn.t3h.bookshop.client.model.Category;

import java.util.List;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, Category.class);
    }

    @Override
    public Category getCategoryById(Long id) {
        return getById(id);
    }
}
