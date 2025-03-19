package vn.t3h.bookshop.client.dao.imp;

import jakarta.persistence.Table;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import vn.t3h.bookshop.client.dao.BaseDao;

import java.util.List;

/**
 * AbstractDao cung cấp các implementation cơ bản cho việc truy cập dữ liệu
 * 
 * @param <T> Entity type
 */
public abstract class AbstractDao<T> implements BaseDao<T> {

    protected final JdbcTemplate jdbcTemplate;
    private final Class<T> clazz;

    public AbstractDao(JdbcTemplate jdbcTemplate, Class<T> clazz) {
        this.jdbcTemplate = jdbcTemplate;
        this.clazz = clazz;
    }

    /**
     * Lấy tên bảng từ Entity class
     * 
     * @return Tên bảng
     */
    protected String getTableName() {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation != null && !tableAnnotation.name().isEmpty()) {
            return tableAnnotation.name();
        }
        return clazz.getSimpleName().toLowerCase();
    }

    /**
     * Lấy tất cả các entity
     * 
     * @return Danh sách các entity
     */
    @Override
    public List<T> findAll() {
        String sql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
    }

    /**
     * Lấy entity theo ID
     * 
     * @param id ID của entity
     * @return Entity tìm được hoặc null nếu không tìm thấy
     */
    @Override
    public T getById(Long id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(clazz));
    }

    /**
     * Xóa entity theo ID
     * 
     * @param id ID của entity cần xóa
     * @return Số bản ghi đã xóa
     */
    public int deleteById(Long id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * Phương thức này chỉ là ví dụ và cần được overridden trong các implementation
     * cụ thể
     * 
     * @param entity Entity cần lưu
     * @return Số bản ghi đã lưu
     */
    public int save(T entity) {
        // Cần được implemented trong các lớp con
        throw new UnsupportedOperationException("Phương thức save() cần được implement trong lớp con");
    }
}
