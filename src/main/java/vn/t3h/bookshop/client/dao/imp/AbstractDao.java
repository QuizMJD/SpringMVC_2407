package vn.t3h.bookshop.client.dao.imp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import vn.t3h.bookshop.client.dao.BaseDao;

import java.util.List;

public abstract class AbstractDao<T> implements BaseDao<T> {

    protected final JdbcTemplate jdbcTemplate;
    private final Class<T> clazz;

    public AbstractDao(JdbcTemplate jdbcTemplate, Class<T> clazz) {
        this.jdbcTemplate = jdbcTemplate;
        this.clazz = clazz;
    }

    @Override
    public List<T> findAll() {
        String sql = "SELECT * FROM " + clazz.getSimpleName().toLowerCase();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
    }

    @Override
    public T getById(Long id) {
        String sql = "SELECT * FROM " + clazz.getSimpleName().toLowerCase() + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(clazz));
    }
    public int deleteById(Long id) {
        String sql = "DELETE FROM " + clazz.getSimpleName().toLowerCase() + " WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int save(T entity) {
        return jdbcTemplate.update("INSERT INTO " + clazz.getSimpleName().toLowerCase() + " (...) VALUES (...)");
    }




}
